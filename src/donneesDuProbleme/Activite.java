package donneesDuProbleme;

import exceptions.MachineNotFoundException;

import java.util.ArrayList;

public class Activite {

    private ArrayList<Tuple> durees;

    public Activite(ArrayList<Tuple> mesDurees) {
        this.durees = mesDurees;
    }

    public ArrayList<Tuple> getDurees() {
        return durees;
    }

    public Integer getDureeDeLaMachine(Integer machine) {
        boolean trouve = false;
        Integer solution = -1;

        for (Tuple t : durees) {
            if (t.getMachine() == machine) {
                trouve = true;
                solution = t.getDuree();
            }
        }

        if (trouve == false) {
            try {
                throw new MachineNotFoundException();
            } catch (Exception notFound) {
                notFound.printStackTrace();
            }
        }

        return solution;
    }

    public Tuple getTupleDeDureeMin() {
        Integer dureeMin = Integer.MAX_VALUE;
        Tuple resultat = new Tuple(-1,-1);
        for (Tuple t : durees) {
            if (t.getDuree()<dureeMin) {
                dureeMin = t.getDuree();
                resultat = t;
            }
        }
        return resultat;
    }

}
