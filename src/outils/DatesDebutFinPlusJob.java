package outils;

public class DatesDebutFinPlusJob extends DatesDebutFin {

    // Seulement quand utilisé avec la classe Gant (très très sale)
    private Integer job;

    public DatesDebutFinPlusJob(Integer d, Integer f, Integer j) {
        super(d,f);
        this.job = j;
    }

    public Integer getJob() {
        return job;
    }

    public void setJob(Integer job) {
        this.job = job;
    }

    @Override
    public int compareTo(outils.DatesDebutFin datesDebutFin) {
        int resultat = 0;
        if (this.getDebut()<datesDebutFin.getDebut()) resultat = -1;
        return resultat;
    }
}
