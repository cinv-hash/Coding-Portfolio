
public class LinkedList {
	
	private LinkedListNode head;
	
	public LinkedList(){
		this.head = null;
	}
	
	public Boolean isEmpty(){
		Boolean rtnbool = false;
		if ( this.head == null ){
			rtnbool = true;
		}else{
			rtnbool = false;
		}
		return rtnbool;
	}
	
	public LinkedListNode getHead() {
		return this.head;
	}
	
	public void setHead( LinkedListNode item ){
		this.head = item;
	}
	
	public Comparable<Object> insert(Comparable<Object> item){
		Comparable<Object> rtnObject = item; //return object is the item
		if(this.head == null){
			// In this case, there is no head, so you are just inserting
			this.head = new LinkedListNode(item);	
		}else if(item.compareTo(this.head.getData()) < 0){ //there is a head, data to insert should come before the head's data
			LinkedListNode temp = new LinkedListNode(item); //putting the new node infront of the head's node
			temp.setNext(head);
			this.head = temp;
		}else if( item.compareTo(this.head.getData()) == 0){ //there is a head, but the items are the same
			rtnObject = this.head.getData(); //return object is the data inside the head
		}else{
			boolean inserted = false;
			LinkedListNode current = this.head;
			while(!inserted){
				if(current.getNext() == null){
					// We have reached the end of the list
					current.setNext(new LinkedListNode(item));
					inserted = true;
				}else if(current.getNext().getData().compareTo(item) > 0){
					// The new item goes after the current item but before the next
					// item, so we need to insert in between
					LinkedListNode temp = new LinkedListNode(item);
					temp.setNext(current.getNext());
					current.setNext(temp);
					inserted = true;
				}else if( current.getNext().getData().compareTo(item) == 0 ){ // if the node's next node's data is the same as what's being compared to
					inserted = true;
					rtnObject = current.getNext().getData(); //set return object = to the data inside the next node
				}else{
					current = current.getNext(); //keep going
				}
			}
		}
		return rtnObject;
		
	}
	
	public String toString(){ //returns a string of each node's data, unless it is empty
		String rtnString = "";
	if ( this.head == null ){ //list is empty
			rtnString += "Nothing in here!!";
		}else if (this.head.getNext() == null ){ //if there is only the head
			rtnString += this.head.getData().toString(); //just call the data's toString() for the head
		}else{ //else, need to traverse and call each content's toString()
			LinkedListNode current = this.head;
			while( current.getNext() != null ){ //while there are more items
				rtnString += current.getData().toString() + "\n"; //add the strings of the data to the rtnString
				current = current.getNext(); //get next node in list
			}
			rtnString += current.getData().toString(); 
		}
		return rtnString;
	}
	
	public Comparable<Object> find( Comparable<Object> item ){ //looks through list to find an object that is the same as what is being passed it
		Comparable<Object> rtnObject = null;
		if( this.head == null ){ //if the head is empty, list is empty
			rtnObject = null; //nothing gets returned
		}else if( this.head.getNext() == null){ //if there is a head, just 1 node
			if ( this.head.getData().compareTo( item ) == 0 ){ //
				rtnObject = this.head.getData();
			} else{
				rtnObject = null;
			}
		}else{
			LinkedListNode current = this.head;
			Boolean found = false;
			while ( !found ){ //while it hasnt been found
				if ( current.getNext() == null ){
					rtnObject = null;
					found = true;
				}else if( current.getNext().getData().compareTo( item ) == 0 ){ // if the current objects data is the same as the item's data
					rtnObject = current.getNext().getData(); //rtnObject is the current's data ( which is a comparable object of days?? )
					found = true; // item has been found, break
				}else{ // if current's data is not same as item
					current = current.getNext(); //go to next item
				}
			}
		}
		return rtnObject; //returns a reference to an item in the list that is the same as the item passed in - very useful!
	}
	
	public Comparable<Object> remove( Comparable<Object> item ){ //method checks if object being passed in is in the list, then if it is, it will remove it, then return a reference to the removed object
		Comparable<Object> rtnObject = null;
		
		if( this.head == null ){ //no head = empty
			rtnObject = null;
		}else if( this.head.getNext() == null){ //only a head
			if ( this.head.getData().compareTo( item ) == 0 ){
				rtnObject = this.head.getData();
				this.head.setData( null ); //empty the head - perhaps not necessary
				this.head = null; //delete the head
			} else{
				rtnObject = null;
			}
		}else{
			LinkedListNode current = this.head;
			Boolean found = false;
			while ( !found ){ //while it hasn't been found
				if ( current.getNext().getData().compareTo( item ) == 0 ){ //if the next node's data is the same as the item's data
					rtnObject = current.getNext().getData(); //rtnObject is the next node's data
					current.getNext().setData( null ); //deletes the data in the next node
					current.setNext( current.getNext().getNext() ); // set the current node's next to next next
					found = true; //item has been found, break
				}else{ //if next node's data is not same as item
					current = current.getNext(); //go to next item
				}
			}
		}
		return rtnObject; //returns a reference to the item, if it already exists, and deletes it from the list
	}
	
}
