package ES4;

import java.util.Scanner;

public class ES4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer n;

        do{
            System.out.print("Scrivi un numero e ti dirò se è pari o dispari (scrivi 0 per uscire): ");
            n = scanner.nextInt();

            if(n%2 == 0 && n!=0)
                System.out.println("Pari");
            else if(n%2 == 1)
                System.out.println("Dispari");
        } while (n!=0);
    }
}
