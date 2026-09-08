package sdp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import sdp.content.gameplay.InteractionController;
import sdp.content.gameplay.inventory.items.Item;
import sdp.content.gameplay.talk.requirements.TopicRequirement;
import sdp.content.gameplay.talk.topics.Topic;
import sdp.content.stats.Stat;
import sdp.persistence.DataController;
import sdp.persistence.runtime.data.PresentationData;
import sdp.shared.dtos.initialization.PrinzessinOption;
import sdp.shared.dtos.initialization.SessionContextDTO;
import sdp.content.prinzessins.Prinzessin;
import sdp.content.prinzessins.PrinzessinAttributes;
import sdp.content.gameplay.story.StoryState;
import sdp.content.prinzessins.kyoko.assets.config.KyokoSpriteConfig;
import sdp.content.stats.AffectionType;
import sdp.content.stats.StatParser;
import sdp.modules.assets.Asset;
import sdp.modules.assets.AssetCategory;
import sdp.modules.assets.AssetResolver;
import sdp.modules.audio.AudioController;
import sdp.persistence.persistent.PersistentData;
import sdp.persistence.persistent.dtos.*;
import sdp.persistence.runtime.RuntimeData;
import sdp.shared.dtos.inventory.InventoryEntry;
import sdp.shared.dtos.presentation.PresentationRefreshDTO;
import sdp.shared.dtos.talk.TopicEntry;
import sdp.shared.utils.EncryptionUtil;
import sdp.shared.utils.ImageUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

