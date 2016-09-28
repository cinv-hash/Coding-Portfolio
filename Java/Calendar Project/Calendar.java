
import java.util.Scanner;

public class Calendar {

	private String firstName;
	private String lastName;
	private LinkedList theCalendar;
	
	public Calendar( String first, String last ){
		this.firstName = first;
		this.lastName = last;
		this.theCalendar = new LinkedList();
		//when a calendar is made, print the intro - this makes more sense to me than putting it in the populate() method
		printIntro();
	}
	
	private void printIntro(){
		System.out.println( "Congratulations " + this.firstName + " " + this.lastName + " for creating a super awesome calendar!!" );
	}
	
	public String toString(){
		String owner = firstName + " " + lastName + "'s Calendar:"; //author's calendar
		String border = "============================================================";
		String calDays = this.theCalendar.toString(); //calls linkedlist's toString() method
		return "\n" + border + "\n" + owner + "\n" + "\n" + calDays + "\n" + border + "\n";
	}
	
	public void remove( int startTime, int day, int month, int year ){ //this method removes an event from a specified day, you are only given the start time
		CalendarDay foundDay = (CalendarDay) this.theCalendar.find( new CalendarDay( day, month, year ));//calendar calls the find method, which returns a reference to a comparable object, which i cast to a day object
		foundDay.getEvents().remove( new CalendarEvent( startTime, startTime+1, "", "", "")); // i go into the day's events linkedlist and use linkedList's remove() to remove an event - i make up the event with empty strings, all i need is the start time
		if ( foundDay.getEvents().getHead() == null ){ //in order to not print out empty days, i need to remove the day node if there are no events within a day (head is null)
			this.theCalendar.remove( foundDay );
		}
	}
	
	public CalendarDay find( CalendarDay otherDay ){ //calls find from linkedlist, this returns the reference to the found day, which is returned - i use this method in remove()!!
		CalendarDay foundDay = null;
		foundDay = (CalendarDay) this.theCalendar.find( otherDay );
		return foundDay;
	}
		
	public void populate( Scanner scan ){
		//the following strings just make the instructions for how to enter a day look pretty
		String border = "============================================================";
		String enter = "Enter a new Calendar Event in the following example format:";
		String exit = "ENTER -1 TO QUIT AND PRINT CALENDAR";
		String event = "1100 1300 6 28 2016\nLunch with the family\nCici Pizza\nMeeting my wife and kids for lunch";
		System.out.println( border + "\n" + enter + "\n" + "\n" + event + "\n" + border + "\n" + exit + "\n" );//print the instructions
		String invalid = "Invalid date: Events can only be scheduled today or in the future";
		
/**/	scan.nextLine();  // if main is not working, it might be because of this?
		//i wanted to use the scanner.nextInt() because it is clean, so my line string becomes the ints of startTime, endTime, day, month, year
		String line = scan.nextLine();		
		while ( scan.hasNextLine() && !line.equals("-1")){ //while there is another line and the line is not a -1
			Scanner lineScan = new Scanner(line); //new scanner just to read line - so i can parse it more easily to get the INTS - neater
			int startTime = lineScan.nextInt(), endTime = lineScan.nextInt(), month = lineScan.nextInt(), day = lineScan.nextInt(), year = lineScan.nextInt();
			CalendarDay entryDay = new CalendarDay( month, day, year );			//make a new calendar day out of the input info
			String eventName = scan.nextLine(), location = scan.nextLine(), description = scan.nextLine(); //scanner parses the next 3 lines to get other info
			CalendarEvent entryEvent = new CalendarEvent( startTime, endTime, eventName, location, description );
			if ( !this.validate( entryDay )){ //if NOT a valid day
				System.out.println( invalid + "\n"); //events can only be scheduled today or in the future
			}else{ //it is a valid day
				entryDay = (CalendarDay) this.theCalendar.insert(entryDay);//calls the insert method from linkedlist - this will also check if there already is that day in the calendar and then just add this event to it
				entryDay.getEvents().insert(entryEvent); //this will insert the new event into the day, checking if the event overlaps anything - if it does, it will not be entered
			}
			line = scan.nextLine(); //get the next line
			lineScan.close(); //close my line scanner
		}	
	}
				//this.events.insert(other.events.getHead().getData());
				

	
	private boolean validate( CalendarDay other ){
		Boolean rtnBool = false;
		java.util.Calendar cal = java.util.Calendar.getInstance();
		CalendarDay today = new CalendarDay( ((cal.get(java.util.Calendar.MONTH)) + 1 ), cal.get( java.util.Calendar.DATE ), cal.get( java.util.Calendar.YEAR ));

		int x = today.compareTo( other ); 
		if ( x == 1 ){					//1 means today is after the day you're comparing to
			rtnBool = false;
		}else{							//-1 means today is before the day you're comparing to
			rtnBool = true;				//0 means they are the same time/date
		}
		return rtnBool;
	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LinkedList getTheCalendar() {
		return theCalendar;
	}

	public void setTheCalendar(LinkedList theCalendar) {
		this.theCalendar = theCalendar;
	}
	
	public String DayToString( int day, int month, int year ){
		String rtnString = "";
		//THIS CALENDAR DAY HAS TO BE PUT IN BACKWARDS!!! - something to do with Sebastian's code
		CalendarDay fakeDay = new CalendarDay( day, month, year );
		CalendarDay rtnDay = (CalendarDay) this.theCalendar.find( fakeDay );
		String border = "============================================================";
		
		if( rtnDay == null ){
			rtnString = border + "\n" + "Nothing to do on this day!" + "\n" + border;
		}else{
			rtnString = rtnDay.toString();
		}
		return "\n" + rtnString + "\n";
	}

}
