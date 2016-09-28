public class CalendarDay implements Comparable<Object>{

	private int day;
	private int month;
	private int year;
	private LinkedList events;
	
	public CalendarDay( int month, int day, int year ){		
		this.day = day;
		this.month = month;
		this.year = year;
		this.events = new LinkedList();
	}
	
	public LinkedList getEvents() {
		return events;
	}

	public void setEvents(LinkedList events) {
		this.events = events;
	}

	public int compareTo( Object o ){
		CalendarDay other = (CalendarDay) o; //make object into a calendar day
		int rtnval = 0;
		//same day 0 - if the day's month, day, and year are the same, it is the same day
		if ( this.day == other.day && this.month == other.month && this.year == other.year ){
			rtnval = 0;
		}
		//earlier -1 -- if the year is smaller than the other year, if the year is the same, month is smaller, if year and month the same, but day is smaller, it comes before the other
		else if ( (this.year < other.year) || (this.year == other.year && this.month < other.month) || (this.year == other.year && this.month == other.month && this.day < other.day)){
			rtnval = -1;
		}
		else{ //later 1 -- all other cases are later
			rtnval = 1;
		}
		return rtnval;
	}
	
	public String toString() {
		String m = Integer.toString( this.month );
		//here, i just make it look pretty - if the day or month is a single digit, put a 0 in front of it
		if ( this.month < 9 ){
			m = "0" + this.month;
		}
		String d = Integer.toString( this.day );
		if ( this.day < 9 ){
			d = "0" + this.day;
		}
		String date = m + "/" + d + "/" + this.year;
		String topString = "======================== " + date + " ========================";
		String dashes = "------------------------------------------------------------";
		String bottomString = "============================================================";
		String dayEvents = "";
		dayEvents = this.events.toString(); //each event in the day is printed out, underneath each other, within the dashes
		return topString + "\n" + dashes + "\n" + dayEvents + "\n" + bottomString + "\n";
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
