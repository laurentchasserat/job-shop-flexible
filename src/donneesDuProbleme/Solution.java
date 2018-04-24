package donneesDuProbleme;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

        /** C'EST FAUXXX !! **/

        /*

        ArrayList<Integer> tempsFinauxDesMachines = new ArrayList<>();
        ArrayList<Integer> indicesDesActivites = new ArrayList<>();

        for (int i=0; i<probleme.getNbMachines(); i++) {
            tempsFinauxDesMachines.add(0);
        }

        for (int j=0; j<probleme.getNbJobs(); j++) {
            indicesDesActivites.add(0);
        }

        // Pour tous les jobs de la séquence d'opérations
        for (Integer job : operationSequence) {

            int machine = machineAssignment.get(job-1).get(indicesDesActivites.get(job-1));
            // Incrémentation du bon indice
            indicesDesActivites.set(job-1, indicesDesActivites.get(job-1)+1);
            int ancienneDuree = tempsFinauxDesMachines.get(machineAssignment.get(job-1).get(indicesDesActivites.get(job-1))-1);
            int dureeAAjouter = probleme.getJobs().get(job-1).getActivites().get(indicesDesActivites.get(job-1)-1).getDureeDeLaMachine(machine);

            System.out.println("Incrementation sur le job "+job+" machine "+machine+" ancienne durée "+ancienneDuree+" a ajouter "+ dureeAAjouter);
            // On ajoute la durée à la bonne machine
            tempsFinauxDesMachines.set(machine, ancienneDuree + dureeAAjouter);
            System.out.println("Incrémentation ! Nouvelle durée : "+tempsFinauxDesMachines.get(machine));
        }

        int resultat = Collections.max(tempsFinauxDesMachines);
        return resultat;

        */

        /** 2e essai **/

        // Générer le graphe !

        return 0;
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
