package ES4;

import java.util.Scanner;

public class ES4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer n;

        do{
            System.out.print("\nScrivi un numero e ti dirò se è pari o dispari (scrivi 0 per uscire): ");
            if((n = scanner.nextInt()) == 0)
                break;

            if(n%2 == 0 && n!=0)
                System.out.println("\nPari");
            else
                System.out.println("\nDispari");
        } while (n!=0);

        System.out.println("\nGrazie e arrivederci!");

        scanner.close();
    }
}
