public class Blogger extends Visitor {
			
	public Blogger(int id){
		super(id);
	}
	
	public void showType(){
		System.out.println("Visitor " + id + " is a Blogger.");
	}
	
	public boolean preference(int loc){
		return false;
	}
}
