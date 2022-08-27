public class Runway {
	private int landCount = 0;
	private int departCount = 0;
	private int name;

	//nameing the runway
	public Runway(int name) {
		this.name = name;
	}

	//printing out the reports for number of landing, depature and uses
	public void printReport() {
		System.out.println(" Runnway " + name + " has a total of "+ landCount + " landings.");
		System.out.println(" Runnway " + name + " has a total of "+ departCount + " departures.");
		System.out.println(" Runnway " + name + " has a combined total of "+ getCount() + " uses.");
	}
	//Increament of count for either land or depart
	public void inc(String status) {
		if (status.equals("land")) {
			landCount++;
		} else {
			departCount++;
		}
	}

	public String getName() {
		return " Runnway " + name;
	}

	public int getLandCount() {
		return landCount;
	}

	public int getDepartCount() {
		return departCount;
	}
	
	public int getCount() {
		return departCount + landCount;
	}
}