package donneesDuProbleme;

import tests.TestDonnees;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Parser {

    public static Probleme parse(String path) {

        try{
            InputStream flux=new FileInputStream(path);
            InputStreamReader lecture=new InputStreamReader(flux);
            BufferedReader buff=new BufferedReader(lecture);
            String ligne;
            while ((ligne=buff.readLine())!=null){
                System.out.println(ligne);
            }
            buff.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return TestDonnees.exemple1();

    }

}
