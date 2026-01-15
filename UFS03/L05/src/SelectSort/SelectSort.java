package SelectSort;

import java.util.Random;

public class SelectSort {
    public static void main(String[] args) {
        int[] array = creaArray();

        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

        for(int i=0; i<array.length-1; i++){
            int indiceMin = i;
            for(int j=i+1; j<array.length; j++){
                if(array[indiceMin]>array[j])
                    indiceMin = j;
            }
            
            scambioArray(array, i, indiceMin);
        }

        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] creaArray(){
        int[] array = new int[10];
        
        Random random = new Random();

        for (int i=0; i<array.length; i++) {
            array[i] = random.nextInt(1, 100);
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
