package sdp.dialogues;

import sdp.characters.Preference;
import sdp.gameplay.interactions.feed.FeedResult;
import sdp.gameplay.interactions.talk.TopicID;
import sdp.items.ItemList;

public interface DialogueBank {
    Dialogue[] getStoryDialogue(StoryState storyState);
    Dialogue[] getPreferenceDialogue(Preference preference);
    Dialogue[] getSpecialItemDialogue(ItemList item);
    Dialogue[] getTease();
    Dialogue[] getTeaseResponse(int affection);
    Dialogue[] getTalkDialogue(TopicID topicID);
    Dialogue[] getFeedDialogue(FeedResult feedResult);
}
