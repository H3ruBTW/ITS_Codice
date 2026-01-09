package ES4;

import java.util.Scanner;

public class ES4 {
    public static void main(String[] args) {
        System.out.println("Inserisci un numero");

        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();

        boolean primo = true;

        if(num <= 1)
            primo = false;



        for(int i=2; primo && i<num/2; i++){
            if(num%i == 0)
                primo = false;
        }

        if(primo)
            System.out.println("Il numero è primo");
        else 
            System.out.println("Il numero non è primo");

        scanner.close();
    }
}
