package donneesDuProbleme;

import outils.Gantt;
import outils.PlanningDesActivites;

import java.util.ArrayList;
import java.util.Collections;


public class Solution implements Comparable<Solution>{

    // Le problème dont ceci est la solution
    private Probleme probleme;

    private Gantt gantt;

    // Les machines retenues pour chaque activité de chaque job (ex : ( (1,2,2), (2,1,3), (3,2) ) )
    // /!\ Vérifier dans le problème qu'elles appartiennent bien au pull de machines pour cette activité !!!
    private ArrayList<ArrayList<Integer>> machineAssignment;

    // L'ordre dans lequel chaque tache sera exécutée classée selon le numéro du job (ex : (1,1,1,2,2,2,3,3) )
    private ArrayList<Integer> operationSequence;

    private Integer cout;

    public Solution(Probleme pb, ArrayList<ArrayList<Integer>> ma, ArrayList<Integer> os ){
        this.probleme = pb;
        this.machineAssignment = ma;
        this.operationSequence = os;
        this.cout = Integer.MAX_VALUE;
    }

    // Cette fonction renvoie le coût en unité de temps de la solution en question
    public Integer calculerCout(boolean verbose) {

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

            int machineCourante = machineAssignment.get(job-1).get(indicesDesActivites.get(job-1)) -1;

            if (verbose) System.out.println("Job courant : "+job);
            if (verbose) System.out.println("Machine courante : "+(machineCourante+1));
            if (verbose) System.out.println("Activité courante : "+(indicesDesActivites.get(job-1)+1));

            int ancienneDuree = -1;
            int dureeAAjouter = probleme.getJobs().get(job-1).getActivites().get(indicesDesActivites.get(job-1))
                    .getDureeDeLaMachine(machineCourante+1);


            // On place l'activité sur la machine tel que son début soit :
            // max{ fin activité précédente sur ce job, fin activité précédente sur cette machine }
            // Si c'est la première activité du job on prend directement la fin de l'activité précédente sur cette machine

            if (indicesDesActivites.get(job-1).equals(0)) {
                ancienneDuree = dispoAuPlusTotDesMachines.get(machineCourante);

                if (verbose) System.out.println("Premiere activite du job");
                if (verbose) System.out.println("Ancienne durée : "+ancienneDuree);

            } else {
                Integer maxMachines = dispoAuPlusTotDesMachines.get(machineCourante);
                Integer maxJob = planning.getPlanning().get(job-1).get(indicesDesActivites.get(job-1)-1).getFin() ;
                ancienneDuree = Integer.max(maxJob,maxMachines);

                if (verbose) System.out.println("Pas premiere activite du job");
                if (verbose) System.out.println("Max machines : "+maxMachines+", Max jobs : "+maxJob+", Ancienne durée : "+ancienneDuree);
            }

            // Mise à jour des dispos des machines, des dates des activités et des indices

            if (verbose) System.out.println("Incrementation sur le job "+job+" machine "+(machineCourante+1)+" ancienne durée "+ancienneDuree+" a ajouter "+ dureeAAjouter);

            // On ajoute la durée à la bonne machine
            planning.getPlanning().get(job-1).get(indicesDesActivites.get(job-1)).setDebut(ancienneDuree);
            dispoAuPlusTotDesMachines.set(machineCourante, ancienneDuree + dureeAAjouter);
            planning.getPlanning().get(job-1).get(indicesDesActivites.get(job-1)).setFin(ancienneDuree+dureeAAjouter);
            indicesDesActivites.set((job-1), indicesDesActivites.get(job-1)+1);

            if (verbose) System.out.println("Incrémentation ! Nouvelle durée : "+dispoAuPlusTotDesMachines.get(machineCourante));
            if (verbose) System.out.println();
            if (verbose) System.out.println("Dates de fin d'utilisation des machines :");
            for (Integer i : dispoAuPlusTotDesMachines) {
                if (verbose) System.out.println(i);
            }
            if (verbose) System.out.println();
            if (verbose) planning.afficherPlanning();
            if (verbose) System.out.println();
            if (verbose) System.out.println("----------------------");
            if (verbose) System.out.println();

        }

        int resultat = Collections.max(dispoAuPlusTotDesMachines);
        if (verbose) System.out.println(resultat);

        this.cout = resultat;

        Solution tempSol = this;

        this.gantt = new Gantt(this.probleme, tempSol, planning);

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
    }

    public ArrayList<ArrayList<Integer>> getMachineAssignment() {
        return machineAssignment;
    }

    public Gantt getGantt() {
        return gantt;
    }

    public ArrayList<Integer> getOperationSequence() {
        return operationSequence;
    }

    public Integer getCout() {
        return cout;
    }

    public Probleme getProbleme() {
        return probleme;
    }

    @Override
    public int compareTo(Solution s2) {
        int resultat = 0;
        if (this.getCout()<s2.getCout()) resultat = -1;
        return resultat;
    }
}
