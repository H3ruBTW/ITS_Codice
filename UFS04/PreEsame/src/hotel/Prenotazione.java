package hotel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prenotazione {
    private Cliente cliente;
    private Camera camera;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Camera getCamera() {
        return camera;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCheckIn(LocalDate checkIn) {
        if(checkOut.isAfter(checkIn) || checkOut == null)
            this.checkIn = checkIn;
        else
            throw new IllegalArgumentException("Prima di checkIn");
        
    }

    public void setCheckOut(LocalDate checkOut) {
        if(checkOut.isAfter(checkIn))
            this.checkOut = checkOut;
        else
            throw new IllegalArgumentException("Prima di checkIn");
    }

    public Prenotazione(Cliente cliente, Camera camera, LocalDate checkIn, LocalDate checkOut){
        this.cliente = cliente;
        this.camera = camera;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getGiorniSoggiorno(){
        return (int)ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public double getCostoTotale(){
        return getGiorniSoggiorno() * camera.getPrezzo();
    }

    public boolean isAttiva(){
        return checkIn.isBefore(LocalDate.now()) && checkOut.isAfter(LocalDate.now());
    }

    public boolean isFutura(){
        return checkIn.isAfter(LocalDate.now());
    }

    public boolean isTerminata(){
        return checkIn.isBefore(LocalDate.now()) && checkOut.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Cliente\n" + cliente.toString() + "\nCamera" + camera.toString() + "checkIn " + checkIn.toString() + "checkOut " + checkOut.toString();
    }
}