public class SessionController {
    private final DataController dataController = DataController.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);


    // Runtime
    public Stat refreshStat() {
        return new Stat();
    }

    public PresentationRefreshDTO refreshPresentation(){
        PresentationData data = dataController.getPresentationData();
        BufferedImage background = data.getCurrentBackground() == null
            ? null
            : ImageUtil.loadImage(data.getCurrentBackground().getDirectory());
        BufferedImage sprite = data.getCurrentSprite() == null
            ? null
            : ImageUtil.loadImage(data.getCurrentSprite().getDirectory());
        return new PresentationRefreshDTO(
            background,
            sprite
        );
    }

    public InventoryEntry[] refreshInventory(){
        List<InventoryEntry> inventoryEntries = new ArrayList<>();
        List<Item> inventory = dataController.getInventoryData().getInventory();

        for(Item item : inventory){
            inventoryEntries.add(new InventoryEntry(item.getTitle(), item.getDescription(), item.getId(), item.getType()));
        }
        return inventoryEntries.toArray(new InventoryEntry[0]);
    }

    public void restoreDefaultSprite() {
        if (dataController.getSessionData() == null || dataController.getSessionData().getPrinzessinID() == null) {
            return;
        }

        Prinzessin prinzessin = dataController.getSessionData().getPrinzessinID();
        Asset defaultSprite;

        if (prinzessin == Prinzessin.KYOKO) {
            AffectionType affection = StatParser.getAffection();
            defaultSprite = KyokoSpriteConfig.resolveDefaultSprite(affection);
        } else {
            defaultSprite = prinzessin.getInitialSprite();
        }

        if (defaultSprite != null) {
            dataController.getPresentationData().setCurrentSprite(defaultSprite);
        }
    }

    public TopicEntry[] refreshTopics(){
        List<TopicEntry> topicEntries = new ArrayList<>();
        List<Topic> topics = dataController.getTopicData().getTopics();

        for(Topic topic : topics){
            TopicRequirement requirement = topic.getRequirement();
            if(requirement == null){
                topic.setVisibleTrue();
            }
            else{
                topic.setVisible(requirement.check());
            }

            if(topic.isVisible()){
                topicEntries.add(new TopicEntry(topic.getTopic(), topic.getId()));
            }
        }
        return topicEntries.toArray(new TopicEntry[0]);
    }

    // Create / Write Session
    public PrinzessinOption[] getPrinzessinOptions(){
        List<PrinzessinOption> prinzessinOptions = new ArrayList<>();
        Prinzessin[] prinzessins = Prinzessin.values();

        for(Prinzessin prinzessin : prinzessins){
            prinzessinOptions.add(new PrinzessinOption(prinzessin, prinzessin.getFullName(), ImageUtil.loadImage(prinzessin.getSelectSprite())));
        }

        return prinzessinOptions.toArray(new PrinzessinOption[0]);
    }

    public void initializeNewGame(SessionContextDTO context){
        Prinzessin prinzessin = context.prinzessinID();

        dataController.initializeSession(context.playerName(), prinzessin);
        GameController.getInstance().resetInstance();
        InteractionController interaction = GameController.getInstance().getInteractionController();

        dataController.loadRuntimeData(
            new RuntimeData(
                null, null, prinzessin.getInitialSprite(),
                prinzessin.getInsanity(), prinzessin.getAffection(), prinzessin.getHunger(), prinzessin.getMoney(),
                interaction.getDefaultTopics(), interaction.resolveInventory(prinzessin.getItems())
            )
        );

        dataController.getPresentationData().setCurrentBackground(
            new PrinzessinAttributes(prinzessin).getBackgrounds().get(StoryState.PROLOGUE)
        );
    }

    public void save() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Save Game");

        String ext = Config.FILE_EXTENSION;
        String extDesc = Config.FILE_EXTENSION_DESCRIPTION;

        fileChooser.setSelectedFile(new File("save." + ext));

        String desc = String.format("%s (*.%s)", extDesc, ext);
        fileChooser.setFileFilter(new FileNameExtensionFilter(desc, ext));

        if (fileChooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = fileChooser.getSelectedFile();

        if (!file.getName().toLowerCase().endsWith("." + ext.toLowerCase())) {
            file = new File(file.getAbsolutePath() + "." + ext);
        }

        if (file.exists()) {
            int confirm = JOptionPane.showConfirmDialog(
                null,
                "File " + file.getName() + " already exist. Overwrite?",
                "Overwrite save file?",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
        }

        try {
            PersistentData persistentData = dataController.toPersistent();
            String json = objectMapper.writeValueAsString(persistentData);

            String encodedData = EncryptionUtil.encryptAES(json);

            java.nio.file.Files.writeString(file.toPath(), encodedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save game.", e);
        }
    }

    public void load() {
        JFileChooser fileChooser = new JFileChooser();

        String ext = Config.FILE_EXTENSION;
        String extDesc = Config.FILE_EXTENSION_DESCRIPTION;
        String desc = String.format("%s (*.%s)", extDesc, ext);

        fileChooser.setDialogTitle("Load Game");
        fileChooser.setFileFilter(new FileNameExtensionFilter(desc, ext));

        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = fileChooser.getSelectedFile();

        try {
            String encodedData = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            String json = EncryptionUtil.decryptAES(encodedData);
            PersistentData data = objectMapper.readValue(json, PersistentData.class);

            SessionDataDTO sessionData = data.getSessionData();
            PresentationDataDTO presentationData = data.getPresentationData();
            StatDataDTO statData = data.getStatData();

            dataController.initializeSession(
                sessionData.playerName(),
                sessionData.prinzessinID(),
                sessionData.storyState()
            );

            GameController.getInstance().resetInstance();
            InteractionController interaction = GameController.getInstance().getInteractionController();

            Asset savedMusic = AssetResolver.resolve(
                sessionData.prinzessinID(), AssetCategory.MUSIC, presentationData.currentMusic()
            );

            dataController.loadRuntimeData(new RuntimeData(
                savedMusic,
                AssetResolver.resolve(sessionData.prinzessinID(), AssetCategory.VOICE, presentationData.currentVoice()),
                AssetResolver.resolve(sessionData.prinzessinID(), AssetCategory.SPRITE, presentationData.currentSprite()),
                statData.insanity(), statData.affection(), statData.hunger(), statData.money(),
                interaction.resolveTopic(data.getTopicData()),
                interaction.resolveInventory(data.getInventoryData())
            ));
            dataController.getPresentationData().setCurrentBackground(
                AssetResolver.resolve(sessionData.prinzessinID(), AssetCategory.BACKGROUND, presentationData.currentBackground())
            );

            AudioController.stopMusic();
            if (savedMusic != null) {
                AudioController.playMusic(savedMusic);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load game.", e);
        }
    }
}