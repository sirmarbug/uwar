package pl.bugajsky;

/**
 * Created by mariuszbugajski on 02.05.2017.
 */
public class Statystyki {
    private int poziom;
    private int wynik;

    public Statystyki() {
        this.poziom = 1;
        this.wynik = 0;
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
}
