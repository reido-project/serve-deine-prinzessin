package sdp.ui.components.common.setting.subcomponents;

import sdp.AppController;

public class FullscreenBtn extends SettingOptionBtn {

    private final AppController appController;

    public FullscreenBtn(AppController appController, double scale) {
        super("Fullscreen", scale);

        this.appController = appController;

        addActionListener(e -> {
            appController.toggleFullscreen();
        });
    }
}