package ES1;

public class Libro {
    private String Autore, Titolo;
    private Integer NPag, AnnoP;
    private double Prezzo;

    public String getAutore() {
        return Autore;
    }

    public void setAutore(String Autore) {
        this.Autore = Autore;
    }

    public String getTitolo() {
        return Titolo;
    }

    public void setTitolo(String Titolo) {
        this.Titolo = Titolo;
    }
    
    public Integer getNPag() {
        return NPag;
    }

    public void setNPag(Integer NPag) {
        this.NPag = NPag;
    }

    public Integer getAnnoP() {
        return AnnoP;
    }

    public void setAnnoP(Integer AnnoP) {
        this.AnnoP = AnnoP;
    }

    public double getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(double prezzo) {
        Prezzo = prezzo;
    }

    public Libro(String Autore, String Titolo, Integer NPag, double Prezzo, Integer AnnoP){
        this.Autore = Autore;
        this.Titolo = Titolo;
        this.NPag = NPag;
        this.Prezzo = Prezzo;
        this.AnnoP = AnnoP;
    }

    public double VPP(){
        //VPP con solo due decimali
        return Math.floor(Prezzo/(double)NPag*100)/100;
    }

    public Integer EtaLibro(){
        return 2026-AnnoP;
    }

    public Boolean isVintage(){
        return EtaLibro()>=50;
    }

    public void Lunghezza(){
        if(NPag<200)
            System.out.println("Libro corto");
        else if(NPag>400)
            System.out.println("Libro lungo");
        else
            System.out.println("Libro medio");
    }

    public double Sconto(double sconto){
        //Sconto con solo due decimali
        return Math.floor(Prezzo*((100-sconto)/100)*100)/100;
    }

    public double valoreReale(){
        double NuovoValore = Prezzo;

        for(Integer età = EtaLibro(); età>=10; età-=10){
            NuovoValore *= 0.8;
        }

        return Math.floor(NuovoValore*100)/100;
    }
}
