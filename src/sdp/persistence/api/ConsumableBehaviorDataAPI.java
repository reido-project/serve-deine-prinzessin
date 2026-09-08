package sdp.persistence.api;

import sdp.persistence.DataController;

public interface ConsumableBehaviorDataAPI {
    default long getMoney(){
        return DataController.getInstance().getStatData().getMoney();
    }

    default void increaseMoney(long money){
        DataController.getInstance().getStatData().setMoney(getMoney() + money);
    }
}