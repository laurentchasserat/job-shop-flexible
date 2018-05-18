package algos;

import donneesDuProbleme.Activite;
import donneesDuProbleme.Job;
import donneesDuProbleme.Probleme;
import donneesDuProbleme.Solution;
import tests.TestDonnees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class CalculSolutions {

    /*
    Avec l'heuristique gloutonne, on choisit touhours la machine qui a le temps le plus court sur l'activité
    en question, et on crée un vecteur OS de manière "bête" (ex : (1 1 1 2 2 2 3 3) )
    */
    public static Solution heuristiqueGloutonne (Probleme probleme) {

        ArrayList<ArrayList<Integer>> ma = new ArrayList<>();

        // Initialisation des différents jobs au sein de ma
        for (int k=0; k<probleme.getNbJobs(); k++) {
            ma.add(new ArrayList<>());
        }

        int indiceJob = 0;
        //Choix des machines pour chaque activité de chaque job
        for (Job job : probleme.getJobs()) {
            for (Activite act : job.getActivites()) {
                // On ajoute le numéro de la machine qui a la durée la plus courte
                ma.get(indiceJob).add(act.getTupleDeDureeMin().getMachine());
            }
            indiceJob++;
        }

        indiceJob = 1;
        ArrayList<Integer> os = new ArrayList<>();
        // Pour chaque activité de chaque job
        for (Job job : probleme.getJobs()) {
            for (Activite act : job.getActivites()) {
                // On ajoute le numéro du job
                os.add(indiceJob);
            }
            indiceJob++;
        }

        Solution sol = new Solution(probleme, ma, os);

        return sol;

    }

    public static ArrayList<Solution> genererNSolutionsAleatoires(Probleme p, Integer nbSolsAGenerer) {
        ArrayList<Solution> resultat = new ArrayList<>();

        for (int u=0; u<nbSolsAGenerer; u++) {

            //Générer ma
            ArrayList<ArrayList<Integer>> ma = new ArrayList<>();
            // Initialisation des différents jobs au sein de ma
            for (int k=0; k<p.getNbJobs(); k++) {
                ma.add(new ArrayList<>());
            }

            int indiceJob = 0;
            //Choix des machines pour chaque activité de chaque job
            for (Job job : p.getJobs()) {
                for (Activite act : job.getActivites()) {
                    // On choisit un numéro de machine aléatoirement parmi les possibles
                    ma.get(indiceJob).add(act.getTupleAleatoire().getMachine());
                }
                indiceJob++;
            }

            //Gérérer os dans l'ordre
            indiceJob = 1;
            ArrayList<Integer> os = new ArrayList<>();
            // Pour chaque activité de chaque job
            for (Job job : p.getJobs()) {
                for (Activite act : job.getActivites()) {
                    // On ajoute le numéro du job
                    os.add(indiceJob);
                }
                indiceJob++;
            }
            //...puis le mélanger aléatoirement !
            Collections.shuffle(os);

            Solution sol = new Solution(p, ma, os);
            resultat.add(sol);
        }

        return resultat;
    }
}
