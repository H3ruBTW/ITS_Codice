package ES1;

import java.util.ArrayList;
import java.util.Random;

public class Lambda {
    public interface ArrayOperations {
        Integer estrai(ArrayList<Integer> a);
    }

    public Integer opera(ArrayList<Integer> al, ArrayOperations ao){
        return ao.estrai(al);
    }

    public static void main(String[] args) {
        ArrayList<Integer> numeri = new ArrayList<>();
        Random random = new Random();
        Lambda l = new Lambda();

        for (int i = 0; i < 5; i++) {
            numeri.add(random.nextInt(0,50));
        }

        ArrayOperations findMax = (ArrayList<Integer> a) -> {
            int max = Integer.MIN_VALUE;
            for (Integer numero : numeri) {
                if(numero > max)
                    max = numero;
            }
            return max;
        };

        ArrayOperations findMin = (ArrayList<Integer> a) -> {
            int min = Integer.MAX_VALUE;
            for (Integer numero : numeri) {
                if(numero < min)
                    min = numero;
            }
            return min;
        };

        ArrayOperations somma = (ArrayList<Integer> a) -> {
            int sum = 0;
            for (Integer numero : numeri) {
                sum += numero;
            }
            return sum;
        };

        ArrayOperations media = (ArrayList<Integer> a) -> {
            int sum = 0;
            for (Integer numero : numeri) {
                sum += numero;
            }
            return sum / a.size();
        };
        System.out.print("Array : [ ");
        numeri.forEach((numero) -> System.out.print("|" + numero + "| "));
        System.out.println("]");
        System.out.println("Massimo: " + l.opera(numeri, findMax));
        System.out.println("Minimo : " + l.opera(numeri, findMin));
        System.out.println("Somma  : " + l.opera(numeri, somma));
        System.out.println("Media  : " + l.opera(numeri, media));
    }
}
