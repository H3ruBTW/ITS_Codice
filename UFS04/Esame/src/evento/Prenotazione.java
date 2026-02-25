package evento;

public class Prenotazione {
    private Evento evento;
    private String nome_persona;
    private int numero_posti;

    public Evento getEvento() {
        return evento;
    }

    public String getNome_persona() {
        return nome_persona;
    }

    public int getNumero_posti() {
        return numero_posti;
    }

    public void setNome_persona(String nome_persona) throws IllegalArgumentException {
        if(!nome_persona.isBlank())
            this.nome_persona = nome_persona;
        else
            throw new IllegalArgumentException("Blank");
    }

    public void setNumero_posti(int numero_posti) {
        if(numero_posti > 0)
            this.numero_posti = numero_posti;
        else
            throw new IllegalArgumentException("<0");
    }

    public Prenotazione(Evento evento, String nome_persona, int numero_posti) throws IllegalArgumentException {
        this.evento = evento;
        setNome_persona(nome_persona);
        setNumero_posti(numero_posti);
    }

    @Override
    public String toString() {
        return "Prenotazione [Evento [" +
        evento.descrizioneEvento() +
        " ],Nome Persona " + nome_persona +
        ",Posti presi " + numero_posti + "]";
    }
}
