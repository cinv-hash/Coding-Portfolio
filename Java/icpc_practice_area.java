/*
Calculate the area based on coordinates
@author Megan Landau
2016

This is a simple program that calculates the area of a rectangular shape 
based on the parameters passed in from STDIN in the following format:
x1 x2 y1 y2
where the input represents points on a coordinate system. 
Output is formatted to a float rounded to the hundredth place.
*/


import java.util.Scanner;

public class Area {

	public static void main(String[] args) {
		//Scanner scan = new Scanner( System.in );
		Scanner scan = new Scanner( "0 0 3 4\n5.2 -4.64 -3.47 2.2\n 0.0 0.0 0.0 0.0");
		
		while (scan.hasNext()){
			float x1 = Float.valueOf(scan.next());
			float y1 = Float.valueOf(scan.next());
			float x2 = Float.valueOf(scan.next());
			float y2 = Float.valueOf(scan.next());

			float area = Math.abs(x1-x2) * Math.abs(y1-y2);
			if (area > 0){
				System.out.println(String.format("%.2f", area));
			}
		}
	}
}
