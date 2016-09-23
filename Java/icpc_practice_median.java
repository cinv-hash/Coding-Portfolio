/*
Median calculator
@author Megan Landau

This program takes in a list of inputs read from STDIN until the 
number "-1" is entered. It then calculates and outputs the median of the set.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class prob1 {

	public static void main(String[] args) {
		Scanner scan  = new Scanner(System.in);
		
		String line = scan.nextLine();
		
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		while (!line.equals("-1")){
			
			
			array.add(Integer.valueOf(line));
			
			line = scan.nextLine();
		
		}
		Collections.sort(array);
		

		
		if( array.size()%2 == 0){
			int num1 = array.get(array.size()/2-1);
			int num2 = array.get(array.size()/2);

			float f = Float.valueOf(num1+num2);
			float fin = f/((float) 2.0);
			
			System.out.println(fin + "%");
			
			
		}else{
			System.out.println((float)array.get(array.size()/2 +1)+"%");
		}

		

	}

}

