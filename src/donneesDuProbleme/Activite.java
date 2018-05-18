package donneesDuProbleme;

import exceptions.MachineNotFoundException;

import java.util.ArrayList;
import java.util.Random;

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

    public Tuple getTupleAleatoire() {
        Random rng = new Random();
        rng.setSeed(System.nanoTime());
        Integer random = Math.abs(rng.nextInt());
        Integer indice = random%(durees.size());
        //System.out.println("Random : "+random+", Indice : "+indice);
        return durees.get(indice);
    }

}
