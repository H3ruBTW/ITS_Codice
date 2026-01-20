package ES5;

import java.util.Scanner;

public class ES5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Scrivi una frase e ne conterò le vocali\n");

        String frase = scanner.nextLine().toLowerCase();

        int cont = 0;

        for (int i = 0; i < frase.length(); i++) {
            switch (frase.charAt(i)) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    cont++;
                    break;
            
                default:
                    break;
            }
        }

        System.out.println("\nIl numero di vocali presenti nella frase è: " + cont);

        scanner.close();
    }
}
