package sdp.ui.components.game.interaction;

import sdp.gameplay.GameController;

public class FeedBtn extends InteractionBtn {

    private final Runnable onFeed;

    public FeedBtn(
        GameController gameController,
        Runnable onFeed
    ) {
        super("Feed", gameController);

        if (onFeed == null) {
            throw new IllegalArgumentException(
                "onFeed cannot be null."
            );
        }

        this.onFeed = onFeed;

        addActionListener(e -> {

            if (!isInteractionAllowed()) {
                return;
            }

            gameController.feed();

            onFeed.run();
        });
    }
}