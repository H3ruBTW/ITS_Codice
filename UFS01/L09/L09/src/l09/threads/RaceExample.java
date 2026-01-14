package l09.threads;

public class RaceExample {
	static int counter = 0;
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(RaceExample::Increment);
		Thread t2 = new Thread(RaceExample::Increment);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("Counter: " + counter);
	}
	
	synchronized static void Increment() {
		for(int i = 0; i< 1000000; i++) {
			counter++;
		}
	}

}
