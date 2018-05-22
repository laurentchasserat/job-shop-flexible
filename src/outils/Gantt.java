package outils;

import donneesDuProbleme.Probleme;
import donneesDuProbleme.Solution;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Gantt {

    private List<ArrayList<DatesDebutFinPlusJob>> gantt;
    private int nbJobs;

    private Integer tempsTotal;

    public Gantt(Probleme pb, Solution sol, PlanningDesActivites acts) {
        this.nbJobs = pb.getNbJobs();
        this.tempsTotal = sol.getCout();
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

        String ANSI_RESET = "\u001B[0m";
        ArrayList<String> colors = new ArrayList<>();
        colors.add("\u001B[34m");
        colors.add("\u001B[31m");
        colors.add("\u001B[33m");
        colors.add("\u001B[32m");
        colors.add("\u001B[35m");
        colors.add("\u001B[36m");
        colors.add("\u001B[37m");

        System.out.println();
        System.out.println("Gantt :");
        System.out.println("-------");
        System.out.println();
        System.out.println("Légende :");
        ArrayList<Character> jobSymbols = new ArrayList<>();
        for (int i = 0; i<nbJobs; i++) {
            jobSymbols.add((char)(i+49));
            System.out.println("Job "+(i+1)+" : "+((char)(i+49)));
        }
        System.out.println();

        int machine = 0;
        System.out.print("-----------------"); for (int n=0; n<tempsTotal; n++) System.out.print("----"); System.out.println();

        for (ArrayList<DatesDebutFinPlusJob> j : gantt) {

            System.out.format("| Machine %3d : | ", machine+1);

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
                    System.out.print(colors.get((int)jobSymbols.get(dates.getJob())%7)+jobSymbols.get(dates.getJob())+ANSI_RESET+" | ");
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
            System.out.print("-----------------"); for (int n=0; n<tempsTotal; n++) System.out.print("----"); System.out.println();

        }
        System.out.println();
        System.out.print("| Temps    : "); for (int n=0; n<=tempsTotal; n++) System.out.format("---|", n); System.out.println("-->");
        System.out.print("             "); for (int n=0; n<=tempsTotal; n++) System.out.format("%4d", n); System.out.println();
        System.out.println();
        System.out.println("Temps total : "+tempsTotal);
    }

    public void afficherGanttDansFichier(FileWriter ffw) throws IOException {

        String output;

        ffw.write("\n");
        ffw.write("Légende :\n");
        ArrayList<Character> jobSymbols = new ArrayList<>();
        for (int i = 0; i<nbJobs; i++) {
            jobSymbols.add((char)(i+49));
            ffw.write("Job "+(i+1)+" : "+((char)(i+49))+"\n");
        }
        ffw.write("\n");

        int machine = 0;
        ffw.write("-----------------"); for (int n=0; n<tempsTotal; n++) ffw.write("----"); ffw.write("\n");

        for (ArrayList<DatesDebutFinPlusJob> j : gantt) {


            output = String.format("| Machine %3d : | ", machine+1);
            ffw.write(output);

            boolean initialized = false;
            DatesDebutFinPlusJob dateprec = new DatesDebutFinPlusJob(0,0,0);

            int compteur = 0;
            for (DatesDebutFinPlusJob dates : j) {

                for (int l = 0; l<(dates.getDebut()-dateprec.getFin()); l++){
                    ffw.write("  | ");
                    compteur++;
                }

                for (int l = 0; l<(dates.getFin()-dates.getDebut()); l++){
                    initialized = true;
                    ffw.write(jobSymbols.get(dates.getJob())+" | ");
                    compteur++;
                }



                dateprec = dates;


            }
            while (compteur<tempsTotal) {
                ffw.write("  | ");
                compteur++;
            }
            machine++;
            ffw.write("\n");
            ffw.write("-----------------"); for (int n=0; n<tempsTotal; n++) ffw.write("----"); ffw.write("\n");

        }
        ffw.write("\n");
        ffw.write("| Temps    : ");
        for (int n=0; n<=tempsTotal; n++) {
            output = String.format("---|", n);
            ffw.write(output);
        }
        ffw.write("-->\n");
        ffw.write("             ");
        for (int n=0; n<=tempsTotal; n++) {
            output = String.format("%4d", n);
            ffw.write(output);
        }
        ffw.write("\n\n");
        ffw.write("Temps total : "+tempsTotal+"\n");
    }

    public Integer getTempsTotal() {
        return tempsTotal;
    }
}
