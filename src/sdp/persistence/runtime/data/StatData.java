package sdp.persistence.runtime.data;

public class StatData {
    private int insanity = 0;
    private int affection = 0;
    private int hunger = 0;
    private long money = 0;

    public int getInsanity() {
        return insanity;
    }

    public void setInsanity(int insanity) {
        this.insanity = insanity;
    }

    public int getAffection() {
        return affection;
    }

    public void setAffection(int affection) {
        this.affection = affection;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }
}
