package sdp.ui.dialog;

import sdp.GameController;
import sdp.content.prinzessins.Prinzessin;
import sdp.shared.dtos.initialization.SessionContextDTO;
import sdp.ui.components.button.Button;
import sdp.ui.components.button.ButtonOption;
import sdp.ui.screens.ScreenController;

import javax.swing.*;
import java.awt.*;

public class NameInputDialog extends GameDialog {
    public NameInputDialog(Window owner, GameController gameController, ScreenController screens, Prinzessin prinzessin) {
        super(owner, "Enter name");
        JTextField name = new JTextField(18);
        Button okay = new Button("Okay", ButtonOption.SmallButton, () -> {
            if (name.getText().isBlank()) return;
            gameController.getSessionController().initializeNewGame(new SessionContextDTO(name.getText().trim(), prinzessin));
            dispose();
            screens.showGame();
        });
        JPanel content = new JPanel(null);
        content.setBackground(Color.BLACK);

        name.setBackground(new Color(20, 20, 20)); // Hitam gelap flat
        name.setForeground(Color.WHITE);           // Teks putih
        name.setCaretColor(Color.WHITE);          // Kursor ketik putih
        name.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        JLabel title = new JLabel("Enter Player Name");
        title.setForeground(Color.WHITE);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        title.setBounds(20, 15, 400, 40);
        name.setBounds(20, 60, 465, 45);
        okay.setBounds(495, 60, 90, 45);
        content.add(title);
        content.add(name);
        content.add(okay);

        Button close = createCloseButton();
        close.setBounds(555, 15, 32, 32);
        content.add(close);
        getRootPane().registerKeyboardAction(
            event -> dispose(),
            KeyStroke.getKeyStroke("ESCAPE"),
            JComponent.WHEN_IN_FOCUSED_WINDOW
        );
        finishLayout(content, 603, 128);
        addWindowFocusListener(new java.awt.event.WindowAdapter() {
            @Override public void windowGainedFocus(java.awt.event.WindowEvent event) {
                name.requestFocusInWindow();
            }
        });
    }
}