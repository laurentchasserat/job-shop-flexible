package outils;

import donneesDuProbleme.Probleme;
import donneesDuProbleme.Solution;

import java.util.ArrayList;
import java.util.List;

public class Gantt {

    private List<ArrayList<DatesDebutFinPlusJob>> gantt;
    private int nbJobs;

    public Gantt(Probleme pb, Solution sol, PlanningDesActivites acts) {
        this.nbJobs = pb.getNbJobs();
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
            System.out.println("Job "+(i+1)+" : "+((char)(i+65)));
        }
        System.out.println("----------");

        System.out.println("Temps :     0   1   2   ");
        System.out.println("            |   |   |   ");
        System.out.println("            V   V   V   ");
        int machine = 0;

        for (ArrayList<DatesDebutFinPlusJob> j : gantt) {
            System.out.format("Machine %3d | ", machine+1);
            boolean initialized = false;
            DatesDebutFinPlusJob dateprec = new DatesDebutFinPlusJob(0,0,0);
            for (DatesDebutFinPlusJob dates : j) {

                if (initialized) {
                    for (int l = 0; l<(dates.getDebut()-dateprec.getFin()); l++){
                        System.out.print("  | ");
                    }
                }

                for (int l = 0; l<(dates.getFin()-dates.getDebut()); l++){
                    initialized = true;
                    System.out.print(jobSymbols.get(dates.getJob())+" | ");
                }
                dateprec = dates;

            }
            System.out.print("--> "+dateprec.getFin());
            machine++;
            System.out.println();

        }
    }

}
