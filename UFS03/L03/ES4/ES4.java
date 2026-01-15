package ES4;

import java.util.Scanner;

public class ES4 {
    public static void main(String[] args) {
        int num;

        Scanner scanner = new Scanner(System.in);

        int somma = 0;

        do{
            num = scanner.nextInt();

            if(num != 0){
                if(num%3 == 0 && num%5 != 0)
                    somma += num;
            }
        }while(num != 0);

        System.out.println("La somma è " + somma);

        scanner.close();
    }
}
