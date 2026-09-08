package sdp;

import sdp.content.gameplay.InteractionController;
import sdp.modules.dialogue.DialogueRunner;

public class GameController {
    private GameController() {}
    private static class Holder { private static final GameController INSTANCE = new GameController(); }
    public static GameController getInstance() { return Holder.INSTANCE; }

    // Fields
    private final SessionController sessionController = new SessionController();
    private final DialogueRunner dialogueRunner = new DialogueRunner();
    private InteractionController interactionController = null;

    // Initializations
    private void initializeInteractionController() {
        this.interactionController = new InteractionController();
    }

    // Reset
    public void resetInstance(){
        interactionController = null;
    }

    // Getters
    public SessionController getSessionController() {
        return sessionController;
    }

    public InteractionController getInteractionController() {
        if(this.interactionController == null) {
            initializeInteractionController();
        }
        return interactionController;
    }

    public DialogueRunner getDialogueRunner(){
        return dialogueRunner;
    }
}