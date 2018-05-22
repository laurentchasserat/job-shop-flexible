package tests;

import algos.*;
import donneesDuProbleme.Parser;
import donneesDuProbleme.Probleme;
import donneesDuProbleme.Solution;

import java.util.ArrayList;

public class Tests {
    public static void runTests(){

        System.out.println();
        System.out.println("############################## PROGRAMME DE TESTS DU PROJET JOB SHOP FLEXIBLE ##############################");
        System.out.println();
        System.out.println("Auteurs : CHASSERAT Laurent, HERNANDEZ Lucile");
        System.out.println("INSA Toulouse 4e année IR");
        System.out.println();

        /** Tests de création des problèmes **/

        System.out.println("############################# TESTS SUR LE PARSING ET LA CREATION DES PROBLEMES ############################");
        System.out.println();

        /* Edit : j'ai pas tout testé mais après quelques tatonnages ça a l'air de marcher sur tous les fichiers, malgré
        leurs différences ! Voici quelques exemples vérifiés. */

        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Test de la création d'un problème \"à la main\" se basant sur l'exemple du cours :");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println();
        Probleme pb1 = TestDonnees.exemple1();
        pb1.afficherProbleme();


        // TEMPORAIREMENT COMMENTE PARCE QUE PLUS LISIBLE POUR DEBUGER

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Test de la création d'un problème à l'aide du parser sur le jeu de données \"TextData/Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk01.fjs\" : ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        Probleme pb2 = Parser.parse("./TextData/Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk01.fjs");
        pb2.afficherProbleme();

        /*
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Test de la création d'un problème à l'aide du parser sur le jeu de données \"/TextData/Monaldo/Fjsp/Job_Data/Barnes/Text/mt10c1.fjs\" : ");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        Probleme pb3 = Parser.parse("./TextData/Monaldo/Fjsp/Job_Data/Barnes/Text/mt10c1.fjs");
        pb3.afficherProbleme();

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Test de la création d'un problème à l'aide du parser sur le jeu de données \"TextData/Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk08.fjs\" : ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        Probleme pb4 = Parser.parse("./TextData/Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk08.fjs");
        pb4.afficherProbleme();
        */



        System.out.println("####################### TESTS SUR L'EVALUATION ET L'AFFICHAGE DE SOLUTIONS #######################");
        System.out.println();

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Test de la création et de l'affichage d'une solution \"à la main\" se basant sur l'exemple du cours ");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println();
        Solution sol1 = TestDonnees.solution1();
        Solution sol2 = TestDonnees.solution2();
        sol1.afficherSolution();

        System.out.println("--------------------------------------");
        System.out.println("Test du calcul du coût d'une solution ");
        System.out.println("--------------------------------------");
        System.out.println();
        System.out.println("Coût calculé pour MA = ( ( 1 2 1 ) ( 2 1 3 ) ( 3 2 ) ), OS = ( 1 1 1 2 2 2 3 3 ) : "+sol1.calculerCout(true)+" unités de temps.");
        System.out.println("Coût calculé pour MA = ( ( 1 2 1 ) ( 2 1 3 ) ( 3 2 ) ), OS = ( 1 2 3 1 2 3 1 2 ) : "+sol2.calculerCout(false)+" unités de temps.");

        System.out.println();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test de la génération et de l'affichage du diagramme de Gantt ");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
        System.out.println("Pour la solution MA = ( ( 1 2 1 ) ( 2 1 3 ) ( 3 2 ) ), OS = ( 1 1 1 2 2 2 3 3 )");
        sol1.getGantt().afficherGantt();
        System.out.println();
        System.out.println("Pour la solution MA = ( ( 1 2 1 ) ( 2 1 3 ) ( 3 2 ) ), OS = ( 1 2 3 1 2 3 1 2 )");
        sol2.getGantt().afficherGantt();

        System.out.println();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test de la génération d'une solution par heuristique gloutonne");
        System.out.println("--------------------------------------------------------------");

        System.out.println();
        System.out.println("Pour le problème du cours...");
        Solution solGloutonne = CalculSolutions.heuristiqueGloutonne(pb1);
        solGloutonne.afficherSolution();
        solGloutonne.getGantt().afficherGantt();

        /*
        System.out.println();
        System.out.println("...et pour un problème un peu plus gros !");
        System.out.println();
        Solution solGloutonne2 = CalculSolutions.heuristiqueGloutonne(pb4);
        solGloutonne2.afficherSolution();
        solGloutonne2.getGantt().afficherGantt();
        */

