package ES1;

public class Transazione  {
    private String tipologia, descrizione, data;
    private double quantitaSoldi;

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        switch (tipologia) {
            case "Pagamento", "Prelievo", "Deposito":
                this.tipologia = tipologia;
                break;
        
            default:
                throw new IllegalArgumentException("Errore di battitua per argomento tipologia");
        }
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getQuantitaSoldi() {
        return quantitaSoldi;
    }

    public void setQuantitaSoldi(double quantitaSoldi) {
        if(quantitaSoldi>=0)
            this.quantitaSoldi = quantitaSoldi;
        else
            throw new IllegalArgumentException("Soldi in negativo");
    }

    public Transazione(String tipologia, String descrizione, double quantitaSoldi, String data) {
        switch (tipologia) {
            case "Pagamento", "Prelievo", "Deposito":
                this.tipologia = tipologia;
                break;
        
            default:
                throw new IllegalArgumentException("Tipologia errata di tipologia");
        }

        this.descrizione = descrizione;
        
        if(quantitaSoldi>=0)
            this.quantitaSoldi = quantitaSoldi;
        else
            throw new IllegalArgumentException("Soldi in negativo");

        this.data = data;
    }
}