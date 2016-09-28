
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception{

		Scanner myScan = new Scanner(System.in);

		Calendar myCal = new Calendar("Sebastian", "van Delden");
		boolean RunCalendarProgram = true;
		while(RunCalendarProgram){
			System.out.println("What do you want to do?\n");
			System.out.println("1) Add event(s) to Calendar\n2) Print Calendar\n3) Print a Day\n4) Remove an Event\n9) Exit");
			int myChoice = myScan.nextInt();
			switch(myChoice){
				case 1:
					myCal.populate(myScan);
					break;
				case 2:
					System.out.println(myCal);
					break;
				case 3:
					System.out.println("Which day would you like to print? Enter: day month year");
					System.out.println(myCal.DayToString(myScan.nextInt(), myScan.nextInt(), myScan.nextInt()));
					break;
				case 4:
					System.out.println("Which event would you like to remove? Enter: start-time day month year");
					int startTime = myScan.nextInt(), day = myScan.nextInt(), month = myScan.nextInt(), year = myScan.nextInt();					
					myCal.remove(startTime, day, month, year);
					break;
				case 9:
					System.out.println("Bye bye!");
					RunCalendarProgram = false;
					break;
			}
		}
	}
}