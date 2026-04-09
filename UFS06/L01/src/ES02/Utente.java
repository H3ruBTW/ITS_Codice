package ES02;

import java.time.LocalDate;

public class Utente {
    private int ID;
    private String nome, cognome;
    private LocalDate dataNascita;

    public Utente(int ID, String nome, String cognome, int GG, int MM, int YYYY){
        this.ID = ID;
        setNome(nome);
        setCognome(cognome);
        setDataNascita(GG, MM, YYYY);
    }

    public int getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setNome(String nome) {
        if(!nome.isBlank())
            this.nome = nome;
        else
            throw new IllegalArgumentException("Nome non inserito");
    }

    public void setCognome(String cognome) {
        if(!cognome.isBlank())
            this.cognome = cognome;
        else
            throw new IllegalArgumentException("Cognome non inserito");
    }

    public void setDataNascita(int GG, int MM, int YYYY) {
        try {
            dataNascita = LocalDate.of(YYYY, MM, GG);
            return;
        } catch (Exception e) {}

        throw new IllegalArgumentException("Data non formattabile");
        
    }

    @Override
    public String toString(){
        return "{\nID: " + ID + "\nNome: " + nome + "\nCognome: " + cognome + "\nData di nascita: " + dataNascita + "\n}";
    }
}
