package outils;

public class DatesDebutFin {
    private int debut;
    private int fin;

    public DatesDebutFin(int d, int f) {
        this.debut = d;
        this.fin = f;
    }

    public int getDebut() {
        return debut;
    }

    public int getFin() {
        return fin;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }
}
