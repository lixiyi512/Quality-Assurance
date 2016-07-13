package deliberable4_QA;

import java.util.ArrayList;
import java.util.Random;

public class DE4 {
	
	public int[] billify(int[] x){
		int size1 = x.length;
		int size2 = size1 + 1;
		int[] result = new int[size2];
		int sum = 0;
		int i = 0;
		for(; i < size1; i++){
			result[i] = x[i] * x[i];
			sum = sum + result[i];
		}
		result[i] = sum;
		return result;
	}
	
	public ArrayList<int[]> generateArrays(){
		Random random = new Random();
		ArrayList<int[]> result = new ArrayList<int[]>();
		for(int i = 0; i < 150; i++){
			//make sure each array will contain one or more elements,and a maximum of one hundred elements.
			int size = random.nextInt(100) + 1;
			int[] array = new int[size];
			for(int j = 0; j < size; j++){
				//make sure the random number is positive integers between the values of 1 and 100
				array[j] = random.nextInt(100)+1; 
			}
			result.add(array);
		}
		return result;
	}

}
