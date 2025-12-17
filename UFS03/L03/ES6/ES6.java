package ES6;

import java.util.Random;
import java.util.Scanner;

public class ES6 {
    public static void main(String[] args) {
        int Numt, NVol = 0;
        int num;

        Scanner scanner = new Scanner(System.in);

        Random random = new Random();

        Numt = random.nextInt(10)+1;

        do{

            num = scanner.nextInt();

            if(num == Numt)
                NVol++;

        }while(num != 0);

        System.out.println("Il numero " + Numt + " è stato trovato n° " + NVol + " volte");

    }
}
