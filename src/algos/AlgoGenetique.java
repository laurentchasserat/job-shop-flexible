package algos;

import donneesDuProbleme.Probleme;
import donneesDuProbleme.Solution;

import java.util.ArrayList;
import java.util.Random;

public class AlgoGenetique {

    private Probleme probleme;

    private int nbIterations;
    private int poolSize;

    public AlgoGenetique(Probleme pb ) {
        this.probleme = pb;
        this.nbIterations = 0;
        this.poolSize = 1;
    }

    public Solution algoGenetiqueTournoiMutations(ArrayList<Solution> poolInitial) {
        ArrayList<Solution> resultat = poolInitial; //poolInitial est sensé arriver trié
        poolSize = poolInitial.size();

        // Génération d'enfants par mutations (les 50 meilleurs se reproduisent)
        ArrayList<Solution> enfants = new ArrayList<>();

        Random rng = new Random();
        rng.setSeed(System.nanoTime());
        Integer random = Math.abs(rng.nextInt());
        Integer indice = random%2;

        for (int z = 0; z<(poolSize/2); z++) {
            Solution temp = poolInitial.get(z);
            if (indice == 0){
                temp = CalculMutations.mutationChangementSurMASimple(poolInitial.get(z));
            } else {
                temp = CalculMutations.mutationSwapSurOSSimple(poolInitial.get(z));
            }
            enfants.add(temp);
            random = Math.abs(rng.nextInt());
            indice = random%2;
        }

        //Tournoi pour sélectionner poolSize individus
        ArrayList<Solution> tout = new ArrayList<>();
        tout.addAll(poolInitial);
        tout.addAll(enfants);

        //Sélectionner à chaque fois 2, choisir le meilleur tant qu'on en a pas 100


        resultat.sort(Solution::compareTo);
        return resultat.get(0);
    }
}
