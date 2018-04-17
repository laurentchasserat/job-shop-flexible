import donneesDuProbleme.*;
import tests.TestDonnees;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Probleme pb = Parser.parse("random.txt");
        pb.afficherProbleme();

    }

}
