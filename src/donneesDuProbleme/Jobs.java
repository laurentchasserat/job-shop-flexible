package donneesDuProbleme;

import java.util.ArrayList;

public class Jobs {

    private ArrayList<Activite> activites;

    public Jobs(ArrayList<Activite> mesActivites) {
        this.activites = mesActivites;
    }

    public Activite getActiviteNumero(int index) {
        return activites.get(index);
    }

}
