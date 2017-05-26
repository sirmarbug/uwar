package pl.bugajsky;

/**
 * Created by mariuszbugajski on 02.05.2017.
 */
public class Statystyki {
    private int poziom;
    private int wynik;
    private int wrogowie;

    public Statystyki() {
        poziom = 1;
        wynik = 0;
        wrogowie = 0;
    }

    public int getPoziom() {
        return poziom;
    }

    public void setPoziom(int poziom) {
        this.poziom = poziom;
    }

    public int getWynik() {
        return wynik;
    }

    public void setWynik(int wynik) {
        this.wynik = wynik;
    }

    public int getWrogowie() {
        return wrogowie;
    }

    public void setWrogowie(int wrogowie) {
        this.wrogowie = wrogowie;
    }
    
}
