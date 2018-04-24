package donneesDuProbleme;

import outils.PlanningDesActivites;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static java.lang.Math.max;

public class Solution {

    // Le problème dont ceci est la solution
    private Probleme probleme;

    // Les machines retenues pour chaque activité de chaque job (ex : ( (1,2,2), (2,1,3), (3,2) ) )
    private ArrayList<ArrayList<Integer>> machineAssignment;

    // L'ordre dans lequel chaque tache sera exécutée classée selon le numéro du job (ex : (1,1,1,2,2,2,3,3) )
    private ArrayList<Integer> operationSequence;

    public Solution(Probleme pb, ArrayList<ArrayList<Integer>> ma, ArrayList<Integer> os ){
        this.probleme = pb;
        this.machineAssignment = ma;
        this.operationSequence = os;
    }

    // Cette fonction renvoie le coût en unité de temps de la solution en question
    public Integer calculerCout() {

        // Initialisation des dispos des machines
        ArrayList<Integer> dispoAuPlusTotDesMachines = new ArrayList<>();
        for (int k = 0; k<probleme.getNbMachines(); k++) dispoAuPlusTotDesMachines.add(0);

        // Indice de l'activité en cours pour chaque job
        ArrayList<Integer> indicesDesActivites = new ArrayList<>();
        for (int j=0; j<probleme.getNbJobs(); j++) indicesDesActivites.add(0); // Initialisé à 0

        //Initialisation du planning
        PlanningDesActivites planning = new PlanningDesActivites(probleme);

        // Pour tous les jobs de la séquence d'opérations
        for (Integer job : operationSequence) {

            try {
                System.out.println("Pause.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int machineCourante = machineAssignment.get(job-1).get(indicesDesActivites.get(job-1)) -1;
            System.out.println("Machine courante : "+machineCourante);
            int ancienneDuree = -1;
            int dureeAAjouter = probleme.getJobs().get(job-1).getActivites().get(indicesDesActivites.get(job-1))
                    .getDureeDeLaMachine(machineCourante+1);


            // On place l'activité sur la machine tel que son début soit :
            // max{ fin activité précédente sur ce job, fin activité précédente sur cette machine }
            // Si c'est la première activité du job on prend directement la fin de l'activité précédente sur cette machine

            if (indicesDesActivites.get(job-1).equals(0)) {
                System.out.println("Premiere activite du job");
                ancienneDuree = dispoAuPlusTotDesMachines.get(machineCourante);
            } else {
                System.out.println("Pas premiere activite du job");
                ancienneDuree = max(
                        dispoAuPlusTotDesMachines.get(machineCourante),
                        planning.getPlanning().get(job-1).get(indicesDesActivites.get(job - 2)).getFin() );
            }

            // Mise à jour des dispos des machines et des dates des activités

            System.out.println("Incrementation sur le job "+job+" machine "+machineCourante+" ancienne durée "+ancienneDuree+" a ajouter "+ dureeAAjouter);
            // On ajoute la durée à la bonne machine
            planning.getPlanning().get(job-1).get(indicesDesActivites.get(job-1)).setDebut(ancienneDuree);
            dispoAuPlusTotDesMachines.set(machineCourante, ancienneDuree + dureeAAjouter);
            planning.getPlanning().get(job-1).get(indicesDesActivites.get(job-1)).setDebut(ancienneDuree+dureeAAjouter);
            System.out.println("Incrémentation ! Nouvelle durée : "+dispoAuPlusTotDesMachines.get(machineCourante));
            System.out.println();

        }

        System.out.println("Dates de fin d'utilisation des machines :");
        for (Integer i : dispoAuPlusTotDesMachines) {
            System.out.println(i);
        }
        System.out.println();

        int resultat = Collections.max(dispoAuPlusTotDesMachines);


        return resultat;
    }




    public void afficherSolution() {
        System.out.println("Solution :");

        System.out.print("MA = (");
        for (ArrayList<Integer> ar : machineAssignment) {
            System.out.print(" ( ");
            for (Integer val : ar) {
                System.out.print(val+" ");
            }
            System.out.print(")");
        }
        System.out.print(" )");
        System.out.println();

        System.out.print("OS = ( ");
        for (Integer job : operationSequence) {
            System.out.print(job+" ");
        }
        System.out.print(")");
        System.out.println();


        System.out.println();
    }
}
