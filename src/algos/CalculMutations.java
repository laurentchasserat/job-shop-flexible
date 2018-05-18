package algos;

import donneesDuProbleme.Solution;

import java.util.ArrayList;
import java.util.Random;

public class CalculMutations {

    /** Remarque : parfois les mutations n'auront pas d'effet si la nouvelle machine choisie est égale à l'ancienne ou
     * si les jobs interchangés sont identiques **/

    public static Solution mutationSwapSurOSSimple(Solution initiale) {
        boolean verbose = false;
        ArrayList<Integer> newOS = initiale.getOperationSequence();
        if (verbose) System.out.println("Size : "+newOS.size());

        Random rng = new Random();
        rng.setSeed(System.nanoTime());
        Integer random = Math.abs(rng.nextInt());
        Integer indice1 = random%(newOS.size());
        if (verbose) System.out.println("Indice1 : "+indice1);

        Integer indice2 = indice1;
        while (indice2==indice1) {
            random = Math.abs(rng.nextInt());
            indice2 = random%(newOS.size());
        }
        if (verbose) System.out.println("Indice2 : "+indice2);

        Integer temp = newOS.get(indice1);
        newOS.set(indice1, newOS.get(indice2));
        newOS.set(indice2, temp);


        return new Solution(initiale.getProbleme(), initiale.getMachineAssignment(), newOS);
    }

    public static Solution mutationChangementSurMASimple(Solution initiale) {
        boolean verbose = false;
        ArrayList<ArrayList<Integer>> newMA = initiale.getMachineAssignment();
        if (verbose) System.out.println("Size : "+newMA.size());

        Random rng = new Random();
        rng.setSeed(System.nanoTime());
        Integer random = Math.abs(rng.nextInt());
        Integer indice1 = random%(newMA.size());
        if (verbose) System.out.println("Indice job : "+indice1);

        if (verbose) System.out.println("NbTaches : "+newMA.get(indice1).size());

        random = Math.abs(rng.nextInt());
        Integer indice2 = random%(newMA.get(indice1).size());
        if (verbose) System.out.println("Indice tache : "+indice2);

        newMA.get(indice1).set(indice2, initiale.getProbleme().getJobs().get(indice1).getActivites()
                .get(indice2).getTupleAleatoire().getMachine());

        return new Solution(initiale.getProbleme(), newMA, initiale.getOperationSequence());
    }

}
