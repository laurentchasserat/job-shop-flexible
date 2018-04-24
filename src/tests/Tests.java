package tests;

import algos.CalculSolutions;
import donneesDuProbleme.Parser;
import donneesDuProbleme.Probleme;
import donneesDuProbleme.Solution;

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

        /* TEMPORAIREMENT COMMENTE PARCE QUE PLUS LISIBLE POUR DEBUGER

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Test de la création d'un problème à l'aide du parser sur le jeu de données \"TextData/Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk01.fjs\" : ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        Probleme pb2 = Parser.parse("./TextData/Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk01.fjs");
        pb2.afficherProbleme();

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
        System.out.println("-------------------------------------------------------------");
        System.out.println("Test de la génération et de l'affichagedu diagramme de Gantt ");
        System.out.println("-------------------------------------------------------------");
        System.out.println();
        System.out.println("Pour la solution MA = ( ( 1 2 1 ) ( 2 1 3 ) ( 3 2 ) ), OS = ( 1 1 1 2 2 2 3 3 )");
        sol1.getGantt().afficherGantt();
        System.out.println();
        System.out.println("Pour la solution MA = ( ( 1 2 1 ) ( 2 1 3 ) ( 3 2 ) ), OS = ( 1 2 3 1 2 3 1 2 )");
        sol2.getGantt().afficherGantt();

        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Test de la génération d'une solution par heuristique gloutonne pour le problème du cours ");
        System.out.println("-----------------------------------------------------------------------------------------");
        Solution solGloutonne = CalculSolutions.heuristiqueGloutonne(pb1);
        solGloutonne.afficherSolution();
        solGloutonne.getGantt().afficherGantt();
    }
}
