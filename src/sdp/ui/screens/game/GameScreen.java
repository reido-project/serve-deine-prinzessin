package sdp.ui.screens.game;

import sdp.GameController;
import sdp.content.stats.Stat;
import sdp.modules.dialogue.Dialogue;
import sdp.shared.dtos.dialogue.DialogueDisplayDTO;
import sdp.shared.dtos.presentation.PresentationRefreshDTO;
import sdp.ui.components.button.Button;
import sdp.ui.components.button.ButtonOption;
import sdp.ui.components.game.DialogueBox;
import sdp.ui.dialog.InventoryDialog;
import sdp.ui.dialog.SettingDialog;
import sdp.ui.dialog.TalkDialog;
import sdp.ui.screens.Screen;
import sdp.ui.screens.ScreenController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameScreen extends JPanel implements Screen {
    private final GameController gameController;
    private final ScreenController screens;
    private final ImagePanel sprite = new ImagePanel(true);
    private final JLabel money = new JLabel();
    private final JLabel stats = new JLabel();
    private final DialogueBox dialogueBox;
    private Button[] interactionButtons;
    private Dialogue[] dialogues = new Dialogue[0];
    private int dialogueIndex;
    private BufferedImage renderedBackground;

    public GameScreen(GameController gameController, ScreenController screens) {
        this.gameController = gameController;
        this.screens = screens;
        setLayout(null);

        sprite.setBounds(475, 0, 970, 1080);

        JPanel moneyPanel = new TransparentStatPanel();
        moneyPanel.setBounds(10, 10, 440, 45);
        moneyPanel.setLayout(new BorderLayout());
        money.setForeground(Color.WHITE);
        money.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        money.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
        moneyPanel.add(money, BorderLayout.CENTER);

        JPanel statsPanel = new TransparentStatPanel();
        statsPanel.setBounds(1640, 15, 260, 120);
        statsPanel.setLayout(new BorderLayout());
        stats.setForeground(Color.WHITE);
        stats.setHorizontalAlignment(SwingConstants.RIGHT);
        stats.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        stats.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
        statsPanel.add(stats, BorderLayout.CENTER);

        dialogueBox = new DialogueBox(this::advanceDialogue);
        dialogueBox.setBounds(245, 795, 1650, 285);

        add(sprite);
        add(moneyPanel);
        add(statsPanel);
        add(dialogueBox);
        add(createButtons(), 0);
        setComponentZOrder(dialogueBox, 0);

        refreshPresentation();
        refreshStats();
        showDialogues(gameController.getInteractionController().checkStory());
    }

    private JPanel createButtons() {
        JPanel panel = new JPanel(null);
        panel.setOpaque(false);
        panel.setBounds(20, 795, 210, 280);
        interactionButtons = new Button[] {
            new Button("Talk", ButtonOption.InteractionOption, () -> {
                if (dialogueBox.isDialogueRunning()) return;
                Window owner = SwingUtilities.getWindowAncestor(this);
                new TalkDialog(owner, gameController, this::interact).setVisible(true);
            }),
            new Button("Tease", ButtonOption.InteractionOption, () -> {
                if (dialogueBox.isDialogueRunning()) return;
                interact(gameController.getInteractionController().tease());
            }),
            new Button("Inventory", ButtonOption.InteractionOption, () -> {
                if (dialogueBox.isDialogueRunning()) return;
                Window owner = SwingUtilities.getWindowAncestor(this);
                new InventoryDialog(owner, gameController, this::interact).setVisible(true);
            }),
            new Button("Feed", ButtonOption.InteractionOption, () -> {
                if (dialogueBox.isDialogueRunning()) return;
                interact(gameController.getInteractionController().feed());
            }),
            new Button("Setting", ButtonOption.SettingBtn, () -> {
                if (dialogueBox.isDialogueRunning()) return;
                Window owner = SwingUtilities.getWindowAncestor(this);
                new SettingDialog(owner, gameController, (sdp.ui.GameFrame) owner, screens, true).setVisible(true);
            })
        };
        for (int index = 0; index < interactionButtons.length; index++) {
            Button btn = interactionButtons[index];
            int w = btn.getAppearance().getWidth();
            int h = btn.getAppearance().getHeight();
            btn.setBounds(0, index * 57, w, h);
            panel.add(btn);
        }
        updateInteractionButtonState();
        return panel;
    }

    private void updateInteractionButtonState() {
        boolean running = dialogueBox.isDialogueRunning();
        for (Button button : interactionButtons) {
            button.setEnabled(!running);
            button.setFocusable(!running);
        }
    }

    private void interact(Dialogue[] result) {
        refreshStats();
        showDialogues(result);
    }

    private void showDialogues(Dialogue[] result) {
        dialogues = result == null ? new Dialogue[0] : result;
        dialogueIndex = 0;
        if (dialogues.length == 0) {
            gameController.getSessionController().restoreDefaultSprite();
            dialogueBox.clearDialogue();
            updateInteractionButtonState();
            refreshPresentation();
            return;
        }
        displayCurrentDialogue();
    }

    private void displayCurrentDialogue() {
        DialogueDisplayDTO display = gameController.getDialogueRunner().run(dialogues[dialogueIndex]);
        dialogueBox.showDialogue(display);
        updateInteractionButtonState();
        refreshPresentation();
    }

    private void advanceDialogue() {
        if (dialogueIndex + 1 < dialogues.length) {
            dialogueIndex++;
            displayCurrentDialogue();
        } else {
            gameController.getSessionController().restoreDefaultSprite();
            dialogueBox.clearDialogue();
            updateInteractionButtonState();
            refreshPresentation();
        }
    }

    private void refreshPresentation() {
        PresentationRefreshDTO presentation = gameController.getSessionController().refreshPresentation();
        renderedBackground = presentation.background();
        BufferedImage renderedSprite = presentation.sprite();
        sprite.setImage(renderedSprite);
        revalidate();
        repaint();
    }

    private void refreshStats() {
        Stat stat = gameController.getSessionController().refreshStat();
        money.setText("Money: ¥ " + stat.getMoney());
        stats.setText("<html>Insanity: " + stat.getInsanity() + "%<br>Hunger Bar: " + stat.getHunger()
            + "%<br>Affection: " + stat.getAffection() + "%</html>");
    }

    @Override public JComponent getComponent() { return this; }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (renderedBackground != null) {
            graphics.drawImage(renderedBackground, 0, 0, getWidth(), getHeight(), null);
        }
    }

    private static final class TransparentStatPanel extends JPanel {
        private static final Color BACKGROUND = new Color(0, 0, 0, 128);
        private static final int CORNER_RADIUS = 10;

        private TransparentStatPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            try {
                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );
                g2.setColor(BACKGROUND);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), CORNER_RADIUS * 2, CORNER_RADIUS * 2);
            } finally {
                g2.dispose();
            }
            super.paintComponent(graphics);
        }
    }

    private static final class ImagePanel extends JPanel {
        private final boolean preserveAspectRatio;
        private BufferedImage image;

        private ImagePanel(boolean preserveAspectRatio) {
            this.preserveAspectRatio = preserveAspectRatio;
            setOpaque(false);
        }

        private void setImage(BufferedImage image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            if (image == null) return;

            int width = getWidth();
            int height = getHeight();
            if (!preserveAspectRatio) {
                graphics.drawImage(image, 0, 0, width, height, null);
                return;
            }

            double scale = Math.min(
                width / (double) image.getWidth(),
                height / (double) image.getHeight()
            );
            int imageWidth = (int) Math.round(image.getWidth() * scale);
            int imageHeight = (int) Math.round(image.getHeight() * scale);
            graphics.drawImage(image, (width - imageWidth) / 2, height - imageHeight, imageWidth, imageHeight, null);
        }
    }
}