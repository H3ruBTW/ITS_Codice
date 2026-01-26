package ES2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Studente {
    private String Nome;
    private ArrayList<Double> Voti = new ArrayList<>();

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    
    public Studente(String Nome){
        this.Nome = Nome;
    }

    public void NewVoto(double voto){
        if(voto>=0 && voto<=31)
            Voti.add(voto);
        else
            System.out.println("Il voto deve essere tra 0 e 31");
    }

    public double media(){
        double somma = 0;

        for (Double voto : Voti) {
            somma += voto;
        }

        //due decimali
        return Math.floor(somma/Voti.size()*100)/100;
    }

    public Boolean isPromosso(){
        if(insufficienze()>0)
            return false;

        return true;
    }

    public double VMax(){
        double max = Voti.getFirst();

        for (Double voto : Voti) {
            if(max<voto)
                max = voto;
        }

        return max;
    }

    public double VMin(){
        double min = Voti.getFirst();

        for (Double voto : Voti) {
            if(min>voto)
                min = voto;
        }

        return min;
    }

    public double mediana(){
        Double[] array = Voti.toArray(new Double[0]);

        for (int i = 0; i < array.length-1; i++) {
            int min = i;
            for (int j = i+1; j < array.length; j++) {
                if(array[min]>array[j])
                    min = j;
            }

            double temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }

        if(Voti.size()%2==0)
            return (array[Voti.size()/2-1]+array[Voti.size()/2])/2;
        else
            return array[Voti.size()/2];
    }

    public int insufficienze(){
        int num = 0;

        for (Double voto : Voti) {
            if(voto<18)
                num++;
        }

        return num;
    }

    public double moda(){
        HashMap<Double, Integer> frequenze = new HashMap<>();

        for (Double voto : Voti) {
            if(frequenze.containsKey(voto))
                frequenze.put(voto, frequenze.get(voto)+1);
            else
                frequenze.put(voto, 1);
        }

        int maxFr = Collections.max(frequenze.values());

        Double moda = 0.0;

        for(Map.Entry<Double, Integer> entry : frequenze.entrySet()){
            if(entry.getValue() == maxFr){
                moda = entry.getKey();
                break;
            }
        }

        return moda;
    }
}
