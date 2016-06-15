public class Student extends Visitor {
		
		public Student(int id){
			super(id);
		}
		
		public void showType(){
			System.out.println("Visitor " + id + " is a Student.");
		}
		
		
		public boolean preference(int loc){
			boolean like = false;
			if(loc == 0) like = false;
			else if(loc == 1 || loc == 2 || loc ==3) like = true;
			return like;
		}
		
}