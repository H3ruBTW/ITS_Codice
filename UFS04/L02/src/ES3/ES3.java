package ES3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ES3 {
    public static void main(String[] args) {
        ArrayList<Float> arrayList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            arrayList.add((float)random.nextInt(1,11));
        }

        System.out.println("Array: " + arrayList);

        Iterator<Float> it = arrayList.iterator();

        float ris = 0;

        while (it.hasNext()) {
            ris += it.next();
        }

        System.out.println("Risultato: " + (ris/arrayList.size()));
    }
}
