package outils;

import donneesDuProbleme.Solution;

import java.util.ArrayList;

public class Probas {

    public static Double MoyenneSols(ArrayList<Solution> ar) {
        Double sum = 0.0;
        for (Solution s : ar) {
            sum += (double) s.getCout();
        }
        Double result = sum/ar.size();
        return  result;
    }

    public static Double Moyenne(ArrayList<Double> ar) {
        Double sum = 0.0;
        for (Double s : ar) {
            sum += s;
        }
        Double result = sum/ar.size();
        return result ;
    }

    public static Double EcartType(ArrayList<Solution> ar) {
        ArrayList<Double> arSquared = new ArrayList<>();
        for (Solution s : ar) {
            arSquared.add((Math.pow(s.getCout(),2.0)));
        }

        Double result = Math.sqrt(Moyenne(arSquared)-Math.pow(MoyenneSols(ar),2.0));
        return  result;

    }
}