        System.out.println();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test de la recherche de voisinnage par permutation            ");
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        System.out.println("Solution initiale :");
        sol2.afficherSolution();
        Solution solVoisine1 = GenererVoisinnages.OSPermutation(sol2, 2, 4);
        System.out.println();
        System.out.println("Permutation des indices 2 et 4 de OS :");
        System.out.println();
        solVoisine1.afficherSolution();

        System.out.println();
        System.out.println("####################### TESTS SUR L'ALGORITHME DE HILL CLIMBING #######################");
        System.out.println();

        System.out.println();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test de l'algo de hill climbing                               ");
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        /*
        AlgoRechercheLocale algo = new AlgoRechercheLocale(TestDonnees.exemple1());
        Solution sol = algo.rechercheLocaleParPermutationsSimples(CalculSolutions.heuristiqueGloutonne(TestDonnees.exemple1()));
        System.out.println("Solution initiale : ");
        CalculSolutions.heuristiqueGloutonne(TestDonnees.exemple1());
        System.out.println("Solution trouvée par l'algo :");
        sol.afficherSolution();
        System.out.println("Cout : "+sol.getCout());
        sol.getGantt().afficherGantt();
        */
        AlgoRechercheLocale algo = new AlgoRechercheLocale();
        Solution sol = algo.rechercheLocaleParPermutationsSimples(CalculSolutions.heuristiqueGloutonne(pb2));
        System.out.println("Solution initiale : ");
        CalculSolutions.heuristiqueGloutonne(pb2);
        System.out.println("Solution trouvée par l'algo :");
        sol.afficherSolution();
        System.out.println("Cout : "+sol.getCout());
        sol.getGantt().afficherGantt();
        /*
        AlgoRechercheLocale algo = new AlgoRechercheLocale(pb4);
        Solution sol = algo.rechercheLocaleParPermutationsSimples(CalculSolutions.heuristiqueGloutonne(pb4));
        System.out.println("Solution initiale : ");
        CalculSolutions.heuristiqueGloutonne(pb4);
        System.out.println("Solution trouvée par l'algo :");
        sol.afficherSolution();
        System.out.println("Cout : "+sol.getCout());
        sol.getGantt().afficherGantt();
        */

        System.out.println();
        System.out.println("####################### TESTS SUR L'ALGORITHME GENETIQUE #######################");
        System.out.println();

        System.out.println();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test de la génération aléatoire de solutions multiples        ");
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        System.out.println("Génération de 15 solutions aléatoires...");
        ArrayList<Solution> sols = CalculSolutions.genererNSolutionsAleatoiresTriees(pb2,15);
        int v = 1;
        for (Solution s : sols) {
            System.out.print("[Solution "+v+++"]");
            System.out.print(" Cout : "+s.getCout());
            //s.getGantt().afficherGantt();
            System.out.println();
        }
        System.out.println("Lancement d'un hill climbing sur la meilleure solution générée...");
        Solution superSol = algo.rechercheLocaleParPermutationsSimples(sols.get(0));
        superSol.getGantt().afficherGantt();

        System.out.println();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Tests sur les mutations                                       ");
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        System.out.println("Solution considérée :");
        TestDonnees.solution1().afficherSolution();
        System.out.println("Mutation Swap/OS :");
        Solution solMutee1 = CalculMutations.mutationSwapSurOSSimple(TestDonnees.solution1());
        solMutee1.afficherSolution();
        System.out.println("Mutation Changement machine :");
        Solution solMutee2 = CalculMutations.mutationChangementSurMASimple(solMutee1);
        solMutee2.afficherSolution();

        System.out.println();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Tests algorithme génétique Tournoi + Mutations                ");
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        System.out.println("Génération de 100 solutions aléatoires...");
        ArrayList<Solution> sols2 = CalculSolutions.genererNSolutionsAleatoiresTriees(pb2,100);
        AlgoGenetique algo2 = new AlgoGenetique();
        System.out.println("Lancement d'un algo sur 100 générations");
        Solution solGenetique = algo2.algoGenetiqueTournoiMutations(sols2, 100);
        System.out.println("Solution obtenue par l'algo génétique : "+solGenetique.getCout());
        System.out.println("Hill-climbing sur celle-ci");
        Solution apresHC = algo.rechercheLocaleParPermutationsSimples(solGenetique);
        System.out.println("Solution finale obtenue : "+apresHC.getCout());




    }
}
