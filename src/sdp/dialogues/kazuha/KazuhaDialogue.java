package sdp.dialogues.kazuha;

import sdp.characters.Preference;
import sdp.dialogues.Dialogue;
import sdp.dialogues.DialogueBank;
import sdp.dialogues.StoryState;
import sdp.gameplay.interactions.feed.FeedResult;
import sdp.gameplay.interactions.talk.TopicID;
import sdp.items.ItemList;

public class KazuhaDialogue implements DialogueBank {

    @Override
    public Dialogue[] getStoryDialogue(StoryState storyState) {
        return new Dialogue[0];
    }

    @Override
    public Dialogue[] getPreferenceDialogue(Preference preference) {
        return new Dialogue[0];
    }

    @Override
    public Dialogue[] getSpecialItemDialogue(ItemList item) {
        return new Dialogue[0];
    }

    @Override
    public Dialogue[] getTease() {
        return new Dialogue[0];
    }

    @Override
    public Dialogue[] getTeaseResponse(int affection) {
        return new Dialogue[0];
    }

    @Override
    public Dialogue[] getTalkDialogue(TopicID topicID) {
        return new Dialogue[0];
    }

    @Override
    public Dialogue[] getFeedDialogue(FeedResult feedResult) {
        return new Dialogue[0];
    }
}
