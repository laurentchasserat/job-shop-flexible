package algos;

import donneesDuProbleme.Solution;

import java.util.ArrayList;

public class GenererVoisinnages {

    public static Solution OSPermutation(Solution etatInitial, Integer indice1, Integer indice2) {
        ArrayList<Integer> newOS = etatInitial.getOperationSequence();
        Integer temp = etatInitial.getOperationSequence().get(indice1);
        newOS.set(indice1, etatInitial.getOperationSequence().get(indice2));
        newOS.set(indice2, temp);

        Solution sol = new Solution(etatInitial.getProbleme(), etatInitial.getMachineAssignment(), newOS);
        sol.calculerCout(false);
        return sol;
    }

}
