import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Xiyi Li
 *
 */
public class CitySim9002 {
	
	public static final int VISIT_STU = 0;
	public static final int VISIT_PRO = 1;
	public static final int VISIT_BUSI = 2;
	public static final int VISIT_BLOG = 3;
	
	int seed;
	
	
	public static void main(String args[]){
		CitySim9002 cs = new CitySim9002();
		if(!cs.judgeInput(args)){
			System.out.println("Please enter one integer argument, seed");
			System.exit(0);
		} 
		System.out.println("Welcome to CitySim! Your seed is " + cs.seed + ".");
		Random random = new Random(cs.seed);
		int visitorType = -1;
		
		//Generate a visitor randomly and display the welcome message
		for(int i = 1; i <= 5; i++){
			visitorType = random.nextInt(4);
			Visitor visitor = cs.generateVisitor(visitorType, i);
			visitor.showType();
			
			int loc = visitor.firstVisit(visitor.id, random); //choose the first location to visit and show the message
			String ln = visitor.LocationName(loc);
			visitor.typePreference(ln, visitor.id, loc); //see whether the visitor likes the location
			loc = visitor.location(visitor.id, random);//choose next location to visit
			while(loc != Visitor.LOC_LEAVE){ //choose locations to visit until the visitor leaves this city
				visitor.typePreference(visitor.LocationName(loc), visitor.id, loc);
				
				loc = visitor.location(visitor.id, random);
			}
			System.out.println("***");
	
		}	
		
	}
	

	/**
	 * If the argument is one integer, return true
	 * Else return false
	 */
	public boolean judgeInput(String[] args){
		boolean flag = false;
		if(args.length == 1){	
			if(args.length == 1){
				try{
					seed = Integer.parseInt(args[0]);
					flag = true;
				} catch(Exception e){
					flag = false;
				}
				} else{
					flag = false;
				}
		}
		return flag;
		
	}
	
    /**
     * Generate a student/Professor/Business Person/Blogger according to the visitortype
     * and return this object
     */
	public Visitor generateVisitor(int visitorType, int id) {
		Visitor visitor = null;
		switch(visitorType){
		case VISIT_STU:
			visitor = new Student(id);
			break;
		case VISIT_PRO:
			visitor = new Professor(id);
			break;
		case VISIT_BUSI:
			visitor = new BusinessPerson(id);
			break;
		case VISIT_BLOG:
			visitor = new Blogger(id);
			break;
		}
		return visitor; 
	}
		
}