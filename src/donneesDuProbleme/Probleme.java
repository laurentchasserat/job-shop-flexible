package donneesDuProbleme;

import java.util.ArrayList;

public class Probleme {
    private ArrayList<Job> jobs;

    private int nbMachines;
    private int nbJobs;

    public Probleme(ArrayList<Job> acts, int nm, int nj) {
        this.jobs = acts;
        this.nbMachines = nm;
        this.nbJobs = nj;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void afficherProbleme() {
        System.out.println("Problème à "+this.nbJobs+" jobs et "+this.nbMachines+" machines dont les jobs sont :");
        System.out.println();
        Integer k = 0;
        for (Job job : this.getJobs()) {

            System.out.println("Job "+(++k).toString());

            Integer l = 0;

            for (Activite act : job.getActivites()) {

                System.out.print("-> Activite "+k+"-"+(++l).toString()+" { ");

                for (Tuple t : act.getDurees()) {
                    System.out.print("(Machine "+t.getMachine()+", durée "+t.getDuree()+") ");
                }

                System.out.print("}\n");

            }

            System.out.print("\n");

        }
    }

    public int getNbMachines() {
        return nbMachines;
    }

    public int getNbJobs() {
        return nbJobs;
    }
}
