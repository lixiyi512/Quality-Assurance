public class Professor extends Visitor {
			
	public Professor(int id){
		super(id);
	}
	
	public void showType(){
		System.out.println("Visitor " + id + " is a Professor.");
	}
	
	public boolean preference(int loc){
		return true;
	}
}
