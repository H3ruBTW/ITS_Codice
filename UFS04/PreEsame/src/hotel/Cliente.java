package hotel;

public class Cliente {
    private String nome, cognome, mail, telefono;

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getMail() {
        return mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setCognome(String cognome) {

        if (!cognome.isBlank()) {
            this.cognome = cognome;
        } else {
            throw new IllegalArgumentException("Non hai inserito niente");
        }
    }

    public void setNome(String nome) {
        if (!nome.isBlank()) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Non hai inserito niente");
        }
    }

    public void setMail(String mail) {
        if (!mail.isBlank() && mail.contains("@")) {
            this.mail = mail;
        } else {
            throw new IllegalArgumentException("Formato non accettato");
        }
    }
    
    public void setTelefono(String telefono) {
        if (!telefono.isBlank() && telefono.startsWith("+")) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("Formato non accettato");
        }
    }

    public Cliente(String nome, String cognome, String mail, String telefono){
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Nome " + getNome() + "\nCognome " + getCognome() + "\nMail " + getMail() + "\nTelefono " + getTelefono();
    }
}
