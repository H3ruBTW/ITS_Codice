package ES1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ES1 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            arrayList.add(random.nextInt(50));
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();

        Iterator<Integer> it = arrayList.iterator();

        Integer Mag1 = it.next();

        Integer Mag2 = it.next();

        Integer temp;

        if(Mag1<Mag2){
            temp = Mag1;
            Mag1 = Mag2;
            Mag2 = temp;
        }

        while(it.hasNext()){
            Integer n = it.next();

            if(n>=Mag1){
                temp = Mag1;
                Mag1 = Mag2;
                Mag2 = temp;
                Mag1 = n;
                continue;
            }

            if(n>Mag2){
                Mag2 = n;
            }
        }

        System.out.println("Il numero maggiore è: " + Mag1 + "\nIl secondo numero maggiore è: " + Mag2);
    }
}
