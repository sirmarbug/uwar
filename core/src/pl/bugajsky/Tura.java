package pl.bugajsky;

/**
 * Created by mariuszbugajski on 02.05.2017.
 */
public class Tura {
    private boolean typ;
    private boolean boss;
    private float time;
    private float bossTime;
    private float koniecCzasuAtaku;
    private float koniecCzasuPrzerwy;
    private int makspotworow;
    private boolean bossdodany;

    public Tura(boolean typ, boolean boss, float time) {
        this.typ = typ;
        this.boss = boss;
        this.time = time;
        bossdodany = false;
        makspotworow = 10;
        bossTime = 0;
        koniecCzasuAtaku = 60;
        koniecCzasuPrzerwy = 10;
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

    public boolean isBossdodany() {
        return bossdodany;
    }

    public void setBossdodany(boolean bossdodany) {
        this.bossdodany = bossdodany;
    }

    public float getBossTime() {
        return bossTime;
    }

    public void setBossTime(float bossTime) {
        this.bossTime = bossTime;
    }

    public float getKoniecCzasuAtaku() {
        return koniecCzasuAtaku;
    }

    public void setKoniecCzasuAtaku(float koniecCzasuAtaku) {
        this.koniecCzasuAtaku = koniecCzasuAtaku;
    }

    public float getKoniecCzasuPrzerwy() {
        return koniecCzasuPrzerwy;
    }

    public void setKoniecCzasuPrzerwy(float koniecCzasuPrzerwy) {
        this.koniecCzasuPrzerwy = koniecCzasuPrzerwy;
    }

    public void changeTimeAtac(){
        koniecCzasuAtaku += 5;
    }

    public void changeTimeBreak(){
        koniecCzasuPrzerwy += 2;
    }
}
