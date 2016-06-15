import java.util.Random;

public class Visitor{
	
	public static final int LOC_CL = 0;
	public static final int LOC_SH = 1;
	public static final int LOC_TP = 2;
	public static final int LOC_DT = 3;
	public static final int LOC_LEAVE = 4;

		
	int id;
	
	public Visitor(int id) {
		this.id = id;
	}
	
	/**
	 * print the message of the location visitor goes to the first time
	 */
	public int firstVisit(int id, Random random){
		int loc = random.nextInt(4);  
		String ln = LocationName(loc);
		System.out.println("Visitor " + id + " is going to " + ln + "!");
		return loc;
	};
	
	/**
	 * choose a location to go and display this message
	 */
	public int location(int id, Random random){
		int loc = random.nextInt(5); 
		String ln = LocationName(loc);
		if(loc == LOC_LEAVE){
			System.out.println("Visitor " + id + ln);
		} else{
			System.out.println("Visitor " + id + " is going to " + ln + "!");
		} return loc;
		
	};
	/**
	 * the subclass can overwrite this method
	 */
	public boolean preference(int loc){
		return false;
	};
	
	
	public String LocationName(int loc){
		String LN = "";
		if(loc == LOC_CL) LN = "The Cathedral of Learning";
		if(loc == LOC_SH) LN = "Squirrel Hill";
		if(loc == LOC_TP) LN = "The Point";
		if(loc == LOC_DT) LN = "Downtown";
		if(loc == LOC_LEAVE) LN = " has left the city.";
		return LN;
	}
	
	/** 
	 * show whether the visitor likes this location
	 */
	public void typePreference(String LN, int id, int loc){
		boolean like = preference(loc);
		if(like == false){
			System.out.println("Visitor " + id + " did not like " + LN + ".");
		}else if(like == true){
			System.out.println("Visitor " + id + " did like " + LN + ".");
		}
	}
	
	/**
	 * the subclass can overwrite this method
	 */
	public void showType(){};
	
	
	
	/**
	 * for testing GenerateVisitor 
	 */
	@Override
	public String toString() {
	String className = this.getClass().toString();
	String idName = String.valueOf(id);
	String outPut = className + " " + idName;
	return outPut;
	}

	
		
}