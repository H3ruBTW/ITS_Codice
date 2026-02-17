package negozio;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

public class Vendita {
    private Prodotto prodotto;
    private LocalDate dataVendita;
    private String metodoPagamento;
    HashSet<String> possibiliPag = new HashSet<>(Arrays.asList("carta", "contanti", "finanziamento"));

    public Prodotto getProdotto() {
        return prodotto;
    }

    public LocalDate getDataVendita() {
        return dataVendita;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) throws IllegalArgumentException {
        if(possibiliPag.contains(metodoPagamento.toLowerCase()))
            this.metodoPagamento = metodoPagamento.toLowerCase();
        else
            throw new IllegalArgumentException("Metodo non valido");
    }

    public Vendita(Prodotto prodotto, String metodoPagamento) throws IllegalArgumentException{
        this.prodotto = prodotto;
        dataVendita = LocalDate.now();
        setMetodoPagamento(metodoPagamento);
    }

    @Override
    public String toString() {
        return "\nVendita" +
        "\n" + prodotto +
        "\nData " + dataVendita +
        "\nMetodo Pagamento " + metodoPagamento;
    }
}
