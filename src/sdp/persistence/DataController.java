package sdp.persistence;

import sdp.characters.Character;
import sdp.characters.CharacterList;
import sdp.characters.kyoko.Kyoko;
import sdp.dialogues.StoryState;

import java.util.HashSet;
import java.util.Set;

import static sdp.items.ItemList.*;

public final class DataController {

    private final RuntimeData runtimeData;

    private DataController() {
        runtimeData = new RuntimeData();
    }

    private static class Holder {
        private static final DataController INSTANCE =
            new DataController();
    }

    public static DataController getInstance() {
        return Holder.INSTANCE;
    }

    public RuntimeData getRuntimeData() {
        return runtimeData;
    }

    public void initializeNewGame(CharacterList prinzessinId) {
        RuntimeData data = getRuntimeData();

        data.setPlayerInsanity(30);
        data.setInventory(new HashSet<>(Set.of(
            JARI_SUKUNA, AHEGAO_HOODIE, BUKU_PEREMPUAN, TOPI_SHERLOCK, SWALLOW_KANAN, DIFTERI_ABED, PATUNG_AYAM,
            PIRING_SEKOLAH, LUKISAN_KELIR, PHONE_WAVE, PISTOL_KOREK, HEADPHONE_AMBAUDIO, RAMUAN_POSESI,
            RUMPUT_TETANGGA, VHS_DESPAIR, KITAB_CHEAT, MIC_PELUNAS, TAKEDA_PORO, DISTRUST_WALKMAN, ULET_BULU,
            CYALUME_SABER, KOTA_JAHAT, BUKU_101, BIT_GUN, KAMUS_JAWA, RYZEN_4090TI, ADAPTER_ROKOK, VHS_DEBUS,
            KITAB_PRIMBON, LIFE_NOTE
        )));

        data.setPrinzessinAffection(50);
        data.setPrinzessinHunger(100);

        data.setStoryState(StoryState.PROLOGUE);

        data.setDialogueType(null);
        data.setPreference(null);
        data.setItemList(null);
        data.setVariantIdentifier(0);
        data.setDialogueIndex(0);

        data.setMusic(null);
        data.setSpriteState(null);

        data.setPrinzessin(resolveCharacter(prinzessinId));

        data.setPlayerMoney(0);
        data.setKotaJahatUses(0);
        data.setMicrophonePelunasBroken(false);
    }

    private Character resolveCharacter(CharacterList id) {
        return switch (id) {
            case KYOKO -> new Kyoko();
            default -> throw new IllegalStateException("Unexpected value: " + id);
            //todo
            // case CHIAKI -> new Chiaki();
            // case KAZUHA -> new Kazuha();
            // case TOMOYO -> new Tomoyo();
        };
    }

    public void save() {
        PersistentData persistentData = toPersistentData();

        // serialize persistentData -> JSON
    }

    public void load() {
        // nanti
    }

    private PersistentData toPersistentData() {
        PersistentData data = new PersistentData();

        data.setPlayerName(runtimeData.getPlayerName());
        data.setPlayerInsanity(runtimeData.getPlayerInsanity());

        if (runtimeData.getInventory() != null) {
            data.setInventory(
                new HashSet<>(runtimeData.getInventory())
            );
        }

        Character prinzessin = runtimeData.getPrinzessin();

        if (prinzessin != null) {
            data.setPrinzessinId(prinzessin.getId());
        }

        data.setPrinzessinAffection(
            runtimeData.getPrinzessinAffection()
        );

        data.setPrinzessinHunger(
            runtimeData.getPrinzessinHunger()
        );

        data.setDialogueType(
            runtimeData.getDialogueType()
        );

        data.setStoryState(
            runtimeData.getStoryState()
        );

        data.setPreference(
            runtimeData.getPreference()
        );

        data.setItemList(
            runtimeData.getItemList()
        );

        data.setVariantIdentifier(
            runtimeData.getVariantIdentifier()
        );

        data.setDialogueIndex(
            runtimeData.getDialogueIndex()
        );

        data.setMusic(
            runtimeData.getMusic()
        );

        data.setSpriteState(
            runtimeData.getSpriteState()
        );

        return data;
    }
}