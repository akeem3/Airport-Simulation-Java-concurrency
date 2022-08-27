import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Aircraft implements Runnable{
	private String status;
	private String passstatus;
	private int ID;
	private BlockingQueue<Runway> runways = null;
	private BlockingQueue<Gate> gates = null;
	private Runway runway;
	private Gate gate;
	int min = 1;
	int max = 4;
	public int getr(){
		int getR = 0;
		for (int i = min; i <= max; i++) {
			getR = (int) (Math.random() * (max - min)) + min;
		}
		return getR;
	}

	public void event(){
		if(getr() == 1){
			System.out.println("plane mechanical malfunction");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("engine fixed");
		}
		else if(getr() == 2){
			System.out.println("medical crisis");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("resolved");
		}
	}


	public Aircraft(int ID, Boolean bool, BlockingQueue<Runway> runways, BlockingQueue<Gate> gates) {
		this.ID = ID;
		this.runways = runways;
		this.gates = gates;

		// Determine if aircraft wants to land or depart based on boolean passed as the second argument
		// and if Passangers are to disembark or not.
		if (bool) {
			status = "land";
			passstatus = " Passangers disembarking ";
		} else {
			status = "depart";
			passstatus = " passangers boarding ";
		}

		System.out.println(java.time.LocalTime.now() + " An aircraft with the ID of " + ID + " has been created.");
	}

	@Override
	public void run() {
		try {
			//the process of the aircraft traffic as they land or depart
			runway = runways.take();
			gate = gates.take();
			if(status.equals("depart")){
				event();
				Thread.sleep(1000);
				System.out.println(" Aircraft " + ID +" is being refueled");
				Thread.sleep(1000);
				System.out.println(" Aircraft " + ID +" is being resupplied");
				Thread.sleep(1000);
				System.out.println(passstatus+ " Aircraft " + ID);
				Thread.sleep(1000);
			}
			System.out.println(java.time.LocalTime.now() + " Aircraft " + ID + " has been assigned " + gate.getName() + ".");
			if(status.equals("depart")){
				Thread.sleep(1000);
				System.out.println(" Aircraft " + ID +" is undocing from "+ gate.getName() + ".");
				Thread.sleep(1000);
			}
			System.out.println(java.time.LocalTime.now() + " Aircraft " + ID + " has been assigned " + runway.getName() + ".");
			System.out.println(java.time.LocalTime.now() + " Aircraft " + ID + " will now " + status + ".");


			//distributing appropriate sleep time after land and depart
			if (status == "land") {
				Thread.sleep(10000);
			} else if (status == "depart") {
				Thread.sleep(5000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		runway.inc(status);
		System.out.println(java.time.LocalTime.now() + " Aircraft " + ID + " has " + status + "ed.");
		try {
			if(status.equals("land")){
				Thread.sleep(1000);
				System.out.println(" Aircraft " + ID +" is docking at "+ gate.getName() + ".");
				Thread.sleep(1000);
				System.out.println(passstatus + " Aircraft " + ID);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//adding the runways to the aircrafts
		runways.add(runway);
		gates.add(gate);
	}
}
