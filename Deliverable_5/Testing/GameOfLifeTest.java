import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import org.junit.Test;

public class GameOfLifeTest {
	
	
	/**
	 * Test method toString() gives the same output before and after modification
	 * @author Xiyi Li
	 */
	
	//If the cell is alive, the text on the button should be "X"
	//Test method toString() and toStringOriginal() 
	//The output should be the same 
	@Test
	public void testToString1() {
		Cell c = new Cell();
		c.setAlive(true);
		String before = c.toStringOriginal();
		String after = c.toString();
		assertEquals(before, after);
	}
	

	//If the cell has been alive, the text on the button should be " "
	//Test method toString() and toStringOriginal()  
	//The output should be the same
	@Test
	public void testToString2() {
		Cell c = new Cell();
		c.setAlive(true);
		c.setAlive(false);
		String before = c.toStringOriginal();
		String after = c.toString();
		assertEquals(before, after);
	}
	
	
	//If the cell is dead, the text on the button should be "."
	//Test toString() and toStringOriginal() 
	//The output should be the same
	@Test
	public void testToString3() {
		Cell c = new Cell();
		c.setAlive(false);
		String before = c.toStringOriginal();
		String after = c.toString();
		assertEquals(before, after);
	}
	
	/**
	 * Test method ConvertToInt(int x) gives the same output before and after modification
	 * @author Xiyi Li
	 */
	//Because method ConvertToInt(int x) is called by method getNumNeighbors()
	//So we need to find out the boundary integers passed into ConvertToInt(int x)
	//If all of neighbors of the cell are alive
	//Then the number of neighbors should be 8
	@Test
	public void tesGetNumNeighbors() {
		try{
			Method method = MainPanel.class.getDeclaredMethod("convertToInt", int.class);
			Method method2 = MainPanel.class.getDeclaredMethod("convertToIntOriginal", int.class);
			method.setAccessible(true);
			method2.setAccessible(true);
			MainPanel mp = new MainPanel(15);
			Object returnValue = method.invoke(mp, 8);
			Object returnValue2 = method2.invoke(mp, 8);
			int after = ((Integer) returnValue).intValue();
			int before = ((Integer) returnValue2).intValue();
			assertEquals(before,after);
			
		}catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException ex){
			fail();
		}
			
		}
	//If all of neighbors of the cell are dead
	//Then the number of neighbors should be 0
	@Test
	public void testPrivateConvertToInt2() {
		try{
			Method method = MainPanel.class.getDeclaredMethod("convertToInt", int.class);
			Method method2 = MainPanel.class.getDeclaredMethod("convertToIntOriginal", int.class);
			method.setAccessible(true);
			method2.setAccessible(true);
			MainPanel mp = new MainPanel(15);
			Object returnValue = method.invoke(mp, 0);
			Object returnValue2 = method2.invoke(mp,0);
			int after = ((Integer) returnValue).intValue();
			int before = ((Integer) returnValue2).intValue();
			assertEquals(before,after);
			
		}catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException ex){
			fail();
		}
			
		}
	//If neighbors of the cell are either dead or alive (at least one neighbor is dead or at least one neighbor is alive)
	//Then the number of neighbors should be a random integer between 1-7 
	@Test
	public void testPrivateConvertToInt3() {
		try{
			Random mockRandom = mock(Random.class);
			int r = mockRandom.nextInt(7) + 1;
			Method method = MainPanel.class.getDeclaredMethod("convertToInt", int.class);
			Method method2 = MainPanel.class.getDeclaredMethod("convertToIntOriginal", int.class);
			method.setAccessible(true);
			method2.setAccessible(true);
			MainPanel mp = new MainPanel(15);
			Object returnValue = method.invoke(mp, r);
			Object returnValue2 = method2.invoke(mp,r);
			int after = ((Integer) returnValue).intValue();
			int before = ((Integer) returnValue2).intValue();
			assertEquals(before,after);			
		}catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException ex){
			fail();
		}
		
	}

}
