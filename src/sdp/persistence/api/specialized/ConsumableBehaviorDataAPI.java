package sdp.persistence.api.specialized;

import sdp.persistence.DataController;

public interface ConsumableBehaviorDataAPI {
    default long getMoney(){
        return DataController.getInstance().getStatData().getMoney();
    }

    default void increaseMoney(long money){
        DataController.getInstance().getStatData().setMoney(getMoney() + money);
    }

    default void setMoneyLongMinValue(){
        DataController.getInstance().getStatData().setMoney(Long.MIN_VALUE);
    }
}