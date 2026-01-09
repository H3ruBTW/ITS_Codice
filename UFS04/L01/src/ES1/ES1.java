package ES1;

import java.util.Scanner;

public class ES1 {
    public static void main(String[] args) {
        System.out.println("Scrivi tre numeri");

        Scanner scanner = new Scanner(System.in);

        System.out.print("A: ");
        float n1 = scanner.nextFloat();

        System.out.print("B: ");
        float n2 = scanner.nextFloat();

        System.out.print("C: ");
        float n3 = scanner.nextFloat();

        System.out.println("La somma dei tre numeri è: " + (n1+n2+n3));
        System.out.println("La media dei tre numeri è: " + (n1+n2+n3)/3);

        scanner.close();
    }
}
