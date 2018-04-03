package donneesDuProbleme;

public class Tuple {

    private int indexMachine;
    private int duree;

    public Tuple(int maMachine, int maDuree) {
        this.indexMachine = maMachine;
        this.duree = maDuree;
    }

    public int getMachine() { return indexMachine; }
    public int getDuree() { return duree; }
}
