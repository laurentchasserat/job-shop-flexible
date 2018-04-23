package donneesDuProbleme;

import tests.TestDonnees;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Parser {

    public static Probleme parse(String path) {

        // Liste des jobs à venir
        ArrayList<Job> jobs = new ArrayList<>();
        Probleme problemToReturn = new Probleme(new ArrayList<>(), 0, 0);

        try{

            InputStream flux=new FileInputStream(path);
            InputStreamReader lecture=new InputStreamReader(flux);
            BufferedReader buff=new BufferedReader(lecture);
            String ligne;
            // Toute forme d'espace
            String delims = "\\s+";

            ligne = buff.readLine();

            String[] tokens = ligne.split(delims);
            int nbMachines = Integer.parseInt(tokens[1]);
            int nbJobs = Integer.parseInt(tokens[0]);

            //Pour chaque job
            while ((ligne=buff.readLine())!=null){

                if (!ligne.isEmpty()) {
                    tokens = ligne.split(delims);

                    ArrayList<Tuple> tuples;
                    ArrayList<Activite> activites = new ArrayList<>();


                    // Pour chaque activité
                    for (int i = 0; i < tokens.length; ) {


                        // Reset des tuples
                        tuples = new ArrayList<>();

                        if (i == 0) {
                            // Premier chiffre inutile
                            //Si la ligne commence par un espace alors on saute un caractère de plus
                            if (tokens[0].isEmpty()) i++;
                            i++;

                        } else {

                            //Le premier nombre représente le nombre de machine pour cette activité
                            int nbMachinesPossibles = Integer.parseInt(tokens[i]);
                            i++;

                            // Pour chaque paire machine-temps
                            for (int k = 0; k < nbMachinesPossibles; k++) {
                                Tuple t = new Tuple(Integer.parseInt(tokens[i]), Integer.parseInt(tokens[i + 1]));
                                i++;
                                i++;
                                tuples.add(t);
                            }
                        }

                        // On ajoute l'activité (sauf au premier passage qui est inutile);
                        if (tokens[0].isEmpty()) { // Si les lignes commencent par un espace
                            if (i > 2) activites.add(new Activite(tuples));
                        } else { // Si les lignes commencent par un chiffre
                            if (i > 1) activites.add(new Activite(tuples));
                        }

                    }

                    // Fini de créer les activités : on les ajoute au job
                    jobs.add(new Job(activites));

                }
            }

            // Fini de lire : on peut générer le problème
            buff.close();
            problemToReturn = new Probleme(jobs, nbMachines, nbJobs);



        }
        catch (Exception e){

            System.out.println(e.toString());

        }

        return problemToReturn;

    }

}
