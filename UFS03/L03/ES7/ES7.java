package ES7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ES7 {
    public static void main(String[] args) {
        List<Float> Numeri = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        do{

            Numeri.add(scanner.nextFloat());

        }while(Numeri.get(Numeri.size()-1) != 0);

        float media_i = 0;

        for(int i=0; i<Numeri.size()-1; i++){
            media_i += Numeri.get(i);
        }

        media_i /= Numeri.size()-1;

        float media_f = 0;

        int times = 0;

        for (int i=0; i<Numeri.size()-1 ; i++) {
            if(Numeri.get(i)>media_i){
                media_f += Numeri.get(i);
                times++;
            }
                
        }

        System.out.println("La media dei numeri maggiori a " + media_i + " è " + media_f/times);
    }
}
