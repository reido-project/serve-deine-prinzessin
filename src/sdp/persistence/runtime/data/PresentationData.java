package sdp.persistence.runtime.data;

import sdp.modules.assets.Asset;

public class PresentationData {
    private Asset currentMusic = null;
    private Asset currentVoice = null;
    private Asset currentSprite = null;

    public Asset getCurrentMusic() {
        return currentMusic;
    }

    public void setCurrentMusic(Asset currentMusic) {
        this.currentMusic = currentMusic;
    }

    public Asset getCurrentVoice() {
        return currentVoice;
    }

    public void setCurrentVoice(Asset currentVoice) {
        this.currentVoice = currentVoice;
    }

    public Asset getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(Asset currentSprite) {
        this.currentSprite = currentSprite;
    }
}
