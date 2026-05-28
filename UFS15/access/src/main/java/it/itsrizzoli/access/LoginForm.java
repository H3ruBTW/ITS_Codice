package it.itsrizzoli.access;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginForm {
    @NotNull
    @Size(min=4, max=20, message = "Minimo 4, massimo 20 caratteri")
    String username;

    @NotNull
    @Pattern(regexp = "^[\\S]{8,}$", message = "La tua password ha meno di 8 caratteri o contiene spazi")
    String pass;

    public LoginForm(String username, String pass){
        setUsername(username);
        setPass(pass);
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
