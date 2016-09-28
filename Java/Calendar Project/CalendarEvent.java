

public class CalendarEvent implements Comparable<Object>{
	
	private int startTime;
	private int endTime;
	private String eventName;
	private String location;
	private String description;
	
	public CalendarEvent( int startTime, int endTime, String eventName, String location, String description ){
		this.startTime = startTime;
		this.endTime = endTime;
		this.eventName = eventName;
		this.location = location;
		this.description = description;
	}
	
	public int compareTo( Object o ){ //an event is a comparable object - this means we can check if it overlaps another event - if it does, it must not be allowed to be inserted into the day
		CalendarEvent other = (CalendarEvent) o;
		int rtnval = 0;
		if ( other.endTime > this.startTime && other.endTime < this.endTime ){ 	//0 means it is the same / overlapping / coinciding
			 rtnval = 0;
		}else if ( this.endTime > other.startTime && this.endTime < other.endTime ){
			rtnval = 0;
		}else if ( this.endTime < other.startTime ){ //other happens after this 
			rtnval = -1;
		}else if ( this.startTime > other.endTime ){//other happens before this 
			rtnval = 1;
		}
		return rtnval;
	}
	
	public String toString() { //formats a string so event is printed out nicely, with even columns and one dash line at the bottom, then they can be concatenated without duplication
		String time = Integer.toString(this.startTime) + " - " + Integer.toString( this.endTime);
		String dashes = "------------------------------------------------------------";
		String one = String.format("%1$-15s %2$-20s", "Event:", this.eventName );
		String two = String.format("%1$-15s %2$-20s", "Time:", time );
		String three = String.format("%1$-15s %2$-20s", "Location:", this.location );
		String four = String.format("%1$-15s %2$-20s", "Description:", this.description );
		return one + "\n" + two + "\n" + three + "\n" + four + "\n" + dashes;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
