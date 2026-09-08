package sdp.ui.components.game;

import sdp.persistence.RuntimeData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatusPanel extends JPanel {

    private static final Color BACKGROUND =
        new Color(0, 0, 0, 128);

    private static final int CORNER_RADIUS = 10;

    private final RuntimeData data;

    private final JLabel insanityValue;
    private final JLabel hungerValue;
    private final JLabel affectionValue;

    public StatusPanel(RuntimeData data) {
        this.data = data;

        setOpaque(false);

        setLayout(
            new GridLayout(
                3,
                1
            )
        );

        insanityValue =
            createValueLabel();

        hungerValue =
            createValueLabel();

        affectionValue =
            createValueLabel();

        add(
            createRow(
                "Insanity",
                insanityValue
            )
        );

        add(
            createRow(
                "Hunger Bar",
                hungerValue
            )
        );

        add(
            createRow(
                "Affection",
                affectionValue
            )
        );

        refresh();
    }

    private JPanel createRow(
        String name,
        JLabel value
    ) {
        JPanel row = new JPanel(
            new BorderLayout()
        );

        row.setOpaque(false);

        JLabel nameLabel =
            new JLabel(name);

        nameLabel.setFont(
            new Font(
                Font.SANS_SERIF,
                Font.PLAIN,
                24
            )
        );

        nameLabel.setForeground(
            Color.WHITE
        );

        nameLabel.setBorder(
            new EmptyBorder(
                0,
                10,
                0,
                0
            )
        );

        row.add(
            nameLabel,
            BorderLayout.WEST
        );

        row.add(
            value,
            BorderLayout.EAST
        );

        return row;
    }

    private JLabel createValueLabel() {
        JLabel label = new JLabel();

        label.setFont(
            new Font(
                Font.SANS_SERIF,
                Font.PLAIN,
                24
            )
        );

        label.setForeground(
            Color.WHITE
        );

        label.setHorizontalAlignment(
            SwingConstants.RIGHT
        );

        label.setVerticalAlignment(
            SwingConstants.CENTER
        );

        label.setBorder(
            new EmptyBorder(
                0,
                0,
                0,
                10
            )
        );

        return label;
    }

    public void refresh() {
        insanityValue.setText(
            data.getPlayerInsanity() + "%"
        );

        hungerValue.setText(
            data.getPrinzessinHunger() + "%"
        );

        affectionValue.setText(
            data.getPrinzessinAffection() + "%"
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