package outils;

import algos.AlgoGenetique;
import algos.AlgoRechercheLocale;
import algos.CalculSolutions;
import donneesDuProbleme.Parser;
import donneesDuProbleme.Probleme;
import donneesDuProbleme.Solution;
import tests.GenererRapportDeTests;
import tests.TestDonnees;
import tests.Tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    public static void lancerMenu() {

        BufferedReader entree = new BufferedReader
                (new InputStreamReader(System.in));
        String ligne;
        Probleme pb;
        Solution sol;



        try {

            System.out.println("+---------------------------------------------------+");
            System.out.println("|           Projet Méta-Heuristiques 4IR            |");
            System.out.println("|                                                   |");
            System.out.println("| Elements de solution au Flexible Job Shop problem |");
            System.out.println("+---------------------------------------------------+");
            System.out.println("|        CHASSERAT Laurent & HERNANDEZ Lucile       |");
            System.out.println("+---------------------------------------------------+");

            boolean over = false;

            while(!over) {


                System.out.println();
                System.out.println("--> Choisissez l'action à effectuer : ");
                System.out.println("1 - Trouver une solution triviale par heuristique gloutonne");
                System.out.println("2 - Appliquer un algorithme de Hill-climbing");
                System.out.println("3 - Appliquer un algorithme génétique");
                System.out.println("4 - Générer un rapport de tests");
                System.out.println("5 - Afficher les tests des développeurs");
                System.out.println("6 - Quitter l'application.");


                boolean ok = false;

                while (!ok) {
                    ligne = entree.readLine();

                    if (ligne.equals("1")) {
                        ok = true;
                        System.out.println("Calcul d'une solution triviale par heuristique gloutonne.");
                        pb = choisirProbleme();
                        sol = CalculSolutions.heuristiqueGloutonne(pb);
                        System.out.println();
                        sol.afficherSolution();
                        sol.getGantt().afficherGantt();
                    } else if (ligne.equals("2")) {
                        ok = true;
                        System.out.println("Calcul d'une solution avec un algorithme de Hill-climbing.");
                        pb = choisirProbleme();
                        AlgoRechercheLocale algo = new AlgoRechercheLocale();
                        sol = algo.rechercheLocaleParPermutationsSimples(CalculSolutions.heuristiqueGloutonne(pb));
                        System.out.println();
                        sol.afficherSolution();
                        sol.getGantt().afficherGantt();
                    } else if (ligne.equals("3")) {
                        ok = true;
                        System.out.println("Calcul d'unne solution avec un algotithme génétique.");
                        pb = choisirProbleme();
                        AlgoGenetique algo = new AlgoGenetique();
                        Integer tailleGénération = getInt("la taille d'une population");
                        Integer nbGénérations = getInt("le nombre de générations");
                        sol = algo.algoGenetiqueTournoiMutations(CalculSolutions.genererNSolutionsAleatoiresTriees(pb,tailleGénération),nbGénérations);
                        System.out.println();
                        sol.afficherSolution();
                        sol.getGantt().afficherGantt();
                    } else if (ligne.equals("4")) {
                        ok = true;
                        System.out.println("Génération d'un rapport de tests.");
                        GenererRapportDeTests.genererRapport();
                        System.out.println("Génération de rapport.txt terminée.");
                    } else if (ligne.equals("5")) {
                        ok = true;
                        System.out.println("Affichage des tests des développeurs.");
                        Tests.runTests();
                        System.out.println("Tests développeurs terminés.");
                    } else if (ligne.equals("6")) {
                        ok = true;
                        over = true;
                        System.out.println("Arrêt en cours...");
                    }else {
                        System.out.println("Saisie incorrecte. Réessayez :");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Probleme choisirProbleme() {
        BufferedReader entree = new BufferedReader
                (new InputStreamReader(System.in));
        String ligne;
        Probleme pb = TestDonnees.exemple1();

        System.out.println("--> Choisissez le problème à traiter : ");
        System.out.println("1 - Exemple du sujet");
        System.out.println("2 - Données MK01");
        System.out.println("3 - Données MK02");
        System.out.println("4 - Données MK03");
        System.out.println("5 - Données MK04");

        boolean ok = false;

        while (!ok) {
            try {
                ligne = entree.readLine();
                if (ligne.equals("1")) {
                    ok = true;
                } else if (ligne.equals("2")) {
                    ok = true;
                    pb = Parser.parse("./TextData/Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk01.fjs");
                } else if (ligne.equals("3")) {
                    ok = true;
                    pb = Parser.parse("./TextData/Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk02.fjs");
                } else if (ligne.equals("4")) {
                    ok = true;
                    pb = Parser.parse("./TextData/Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk03.fjs");
                } else if (ligne.equals("5")) {
                    ok = true;
                    pb = Parser.parse("./TextData/Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk04.fjs");
                } else {
                    System.out.println("Saisie incorrecte. Réessayez :");
                }
            } catch (IOException e) {
                System.out.println("Erreur lors de la saisie. L'exemple du sujet sera retourné.");
                e.printStackTrace();
            }

        }

        return pb;
    }

    private static Integer getInt(String param) {
        BufferedReader entree = new BufferedReader
                (new InputStreamReader(System.in));
        String ligne;
        Integer res = -1;

        System.out.println("--> Saisissez "+param);

        boolean ok = false;

        while (!ok) {
            try {
                ligne = entree.readLine();
                try {
                    res =  Integer.parseInt(ligne);
                } catch (NumberFormatException e) {

                }
                if ( res>0 ) {
                    ok = true;
                }else {
                    System.out.println("Saisie incorrecte. Réessayez :");
                }
            } catch (IOException e) {
                System.out.println("Erreur lors de la saisie. -1 sera retourné.");
                e.printStackTrace();
            }

        }

        return res;

    }
}
