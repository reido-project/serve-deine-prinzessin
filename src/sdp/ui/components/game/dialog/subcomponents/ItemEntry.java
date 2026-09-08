package sdp.ui.components.game.dialog.subcomponents;

import sdp.items.ItemList;
import sdp.items.ItemType;

import javax.swing.*;
import java.awt.*;

public class ItemEntry extends JPanel {

    private static final int WIDTH = 1427;
    private static final int HEIGHT = 58;

    private static final int TITLE_X = 7;
    private static final int TITLE_Y = 5;

    private static final int DESCRIPTION_X = 7;
    private static final int DESCRIPTION_Y = 34;

    private static final int BUTTON_X = 1310;
    private static final int BUTTON_Y = 10;

    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 40;

    private static final int CORNER_RADIUS = 10;

    private static final Color BACKGROUND =
        Color.decode("#2C2C2C");

    private final ItemList item;

    public ItemEntry(
        ItemList item,
        double scale,
        Runnable onAction
    ) {
        if (item == null) {
            throw new IllegalArgumentException(
                "Item cannot be null."
            );
        }

        if (onAction == null) {
            throw new IllegalArgumentException(
                "onAction cannot be null."
            );
        }

        this.item = item;

        setLayout(null);
        setOpaque(false);

        int width = scale(WIDTH, scale);
        int height = scale(HEIGHT, scale);

        setPreferredSize(
            new Dimension(width, height)
        );

        setMinimumSize(
            new Dimension(width, height)
        );

        setMaximumSize(
            new Dimension(width, height)
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel title =
            new JLabel(item.getName());

        title.setFont(
            new Font(
                Font.SANS_SERIF,
                Font.BOLD,
                scale(20, scale)
            )
        );

        title.setForeground(Color.WHITE);

        title.setBounds(
            scale(TITLE_X, scale),
            scale(TITLE_Y, scale),
            scale(1200, scale),
            scale(25, scale)
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        JLabel description =
            new JLabel(item.getDescription());

        description.setFont(
            new Font(
                Font.SANS_SERIF,
                Font.PLAIN,
                scale(16, scale)
            )
        );

        description.setForeground(Color.WHITE);

        description.setBounds(
            scale(DESCRIPTION_X, scale),
            scale(DESCRIPTION_Y, scale),
            scale(1280, scale),
            scale(20, scale)
        );

        // =====================================================
        // ACTION BUTTON
        // =====================================================

        JButton actionButton =
            createActionButton(
                item,
                scale,
                onAction
            );

        actionButton.setBounds(
            scale(BUTTON_X, scale),
            scale(BUTTON_Y, scale),
            scale(BUTTON_WIDTH, scale),
            scale(BUTTON_HEIGHT, scale)
        );

        add(title);
        add(description);
        add(actionButton);
    }

    private JButton createActionButton(
        ItemList item,
        double scale,
        Runnable action
    ) {
        if (item.getItemType() == ItemType.CONSUMABLE) {
            return new UseBtn(action, scale);
        }

        return new GiveBtn(action, scale);
    }

    private static int scale(
        int value,
        double scale
    ) {
        return (int) Math.round(
            value * scale
        );
    }

    public ItemList getItem() {
        return item;
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
            scale(CORNER_RADIUS, componentScale);

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