package sdp.ui.components.game.dialog;

import sdp.gameplay.GameController;
import sdp.gameplay.GameState;
import sdp.items.ItemController;
import sdp.items.ItemList;
import sdp.items.ItemType;
import sdp.persistence.DataController;
import sdp.persistence.RuntimeData;
import sdp.ui.GameCanvas;
import sdp.ui.components.common.setting.subcomponents.CloseBtn;
import sdp.ui.components.game.dialog.subcomponents.ItemEntry;
import sdp.ui.dialog.GameDialog;
import sdp.ui.util.GraphicsUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class InventoryDialog extends GameDialog {

    // =========================================================
    // LOGICAL DIMENSIONS
    // =========================================================

    private static final int LOGICAL_WIDTH = 1475;
    private static final int LOGICAL_HEIGHT = 960;

    // Position relative to 1920x1080 GameCanvas
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
    // CLOSE BUTTON
    // =========================================================

    private static final int CLOSE_X = 1410;
    private static final int CLOSE_Y = 25;

    private static final int CLOSE_SIZE = 40;

    // =========================================================
    // ITEM LIST
    // =========================================================

    private static final int ENTRY_X = 17;
    private static final int ENTRY_Y = 100;

    private static final int ENTRY_WIDTH = 1427;
    private static final int ENTRY_HEIGHT = 58;

    private static final int SCROLL_UNIT = 30;

    /*
     * Requirement:
     *
     * "Jarak vertikal antar-ItemEntry: 70px"
     *
     * Entry height = 58
     * Vertical distance = 70
     *
     * Therefore the actual gap is:
     *
     * 70 - 58 = 12px
     */
    private static final int ENTRY_GAP = 12;

    private static final Color BACKGROUND =
        new Color(0, 0, 0, 204);

    private final GameCanvas gameCanvas;
    private final GameController gameController;
    private final RuntimeData data;
    private final ItemController itemController;

    private final JPanel root;
    private JPanel itemListPanel;
    private JScrollPane scrollPane;

    private final Runnable onClose;

    public InventoryDialog(
        Window owner,
        GameCanvas gameCanvas,
        GameController gameController,
        Runnable onClose
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

        this.gameCanvas = gameCanvas;
        this.gameController = gameController;
        this.onClose = onClose;

        this.data =
            DataController.getInstance().getRuntimeData();

        this.itemController =
            gameController.getItemController();

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
        initializeItemList();

        /*
         * GameDialog handles:
         *
         * logical 1475x960
         * ->
         * scaled physical dialog size
         */
        applyLogicalSize();

        /*
         * We deliberately DON'T use centerOnGameCanvas()
         * because InventoryDialog has an explicit logical
         * coordinate:
         *
         * X = 250
         * Y = 120
         */
        positionDialog();

        refreshInventory();
    }

    // =========================================================
    // TITLE
    // =========================================================

    private void initializeTitle() {

        JLabel title =
            new JLabel("Inventory");

        title.setFont(
            new Font(
                Font.SANS_SERIF,
                Font.BOLD,
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
    // ITEM LIST
    // =========================================================

    private void initializeItemList() {

        itemListPanel =
            new JPanel();

        itemListPanel.setLayout(
            new BoxLayout(
                itemListPanel,
                BoxLayout.Y_AXIS
            )
        );

        itemListPanel.setOpaque(false);

        scrollPane =
            new JScrollPane(
                itemListPanel
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
    // INVENTORY
    // =========================================================

    private void refreshInventory() {

        itemListPanel.removeAll();

        Set<ItemList> inventory =
            itemController.getInventory();

        if (inventory != null) {

            for (ItemList item : inventory) {
                addItem(item);
            }
        }

        itemListPanel.revalidate();
        itemListPanel.repaint();
    }

    // =========================================================
    // ITEM ENTRY
    // =========================================================

    private void addItem(ItemList item) {

        if (item == null) {
            return;
        }

        ItemEntry entry =
            new ItemEntry(
                item,
                getScale(),
                () -> handleItemAction(item)
            );

        Dimension entrySize =
            new Dimension(
                scale(ENTRY_WIDTH),
                scale(ENTRY_HEIGHT)
            );

        entry.setPreferredSize(entrySize);
        entry.setMinimumSize(entrySize);
        entry.setMaximumSize(entrySize);

        entry.setAlignmentX(Component.LEFT_ALIGNMENT);

        itemListPanel.add(entry);

        itemListPanel.add(
            Box.createVerticalStrut(
                scale(ENTRY_GAP)
            )
        );
    }



    // =========================================================
    // ITEM ACTION
    // =========================================================

    private void handleItemAction(ItemList item) {
        if (item == null) {
            return;
        }

        if (item.getItemType() == ItemType.CONSUMABLE) {

            gameController
                .getItemController()
                .use(item);

            refreshInventory();

        } else if (item.getItemType() == ItemType.GIFT) {

            gameController
                .getItemController()
                .give(item);

            dispose();

            if (onClose != null) {
                onClose.run();
            }
        }
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
        gameController.setGameState(GameState.IDLE);
        super.dispose();
    }
}