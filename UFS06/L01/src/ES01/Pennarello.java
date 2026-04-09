package ES01;

public class Pennarello extends Scrive {
    private final int CONSUMO = 2;

    @Override
    public void scrive(String scrittura){
        String char_scritti = "";
        char c;

        for(int i=0; scrittura.length()>i; i++){
            
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
