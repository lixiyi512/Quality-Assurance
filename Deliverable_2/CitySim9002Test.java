import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Random;


public class CitySim9002Test {
	//The program shall accept one and only one argument, which acts as a seed for the random number generator.
	//the input of seed should only be an integer.
	//put 99, which is an integer, in the array list
	//99 should pass the judgeInput method
	@Test
	public void testjudgeInputInt() {
		
		CitySim9002 cs = new CitySim9002();
		String st[] = {"99"};
		assertTrue(cs.judgeInput(st));
		
	}
	
	//The program shall accept one and only one argument, which acts as a seed for the random number generator.
	//The input of seed should only be an integer.
	//Put abc, which is a string, in the array list
	//abc should not pass the judgeInput method
	@Test
	public void testjudgeInputLetter() {
		
		CitySim9002 cs = new CitySim9002();
		String st[] = {"abc"};
		assertFalse(cs.judgeInput(st));
		
	}
	
	//The program shall accept one and only one argument, which acts as a seed for the random number generator.
	//The input of seed should only be an integer.
	//Put 12,23,58, which are three integers, in the array list
	//12,23,58 should not pass the judgeInput method
	@Test
	public void testjudgeInputMultipleInt() {
		
		CitySim9002 cs = new CitySim9002();
		String st[] = {"12","23","58"};
		assertFalse(cs.judgeInput(st));
		
	}

	//The program shall accept one and only one argument, which acts as a seed for the random number generator.
	//The input of seed should only be an integer.
	//Put null in the array list
	//null should not pass the judgeInput method
	@Test
	public void testjudgeInputNull() {
		
		CitySim9002 cs = new CitySim9002();
		String st[] = {""};
		assertFalse(cs.judgeInput(st));
		
	}
	
	//The program shall accept one and only one argument, which acts as a seed for the random number generator.
	//The input of seed should only be an integer.
	//Put 3.589, which is a decimal, in the array list
	//3.589 should not pass the judgeInput method
	@Test
	public void testjudgeInputDecimal() {
		
		CitySim9002 cs = new CitySim9002();
		String st[] = {"3.589"};
		assertFalse(cs.judgeInput(st));
		
	}
	
	//The program shall accept one and only one argument, which acts as a seed for the random number generator.
	//The input of seed should only be an integer.
	//Put 009, which can be treated as an integer in this program, in the array list
	//009 should pass the judgeInput method
	@Test
	public void testjudgeInputIntStartWithZero() {
		
		CitySim9002 cs = new CitySim9002();
		String st[] = {"009"};
		assertTrue(cs.judgeInput(st));
		
	}
	
	//Generate a visitor type 0: student
	//Create a mock Student object with id=2
	//Call generateVisitor() to new an object with visitor type 0 and id=2
	// these two objects have same id and visitor type
	@Test
	public void testGenerateVisitor_Stu() {
		CitySim9002 cs = new CitySim9002();
		Student mockStu = mock(Student.class);
		when(mockStu.toString()).thenReturn("class Student 2");
		assertEquals(mockStu.toString(), cs.generateVisitor(0,2).toString());

	}
	
	//Generate a visitor type 1: professor
	//Create a mock Student object with id=3
	//Call generateVisitor() to new an object with visitor type 1 and id=3
	// these two objects have same id and visitor type
	@Test
	public void testGenerateVisitor_Pro() {
		CitySim9002 cs = new CitySim9002();
		Professor mockPro = mock(Professor.class);
		when(mockPro.toString()).thenReturn("class Professor 3");
		assertEquals(mockPro.toString(), cs.generateVisitor(1,3).toString());
	}
	
	//For the first visit for a given Visitor, the Visitor cannot leave the City.  
	//The Visitor must visit at least one Location.
	//When random number for location is 0, the visitor whose id=2 should go to location 0.
	@Test
	public void testFirstVisit_0(){
		Random mockRandom = mock(Random.class);
		when(mockRandom.nextInt(4)).thenReturn(0);
		Visitor visitor = new Visitor(2);
		assertEquals(0,visitor.firstVisit(2, mockRandom));
	}

	//For the first visit for a given Visitor, the Visitor cannot leave the City.  
	//The Visitor must visit at least one Location.
	//When random number for location is 1, the visitor whose id=3 should go to location 1.
	@Test
	public void testFirstVisit_1(){
		Random mockRandom = mock(Random.class);
		when(mockRandom.nextInt(4)).thenReturn(1);
		Visitor visitor = new Visitor(3);
		assertEquals(1,visitor.firstVisit(3, mockRandom));
	}

