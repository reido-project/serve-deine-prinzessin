package sdp.ui.components.common.setting.subcomponents;

import java.awt.*;

public class SaveBtn extends SettingOptionBtn {

    public SaveBtn(double scale) {
        super("Save", scale);

        addActionListener(e -> {
            // TODO: Save game
        });
    }

    @Override
    protected Color getButtonColor() {
        return Color.CYAN;
    }
}