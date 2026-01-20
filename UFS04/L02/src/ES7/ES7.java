package ES7;

import java.util.Scanner;

public class ES7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci un numero e stamperò i numeri minori di questo nella sequenza di Fibonacci: ");
        Integer n = scanner.nextInt();
        Integer fibonacci1 = 1, fibonacci2 = 0, temp;
        if(n>0)
            System.out.print(fibonacci2 + " ");

        while(n>fibonacci1){
            System.out.print(fibonacci1 + " ");
            temp = fibonacci1 + fibonacci2;
            fibonacci2 = fibonacci1;
            fibonacci1 = temp;
        }
        System.out.println();

        scanner.close();
    }
}
