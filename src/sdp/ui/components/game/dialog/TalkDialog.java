package sdp.ui.components.game.dialog;

import sdp.gameplay.GameController;
import sdp.gameplay.GameState;
import sdp.gameplay.interactions.talk.Topic;
import sdp.gameplay.interactions.talk.TopicID;
import sdp.ui.GameCanvas;
import sdp.ui.components.common.setting.subcomponents.CloseBtn;
import sdp.ui.components.game.dialog.subcomponents.TalkEntry;
import sdp.ui.dialog.GameDialog;
import sdp.ui.util.GraphicsUtil;

import javax.swing.*;
import java.awt.*;

public class TalkDialog extends GameDialog {

    // =========================================================
    // LOGICAL DIMENSIONS
    // =========================================================

    private static final int LOGICAL_WIDTH = 1475;
    private static final int LOGICAL_HEIGHT = 960;

    private static final int DIALOG_X = 250;
    private static final int DIALOG_Y = 120;

    // =========================================================
    // TITLE
    // =========================================================

    private static final int TITLE_X = 537;
    private static final int TITLE_Y = 25;

    private static final int TITLE_WIDTH = 400;
    private static final int TITLE_HEIGHT = 60;

    private static final int TITLE_FONT_SIZE = 50;

    // =========================================================
    // CLOSE
    // =========================================================

    private static final int CLOSE_X = 1410;
    private static final int CLOSE_Y = 25;

    private static final int CLOSE_SIZE = 40;

    // =========================================================
    // TOPIC LIST
    // =========================================================

    private static final int ENTRY_X = 17;
    private static final int ENTRY_Y = 100;

    private static final int ENTRY_WIDTH = 1427;
    private static final int ENTRY_HEIGHT = 58;

    private static final int SCROLL_UNIT = 30;

    private static final int ENTRY_GAP = 12;

    private static final Color BACKGROUND =
        new Color(0, 0, 0, 204);

    private final GameCanvas gameCanvas;
    private final GameController gameController;

    private final Runnable onTalk;

    private final JPanel root;
    private JPanel topicListPanel;
    private JScrollPane scrollPane;

    public TalkDialog(
        Window owner,
        GameCanvas gameCanvas,
        GameController gameController,
        Runnable onTalk
    ) {
        super(owner, gameCanvas);

        setDefaultCloseOperation(
            WindowConstants.DISPOSE_ON_CLOSE
        );

        if (gameCanvas == null) {
            throw new IllegalArgumentException(
                "GameCanvas cannot be null."
            );
        }

        if (gameController == null) {
            throw new IllegalArgumentException(
                "GameController cannot be null."
            );
        }

        if (onTalk == null) {
            throw new IllegalArgumentException(
                "onTalk cannot be null."
            );
        }

        this.onTalk = onTalk;

        this.gameCanvas = gameCanvas;
        this.gameController = gameController;

        // =====================================================
        // ROOT
        // =====================================================

        root = new JPanel(null) {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 =
                    GraphicsUtil.createAntialiased(g);

                g2.setColor(BACKGROUND);

                g2.fillRect(
                    0,
                    0,
                    getWidth(),
                    getHeight()
                );

                g2.dispose();
            }
        };

        root.setOpaque(false);

        setContentPane(root);

        initializeTitle();
        initializeCloseButton();
        initializeTopicList();

        applyLogicalSize();
        positionDialog();

        refreshTopics();
    }

    // =========================================================
    // TITLE
    // =========================================================

    private void initializeTitle() {

        JLabel title =
            new JLabel("Talk");

        title.setFont(
            new Font(
                Font.SANS_SERIF,
                Font.PLAIN,
                scale(TITLE_FONT_SIZE)
            )
        );

        title.setForeground(Color.WHITE);

        title.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        title.setBounds(
            scale(TITLE_X),
            scale(TITLE_Y),
            scale(TITLE_WIDTH),
            scale(TITLE_HEIGHT)
        );

        root.add(title);
    }

    // =========================================================
    // CLOSE
    // =========================================================

    private void initializeCloseButton() {

        CloseBtn closeBtn =
            new CloseBtn(getScale());

        closeBtn.setBounds(
            scale(CLOSE_X),
            scale(CLOSE_Y),
            scale(CLOSE_SIZE),
            scale(CLOSE_SIZE)
        );

        root.add(closeBtn);
    }

    // =========================================================
    // TOPIC LIST
    // =========================================================

    private void initializeTopicList() {

        topicListPanel =
            new JPanel();

        topicListPanel.setLayout(
            new BoxLayout(
                topicListPanel,
                BoxLayout.Y_AXIS
            )
        );

        topicListPanel.setOpaque(false);

        scrollPane =
            new JScrollPane(
                topicListPanel
            );

        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);

        scrollPane.getViewport()
            .setOpaque(false);

        scrollPane.setHorizontalScrollBarPolicy(
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.setVerticalScrollBarPolicy(
            ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER
        );

        scrollPane.getVerticalScrollBar()
            .setUnitIncrement(SCROLL_UNIT);

        int viewportHeight =
            LOGICAL_HEIGHT - ENTRY_Y;

        scrollPane.setBounds(
            scale(ENTRY_X),
            scale(ENTRY_Y),
            scale(ENTRY_WIDTH),
            scale(viewportHeight)
        );

        root.add(scrollPane);
    }

    // =========================================================
    // TOPICS
    // =========================================================

    private void refreshTopics() {

        topicListPanel.removeAll();

        Topic[] topics =
            gameController
                .getInteractionController()
                .getTalkTopics();

        if (topics != null) {

            for (Topic topic : topics) {
                addTopic(topic);
            }
        }

        topicListPanel.revalidate();
        topicListPanel.repaint();
    }

    // =========================================================
    // TOPIC ENTRY
    // =========================================================

    private void addTopic(Topic topic) {

        if (topic == null) {
            return;
        }

        TalkEntry entry =
            new TalkEntry(
                topic,
                getScale(),
                () -> handleTopicAction(topic)
            );

        Dimension entrySize =
            new Dimension(
                scale(ENTRY_WIDTH),
                scale(ENTRY_HEIGHT)
            );

        entry.setPreferredSize(entrySize);
        entry.setMinimumSize(entrySize);
        entry.setMaximumSize(entrySize);

        entry.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        topicListPanel.add(entry);

        topicListPanel.add(
            Box.createVerticalStrut(
                scale(ENTRY_GAP)
            )
        );
    }

    // =========================================================
    // DISCUSS
    // =========================================================

    private void handleTopicAction(Topic topic) {

        if (topic == null) {
            return;
        }

        TopicID topicID =
            topic.getId();

        if (topicID == null) {
            return;
        }

        dispose();

        gameController.startTalk(topicID);

        onTalk.run();
    }

    // =========================================================
    // POSITION
    // =========================================================

    private void positionDialog() {

        if (!gameCanvas.isShowing()) {
            setLocationRelativeTo(getOwner());
            return;
        }

        Point canvasLocation =
            gameCanvas.getLocationOnScreen();

        int x =
            canvasLocation.x
                + scale(DIALOG_X);

        int y =
            canvasLocation.y
                + scale(DIALOG_Y);

        setLocation(x, y);
    }

    // =========================================================
    // GAME DIALOG CONTRACT
    // =========================================================

    @Override
    protected int getLogicalWidth() {
        return LOGICAL_WIDTH;
    }

    @Override
    protected int getLogicalHeight() {
        return LOGICAL_HEIGHT;
    }

    @Override
    public void dispose() {
        gameController.setGameState(
            GameState.IDLE
        );

        super.dispose();
    }
}