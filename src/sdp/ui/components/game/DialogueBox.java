package sdp.ui.components.game;

import sdp.shared.dtos.dialogue.DialogueDisplayDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogueBox extends JPanel {
    private static final int TYPEWRITER_DELAY = 16;
    private static final int TYPEWRITER_CPS = 55;

    private final JLabel speaker = new JLabel();
    private final JTextArea line = new JTextArea();
    private final Timer typewriterTimer;

    private DialogueDisplayDTO currentDialogue;
    private String fullLine;
    private int visibleCharacters;
    private long typewriterStartTime;
    private boolean typing;

    private static final long ADVANCE_COOLDOWN_MS = 35;
    private long lastAdvanceTime = 0;

    public DialogueBox(Runnable onAdvance) {
        setLayout(null);
        setBorder(BorderFactory.createEmptyBorder(18, 24, 18, 24));
        setOpaque(false);

        speaker.setForeground(Color.WHITE);
        speaker.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));

        line.setForeground(Color.WHITE);
        line.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        line.setOpaque(false);
        line.setEditable(false);
        line.setFocusable(false);
        line.setLineWrap(true);
        line.setWrapStyleWord(true);

        speaker.setBounds(24, 18, 1600, 42);
        line.setBounds(24, 68, 1600, 190);

        add(speaker);
        add(line);

        typewriterTimer = new Timer(TYPEWRITER_DELAY, this::typeNextCharacter);

        // Keybindings & Mouse Listeners
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "advance");
        getActionMap().put("advance", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                handleAdvanceInput(onAdvance);
            }
        });

        MouseAdapter advanceListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                handleAdvanceInput(onAdvance);
            }
        };

        addMouseListener(advanceListener);
        speaker.addMouseListener(advanceListener);
        line.addMouseListener(advanceListener);

        setVisible(false);
    }

    public void showDialogue(DialogueDisplayDTO dialogue) {
        if (dialogue == null) {
            clearDialogue();
            return;
        }

        if (dialogue.equals(currentDialogue) && (typing || visibleCharacters >= fullLine.length())) {
            return;
        }

        stopTypewriter();

        currentDialogue = dialogue;
        speaker.setText(dialogue.speaker());
        fullLine = dialogue.line() == null ? "" : dialogue.line();
        visibleCharacters = 0;
        typing = true;

        line.setText("");
        typewriterStartTime = System.nanoTime();

        setVisible(true);
        typewriterTimer.start();
    }

    public void clearDialogue() {
        stopTypewriter();
        currentDialogue = null;
        fullLine = null;
        line.setText("");
        setVisible(false);
    }

    public boolean isDialogueRunning() {
        return typing || isVisible();
    }

    private void handleAdvanceInput(Runnable onAdvance) {
        long currentTime = System.currentTimeMillis();

        if (typing) {
            skipTypewriter();
            lastAdvanceTime = currentTime;
            return;
        }

        if (currentTime - lastAdvanceTime < ADVANCE_COOLDOWN_MS) {
            return;
        }

        lastAdvanceTime = currentTime;
        onAdvance.run();
    }

    private void typeNextCharacter(ActionEvent event) {
        if (!typing || fullLine == null) {
            stopTypewriter();
            return;
        }

        double elapsedSeconds = (System.nanoTime() - typewriterStartTime) / 1_000_000_000.0;
        int targetCharacters = Math.min((int) (elapsedSeconds * TYPEWRITER_CPS), fullLine.length());

        if (targetCharacters <= visibleCharacters) return;

        visibleCharacters = targetCharacters;
        line.setText(fullLine.substring(0, visibleCharacters));

        if (visibleCharacters >= fullLine.length()) {
            finishTypewriter();
        }
    }

    private void skipTypewriter() {
        if (!typing) return;

        visibleCharacters = fullLine.length();
        line.setText(fullLine);
        finishTypewriter();
    }

    private void finishTypewriter() {
        typing = false;
        typewriterTimer.stop();
        if (fullLine != null) {
            line.setText(fullLine);
        }
    }

    private void stopTypewriter() {
        typewriterTimer.stop();
        typing = false;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 200));
        graphics.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(graphics);
    }
}