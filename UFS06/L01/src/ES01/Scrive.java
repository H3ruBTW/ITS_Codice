package ES01;

public abstract class Scrive {
    protected int q_inchiostro = 100;

    public int getQ_inchiostro() {
        return q_inchiostro;
    }

    public abstract void scrive(String scrittura);
}
