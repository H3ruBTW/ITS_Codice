package database;

import java.util.ArrayList;

public class VistaDocente {
    String username;

    public VistaDocente(String username){
        this.username = username;
    }

    public static void main(String[] args) {
        VistaDocente rossi = new VistaDocente("m.rossi");

        rossi.printYourMaterie();
    }

    public void printYourMaterie(){
        ArrayList<Materia> allInfo = DaoDocente.getInfo(username);

        for(Materia m : allInfo){
            System.out.println("Materia: " + m.getNome());

            ArrayList<Studente> studenti = m.getStudenti();

            for (Studente s : studenti) {
                System.out.println("\tNome: " + s.getNome());
                if(!s.getMid_name().isBlank())
                    System.out.println("\tCognome: " + s.getMid_name());
                System.out.println("\tCognome: " + s.getCognome());
                System.out.println("\tData di nascita: " + s.getData());
                System.out.println();
            }
        }
    }
}
