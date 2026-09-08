package sdp.ui.components.home;

import sdp.AppController;
import sdp.ui.GameCanvas;
import sdp.ui.components.home.subcomponents.NameInput;
import sdp.ui.components.home.subcomponents.OkayBtn;
import sdp.ui.dialog.GameDialog;
import sdp.ui.screens.ScreenController;

import javax.swing.*;
import java.awt.*;

public class NameInputDialog extends GameDialog {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 130;

    private static final int TITLE_X = 20;
    private static final int TITLE_Y = 15;

    private static final int INPUT_X = 20;
    private static final int INPUT_Y = 60;

    private static final int OKAY_X = 495;
    private static final int OKAY_Y = 60;

    private final AppController appController;
    private final ScreenController screenController;

    private NameInput nameInput;

    public NameInputDialog(
        Window owner,
        GameCanvas gameCanvas,
        AppController appController,
        ScreenController screenController
    ) {
        super(owner, gameCanvas);

        this.appController = appController;
        this.screenController = screenController;

        initialize();

        applyLogicalSize();
    }

    private void initialize() {
        JPanel content = new JPanel(null);

        content.setBackground(Color.BLACK);

        setContentPane(content);

        initializeTitle(content);
        initializeInput(content);
        initializeOkayButton(content);
    }

    private void initializeTitle(JPanel content) {
        JLabel title = new JLabel("Enter Player Name");

        title.setForeground(Color.WHITE);

        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, scale(30)));

        title.setBounds(
            scale(TITLE_X),
            scale(TITLE_Y),
            scale(400),
            scale(40)
        );

        content.add(title);
    }

    private void initializeInput(JPanel content) {
        nameInput = new NameInput(getScale());

        nameInput.setBounds(
            scale(INPUT_X),
            scale(INPUT_Y),
            scale(465),
            scale(45)
        );

        content.add(nameInput);
    }

    private void initializeOkayButton(JPanel content) {
        OkayBtn okayBtn = new OkayBtn(
            getScale(),
            appController,
            screenController,
            nameInput
        );

        okayBtn.setBounds(
            scale(OKAY_X),
            scale(OKAY_Y),
            scale(90),
            scale(45)
        );

        content.add(okayBtn);
    }

    @Override
    protected int getLogicalWidth() {
        return WIDTH;
    }

    @Override
    protected int getLogicalHeight() {
        return HEIGHT;
    }
}