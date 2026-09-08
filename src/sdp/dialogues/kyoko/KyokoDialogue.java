package sdp.dialogues.kyoko;

import sdp.characters.Preference;
import sdp.dialogues.Dialogue;
import sdp.dialogues.DialogueBank;
import sdp.dialogues.StoryState;
import sdp.gameplay.interactions.feed.FeedResult;
import sdp.gameplay.interactions.talk.TopicID;
import sdp.items.ItemList;

public class KyokoDialogue implements DialogueBank {
    private final PreferenceDialogue preferenceDialogue;
    private final SpecialItemDialogue specialItemDialogue;
    private final StoryDialogue storyDialogue;
    private final TeaseDialogue teaseDialogue;
    private final TalkDialogue talkDialogue;
    private final FeedDialogue feedDialogue;

    public KyokoDialogue() {
        this.preferenceDialogue = new PreferenceDialogue();
        this.specialItemDialogue = new SpecialItemDialogue();
        this.storyDialogue = new StoryDialogue();
        this.teaseDialogue = new TeaseDialogue();
        this.talkDialogue = new TalkDialogue();
        this.feedDialogue = new FeedDialogue();
    }


    @Override
    public Dialogue[] getStoryDialogue(StoryState storyState) {
        return storyDialogue.get(storyState);
    }

    @Override
    public Dialogue[] getPreferenceDialogue(Preference preference) {
        return preferenceDialogue.get(preference);
    }

    @Override
    public Dialogue[] getSpecialItemDialogue(ItemList item) {
        return specialItemDialogue.get(item);
    }

    @Override
    public Dialogue[] getTease(){
        return teaseDialogue.get();
    }

    @Override
    public Dialogue[] getTeaseResponse(int affection){
        return teaseDialogue.get(affection);
    }

    @Override
    public Dialogue[] getTalkDialogue(TopicID topicID) {
        return talkDialogue.get(topicID);
    }

    @Override
    public Dialogue[] getFeedDialogue(FeedResult feedResult) {
        return feedDialogue.get(feedResult);
    }
}
