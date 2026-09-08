package sdp.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import sdp.Config;
import sdp.GameController;
import sdp.content.gameplay.InteractionController;
import sdp.content.gameplay.inventory.items.Item;
import sdp.content.gameplay.talk.topics.Topic;
import sdp.content.stats.Stat;
import sdp.shared.dtos.initialization.SessionContextDTO;
import sdp.content.prinzessins.Prinzessin;
import sdp.persistence.persistent.PersistentData;
import sdp.persistence.persistent.dtos.*;
import sdp.persistence.runtime.RuntimeData;
import sdp.shared.dtos.inventory.InventoryEntry;
import sdp.shared.dtos.talk.TopicEntry;
import sdp.shared.utils.EncryptionUtil;
import sdp.shared.utils.ImageUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SessionController {
    private final DataController dataController = DataController.getInstance();
    private final GameController gameController = GameController.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);


    // Runtime
    public Stat refreshStat() {
        return new Stat();
    }

    public BufferedImage refreshSprite(){
        return ImageUtil.loadImage(dataController.getPresentationData().getCurrentSprite().getDirectory());
    }

    public InventoryEntry[] refreshInventory(){
        List<InventoryEntry> inventoryEntries = new ArrayList<>();
        List<Item> inventory = dataController.getInventoryData().getInventory();

        for(Item item : inventory){
            inventoryEntries.add(new InventoryEntry(item.getTitle(), item.getDescription(), item.getId()));
        }
        return inventoryEntries.toArray(new InventoryEntry[0]);
    }

    public TopicEntry[] refreshTopics(){
        List<TopicEntry> topicEntries = new ArrayList<>();
        List<Topic> topics = dataController.getTopicData().getTopics();
        for(Topic topic : topics){
            topicEntries.add(new TopicEntry(topic.getTopic(), topic.getId()));
        }
        return topicEntries.toArray(new TopicEntry[0]);
    }

    // Create / Write Session
    public void initializeNewGame(SessionContextDTO context){
        Prinzessin prinzessin = context.prinzessinID();

        dataController.initializeSession(context.playerName(), prinzessin);
        InteractionController interaction = gameController.getInteractionController();

        dataController.loadRuntimeData(
            new RuntimeData(
                prinzessin.getInsanity(), prinzessin.getAffection(), prinzessin.getHunger(), prinzessin.getMoney(),
                interaction.getDefaultTopics(), interaction.resolveInventory(prinzessin.getItems())
            )
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

            dataController.initializeSession(sessionData.playerName(), sessionData.prinzessinID());

            gameController.resetInstance();
            InteractionController interaction = gameController.getInteractionController();

            dataController.loadRuntimeData(new RuntimeData(
                presentationData.currentMusic(), presentationData.currentVoice(), presentationData.currentSprite(),
                statData.insanity(), statData.affection(), statData.hunger(), statData.money(),
                interaction.resolveTopic(data.getTopicData()),
                interaction.resolveInventory(data.getInventoryData())
            ));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load game.", e);
        }
    }
}