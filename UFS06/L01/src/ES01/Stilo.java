package ES01;

import java.util.Arrays;
import java.util.HashSet;

public class Stilo extends Scrive {
    private final int CONSUMO = 1;

    @Override
    public void scrive(String scrittura){
        String char_scritti = "";
        HashSet<Character> char_non_cons = new HashSet<>(Arrays.asList( '(', ')', '{', '}', '@', '[', ']'));
        char c;

        for(int i=0; scrittura.length()>i; i++){
            if(!char_non_cons.contains(c = scrittura.charAt(i)))
                if(q_inchiostro - CONSUMO < 0){
                    System.out.println("Pennarello scarico");
                    break;
                } else {
                    if((c = scrittura.charAt(i)) != ' ')
                        q_inchiostro -= CONSUMO;
                    char_scritti += c;
                }
        }

        System.out.println("Quello che si voleva scrivere: \n" + scrittura);
        System.out.println("Quello che si è potuto scrivere: \n" + char_scritti);
    }
}
