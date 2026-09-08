package sdp.ui.components.game;

import sdp.persistence.RuntimeData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MoneyPanel extends JPanel {

    private static final Color BACKGROUND =
        new Color(0, 0, 0, 128);

    private static final int CORNER_RADIUS = 10;

    private final RuntimeData data;

    private final JLabel moneyLabel;

    public MoneyPanel(RuntimeData data) {
        this.data = data;

        setOpaque(false);
        setLayout(new BorderLayout());

        moneyLabel = new JLabel();
        moneyLabel.setFont(
            new Font(
                Font.SANS_SERIF,
                Font.PLAIN,
                24
            )
        );

        moneyLabel.setForeground(Color.WHITE);

        moneyLabel.setHorizontalAlignment(
            SwingConstants.LEFT
        );

        moneyLabel.setVerticalAlignment(
            SwingConstants.CENTER
        );

        moneyLabel.setBorder(
            new EmptyBorder(
                0,
                12,
                0,
                12
            )
        );

        add(
            moneyLabel,
            BorderLayout.CENTER
        );

        refresh();
    }

    public void refresh() {
        moneyLabel.setText(
            "Money: ¥" + data.getPlayerMoney()
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 =
            (Graphics2D) g.create();

        try {
            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(BACKGROUND);

            g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                CORNER_RADIUS * 2,
                CORNER_RADIUS * 2
            );
        }
        finally {
            g2.dispose();
        }

        super.paintComponent(g);
    }
}