package pl.bugajsky;

/**
 * Created by mariuszbugajski on 02.05.2017.
 */
public class Tura {
    private boolean typ;
    private boolean boss;
    private float time;
    private int makspotworow;

    public Tura(boolean typ, boolean boss, float time) {
        this.typ = typ;
        this.boss = boss;
        this.time = time;
        makspotworow = 10;
    }

    public boolean isTyp() {
        return typ;
    }

    public void setTyp(boolean typ) {
        this.typ = typ;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public int getMakspotworow() {
        return makspotworow;
    }

    public void setMakspotworow(int makspotworow) {
        this.makspotworow = makspotworow;
    }
}
