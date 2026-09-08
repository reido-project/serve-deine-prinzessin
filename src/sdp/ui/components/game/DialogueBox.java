package sdp.ui.components.game;

import sdp.dialogues.Dialogue;
import sdp.dialogues.DialogueController;
import sdp.ui.util.GraphicsUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogueBox extends JPanel {

    private static final int CORNER_RADIUS = 15;

    // --------------------------------------------------
    // TYPOGRAPHY
    // --------------------------------------------------

    private static final int SPEAKER_FONT_SIZE = 40;
    private static final int LINE_FONT_SIZE = 30;

    private static final int SPEAKER_X = 30;
    private static final int SPEAKER_Y = 15;

    private static final int LINE_X = 30;
    private static final int LINE_Y = 78;

    // --------------------------------------------------
    // TYPEWRITER
    // --------------------------------------------------

    private static final int TYPEWRITER_DELAY = 16;
    private static final int TYPEWRITER_CPS = 55;

    private long typewriterStartTime;

    private final DialogueController dialogueController;
    private final Runnable onAdvance;

    private final JLabel speakerLabel;
    private final JTextArea dialogueLabel; // UBAH KE JTEXTAREA

    private final Timer typewriterTimer;

    private Dialogue typingDialogue;
    private String fullLine;
    private int visibleCharacters;

    private boolean typing;

    public DialogueBox(
        DialogueController dialogueController,
        Runnable onAdvance
    ) {
        this.dialogueController = dialogueController;
        this.onAdvance = onAdvance;

        setLayout(null);
        setOpaque(false);

        speakerLabel = new JLabel();
        dialogueLabel = new JTextArea(); // UBAH KE JTEXTAREA

        typewriterTimer = new Timer(
            TYPEWRITER_DELAY,
            this::typeNextCharacter
        );

        initialize();
        initializeMouseListener();
        initializeKeyBindings();
    }

    private void initialize() {
        // --------------------------------------------------
        // SPEAKER
        // --------------------------------------------------

        speakerLabel.setFont(
            new Font(
                Font.SANS_SERIF,
                Font.BOLD,
                SPEAKER_FONT_SIZE
            )
        );

        speakerLabel.setForeground(Color.WHITE);

        speakerLabel.setBounds(
            SPEAKER_X,
            SPEAKER_Y,
            1000,
            55
        );

        // --------------------------------------------------
        // DIALOGUE LINE (JTextArea Optimization)
        // --------------------------------------------------

        dialogueLabel.setFont(
            new Font(
                Font.SANS_SERIF,
                Font.PLAIN,
                LINE_FONT_SIZE
            )
        );

        dialogueLabel.setForeground(Color.WHITE);

        // Setting wajib JTextArea agar bertindak seperti Label transparan dengan Text-Wrap
        dialogueLabel.setOpaque(false);
        dialogueLabel.setEditable(false);
        dialogueLabel.setFocusable(false);
        dialogueLabel.setLineWrap(true);
        dialogueLabel.setWrapStyleWord(true);

        dialogueLabel.setBounds(
            LINE_X,
            LINE_Y,
            1590,
            190
        );

        add(speakerLabel);
        add(dialogueLabel);
    }

    // --------------------------------------------------
    // INPUT
    // --------------------------------------------------

    private void initializeMouseListener() {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleAdvanceInput();
            }
        };

        addMouseListener(listener);
        speakerLabel.addMouseListener(listener);
        dialogueLabel.addMouseListener(listener);
    }

    private void initializeKeyBindings() {
        InputMap inputMap =
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        ActionMap actionMap =
            getActionMap();

        inputMap.put(
            KeyStroke.getKeyStroke("ENTER"),
            "advanceDialogue"
        );

        actionMap.put(
            "advanceDialogue",
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleAdvanceInput();
                }
            }
        );
    }

    private void handleAdvanceInput() {
        if (typing) {
            skipTypewriter();
            return;
        }

        onAdvance.run();
    }

    // --------------------------------------------------
    // DIALOGUE
    // --------------------------------------------------

    public void refresh() {
        Dialogue dialogue =
            dialogueController.getCurrentDialogue();

        if (dialogue == null) {
            stopTypewriter();

            typingDialogue = null;
            fullLine = null;
            visibleCharacters = 0;

            setVisible(false);
            return;
        }

        setVisible(true);

        if (dialogue == typingDialogue) {
            return;
        }

        startDialogue(dialogue);
    }

    private void startDialogue(Dialogue dialogue) {
        stopTypewriter();

        typingDialogue = dialogue;
        fullLine = dialogue.line();
        visibleCharacters = 0;
        typing = true;

        speakerLabel.setText(dialogue.speaker());
        dialogueLabel.setText("");

        if (dialogue.onTrigger() != null) {
            dialogue.onTrigger().run();
        }

        typewriterStartTime = System.nanoTime();

        typewriterTimer.start();
    }

    // --------------------------------------------------
    // TYPEWRITER
    // --------------------------------------------------

    private void typeNextCharacter(ActionEvent event) {
        if (!typing || fullLine == null) {
            stopTypewriter();
            return;
        }

        long elapsedNanos =
            System.nanoTime() - typewriterStartTime;

        double elapsedSeconds =
            elapsedNanos / 1_000_000_000.0;

        int targetCharacters =
            (int) (elapsedSeconds * TYPEWRITER_CPS);

        targetCharacters = Math.min(
            targetCharacters,
            fullLine.length()
        );

        if (targetCharacters <= visibleCharacters) {
            return;
        }

        visibleCharacters = targetCharacters;

        updateVisibleText();

        if (visibleCharacters >= fullLine.length()) {
            finishTypewriter();
        }
    }

    private void updateVisibleText() {
        // Cukup substring murni, TANPA tag <html></html>
        String visibleText =
            fullLine.substring(
                0,
                visibleCharacters
            );

        dialogueLabel.setText(visibleText);
        // Hapus repaint() berlebihan di sini
    }

    private void skipTypewriter() {
        if (!typing) {
            return;
        }

        visibleCharacters = fullLine.length();

        updateVisibleText();

        finishTypewriter();
    }

    private void finishTypewriter() {
        typing = false;

        typewriterTimer.stop();

        if (fullLine != null) {
            dialogueLabel.setText(fullLine);
        }
    }

    private void stopTypewriter() {
        typewriterTimer.stop();
        typing = false;
    }

    // --------------------------------------------------
    // PAINT
    // --------------------------------------------------

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 =
            GraphicsUtil.createAntialiased(g);

        g2.setColor(
            new Color(0, 0, 0, 210)
        );

        g2.fillRoundRect(
            0,
            0,
            getWidth(),
            getHeight(),
            CORNER_RADIUS,
            CORNER_RADIUS
        );

        g2.dispose();

        super.paintComponent(g);
    }
}