package algos;

import donneesDuProbleme.Probleme;
import donneesDuProbleme.Solution;
import tests.TestDonnees;

import java.util.ArrayList;

public class CalculSolutions {

    /*
    Avec l'heuristique gloutonne, on choisit touhours la machine qui a le temps le plus court sur l'activité
    en question, et on crée un vecteur OS "tournant" (ex : (1 2 3 1 2 3 1 2) )
    */
    public static Solution heuristiqueGloutonne (Probleme probleme) {

        ArrayList<ArrayList<Integer>> ma = new ArrayList<>();
        ma.add(new ArrayList<>());
        ma.add(new ArrayList<>());
        ma.add(new ArrayList<>());
        ma.get(0).add(1);
        ma.get(0).add(2);
        ma.get(0).add(1);
        ma.get(1).add(2);
        ma.get(1).add(1);
        ma.get(1).add(3);
        ma.get(2).add(3);
        ma.get(2).add(2);

        ArrayList<Integer> os = new ArrayList<>();
        os.add(1);
        os.add(2);
        os.add(3);
        os.add(1);
        os.add(2);
        os.add(3);
        os.add(1);
        os.add(2);

        Solution sol = new Solution(TestDonnees.exemple1(), ma, os);

        return sol;

    }
}
