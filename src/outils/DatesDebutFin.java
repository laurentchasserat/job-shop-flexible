package outils;

public class DatesDebutFin implements Comparable<DatesDebutFin>{
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

    @Override
    public int compareTo(DatesDebutFin datesDebutFin) {
        int resultat = 0;
        if (this.getDebut()<datesDebutFin.getDebut()) resultat = -1;
        return resultat;
    }
}
