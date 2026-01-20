package ES6;

import java.util.Scanner;

public class ES6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Scrivi una parola e controllerò se è palindroma\n");

        String parola = scanner.nextLine().toLowerCase().replaceAll(" ", "");
        String subString1, subString2;
        boolean pal = true;

        if(parola.length() > 1){
            if(parola.length()%2==0){
                subString1 = parola.substring(0, parola.length()/2);
                subString2 = parola.substring(parola.length()/2, parola.length());
            }else{
                subString1 = parola.substring(0, parola.length()/2);
                subString2 = parola.substring(parola.length()/2+1, parola.length());
            }

            //Stampa di controllo
            System.out.println(subString1 + " " + subString2);

            for (int i = 0; i < subString1.length(); i++) {
                if(subString1.charAt(i) != subString2.charAt(subString2.length()-i-1)){
                    pal = false; 
                    break;
                }
            }
        } else 
            pal = false;

        if(pal)
            System.out.println("La parola è palindroma");
        else
            System.out.println("La parola non è palindroma");

        scanner.close();
    }
}
