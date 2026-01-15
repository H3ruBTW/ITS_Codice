package MergeSort;

import java.util.Random;

public class MergeSort {
    public static void main(String[] args) {
        int[] array = creaArray();

        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

        mergeSort(array, 0, array.length-1);

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

    public static void merge(int[] array, int left, int mid, int right){
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] TempArray1 = new int[n1];
        int[] TempArray2 = new int[n2];

        for(int i = 0; i < n1; i++){
            TempArray1[i] = array[left + i];
        }
        
        for(int j = 0; j < n2; j++){
            TempArray2[j] = array[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while(i < n1 && j < n2){
            if(TempArray1[i] <= TempArray2[j]){
                array[k] = TempArray1[i];
                i++;
            } else {
                array[k] = TempArray2[j];
                j++;
            }
            k++;
        }

        while(i < n1){
            array[k] = TempArray1[i];
            i++;
            k++;
        }

        while(j < n2){
            array[k] = TempArray2[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] array, int left, int right){

        int mid;

        if(left<right){
            mid = (left+right)/2;

            mergeSort(array, left, mid);
            mergeSort(array, mid+1, right);
            merge(array, left, mid, right);
        }
            
    }
}
