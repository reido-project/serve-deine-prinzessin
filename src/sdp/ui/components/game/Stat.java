package sdp.ui.components.game;

import sdp.persistence.DataController;
import sdp.persistence.RuntimeData;

import javax.swing.*;
import java.awt.*;

public class Stat extends JPanel {

    private static final int SCREEN_WIDTH = 1920;
    private static final int SCREEN_HEIGHT = 1080;

    private final RuntimeData data;

    private final MoneyPanel moneyPanel;
    private final StatusPanel statusPanel;

    public Stat() {
        this.data =
            DataController
                .getInstance()
                .getRuntimeData();

        setOpaque(false);
        setLayout(null);

        moneyPanel = new MoneyPanel(data);
        statusPanel = new StatusPanel(data);

        moneyPanel.setBounds(
            10,
            10,
            435,
            35
        );

        statusPanel.setBounds(
            1675,
            10,
            235,
            100
        );

        add(moneyPanel);
        add(statusPanel);

        setPreferredSize(
            new Dimension(
                SCREEN_WIDTH,
                SCREEN_HEIGHT
            )
        );

        refresh();
    }

    public void refresh() {
        moneyPanel.refresh();
        statusPanel.refresh();

        repaint();
    }
}