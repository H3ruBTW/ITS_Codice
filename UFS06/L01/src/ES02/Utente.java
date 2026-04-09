package ES02;

import java.time.LocalDate;

public class Utente {
    private int ID;
    private String nome, cognome;
    private LocalDate dataNascita;

    public Utente(int ID, String nome, String cognome, LocalDate dataNascita){
        this.ID = ID;
        setNome(nome);
        setCognome(cognome);
        setDataNascita(dataNascita);
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
    }

    public void setCognome(String cognome) {
        if(!cognome.isBlank())
            this.cognome = cognome;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    @Override
    public String toString(){
        return "{\nID: " + ID + "\nNome: " + nome + "\nCognome: " + cognome + "\nData di nascita: " + dataNascita + "\n}";
    }
}
