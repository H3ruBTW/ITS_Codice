package BubbleSort;

import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = creaArray();

        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

        boolean scambio;

        do{
            scambio = false;

            for(int i=0; i<array.length-1; i++){
                if(array[i]>array[i+1]){
                    scambioArray(array, i, i+1);
                    scambio = true;
                }
            }

        }while(scambio);

        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] creaArray(){
        int[] array = new int[10];
        
        Random random = new Random();

        for (int i=0; i<array.length; i++) {
            array[i] = random.nextInt(1, 50);
        }

        return array;
    }

    public static int[] scambioArray(int[] v, int p1, int p2){
        int temp = v[p1];
        v[p1] = v[p2];
        v[p2] = temp;

        return v;
    }
}


