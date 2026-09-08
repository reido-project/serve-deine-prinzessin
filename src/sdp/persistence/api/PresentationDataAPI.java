package sdp.persistence.api;

import sdp.modules.assets.Asset;
import sdp.persistence.DataController;

public interface PresentationDataAPI {
    default Asset getMusic(){
        return DataController.getInstance().getPresentationData().getCurrentMusic();
    }

    default void setMusic(Asset music){
        DataController.getInstance().getPresentationData().setCurrentMusic(music);
    }

    default Asset getVoice(){
        return DataController.getInstance().getPresentationData().getCurrentVoice();
    }

    default void setVoice(Asset voice){
        DataController.getInstance().getPresentationData().setCurrentVoice(voice);
    }

    default Asset getSprite(){
        return DataController.getInstance().getPresentationData().getCurrentSprite();
    }

    default void setSprite(Asset sprite){
        DataController.getInstance().getPresentationData().setCurrentSprite(sprite);
    }
}
