package ES3;

import java.util.ArrayList;

public class Gestionario {

    public static void main(String[] args) {
        ArrayList<Dipendente> dipendenti = new ArrayList<>();

        for(int i=0; i<20; i++){
            dipendenti.add(new Dipendente("Persona " + i));
        }

        for (int i = 1; i <= 3; i++) {
            final int counter = i;
            System.out.println("Dipendenti con ruolo");
            dipendenti.stream()
                        .filter(d -> d.getRuolo() == counter)
                        .forEach(d -> System.out.println(d));
        }


        System.out.println("Dipendenti in ordine di privilegio di ruolo");
        dipendenti.sort((a, b) -> Integer.compare(a.getRuolo(), b.getRuolo()));
        dipendenti.forEach((d) -> System.out.println(d));

        System.out.println("Dipendenti in ordine di stipendio che rubano");
        dipendenti.sort((a, b) -> Integer.compare(a.getStipendio(), b.getStipendio()));
        dipendenti.reversed().forEach((d) -> System.out.println(d));
    }
    
}
