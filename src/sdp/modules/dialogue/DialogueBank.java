package sdp.modules.dialogue;

import sdp.modules.assets.Asset;
import sdp.modules.audio.AudioAPI;
import sdp.persistence.DataController;
import sdp.persistence.runtime.data.SessionData;
import sdp.content.prinzessins.Prinzessin;

public abstract class DialogueBank implements AudioAPI {

    protected final SessionData data;
    protected final Prinzessin prinzessin;

    protected final String you; // You
    protected final String prinzessinName; // Prinzessin
    protected final String narrator = ""; // Narrator or thought

    public DialogueBank() {
        this.data = DataController.getInstance().getSessionData();
        this.prinzessin = data.getPrinzessinID();
        this.you = data.getPlayerName();
        this.prinzessinName = prinzessin.getNickname();
    }

    // =========================================================
    // Generic Dialogue Builders
    // =========================================================

    protected Dialogue d(String speaker, String line) {
        return new Dialogue(speaker, line, null, null);
    }

    protected Dialogue d(String speaker, String line, Asset sprite) {
        return new Dialogue(speaker, line, sprite, null);
    }

    protected Dialogue d(String speaker, String line, Asset sprite, Runnable action) {
        return new Dialogue(speaker, line, sprite, action);
    }

    protected Dialogue d(String speaker, String line, Runnable action) {
        return new Dialogue(speaker, line, null, action);
    }

    // =========================================================
    // Player Shortcuts: y(...)
    // =========================================================

    protected Dialogue y(String line) {
        return d(you, line);
    }

    protected Dialogue y(String line, Asset sprite) {
        return d(you, line, sprite);
    }

    protected Dialogue y(String line, Runnable action) {
        return d(you, line, action);
    }

    protected Dialogue y(String line, Asset sprite, Runnable action) {
        return d(you, line, sprite, action);
    }

    // =========================================================
    // Prinzessin Shortcuts: p(...)
    // =========================================================

    protected Dialogue p(String line) {
        return d(prinzessinName, line);
    }

    protected Dialogue p(String line, Asset sprite) {
        return d(prinzessinName, line, sprite);
    }

    protected Dialogue p(String line, Runnable action) {
        return d(prinzessinName, line, action);
    }

    protected Dialogue p(String line, Asset sprite, Runnable action) {
        return d(prinzessinName, line, sprite, action);
    }

    // =========================================================
    // Narrator Shortcuts: n(...)
    // =========================================================

    protected Dialogue n(String line) {
        return d(narrator, line);
    }

    protected Dialogue n(String line, Asset sprite) {
        return d(narrator, line, sprite);
    }

    protected Dialogue n(String line, Runnable action) {
        return d(narrator, line, action);
    }

    protected Dialogue n(String line, Asset sprite, Runnable action) {
        return d(narrator, line, sprite, action);
    }
}