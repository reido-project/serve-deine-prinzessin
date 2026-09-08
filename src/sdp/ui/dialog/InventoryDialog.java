package sdp.ui.dialog;

import sdp.GameController;
import sdp.content.gameplay.inventory.items.ItemType;
import sdp.modules.dialogue.Dialogue;
import sdp.shared.dtos.inventory.InventoryEntry;
import sdp.ui.components.button.Button;
import sdp.ui.components.button.ButtonOption;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class InventoryDialog extends GameDialog {
    private static final int LOGICAL_WIDTH = 1475;
    private static final int LOGICAL_HEIGHT = 960;
    private static final int LIST_BOTTOM_PADDING = 22;

    public InventoryDialog(Window owner, GameController controller, Consumer<Dialogue[]> onDialogue) {
        super(owner, "Inventory");
        double scale = getScale();
        InventoryEntry[] inventory = controller.getSessionController().refreshInventory();
        ItemListPanel items = new ItemListPanel(inventory.length, scale);
        items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));
        items.setOpaque(false);
        for (InventoryEntry inventoryEntry : inventory) {
            items.add(new ItemRow(inventoryEntry, controller, onDialogue, scale));
            items.add(Box.createVerticalStrut(scale(12)));
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

        JLabel title = new JLabel("Inventory", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, scale(50)));
        title.setBounds(scale(537), scale(25), scale(400), scale(60));
        content.add(title);

        Button close = createCloseButton();
        close.setFont(close.getFont().deriveFont((float) (close.getFont().getSize2D() * scale)));
        close.setBounds(scale(1410), scale(25), scale(40), scale(40));
        content.add(close);

        JScrollPane scrollPane = new JScrollPane(items);
        scrollPane.setBounds(scale(17), scale(100), scale(1427), scale(LOGICAL_HEIGHT - 100));
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(scale(30));
        content.add(scrollPane);

        finishScaledLayout(content, LOGICAL_WIDTH, LOGICAL_HEIGHT, 250, 120);
    }

    private static int scale(int value, double scale) {
        return (int) Math.round(value * scale);
    }

    private static final class ItemRow extends JPanel {
        private ItemRow(InventoryEntry entry, GameController controller, Consumer<Dialogue[]> onDialogue, double scale) {
            setLayout(null);
            setOpaque(false);
            int width = scale(1427, scale);
            int height = scale(58, scale);
            setPreferredSize(new Dimension(width, height));
            setMinimumSize(new Dimension(width, height));
            setMaximumSize(new Dimension(width, height));

            JLabel title = new JLabel(entry.name());
            title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, scale(20, scale)));
            title.setForeground(Color.WHITE);
            title.setBounds(scale(7, scale), scale(5, scale), scale(1200, scale), scale(25, scale));

            JLabel description = new JLabel(entry.description());
            description.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, scale(16, scale)));
            description.setForeground(Color.WHITE);
            description.setBounds(scale(7, scale), scale(34, scale), scale(1280, scale), scale(20, scale));

            String actionText = entry.type() == ItemType.CONSUMABLE ? "Use" : "Give";
            Button action = new Button(actionText, ButtonOption.SmallButton, () -> {
                onDialogue.accept(controller.getInteractionController().use(entry.id()));
                if (entry.type() == ItemType.GIFT) {
                    Window window = SwingUtilities.getWindowAncestor(this);
                    if (window != null) window.dispose();
                }
            });

            // Ambil ukuran bawaan dari ButtonOption dan sesuaikan skala-nya
            int actionW = scale(action.getAppearance().getWidth(), scale);
            int actionH = scale(action.getAppearance().getHeight(), scale);
            action.setFont(action.getFont().deriveFont((float) (action.getFont().getSize2D() * scale)));
            action.setBounds(scale(1310, scale), scale(7, scale), actionW, actionH);

            add(title);
            add(description);
            add(action);
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

    private static final class ItemListPanel extends JPanel implements Scrollable {

        private ItemListPanel(int itemCount, double scale) {
            super();
            int logicalHeight = scale(itemCount * 58 + itemCount * 12 + LIST_BOTTOM_PADDING, scale);
            setPreferredSize(new Dimension(scale(1427, scale), logicalHeight));
        }

        @Override
        public Dimension getPreferredScrollableViewportSize() {
            return new Dimension(LOGICAL_WIDTH - 48, LOGICAL_HEIGHT - 100);
        }

        @Override
        public boolean getScrollableTracksViewportWidth() {
            return true;
        }

        @Override
        public boolean getScrollableTracksViewportHeight() {
            return false;
        }

        @Override
        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 30;
        }

        @Override
        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
            return visibleRect.height;
        }
    }
}