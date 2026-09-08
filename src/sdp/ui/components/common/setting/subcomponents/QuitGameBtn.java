package sdp.ui.components.common.setting.subcomponents;

import java.awt.*;

public class QuitGameBtn extends SettingOptionBtn {

    public QuitGameBtn(double scale) {
        super("Quit Game", scale);

        addActionListener(e -> {
            System.exit(0);
        });
    }

    @Override
    protected Color getButtonColor() {
        return Color.CYAN;
    }
}