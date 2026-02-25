package evento;

import java.time.LocalDate;

public abstract class Evento {
    protected String nome;
    protected LocalDate data;
    protected int numero_posti;
    protected int numero_posti_presi;
    protected double prezzo;

    public LocalDate getData() {
        return data;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero_posti() {
        return numero_posti;
    }

    public int getNumero_posti_presi() {
        return numero_posti_presi;
    }

    public void setNome(String nome) throws IllegalArgumentException {
        if(!nome.isBlank())
            this.nome = nome;
        else
            throw new IllegalArgumentException("Blank");
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setNumero_posti(int numero_posti) throws IllegalArgumentException {
        if(numero_posti > 0)
            this.numero_posti = numero_posti;
        else
            throw new IllegalArgumentException("<=0");
    }

    public void setPrezzo(double prezzo) throws IllegalArgumentException {
        //magari è ad accesso gratuito
        if(prezzo >= 0)
            this.prezzo = prezzo;
        else
            throw new IllegalArgumentException("<0");
        
    }

    public void addPostiPresi(int n) throws IllegalArgumentException{
        if(getPostiDisponibili() > n)
            numero_posti_presi += n;
        else
            throw new IllegalArgumentException("Troppi posti presi");
    }

    public Evento(String nome, LocalDate data, int numero_posti, double prezzo) throws IllegalArgumentException{
        setNome(nome);
        setData(data);
        setNumero_posti(numero_posti);
        setPrezzo(prezzo);
        numero_posti_presi = 0;
    }

    public int getPostiDisponibili(){
        return numero_posti - numero_posti_presi;
    }

    public double getPercentualePostiDisp(){
        //prendo la percentuale con due decimali massimo
        return Math.floor(getPostiDisponibili()/numero_posti*10000)/100;
    }

    public abstract String descrizioneEvento();

    public double getPrezzoDinamico(){
        if(getPercentualePostiDisp()<=20)
            return prezzo * 1.15;
        else
            return prezzo;
    }
}
