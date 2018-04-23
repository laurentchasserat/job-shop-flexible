package donneesDuProbleme;

public class Tuple {

    private Integer indexMachine;
    private Integer duree;

    public Tuple(Integer maMachine, Integer maDuree) {
        this.indexMachine = maMachine;
        this.duree = maDuree;
    }

    public int getMachine() { return indexMachine; }
    public int getDuree() { return duree; }
}
