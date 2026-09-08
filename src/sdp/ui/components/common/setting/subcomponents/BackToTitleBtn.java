package sdp.ui.components.common.setting.subcomponents;

import sdp.AppController;
import sdp.ui.GameCanvas;

import java.awt.*;

public class BackToTitleBtn extends SettingOptionBtn {

    private final AppController appController;
    private final GameCanvas gameCanvas;

    public BackToTitleBtn(double scale, AppController appController, GameCanvas gameCanvas) {
        super("Back to Title", scale);

        this.appController = appController;
        this.gameCanvas = gameCanvas;

        setForeground(Color.BLACK);

        addActionListener(e -> {
            appController.getScreenController().showHome();
        });
    }

    @Override
    protected Color getButtonColor() {
        return Color.CYAN;
    }
}