package sdp.content.gameplay.story;

import sdp.content.stats.AffectionType;
import sdp.content.stats.HungerType;
import sdp.content.stats.InsanityType;
import sdp.content.stats.StatParser;
import sdp.modules.assets.Asset;
import sdp.modules.dialogue.Dialogue;
import sdp.modules.dialogue.DialogueRepository;
import sdp.persistence.api.PresentationDataAPI;
import sdp.persistence.api.SessionDataAPI;
import sdp.persistence.api.StatDataAPI;

import java.util.Map;

import static sdp.content.gameplay.story.StoryState.*;

public class StoryController implements SessionDataAPI, StatDataAPI, PresentationDataAPI {
    private final Map<StoryState, Asset> backgrounds;
    private final DialogueRepository dialogueRepository;

    public StoryController(Map<StoryState, Asset> backgrounds, DialogueRepository dialogueRepository) {
        this.backgrounds = backgrounds;
        this.dialogueRepository = dialogueRepository;
    }

    public Dialogue[] checkStory() {
        StoryState storyState = getStoryState();

        if (storyState == PROLOGUE) {
            setBackground(backgrounds.get(PROLOGUE));
            setStoryState(GAME);
            return dialogueRepository.getStory(storyState);
        }

        storyState = refreshStory();

        if (storyState == GAME) {
            return null;
        }

        return dialogueRepository.getStory(storyState);
    }

    private StoryState refreshStory() {
        if (StatParser.getHunger() == HungerType.DEAD) {
            return saveAndReturn(DEAD_END);
        }

        InsanityType insanity = StatParser.getInsanity();
        if (insanity == InsanityType.MAX_INSANITY) return saveAndReturn(MAX_INSANITY_END);
        if (insanity == InsanityType.SANE) return saveAndReturn(SANE_END);

        AffectionType affection = StatParser.getAffection();
        if (affection == AffectionType.MAX_AFFECTION) return saveAndReturn(MAX_AFF_END);
        if (affection == AffectionType.NO_AFFECTION) return saveAndReturn(NO_AFF_END);

        return saveAndReturn(GAME);
    }

    private StoryState saveAndReturn(StoryState state) {
        setBackground(backgrounds.get(state));
        setStoryState(state);
        return state;
    }
}