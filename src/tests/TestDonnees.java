package tests;

import donneesDuProbleme.*;

import java.util.ArrayList;

public class TestDonnees {

    public static Probleme exemple1() {

        // Tuples

        Tuple t1 = new Tuple(1,3);
        Tuple t2 = new Tuple(2,2);
        Tuple t3 = new Tuple(3,4);
        Tuple t4 = new Tuple(2,5);
        Tuple t5 = new Tuple(3,5);
        Tuple t6 = new Tuple(2,4);
        Tuple t7 = new Tuple(1,2);
        Tuple t8 = new Tuple(1,2);
        Tuple t9 = new Tuple(2,4);
        Tuple t10 = new Tuple(3,2);
        Tuple t11 = new Tuple(1,2);
        Tuple t12 = new Tuple(3,2);
        Tuple t13 = new Tuple(2,3);
        Tuple t14 = new Tuple(3,5);

        // Activites

        ArrayList<Tuple> ar1 = new ArrayList<>();
        ar1.add(t1);
        Activite act11 = new Activite(ar1);

        ArrayList<Tuple> ar2 = new ArrayList<>();
        ar2.add(t2);
        ar2.add(t3);
        Activite act12 = new Activite(ar2);

        ArrayList<Tuple> ar3 = new ArrayList<>();
        ar3.add(t4);
        ar3.add(t5);
        Activite act13 = new Activite(ar1);

        ArrayList<Tuple> ar4 = new ArrayList<>();
        ar4.add(t6);
        Activite act21 = new Activite(ar4);

        ArrayList<Tuple> ar5 = new ArrayList<>();
        ar5.add(t7);
        Activite act22 = new Activite(ar5);

        ArrayList<Tuple> ar6 = new ArrayList<>();
        ar6.add(t8);
        ar6.add(t9);
        ar6.add(t10);
        Activite act23 = new Activite(ar6);

        ArrayList<Tuple> ar7 = new ArrayList<>();
        ar7.add(t11);
        ar7.add(t12);
        Activite act31 = new Activite(ar7);

        ArrayList<Tuple> ar8 = new ArrayList<>();
        ar8.add(t13);
        ar8.add(t14);
        Activite act32 = new Activite(ar8);

        // Jobs

        ArrayList<Activite> acts1 = new ArrayList<Activite>();
        acts1.add(act11);
        acts1.add(act12);
        acts1.add(act13);
        Job job1 = new Job(acts1);

        ArrayList<Activite> acts2 = new ArrayList<Activite>();
        acts2.add(act21);
        acts2.add(act22);
        acts2.add(act23);
        Job job2 = new Job(acts2);

        ArrayList<Activite> acts3 = new ArrayList<Activite>();
        acts3.add(act31);
        acts3.add(act32);
        Job job3 = new Job(acts3);

        // Problème

        ArrayList<Job> jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);
        jobs.add(job3);

        Probleme pb = new Probleme(jobs, 3, 3);

        return pb;
    }

    public static Solution solution1() {
        ArrayList<ArrayList<Integer>> ma = new ArrayList<>();
        ma.add(new ArrayList<>());
        ma.add(new ArrayList<>());
        ma.add(new ArrayList<>());
        ma.get(0).add(1);
        ma.get(0).add(2);
        ma.get(0).add(1);
        ma.get(1).add(2);
        ma.get(1).add(1);
        ma.get(1).add(3);
        ma.get(2).add(3);
        ma.get(2).add(2);

        ArrayList<Integer> os = new ArrayList<>();
        os.add(1);
        os.add(1);
        os.add(1);
        os.add(2);
        os.add(2);
        os.add(2);
        os.add(3);
        os.add(3);

        Solution sol = new Solution(exemple1(), ma, os);

        return sol;
    }


}
