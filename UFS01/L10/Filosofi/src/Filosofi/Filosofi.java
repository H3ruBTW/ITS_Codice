package Filosofi;

import java.util.Random;

public class Filosofi extends Thread {
    
    public static void main(String[] args) throws InterruptedException {
        
        Filosofi Fil1 = new Filosofi(1,2);
        Filosofi Fil2 = new Filosofi(2,3);
        Filosofi Fil3 = new Filosofi(3,4);
        Filosofi Fil4 = new Filosofi(4,5);
        Filosofi Fil5 = new Filosofi(5,1);

        Fil1.start();
        Fil2.start();
        Fil3.start();
        Fil4.start();
        Fil5.start();

        Fil1.join();
        Fil2.join();
        Fil3.join();
        Fil4.join();
        Fil5.join();

    }

    static boolean[] Forks = {false, false, false, false, false};

    int f1;
    int f2;

    public Filosofi (int f1, int f2){
        this.f1 = f1-1;
        this.f2 = f2-1;
    }

    @Override
    public void run() {
        Random random = new Random();
        
        while(true){

            System.out.println(this.getName() + " sta pensando");

            try {
                sleep((long)random.nextInt(2,5)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(this.getName() + " sta aspettando la forchetta alla sua sinistra");

            while (Forks[f1] == true) {

                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(this.getName() + " ha preso la forchetta alla sua sinistra");

            Forks[f1] = true;

            try {
                sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(this.getName() + " sta aspettando la forchetta alla sua destra");

            while (Forks[f2] == true){
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(this.getName() + " ha preso la forchetta alla sua destra");

            Forks[f2] = true;

            System.out.println(this.getName() + " sta mangiando");

            try {
                sleep((long)random.nextInt(2,5)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Forks[f1] = false;
            Forks[f2] = false;
        }
    }
}


