package sdp.dialogues;

import sdp.characters.CharacterList;
import sdp.dialogues.kazuha.KazuhaDialogue;
import sdp.dialogues.kyoko.KyokoDialogue;
import sdp.gameplay.interactions.feed.FeedResult;
import sdp.gameplay.interactions.talk.TopicID;
import sdp.items.ItemList;
import sdp.characters.Preference;
import sdp.persistence.DataController;
import sdp.persistence.RuntimeData;

public class DialogueController {

    private final DialogueBank dialogueBank;
    private final RuntimeData data;
    private Dialogue triggeredDialogue;

    public DialogueController(CharacterList characterID) {
        this.data = DataController.getInstance().getRuntimeData();

        this.dialogueBank = switch (characterID) {
            case KYOKO -> new KyokoDialogue();
            case KAZUHA -> new KazuhaDialogue();
            default -> throw new IllegalArgumentException(
                "Unknown Character."
            );
        };
    }

    // =========================================================
    // LOAD
    // =========================================================

    public void startStory(StoryState state) {
        Dialogue[] dialogue = dialogueBank.getStoryDialogue(state);

        if (dialogue == null || dialogue.length == 0) {
            throw new IllegalStateException(
                "Dialogue is empty for story state: " + state
            );
        }

        start(DialogueType.STORY, dialogue);
    }

    public void startPreference(Preference preference) {
        Dialogue[] dialogue =
            dialogueBank.getPreferenceDialogue(preference);

        start(DialogueType.PREFERENCE, dialogue);
    }

    public void startSpecialItem(ItemList item) {
        Dialogue[] dialogue =
            dialogueBank.getSpecialItemDialogue(item);

        start(DialogueType.SPECIAL, dialogue);
    }

    private void start(
        DialogueType type,
        Dialogue[] dialogue
    ) {
        data.setDialogueType(type);
        data.setDialogue(dialogue);
        data.setDialogueIndex(0);
    }

    // =========================================================
    // STATE
    // =========================================================

    public boolean isActive() {
        Dialogue[] dialogue = data.getDialogue();

        return dialogue != null
            && dialogue.length > 0
            && data.getDialogueIndex() < dialogue.length;
    }

    public Dialogue getCurrentDialogue() {
        if (!isActive()) {
            return null;
        }

        Dialogue dialogue =
            data.getDialogue()[data.getDialogueIndex()];

        if (dialogue != triggeredDialogue) {
            triggeredDialogue = dialogue;

            if (dialogue.onTrigger() != null) {
                dialogue.onTrigger().run();
            }
        }

        return dialogue;
    }

    public boolean advance() {
        if (!isActive()) {
            return false;
        }

        data.setDialogueIndex(
            data.getDialogueIndex() + 1
        );

        return isActive();
    }

    public void clear() {
        data.setDialogue(null);
        data.setDialogueType(null);
        data.setDialogueIndex(0);

        triggeredDialogue = null;
    }

    public boolean isFinished() {
        Dialogue[] dialogue = data.getDialogue();

        return dialogue != null
            && data.getDialogueIndex() >= dialogue.length;
    }

    public void startTease() {
        Dialogue[] dialogue =
            dialogueBank.getTease();

        start(DialogueType.TEASE, dialogue);
    }

    public void startTeaseResponse(int affection) {
        Dialogue[] dialogue =
            dialogueBank.getTeaseResponse(affection);

        start(DialogueType.TEASE, dialogue);
    }

    public void startTalk(TopicID topicID) {
        Dialogue[] dialogue =
            dialogueBank.getTalkDialogue(topicID);

        if (dialogue == null || dialogue.length == 0) {
            throw new IllegalStateException(
                "Dialogue is empty for topic: " + topicID
            );
        }

        start(DialogueType.TALK, dialogue);
    }

    public void startFeed(FeedResult result) {
        Dialogue[] dialogue =
            dialogueBank.getFeedDialogue(result);

        if (dialogue == null || dialogue.length == 0) {
            throw new IllegalStateException(
                "Feed dialogue is empty."
            );
        }

        start(DialogueType.FEED, dialogue);
    }
}