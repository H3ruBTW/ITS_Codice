package database;

import java.util.ArrayList;
import java.util.Date;

public class Materia {
    private String nome;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    private ArrayList<Studente> studenti = new ArrayList<>();

    public ArrayList<Studente> getStudenti() {
        return studenti;
    }

    public void addStudenti(String nome, String mid_name, String cognome, Date data) {
        Studente s = new Studente();

        s.setNome(nome);
        s.setCognome(cognome);
        if(!mid_name.isBlank()){
            s.setMid_name(mid_name);
        }
        s.setData(data);

        studenti.add(s);
    }
}
