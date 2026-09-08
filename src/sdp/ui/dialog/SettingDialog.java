package sdp.ui.dialog;

import sdp.GameController;
import sdp.ui.GameFrame;
import sdp.ui.GameResolution;
import sdp.ui.components.button.Button;
import sdp.ui.components.button.ButtonOption;
import sdp.ui.screens.ScreenController;

import javax.swing.*;
import java.awt.*;

public class SettingDialog extends GameDialog {
    public SettingDialog(Window owner, GameController controller, GameFrame frame, ScreenController screens) {
        this(owner, controller, frame, screens, true);
    }

    private void updateDisplayButtonState(Button[] displayButtons, GameFrame frame) {
        for (Button button : displayButtons) {
            String text = button.getText();
            boolean selected = false;
            if ("Fullscreen".equals(text)) {
                selected = frame.isFullscreen();
            } else if ("2560 x 1440".equals(text)) {
                selected = !frame.isFullscreen() && frame.getResolution() == GameResolution.QHD;
            } else if ("1920 x 1080".equals(text)) {
                selected = !frame.isFullscreen() && frame.getResolution() == GameResolution.FULL_HD;
            } else if ("1280 x 720".equals(text)) {
                selected = !frame.isFullscreen() && frame.getResolution() == GameResolution.HD;
            }
            button.setSelectedState(selected);
        }
    }

    public SettingDialog(Window owner, GameController controller, GameFrame frame, ScreenController screens, boolean showGameButtons) {
        super(owner, "Settings");
        JPanel content = new JPanel(null);
        content.setBackground(Color.decode("#454545"));

        JLabel title = new JLabel("Setting", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        title.setBounds(0, 25, 865, 60);
        content.add(title);

        Button close = createCloseButton();
        close.setBounds(800, 25, 45, 45);
        content.add(close);

        JLabel display = new JLabel("Display");
        display.setForeground(Color.WHITE);
        display.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        display.setBounds(58, 130, 200, 40);
        content.add(display);

        int buttonGap = 15;

        final Button[] displayButtons = new Button[4];
        displayButtons[0] = new Button("2560 x 1440", ButtonOption.ResolutionOption, () -> {
            frame.setResolution(GameResolution.QHD);
            updateDisplayButtonState(displayButtons, frame);
        });
        displayButtons[1] = new Button("1920 x 1080", ButtonOption.ResolutionOption, () -> {
            frame.setResolution(GameResolution.FULL_HD);
            updateDisplayButtonState(displayButtons, frame);
        });
        displayButtons[2] = new Button("1280 x 720", ButtonOption.ResolutionOption, () -> {
            frame.setResolution(GameResolution.HD);
            updateDisplayButtonState(displayButtons, frame);
        });
        displayButtons[3] = new Button("Fullscreen", ButtonOption.ResolutionOption, () -> {
            frame.toggleFullscreen();
            updateDisplayButtonState(displayButtons, frame);
        });
        updateDisplayButtonState(displayButtons, frame);
        for (int index = 0; index < displayButtons.length; index++) {
            Button btn = displayButtons[index];
            int w = btn.getAppearance().getWidth();
            int h = btn.getAppearance().getHeight();
            int x = 58 + index * (w + buttonGap);
            btn.setBounds(x, 190, w, h);
            content.add(btn);
        }

        if (showGameButtons) {
            Button[] gameButtons = {
                new Button("Save", ButtonOption.GameOption, controller.getSessionController()::save),
                new Button("Load", ButtonOption.GameOption, () -> { dispose(); controller.getSessionController().load(); screens.showGame(); }),
                new Button("Back to Title", ButtonOption.GameOption, () -> { dispose(); screens.showHome(); }),
                new Button("Quit Game", ButtonOption.GameOption, () -> System.exit(0))
            };
            for (int index = 0; index < gameButtons.length; index++) {
                Button btn = gameButtons[index];
                int w = btn.getAppearance().getWidth();
                int h = btn.getAppearance().getHeight();
                int x = 58 + index * (w + buttonGap);
                btn.setBounds(x, 690, w, h);
                content.add(btn);
            }
            finishLayout(content, 865, 770);
            return;
        }

        finishLayout(content, 865, 770);
    }
}