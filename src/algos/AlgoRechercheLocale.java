package algos;

import donneesDuProbleme.Solution;

import static algos.GenererVoisinnages.OSPermutation;

public class AlgoRechercheLocale {

    private int nbIterations;

    public AlgoRechercheLocale() {
        this.nbIterations = 0;
    }

    public Solution rechercheLocaleParPermutationsSimples(Solution solutionInitiale) {
        if (this.nbIterations == 0) {
            System.out.print("[Algo de recherche locale] ");
        }
        nbIterations++;
        Solution inter = solutionInitiale;
        Integer coutInter;
        int taille = solutionInitiale.getOperationSequence().size();
        //System.out.println("Taille du OS : "+taille);
        boolean over = false;

        for (int ind1 = 0; ((ind1 < taille) && !over); ind1++) {
            for (int ind2 = 1; ((ind2 < taille) && !over); ind2++) {
                inter = OSPermutation(solutionInitiale, ind1, ind2);
                coutInter = inter.getCout();
                //inter.getGantt().afficherGantt();

                if (coutInter < solutionInitiale.getCout()) {
                    //System.out.println("[Iteration "+nbIterations+"] Nouveau meilleur coût trouvé : " + coutInter);
                    System.out.print(coutInter+" -> ");
                    //inter.afficherSolution();
                    over = true;
                }
            }

        }



        Solution solutionFinale = solutionInitiale;
        if (over) {
            solutionFinale = rechercheLocaleParPermutationsSimples(inter);
        } else {
            solutionFinale = solutionInitiale;
            //System.out.println("[Iteration "+nbIterations+"] Solution finale trouvée : "+solutionFinale.getCout()+" au bout de "+nbIterations+" itérations.");
            System.out.print(solutionFinale.getCout()+" -> FIN ("+nbIterations+" itérations).\n");
            System.out.println("                           Best : "+solutionFinale.getCout());
            nbIterations=0;
        }

        return solutionFinale;
    }

}
