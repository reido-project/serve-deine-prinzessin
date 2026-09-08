package sdp.persistence.api;

import sdp.persistence.DataController;

public interface StatDataAPI {
    default int getInsanity(){
        return DataController.getInstance().getStatData().getInsanity();
    }

    default void changeInsanity(int insanity){
        DataController.getInstance().getStatData().setInsanity(getInsanity() + insanity);
    }

    default int getAffection(){
        return DataController.getInstance().getStatData().getAffection();
    }

    default void changeAffection(int affection){
        DataController.getInstance().getStatData().setAffection(getAffection() + affection);
    }

    default int getHunger(){
        return DataController.getInstance().getStatData().getHunger();
    }

    default void changeHunger(int hunger){
        DataController.getInstance().getStatData().setHunger(getHunger() + hunger);
    }
}
