package outils;

import donneesDuProbleme.Probleme;
import donneesDuProbleme.Solution;

import java.util.ArrayList;
import java.util.List;

public class Gantt {

    private List<ArrayList<DatesDebutFinPlusJob>> gantt;
    private int nbJobs;

    private Integer tempsTotal;

    public Gantt(Probleme pb, Solution sol, PlanningDesActivites acts, Integer t) {
        this.nbJobs = pb.getNbJobs();
        this.tempsTotal = t;
        gantt = new ArrayList<>();
        for (int i=0; i<pb.getNbMachines(); i++) {
            gantt.add(new ArrayList<>());
        }

        int job = 0;
        for (ArrayList<DatesDebutFin> j : acts.getPlanning()) {
            int act = 0;
            for (DatesDebutFin dates : j) {
                int machineCourante = sol.getMachineAssignment().get(job).get(act) -1;
                gantt.get(machineCourante).add(new DatesDebutFinPlusJob(dates.getDebut(), dates.getFin(), job));
                act++;
                gantt.get(machineCourante).sort(DatesDebutFin::compareTo);
            }

            job++;
        }


    }

    public void afficherGantt() {

        System.out.println();
        System.out.println("Gantt :");
        System.out.println("----------");
        System.out.println("Légende :");
        ArrayList<Character> jobSymbols = new ArrayList<>();
        for (int i = 0; i<nbJobs; i++) {
            jobSymbols.add((char)(i+49));
            System.out.println("Job "+(i+1)+" : "+((char)(i+49)));
        }
        int machine = 0;
        System.out.println("----------");

        for (ArrayList<DatesDebutFinPlusJob> j : gantt) {

            System.out.format("Machine %3d : | ", machine+1);

            boolean initialized = false;
            DatesDebutFinPlusJob dateprec = new DatesDebutFinPlusJob(0,0,0);

            int compteur = 0;
            for (DatesDebutFinPlusJob dates : j) {

                for (int l = 0; l<(dates.getDebut()-dateprec.getFin()); l++){
                    System.out.print("  | ");
                    compteur++;
                }

                for (int l = 0; l<(dates.getFin()-dates.getDebut()); l++){
                    initialized = true;
                    System.out.print(jobSymbols.get(dates.getJob())+" | ");
                    compteur++;
                }

                dateprec = dates;


            }
            while (compteur<tempsTotal) {
                System.out.print("  | ");
                compteur++;
            }
            machine++;
            System.out.println();

        }

        System.out.println("----------");
        System.out.println("Temps total : "+tempsTotal);
    }

    public Integer getTempsTotal() {
        return tempsTotal;
    }
}
