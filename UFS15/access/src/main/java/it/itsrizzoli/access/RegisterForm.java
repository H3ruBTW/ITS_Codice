package it.itsrizzoli.access;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterForm {
    @NotNull
    @Size(min=2, max=20, message = "Minimo 2, massimo 20 caratteri")
    String nome, cognome;

    @NotNull
    @Size(min=4, max=20, message = "Minimo 4, massimo 20 caratteri")
    String username;

    @NotNull
    @Pattern(regexp = "^[\\S]{8,}$", message = "La tua password ha meno di 8 caratteri o contiene spazi")
    String pass;

    public RegisterForm(String nome, String cognome, String username, String pass){
        setNome(nome);
        setCognome(cognome);
        setUsername(username);
        setPass(pass);
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
