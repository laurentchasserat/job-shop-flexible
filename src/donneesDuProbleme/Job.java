package donneesDuProbleme;

import java.util.ArrayList;

public class Job {

    private ArrayList<Activite> activites;

    public Job(ArrayList<Activite> mesActivites) {
        this.activites = mesActivites;
    }

    public Activite getActiviteNumero(int index) {
        return activites.get(index);
    }

    public ArrayList<Activite> getActivites() {
        return activites;
    }

}
