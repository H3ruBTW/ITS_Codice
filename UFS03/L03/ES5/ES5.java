package ES5;

import java.util.Scanner;

public class ES5 {
    public static void main(String[] args) {
        Integer num;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il numero di numeri da inserire: ");
        Integer num_i = scanner.nextInt(), max = null, pos = 0;

        for(Integer i=0; i<num_i; i++){
            num = scanner.nextInt();

            if(max == null || num > max){
                max = num;
                pos = i+1;
            }   
        }

        System.out.println("Il numero " + max + " è il maggiore e si è trovato nella posizione n° " + pos);
    }
    

}
