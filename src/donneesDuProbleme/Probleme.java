package donneesDuProbleme;

import java.util.ArrayList;

public class Probleme {
    private ArrayList<Job> jobs;

    public Probleme(ArrayList<Job> acts) {
        this.jobs = acts;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void afficherProbleme() {
        Integer k = 0;
        for (Job job : this.getJobs()) {

            System.out.println("Job "+(++k).toString());

            Integer l = 0;

            for (Activite act : job.getActivites()) {

                System.out.print("-> Activite "+k+"-"+(++l).toString()+" { ");

                for (Tuple t : act.getDurees()) {
                    System.out.print("( "+t.getMachine()+" | "+t.getDuree()+" ) ");
                }

                System.out.print("}\n");

            }

        }
    }
}
