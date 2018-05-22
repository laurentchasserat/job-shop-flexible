package tests;

import algos.AlgoGenetique;
import algos.AlgoRechercheLocale;
import algos.CalculSolutions;
import donneesDuProbleme.Parser;
import donneesDuProbleme.Probleme;
import donneesDuProbleme.Solution;
import outils.Probas;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class GenererRapportDeTests {
    public static void genererRapport() {
        try {


            File ff=new File("./rapport.txt");
            ff.createNewFile();
            FileWriter ffw=new FileWriter(ff);
            Date date = new Date();

            ffw.write("--------------------------------------------------------------------------------------\n");
            ffw.write("Rapport de tests de nos différents algorithmes autour du problème du Job Shop Flexible\n");
            ffw.write("sur différents jeux de données.\n");
            ffw.write("Date de génération : "+date.toString()+"\n");
            ffw.write("--------------------------------------------------------------------------------------\n\n");


            //----------------------------------------------------------------------------------------------------

            ffw.write("----------------------------------------------\n");
            ffw.write("Tests sur le jeu de données de l'exemple du TP\n");
            ffw.write("----------------------------------------------\n");
            Probleme pb1 = TestDonnees.exemple1();

            testerUnProbleme(pb1, ffw, 3, 5,10, 10);

            ffw.write("\n\n");

            //----------------------------------------------------------------------------------------------------

            ffw.write("----------------------------------------------------------------------------------\n");
            ffw.write("Tests sur le jeu de données \"Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk01.fjs\"\n");
            ffw.write("----------------------------------------------------------------------------------\n");
            Probleme pb2 = Parser.parse("./TextData/Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk01.fjs");

            testerUnProbleme(pb2, ffw, 10, 10, 50, 50);

            ffw.write("\n\n");

            //----------------------------------------------------------------------------------------------------

            ffw.write("----------------------------------------------------------------------------------\n");
            ffw.write("Tests sur le jeu de données \"Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk08.fjs\"\n");
            ffw.write("----------------------------------------------------------------------------------\n");
            Probleme pb3 = Parser.parse("./TextData/Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk08.fjs");

            // Très long !
            //testerUnProbleme(pb3, ffw);

            ffw.write("\n\n");

            //----------------------------------------------------------------------------------------------------

            ffw.close(); // fermer le fichier à la fin des traitements

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testerUnProbleme(Probleme pb1, FileWriter ffw,Integer nbDEssaisParStrategie,
                                         Integer nbSolutionsGenereesAleatoirementStrat2,
                                         Integer taillePopulation,
                                         Integer nbGenerations) throws IOException {

        long chrono;
        long temps;

        ArrayList<Solution> theBestOfAll = new ArrayList<>();

        ffw.write("Nb. jobs = "+pb1.getNbJobs()+", Nb. machines = "+pb1.getNbMachines()+".\n");

        ffw.write("\nStratégie 1 : Heuristique gloutonne puis Hill Climbing simple.\n\n");
        System.out.println("Test de l'algo de recherche locale");
        AlgoRechercheLocale hc = new AlgoRechercheLocale();
        chrono = System.currentTimeMillis();
        Solution sol1 = hc.rechercheLocaleParPermutationsSimples(CalculSolutions.heuristiqueGloutonne(pb1));
        temps = System.currentTimeMillis()-chrono;
        theBestOfAll.add(sol1);
        ffw.write("Coût de la solution trouvée : "+sol1.getCout()+" [temps : "+temps+" ms]\n");




        ffw.write("\nStratégie 2 : Génération de "+nbSolutionsGenereesAleatoirementStrat2+" solutions aléatoires puis Hill Climbing simple.\n\n");
        ffw.write("sur chacune d'entre elles. On garde la meilleure solution trouvée.\n");
        ffw.write("\nOn effectue cette stratégie "+nbDEssaisParStrategie+" fois pour évaluer la part d'aléatoire dans les solutions trouvées :\n\n");
        ArrayList<Solution> theBest = new ArrayList<>();
        ArrayList<Solution> sols;
        for (int i=0; i<nbDEssaisParStrategie; i++) {
            System.out.println("Test de génération aléatoire puis Hill Climbing numéro "+(i+1));
            chrono = System.currentTimeMillis();
            sols = CalculSolutions.genererNSolutionsAleatoiresTriees(pb1,nbSolutionsGenereesAleatoirementStrat2);
            ArrayList<Solution> solsAmeliorees = new ArrayList<>();
            for (Solution s : sols) {
                solsAmeliorees.add(hc.rechercheLocaleParPermutationsSimples(s));
            }
            solsAmeliorees.sort(Solution::compareTo);
            temps = System.currentTimeMillis()-chrono;
            theBest.add(solsAmeliorees.get(0));
            ffw.write("[Essai "+(i+1)+"] Coût de la meilleure solution trouvée : "+solsAmeliorees.get(0).getCout()+" [temps : "+temps+" ms]\n");
        }
        theBest.sort(Solution::compareTo);
        theBestOfAll.add(theBest.get(0));
        ffw.write("\nMeilleure : "+theBest.get(0).getCout()+", moyenne : "+Probas.MoyenneSols(theBest)
                +", ecart-type : "+Probas.EcartType(theBest)+".\n");




        ffw.write("\nStratégie 3 : Algorithme génétique à deux mutations et sélection par tournoi.\n\n");
        ffw.write("On génère une génération de "+taillePopulation+" solutions aléatoires. Les 25 meilleures vont engendrer\n");
        ffw.write("des enfants en mutant (permutations sur OS et changements sur MA, ce qui donne un pool\n");
        ffw.write("de 75 solutions. On effectue un tournoi pour en retenir 50 et on recommence sur "+nbGenerations+"\n");
        ffw.write("générations.\n");
        ffw.write("\nOn effectue cette stratégie "+nbDEssaisParStrategie+" fois pour évaluer la part d'aléatoire dans les solutions trouvées :\n\n");
        ArrayList<Solution> theBest2 = new ArrayList<>();
        ArrayList<Solution> sols2;
        AlgoGenetique ag = new AlgoGenetique();
        for (int i=0; i<nbDEssaisParStrategie; i++) {
            System.out.println("Test de l'algo génétique numéro "+(i+1));
            chrono = System.currentTimeMillis();
            sols2 = CalculSolutions.genererNSolutionsAleatoiresTriees(pb1,taillePopulation);
            Solution solGenetique = ag.algoGenetiqueTournoiMutations(sols2, nbGenerations);
            temps = System.currentTimeMillis()-chrono;
            //Idée : réaliser un hill climbing sur la dernière el plus
            ffw.write("[Essai "+(i+1)+"] Coût de la meilleure solution trouvée : "+solGenetique.getCout()+" [temps : "+temps+" ms]\n");
            theBest2.add(solGenetique);
        }
        theBest2.sort(Solution::compareTo);
        theBestOfAll.add(theBest2.get(0));
        ffw.write("\nMeilleure : "+theBest2.get(0).getCout()+", moyenne : "+Probas.MoyenneSols(theBest2)
                +", ecart-type : "+Probas.EcartType(theBest2)+".\n");
        theBestOfAll.sort(Solution::compareTo);
        ffw.write("\nDiagramme de Gantt de la meilleure solution trouvée parmi toutes ces stratégies :\n");
        theBestOfAll.get(0).getGantt().afficherGanttDansFichier(ffw);

    }
}
