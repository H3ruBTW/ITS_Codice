package database;

import java.util.Date;

public class Studente {
    private String nome, mid_name, cognome;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMid_name() {
        return mid_name;
    }
    public void setMid_name(String mid_name) {
        this.mid_name = mid_name;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    Date data;

    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
}
