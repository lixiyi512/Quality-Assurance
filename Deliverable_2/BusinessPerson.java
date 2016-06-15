public class BusinessPerson extends Visitor {
		
	public BusinessPerson(int id){
		super(id);
	}
	
	public void showType(){
		System.out.println("Visitor " + id + " is a Business Person.");
	}
	
	public boolean preference(int loc){
		boolean like = false;
		if(loc == LOC_SH || loc == LOC_DT){
			like = true;
		} else{
			like = false;
		} return like;
	}
	
}