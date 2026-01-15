package ES1;

import java.util.Scanner;

public class ES1 {
    public static void main(String[] args) {
        Integer max = null, min = null;

        Integer n;

        Scanner scanner = new Scanner(System.in);

        do {

            n = scanner.nextInt();

            if(n!=0){
                if(max == null || n>max)
                    max = n;

                if(min == null || n<min)
                    min = n;
            }          
            
        } while (n!=0);

        System.out.println("Il massimo trovato è " + max + "\nMentre il minimo trovato è " + min);

        scanner.close();

    }
}
