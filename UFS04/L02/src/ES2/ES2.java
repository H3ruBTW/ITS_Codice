package ES2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ES2 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            arrayList.add(random.nextInt(1,11));
        }

        System.out.println("Array: " + arrayList);

        Iterator<Integer> it = arrayList.iterator();

        Integer ris = it.next();

        while (it.hasNext()) {
            ris *= it.next();
        }

        System.out.println("Risultato: " + ris);
    }
}
