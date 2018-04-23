import donneesDuProbleme.*;

public class Main {

    public static void main(String[] args) {

        Probleme pb = Parser.parse("random.txt");
        pb.afficherProbleme();

    }

}
