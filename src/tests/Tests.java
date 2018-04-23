package tests;

import donneesDuProbleme.Parser;
import donneesDuProbleme.Probleme;

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

        /* A noter : tous les jeux de données ne marchent pas parce qu'ils sont pas tous mis en forme de la même manière...
        J'ai essayé de faire au mieux : le parser gère les lignes vides et les espaces/tabulations ou non en début de ligne,
        mais des tabulations insérées au milieu d'une ligne provoquent une exception.

        En voici tout de même ci dessous quelques une que lon pourra utiliser ! */

        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("Test de la classe TestDonnees (création d'un problème \"à la main\" se basant sur l'exemple du cours) :");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println();
        Probleme pb1 = TestDonnees.exemple1();
        pb1.afficherProbleme();

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
    }
}
