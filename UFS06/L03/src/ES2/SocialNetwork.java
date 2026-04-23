package ES2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

public class SocialNetwork {
    public static void main(String[] args) {
        ArrayList<Persona> persone = new ArrayList<>();
        SocialNetwork sc = new SocialNetwork();

        for(int i=0; i<20; i++){
            persone.add(new Persona("Persona " + i));
            System.out.println(persone.get(i));
        }

        Predicate<Persona> allGiovaniDonne = p -> p.getSesso().equals("Femmina") && 
                                                    LocalDate.now().getYear()-p.getDataN().getYear() >= 18 && 
                                                    LocalDate.now().getYear()-p.getDataN().getYear() < 30;
        Predicate<Persona> allMaschi = p -> p.getSesso().equals("Maschio");
        Predicate<Persona> allStranieri = p -> !p.getNazionalita().equals("Italiana");

        System.out.println("Donne giovani:\n");
        inviaMessaggio(sc.getWithFilter(persone, allGiovaniDonne), "Sei una donna tra i 18 e 29");

        System.out.println("\nMaschi:\n");
        inviaMessaggio(sc.getWithFilter(persone, allMaschi), "sei maschio");

        System.out.println("\nStranieri:\n");
        inviaMessaggio(sc.getWithFilter(persone, allStranieri), "Immigrato");
    }

    public ArrayList<Persona> getWithFilter(ArrayList<Persona> prs, Predicate<Persona> pr){
        ArrayList<Persona> result = new ArrayList<>();

        for (Persona persona : prs) {
            if(pr.test(persona))
                result.add(persona);
        }

        return result;
    }

    public static void inviaMessaggio(ArrayList<Persona> prs, String msg){
        prs.forEach(p -> System.out.println("Ciao, " + p.getNomeCompleto() + "\n" + msg));
    }
}
