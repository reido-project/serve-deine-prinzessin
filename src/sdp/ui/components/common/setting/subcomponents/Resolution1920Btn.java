package sdp.ui.components.common.setting.subcomponents;

import sdp.AppController;
import sdp.ui.GameResolution;

public class Resolution1920Btn extends SettingOptionBtn {

    private final AppController appController;

    public Resolution1920Btn(AppController appController, double scale) {
        super("1920 × 1080", scale);

        this.appController = appController;

        addActionListener(e -> {
            appController.changeResolution(GameResolution.R1920x1080);
        });
    }
}