package ES2;

import java.util.Random;
import java.util.Scanner;

public class ES2 {
    public static void main(String[] args) {
        int Numt;
        int num;

        Scanner scanner = new Scanner(System.in);

        Random random = new Random();

        Numt = random.nextInt(10)+1;

        boolean found = false;

        do{

            num = scanner.nextInt();

            if(num == Numt)
                found = true;

        }while(!found && num != -1);

        if(found)
            System.out.println("Il numero " + Numt + " è stato trovato");
        else
            System.out.println("Il numero non è stato trovato :(");
    }
}
