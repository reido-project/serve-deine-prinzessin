package sdp.ui.components.common.setting.subcomponents;

import java.awt.*;

public class LoadBtn extends SettingOptionBtn {

    public LoadBtn(double scale) {
        super("Load", scale);

        addActionListener(e -> {
            // TODO: Load game
        });
    }

    @Override
    protected Color getButtonColor() {
        return Color.CYAN;
    }
}