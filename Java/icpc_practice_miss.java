/*
Crossing the Mississippi
@author Megan Landau

You are a traveling salesman and you have to travel to cities on either side
of the Mississippi River. This program takes input in the following format:

# Trips
Trip <i> has <c> cities.
City name, State
.
.
.
[until <c> cities have been input]

Here is some sample input:
2 Trips
Trip 1 has 4 cities.
La Crosse, WI
Effingham, IL
St. Louis, MO
Jackson, TN
Trip 2 has 3 cities.
Mountain Home, AR
Rolla, MO
Davenport, IA

This program reads in the input formatted in this way and outputs the number
of times the salesman has to cross the Mississippi River over.
*/


import java.util.Arrays;
import java.util.Scanner;

public class Miss {

	public static void main(String[] args) {

		//String input = "2 trips\nTrip 1 has 4 cities.\nLa Crosse, WI\nEffingham, IL\nSt. Louis, MO\nJackson, TN\nTrip 2 has 3 cities.\nMountain Home, AR\nRolla, MO\nDavenport, IA";
		
		Scanner scan = new Scanner(System.in);
		//Scanner scan = new Scanner(input);
		
		String[] west = {"MN", "IA", "MO", "AR", "LA"};
		String[] east = {"WI", "IL", "KY", "TN", "MS"};

		int numTrips = scan.nextInt();
		scan.nextLine();
		for(int i = 0; i < numTrips; i++){
			String line = scan.nextLine();
			String[] stuff = line.split(" ");
			int numCities = Integer.parseInt(stuff[3]);
			int count = 0;
			String side = "";
			for (int k = 0; k<numCities; k++){
				String cityLine = scan.nextLine();
				String [] city = cityLine.split(", ");
				String state = city[1];
				String newSide = "";
				
				if (k == 0){
					if (Arrays.asList(east).contains(state)){
						side = "east";
					}else{
						side = "west";
					}
				}else{
					if (Arrays.asList(east).contains(state)){
						newSide = "east";
					}else{
						newSide = "west";
					}
				

					if( !newSide.equals(side) ){
						side = newSide;
						count ++;
					}
				}
			}
			if (count == 1){
				System.out.println("Trip "+(i+1)+" crosses the Mississippi " + count + " time.");
			}else{
				System.out.println("Trip "+(i+1)+" crosses the Mississippi " + count + " times.");
			}
			
		}
	}

}

