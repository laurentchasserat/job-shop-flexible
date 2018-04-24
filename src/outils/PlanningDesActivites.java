package outils;

import donneesDuProbleme.Activite;
import donneesDuProbleme.Job;
import donneesDuProbleme.Probleme;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlanningDesActivites {

    /* Ce planning nous aidera à réaliser le calcul. Il prend la forme d'un tableau recensant les dates de début et de
    fin de chaque activité de chaque job, ainsi :

    ------------------------------------------------------
    | Job 1 | Act11 début 0, fin 3 | Act21 début 4 fin 6 |
    | Job 2 | Act12 début 0 fin 4                        |
    ------------------------------------------------------

     */

    private ArrayList<ArrayList<DatesDebutFin>> planning;



    // Initialise le planning de la bonne taille du problème avec toutes les dates à -1
    public PlanningDesActivites(Probleme p){
        planning = new ArrayList<>();
        for (Job j : p.getJobs()) {
            ArrayList<DatesDebutFin> temp = new ArrayList<>();
            for (Activite activite : j.getActivites()) {
                temp.add(new DatesDebutFin(-1, -1));
            }
            planning.add(temp);
        }
    }

    public ArrayList<ArrayList<DatesDebutFin>> getPlanning() {
        return planning;
    }

    public void afficherPlanning() {
        System.out.println("Planning :");
        int k = 1;
        for (ArrayList<DatesDebutFin> j : planning) {
            System.out.print("Job "+k+++" : ");
            int l = 1;
            for (DatesDebutFin dates : j) {
                System.out.print("( Act"+l+++" "+dates.getDebut()+" "+dates.getFin()+" ) ");
            }
            System.out.println();
        }
    }
}
