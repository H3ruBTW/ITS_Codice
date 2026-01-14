package ES3;

import java.util.Scanner;

public class ES3 {

    public static void main(String[] args) {
        int num;

        Scanner scanner = new Scanner(System.in);

        int pos = 0, neg = 0, par = 0;

        do{
            num = scanner.nextInt();

            if(num != 0){
                if(num > 0)
                    pos++;

                if(num < 0)
                    neg++;

                if(num%2 == 0)
                    par++;
            }
        }while(num != 0);

        System.out.println("Sono stati trovati:\n- Positivi: " + pos + "\n- Negativi: " + neg + "\n- Pari: " + par);
    }
    
}
