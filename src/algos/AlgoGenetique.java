package algos;

import donneesDuProbleme.Solution;

import java.util.ArrayList;
import java.util.Random;

public class AlgoGenetique {

    private int nbIterations;
    private int poolSize;
    private Solution best;

    public AlgoGenetique() {
        this.nbIterations = 0;
        this.poolSize = 1;
    }


    //Renvoie un ArrayList trié de solutions après un certain nombre de générations
    public Solution algoGenetiqueTournoiMutations(ArrayList<Solution> poolInitial, Integer nbGenerations) {
        if (this.nbIterations == 0) {
            best = poolInitial.get(0);
            System.out.print("[Algo génétique] ");
        }


        Solution resultat; //poolInitial est sensé arriver trié
        poolSize = poolInitial.size();

        // Génération d'enfants par mutations (les 50 meilleurs se reproduisent)
        ArrayList<Solution> enfants = new ArrayList<>();

        Random rng = new Random();
        rng.setSeed(System.nanoTime());
        Integer random = Math.abs(rng.nextInt());
        Integer indice = random%2;

        for (int z = 0; z<(poolSize/2); z++) {
            Solution temp;
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
        tout.sort(Solution::compareTo);

        //On garde en mémoire la meilleure solution si elle est meilleure que celle qu'on connaît
        if (best.getCout()>tout.get(0).getCout())
            best = tout.get(0);

        //Sélectionner à chaque fois 2, choisir le meilleur tant qu'on en a pas 100
        ArrayList<Solution> nouvelleGeneration = new ArrayList<>();
        Integer indice1;
        Integer indice2;
        while (nouvelleGeneration.size()!=poolSize) {

            //Récupération de deux indices au hasard
            random = Math.abs(rng.nextInt());
            indice1 = random%(tout.size());
            indice2 = indice1;
            while (indice2.equals(indice1)) {
                random = Math.abs(rng.nextInt());
                indice2 = random%(tout.size());
            }

            //On récupère le meilleur et on le met dans nouvelleGénération
            if (tout.get(indice1).getCout()<tout.get(indice2).getCout()) {
                nouvelleGeneration.add(tout.get(indice1));
                tout.remove(indice1);
            } else {
                nouvelleGeneration.add(tout.get(indice2));
                tout.remove(indice2);
            }

        }

        nbIterations++;

        nouvelleGeneration.sort(Solution::compareTo);

        System.out.print(nouvelleGeneration.get(0).getCout()+" -> ");

        if (nbIterations<nbGenerations) {
            resultat = algoGenetiqueTournoiMutations(nouvelleGeneration, nbGenerations);
        }
        else {
            resultat = best;
            System.out.print("FIN ("+nbIterations+" générations). Best : "+best.getCout()+".\n");
            nbIterations = 0;
        }

        return resultat;
    }
}
