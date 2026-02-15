package hotel;

import java.util.Arrays;
import java.util.HashSet;

public class Camera {
    private short numero;
    private String tipo;
    private double prezzo;
    private HashSet<String> tipologie = new HashSet<>(Arrays.asList("singola", "doppia", "suite"));

    public short getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setNumero(short numero) {
        this.numero = numero;
    }

    public void setPrezzo(double prezzo) {
        if(prezzo>0)
            this.prezzo = prezzo;
        else
            throw new IllegalArgumentException("Prezzo sotto 0");
    }

    public void setTipo(String tipo) {
        if(tipologie.contains(tipo.toLowerCase())){
            this.tipo = tipo.toLowerCase();
        } else {
            throw new IllegalArgumentException("Tipologia non accettata");
        }

    }

    public Camera(short numero, String tipo, double prezzo){
        this.numero = numero;

        if(tipologie.contains(tipo.toLowerCase())){
            this.tipo = tipo.toLowerCase();
        } else {
            throw new IllegalArgumentException("Tipologia non accettata");
        }

        if(prezzo>0)
            this.prezzo = prezzo;
        else
            throw new IllegalArgumentException("Prezzo sotto 0");
    }

    @Override
    public String toString() {
        return "Numero " + getNumero() + "\nTipo " + getTipo() + "\nPrezzo " + getPrezzo();
    }
}
