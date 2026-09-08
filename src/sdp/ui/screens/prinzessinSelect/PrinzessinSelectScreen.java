package sdp.ui.screens.prinzessinSelect;

import sdp.Config;
import sdp.GameController;
import sdp.shared.dtos.initialization.PrinzessinOption;
import sdp.shared.utils.ImageUtil;
import sdp.ui.components.button.Button;
import sdp.ui.components.button.ButtonOption;
import sdp.ui.components.prinzessinSelect.CharacterName;
import sdp.ui.dialog.NameInputDialog;
import sdp.ui.screens.Screen;
import sdp.ui.screens.ScreenController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PrinzessinSelectScreen extends JPanel implements Screen {
    private final GameController gameController;
    private final ScreenController screens;
    private final BufferedImage background = ImageUtil.loadImage(Config.PRINZESSIN_SELECT_BACKGROUND);
    private final PrinzessinOption[] options;
    private int selectedIndex;
    private final JLabel name = new CharacterName();
    private final JLabel sprite = new JLabel() {
        @Override
        protected void paintComponent(Graphics graphics) {
            if (getIcon() instanceof ImageIcon imageIcon) {
                Image image = imageIcon.getImage();

                double imageScale = Math.min(
                    getWidth() / (double) image.getWidth(null),
                    getHeight() / (double) image.getHeight(null)
                );

                int width = (int) Math.round(image.getWidth(null) * imageScale);
                int height = (int) Math.round(image.getHeight(null) * imageScale);

                int x = (getWidth() - width) / 2;
                int y = getHeight() - height;

                graphics.drawImage(image, x, y, width, height, null);
            }
        }
    };

    public PrinzessinSelectScreen(GameController gameController, ScreenController screens) {
        this.gameController = gameController;
        this.screens = screens;
        options = gameController.getSessionController().getPrinzessinOptions();

        setLayout(null);

        name.setBounds(645, 260, 580, 80);

        sprite.setBounds(560, 350, 800, 608);

        add(sprite);

        JPanel controls = createControls();
        controls.setBounds(0, 960, 1920, 120);
        add(controls);

        add(name);

        setComponentZOrder(name, 0);
        setComponentZOrder(controls, 1);
        setComponentZOrder(sprite, 2);

        refreshSelection();
    }

    private JPanel createControls() {
        JPanel controls = new JPanel(null);
        controls.setOpaque(false);

        Button start = new Button("Start", ButtonOption.StartBtn, this::openNameDialog);
        Button change = new Button("Change", ButtonOption.LargeButton, this::changeSelection);
        Button back = new Button("Back", ButtonOption.SmallButton, screens::showHome);

        start.setBounds(537, 0, start.getAppearance().getWidth(), start.getAppearance().getHeight());
        change.setBounds(977, 0, change.getAppearance().getWidth(), change.getAppearance().getHeight());
        back.setBounds(27, 49, back.getAppearance().getWidth(), back.getAppearance().getHeight());

        controls.add(start);
        controls.add(change);
        controls.add(back);
        return controls;
    }

    private void changeSelection() {
        selectedIndex = (selectedIndex + 1) % options.length;
        refreshSelection();
    }

    private void refreshSelection() {
        PrinzessinOption option = options[selectedIndex];
        name.setText(option.name());
        sprite.setIcon(new ImageIcon(option.sprite()));
    }

    private void openNameDialog() {
        Window owner = SwingUtilities.getWindowAncestor(this);
        new NameInputDialog(owner, gameController, screens, options[selectedIndex].prinzessin()).setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }

    @Override
    public JComponent getComponent() { return this; }
}