package outils;

public class DatesDebutFin {
    private Integer debut;
    private Integer fin;

    public DatesDebutFin(Integer d, Integer f) {
        this.debut = d;
        this.fin = f;
    }

    public Integer getDebut() {
        return debut;
    }

    public Integer getFin() {
        return fin;
    }

    public void setDebut(Integer debut) {
        this.debut = debut;
    }

    public void setFin(Integer fin) {
        this.fin = fin;
    }
}
