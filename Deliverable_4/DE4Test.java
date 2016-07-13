package deliberable4_QA;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class DE4Test {

	//Property 1: Output array has one more element than the passed-in array
	@Test
	public void TotalElementTest() {		
		DE4 de4 = new DE4();
		ArrayList<int[]> randomArray =  de4.generateArrays();
		for (int i = 0; i < randomArray.size(); i++){
			int sizeInput = randomArray.get(i).length;
			int sizeOutput = de4.billify(randomArray.get(i)).length;
			assertTrue(sizeInput + 1 == sizeOutput);
		}	
	}
	
	//Property 2: The last element in the output array should be greater or equal to any previous element in the output array
	@Test
	public void LastElementTest(){
		DE4 de4 = new DE4();
		ArrayList<int[]> randomArray =  de4.generateArrays();
		for(int i = 0; i < randomArray.size(); i ++){
			int arrayOutput[] = de4.billify(randomArray.get(i));
			int lastElement = arrayOutput[arrayOutput.length - 1];
			for(int j = 0; j < arrayOutput.length - 1 ; j ++){
				assertTrue(lastElement >= arrayOutput[j]);
			}
		}
	}
	
	//Property 3: Pure – running it twice on same input array should always result in same output array
	@Test
	public void PurityTest(){
		DE4 de4 = new DE4();
		ArrayList<int[]> randomArray =  de4.generateArrays();
		for(int i = 0; i <randomArray.size(); i ++){
			int arrayOutput1[] = de4.billify(randomArray.get(i));
			int arrayOutput2[] = de4.billify(randomArray.get(i));
			assertTrue(Arrays.equals(arrayOutput1, arrayOutput2));
		}
	}	
}
