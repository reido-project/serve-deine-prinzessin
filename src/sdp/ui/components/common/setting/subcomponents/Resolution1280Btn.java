package sdp.ui.components.common.setting.subcomponents;

import sdp.AppController;
import sdp.ui.GameResolution;

public class Resolution1280Btn extends SettingOptionBtn {

    private final AppController appController;

    public Resolution1280Btn(AppController appController, double scale) {
        super("1280 × 720", scale);

        this.appController = appController;

        addActionListener(e -> {
            appController.changeResolution(GameResolution.R1280x720);
        });
    }
}