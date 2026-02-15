package hotel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Hotel {
    String nome, indirizzo;
    ArrayList<Camera> camere = new ArrayList<>();
    ArrayList<Prenotazione> prenotazioni = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setNome(String nome) {
        if(!nome.isBlank())
            this.nome = nome;
        else
            throw new IllegalArgumentException("Blank");
    }

    public void setIndirizzo(String indirizzo) {
        if(!indirizzo.isBlank())
            this.indirizzo = indirizzo;
        else
            throw new IllegalArgumentException("Blank");
    }

    public Hotel(String nome, String indirizzo){
        setIndirizzo(indirizzo);
        setNome(nome);
    }

    public void aggiungiCamera(short numero, String tipo, double prezzo){
        camere.add(new Camera(numero, tipo, prezzo));
    }

    public void aggiungiPrenotazione(String nome, String cognome, String mail, String telefono, short numero, LocalDate checkIn, LocalDate checkOut){
        short i;
        if((i = cercaCamera(numero)) != -1)
            prenotazioni.add(new Prenotazione(new Cliente(nome, cognome, mail, telefono), camere.get(i), checkIn, checkOut));
        else
            throw new IllegalArgumentException("Camera non esiste");
    }

    public short cercaCamera(short numero){
        for (short i = 0; i < camere.size(); i++) {
                if(camere.get(i).getNumero() == numero)
                    return i;
        }

        return -1;
    }

    public void getPrenotazioniAttive(){
        System.out.println("Prenotazioni attive");
        for (Prenotazione prenotazione : prenotazioni) {
            if(prenotazione.isFutura() || prenotazione.isAttiva())
                prenotazione.toString();
        }
    }

    public void getPrenotazioniCliente(String mail){
        System.out.println("Prenotazioni cliente: " + mail);

        for (Prenotazione prenotazione : prenotazioni) {
            if(prenotazione.getCliente().getMail().equalsIgnoreCase(mail)){
                prenotazione.toString();
            }
        }
    }

    public void printPrenotazioni(){
        System.out.println("Prenotazioni");
        for (Prenotazione prenotazione : prenotazioni) {
            prenotazione.toString();
        }
    }

    public void printCamere(){
        System.out.println("Camere");
        for (Camera camera : camere) {
            camera.toString();
        }
    }

    public boolean isCameraLibera(short numero, LocalDate dateInizio, LocalDate dateFine){
        if(cercaCamera(numero) != -1){
            for (Prenotazione prenotazione : prenotazioni) {
                LocalDate checkIn = prenotazione.getCheckIn();
                LocalDate checkOut = prenotazione.getCheckOut();
                if(prenotazione.getCamera().getNumero() == numero)
                    if(dateInizio.isBefore(checkOut) && dateFine.isAfter(checkIn))
                        return false;
            }

            return true;
        } else
            return false;
    }

}
