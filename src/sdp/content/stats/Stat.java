package sdp.content.stats;

import sdp.persistence.DataController;
import sdp.persistence.runtime.data.StatData;

public class Stat {
    private final int insanity;
    private final int affection;
    private final int hunger;
    private final long money;

    public Stat(){
        StatData data = DataController.getInstance().getStatData();

        this.insanity = data.getInsanity();
        this.affection = data.getAffection();
        this.hunger = data.getHunger();
        this.money = data.getMoney();
    }

    public int getInsanity() {
        return insanity;
    }

    public int getAffection() {
        return affection;
    }

    public int getHunger() {
        return hunger;
    }

    public long getMoney() {
        return money;
    }
}
