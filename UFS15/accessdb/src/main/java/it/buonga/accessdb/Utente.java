package it.buonga.accessdb;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(min=2, max=20, message = "Minimo 2, massimo 20 caratteri")
    private String nome, cognome;

    @NotNull
    @Size(min=4, max=20, message = "Minimo 4, massimo 20 caratteri")
    private String username;

    @NotNull
    @Pattern(regexp = "^[\\S]{8,}$", message = "La tua password ha meno di 8 caratteri o contiene spazi")
    private String pass;

    public Utente(){};

    public Utente(Integer id, String nome, String cognome, String username, String pass){
        setId(id);
        setNome(nome);
        setCognome(cognome);
        setUsername(username);
        setPass(pass);
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
