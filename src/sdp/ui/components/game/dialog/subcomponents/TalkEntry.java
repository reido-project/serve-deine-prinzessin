package sdp.ui.components.game.dialog.subcomponents;

import sdp.gameplay.interactions.talk.Topic;

import javax.swing.*;
import java.awt.*;

public class TalkEntry extends JPanel {

    private static final int WIDTH = 1427;
    private static final int HEIGHT = 58;

    private static final int TOPIC_X = 7;
    private static final int TOPIC_Y = 0;

    private static final int BUTTON_X = 1280;
    private static final int BUTTON_Y = 9;

    private static final int BUTTON_WIDTH = 140;
    private static final int BUTTON_HEIGHT = 40;

    private static final int CORNER_RADIUS = 10;

    private static final Color BACKGROUND =
        Color.decode("#2C2C2C");

    private final Topic topic;

    public TalkEntry(
        Topic topic,
        double scale,
        Runnable onAction
    ) {
        if (topic == null) {
            throw new IllegalArgumentException(
                "Topic cannot be null."
            );
        }

        if (onAction == null) {
            throw new IllegalArgumentException(
                "onAction cannot be null."
            );
        }

        this.topic = topic;

        setLayout(null);
        setOpaque(false);

        int width = scale(WIDTH, scale);
        int height = scale(HEIGHT, scale);

        Dimension size =
            new Dimension(width, height);

        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        // =====================================================
        // TOPIC
        // =====================================================

        JLabel topicLabel =
            new JLabel(topic.getTopic());

        topicLabel.setFont(
            new Font(
                Font.SANS_SERIF,
                Font.BOLD,
                scale(20, scale)
            )
        );

        topicLabel.setForeground(Color.WHITE);

        topicLabel.setVerticalAlignment(
            SwingConstants.CENTER
        );

        topicLabel.setBounds(
            scale(TOPIC_X, scale),
            scale(TOPIC_Y, scale),
            scale(1280, scale),
            scale(HEIGHT, scale)
        );

        // =====================================================
        // DISCUSS
        // =====================================================

        DiscussBtn discussBtn =
            new DiscussBtn(
                onAction,
                scale
            );

        discussBtn.setBounds(
            scale(BUTTON_X, scale),
            scale(BUTTON_Y, scale),
            scale(BUTTON_WIDTH, scale),
            scale(BUTTON_HEIGHT, scale)
        );

        add(topicLabel);
        add(discussBtn);
    }

    public Topic getTopic() {
        return topic;
    }

    private static int scale(
        int value,
        double scale
    ) {
        return (int) Math.round(
            value * scale
        );
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 =
            (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(BACKGROUND);

        double componentScale =
            getWidth() / (double) WIDTH;

        int radius =
            scale(
                CORNER_RADIUS,
                componentScale
            );

        g2.fillRoundRect(
            0,
            0,
            getWidth(),
            getHeight(),
            radius,
            radius
        );

        g2.dispose();

        super.paintComponent(g);
    }
}