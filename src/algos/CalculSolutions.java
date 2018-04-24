package algos;

import donneesDuProbleme.Activite;
import donneesDuProbleme.Job;
import donneesDuProbleme.Probleme;
import donneesDuProbleme.Solution;
import tests.TestDonnees;

import java.util.ArrayList;

public class CalculSolutions {

    /*
    Avec l'heuristique gloutonne, on choisit touhours la machine qui a le temps le plus court sur l'activité
    en question, et on crée un vecteur OS de manière "bête" (ex : (1 1 1 2 2 2 3 3) )
    */
    public static Solution heuristiqueGloutonne (Probleme probleme) {

        ArrayList<ArrayList<Integer>> ma = new ArrayList<>();

        // Ajout des différents jobs
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
}