	//Randomly select a random location(including the option: leaving the city) for the visitor to visit
	//If random location that the generator generates is location 3
	//Then the visitor whose id=1 should visit location 3
	@Test
	public void testLocation(){
		Random mockRandom = mock(Random.class);
		when(mockRandom.nextInt(5)).thenReturn(3);
		Visitor visitor = new Visitor(1);
		assertEquals(3,visitor.location(1, mockRandom));
	}
	
	//Matching each location id with its name
	//Location 0 should equal to "The Cathedral of Learning"
	@Test
	public void testLocationName_cl(){
		Visitor v = new Visitor(1);
		assertEquals("The Cathedral of Learning",v.LocationName(0));
	}
	
	//Matching each location id with its name
	//Location 1 should equal to "Squirrel Hill"
	@Test
	public void testLocationName_sh(){
		Visitor v = new Visitor(1);
		assertEquals("Squirrel Hill",v.LocationName(1));
	}
	
	//Matching each location id with its name
	//Location 4 should equal to "has left the city"
	@Test
	public void testLocationName_lc(){
		Visitor v = new Visitor(1);
		assertEquals("has left the city",v.LocationName(4));
	}
	
	//Student's preference towards four different locations
	//For the first visitor: student(id=1), when he/she goes to location 0, which is Cathedral of Learning
	//student should not like Cathedral of Learning
	@Test
	public void testStudentPreference_cl(){
		Random mockRandom = mock(Random.class);
		Student s = new Student(1);
		when(s.location(1, mockRandom)).thenReturn(0);
		assertFalse(s.preference(0));
	}
	
	//Student's preference towards four different locations
	//For the first visitor: student(id=1), when he/she goes to location 1, which is Squirrel Hill
	//student should like Squirrel Hill
	@Test
	public void testStudentPreference_sh(){
		Random mockRandom = mock(Random.class);
		Student s = new Student(1);
		when(s.location(1, mockRandom)).thenReturn(1);
		assertTrue(s.preference(1));
	}
	
	//Professor's preference towards four different locations
	//For the second visitor: professor(id=2), when he/she goes to location 1, which is Squirrel Hill
	//Professor should like Squirrel Hill
	@Test
	public void testProfessorPreference_sh(){
		Random mockRandom = mock(Random.class);
		Professor p = new Professor(2);
		when(p.location(2, mockRandom)).thenReturn(1);
		assertTrue(p.preference(1));
	}
	
	//Professor's preference towards four different locations
	//For the second visitor: professor(id=2), when he/she goes to location 2, which is The Point
	//Professor should like The Point
	@Test
	public void testProfessorPreference_tp(){
		Random mockRandom = mock(Random.class);
		Professor p = new Professor(2);
		when(p.location(2, mockRandom)).thenReturn(2);
		assertTrue(p.preference(2));
	}
	
	//Business Person's preference towards four different locations
	//For the third visitor: business person(id=3), when he/she goes to location 3, which is Downtown
	//Business person should like Downtown
	@Test
	public void testBusinessPersonPreference_dt(){
		Random mockRandom = mock(Random.class);
		BusinessPerson bp = new BusinessPerson(3);
		when(bp.location(3, mockRandom)).thenReturn(3);
		assertTrue(bp.preference(3));
	}
	
	//Business Person's preference towards four different locations
	//For the third visitor business person(id=3), when he/she goes to location 0, which is Cathedral of Learning
	//Business person should not like Cathedral of Learning
	@Test
	public void testBusinessPersonPreference_cl(){
		Random mockRandom = mock(Random.class);
		BusinessPerson bp = new BusinessPerson(3);
		when(bp.location(3, mockRandom)).thenReturn(0);
		assertFalse(bp.preference(0));
	}
	
	//Blogger's preference towards four different locations
	//For the forth visitor: blogger(id=4), when he/she goes to location 2, which is The Point
	//Blogger should not like The Point
	@Test
	public void testBloggerPreference_tp(){
		Random mockRandom = mock(Random.class);
		Blogger b = new Blogger(4);
		when(b.location(4, mockRandom)).thenReturn(2);
		assertFalse(b.preference(2));
	}
	
	//Blogger's preference towards four different locations
	//For the forth visitor: blogger(id=4), when he/she goes to location 3, which is Downtown
	//Blogger should not like Downtown
	@Test
	public void testBloggerPreference_dt(){
		Random mockRandom = mock(Random.class);
		Blogger b = new Blogger(4);
		when(b.location(4, mockRandom)).thenReturn(3);
		assertFalse(b.preference(3));
	}
	
	
	

}
