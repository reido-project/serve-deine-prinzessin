package sdp.ui.components.common.setting.subcomponents;

import sdp.AppController;
import sdp.ui.GameResolution;

public class Resolution1600Btn extends SettingOptionBtn {

    private final AppController appController;

    public Resolution1600Btn(AppController appController, double scale) {
        super("1600 × 900", scale);

        this.appController = appController;

        addActionListener(e -> {
            appController.changeResolution(GameResolution.R1600x900);
        });
    }
}