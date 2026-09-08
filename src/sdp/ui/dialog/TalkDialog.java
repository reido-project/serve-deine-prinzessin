package sdp.ui.dialog;

import sdp.GameController;
import sdp.content.gameplay.talk.topics.TopicID;
import sdp.modules.dialogue.Dialogue;
import sdp.shared.dtos.talk.TopicEntry;
import sdp.ui.components.button.Button;
import sdp.ui.components.button.ButtonOption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelListener;
import java.util.function.Consumer;

public class TalkDialog extends GameDialog {
    private static final int LOGICAL_WIDTH = 1475;
    private static final int LOGICAL_HEIGHT = 960;
    private static final int ENTRY_X = 17;
    private static final int ENTRY_Y = 100;
    private static final int ENTRY_WIDTH = 1427;
    private static final int ENTRY_HEIGHT = 58;
    private static final int ENTRY_GAP = 12;
    private static final int LIST_BOTTOM_PADDING = 22;

    public TalkDialog(Window owner, GameController controller, Consumer<Dialogue[]> onDialogue) {
        super(owner, "Talk");
        double scale = getScale();
        TopicEntry[] topics = controller.getSessionController().refreshTopics();
        TopicListPanel list = new TopicListPanel(topics.length, scale);
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
        list.setOpaque(false);

        for (TopicEntry topic : topics) {
            list.add(new TopicRow(topic, controller, onDialogue, scale));
            list.add(Box.createVerticalStrut(scale(ENTRY_GAP, scale)));
        }

        JPanel content = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics graphics) {
                graphics.setColor(new Color(0, 0, 0, 204));
                graphics.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(graphics);
            }
        };
        content.setOpaque(false);

        JLabel title = new JLabel("Talk", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, scale(50, scale)));
        title.setBounds(scale(537, scale), scale(25, scale), scale(400, scale), scale(60, scale));
        content.add(title);

        Button close = createCloseButton();
        close.setFont(close.getFont().deriveFont((float) (close.getFont().getSize2D() * scale)));
        close.setBounds(scale(1410, scale), scale(25, scale), scale(40, scale), scale(40, scale));
        content.add(close);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(scale(ENTRY_X, scale), scale(ENTRY_Y, scale), scale(ENTRY_WIDTH, scale), scale(LOGICAL_HEIGHT - ENTRY_Y, scale));
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(scale(30, scale));
        MouseWheelListener wheelListener = event -> {
            JViewport viewport = scrollPane.getViewport();
            Point position = viewport.getViewPosition();
            int maximum = Math.max(0, list.getHeight() - viewport.getHeight());
            int nextY = Math.clamp(position.y + (long) event.getWheelRotation() * scale(30, scale), 0, maximum);
            viewport.setViewPosition(new Point(position.x, nextY));
            event.consume();
        };
        scrollPane.addMouseWheelListener(wheelListener);
        scrollPane.getViewport().addMouseWheelListener(wheelListener);
        for (Component component : list.getComponents()) {
            addWheelListener(component, wheelListener);
        }
        content.add(scrollPane);

        finishScaledLayout(content, LOGICAL_WIDTH, LOGICAL_HEIGHT, 250, 120);
    }

    private static void addWheelListener(Component component, MouseWheelListener listener) {
        component.addMouseWheelListener(listener);
        if (component instanceof Container container) {
            for (Component child : container.getComponents()) {
                addWheelListener(child, listener);
            }
        }
    }

    private static int scale(int value, double scale) {
        return (int) Math.round(value * scale);
    }

    private static final class TopicRow extends JPanel {
        private TopicRow(TopicEntry entry, GameController controller, Consumer<Dialogue[]> onDialogue, double scale) {
            setLayout(null);
            setOpaque(false);
            Dimension size = new Dimension(scale(ENTRY_WIDTH, scale), scale(ENTRY_HEIGHT, scale));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);

            JLabel topic = new JLabel(entry.topic());
            topic.setForeground(Color.WHITE);
            topic.setFont(new Font(Font.SANS_SERIF, Font.BOLD, scale(20, scale)));
            topic.setVerticalAlignment(SwingConstants.CENTER);
            topic.setBounds(scale(7, scale), 0, scale(1280, scale), scale(ENTRY_HEIGHT, scale));

            Button discuss = new Button("Discuss", ButtonOption.SmallButton, () -> {
                TopicID id = entry.id();
                if (id == null) return;
                onDialogue.accept(controller.getInteractionController().talk(id));
                Window window = SwingUtilities.getWindowAncestor(this);
                if (window != null) window.dispose();
            });

            int discussW = scale(discuss.getAppearance().getWidth(), scale);
            int discussH = scale(discuss.getAppearance().getHeight(), scale);
            discuss.setFont(discuss.getFont().deriveFont((float) (discuss.getFont().getSize2D() * scale)));
            discuss.setBounds(scale(1280, scale), scale(9, scale), discussW, discussH);

            add(topic);
            add(discuss);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(44, 44, 44));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.dispose();
            super.paintComponent(graphics);
        }
    }

    private static final class TopicListPanel extends JPanel implements Scrollable {
        private TopicListPanel(int topicCount, double scale) {
            setPreferredSize(new Dimension(scale(ENTRY_WIDTH, scale), scale(
                topicCount * ENTRY_HEIGHT + topicCount * ENTRY_GAP + LIST_BOTTOM_PADDING, scale
            )));
        }

        @Override public Dimension getPreferredScrollableViewportSize() { return new Dimension(ENTRY_WIDTH, LOGICAL_HEIGHT - ENTRY_Y); }
        @Override public boolean getScrollableTracksViewportWidth() { return true; }
        @Override public boolean getScrollableTracksViewportHeight() { return false; }
        @Override public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) { return 30; }
        @Override public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) { return visibleRect.height; }
    }
}