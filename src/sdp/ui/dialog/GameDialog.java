package sdp.ui.dialog;

import sdp.ui.components.button.Button;
import sdp.ui.components.button.ButtonOption;
import sdp.ui.UiScaler;

import javax.swing.*;
import java.awt.*;

public abstract class GameDialog extends JDialog {
    protected GameDialog(Window owner, String title) {
        super(owner, title, ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
    }

    protected final Button createCloseButton() {
        return new Button(ButtonOption.CloseBtn, this::dispose);
    }

    protected final double getScale() {
        if (getOwner() instanceof sdp.ui.GameFrame frame) {
            return frame.getViewport().getCanvas().getScale();
        }
        return 1.0;
    }

    protected final int scale(int value) {
        return (int) Math.round(value * getScale());
    }

    protected final void finishScaledLayout(JComponent content, int logicalWidth, int logicalHeight, int logicalX, int logicalY) {
        setContentPane(content);
        content.setPreferredSize(new Dimension(scale(logicalWidth), scale(logicalHeight)));
        pack();

        if (getOwner() instanceof sdp.ui.GameFrame frame && frame.getViewport().getCanvas().isShowing()) {
            Point canvasLocation = frame.getViewport().getCanvas().getLocationOnScreen();
            setLocation(canvasLocation.x + scale(logicalX), canvasLocation.y + scale(logicalY));
        } else {
            setLocationRelativeTo(getOwner());
        }
    }

    protected final void finishLayout(JComponent content) {
        finishLayout(content, 900, 650);
    }

    protected final void finishLayout(JComponent content, int logicalWidth, int logicalHeight) {
        content.setBackground(new Color(70, 70, 70));
        double scale = 1.0;
        scale = getScale();
        UiScaler.scaleDialogTree(content, scale);
        content.setPreferredSize(new Dimension(
            (int) Math.round(logicalWidth * scale),
            (int) Math.round(logicalHeight * scale)
        ));
        setContentPane(content);
        pack();
        setLocationRelativeTo(getOwner());
    }

    protected final JPanel createHeader(String title) {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        header.add(label, BorderLayout.CENTER);
        header.add(createCloseButton(), BorderLayout.EAST);
        return header;
    }
}