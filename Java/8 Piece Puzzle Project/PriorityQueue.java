
public class PriorityQueue extends Queue{
	
	public PriorityQueue(){
		
		super(); // inherits attributes from queue which inherits from linked list
	}
	/**
	 * @method priorityEnqueue
	 * @param item
	 * Places the parameter object into the queue based on how the objects compare to each other in priority.
	 */
	public void priorityEnqueue(Comparable<Object> item){
		
		if(this.head == null){
			this.head = new LinkedListNode(item);
			size++;
		}else if(item.compareTo(this.head.getData()) <= 0){
			LinkedListNode temp = new LinkedListNode(item);
			temp.setNext(head);
			this.head = temp;
			size++;
		}else{
			boolean inserted = false;
			LinkedListNode current = this.head;
			while(!inserted){
				if(current.getNext() == null){
					current.setNext(new LinkedListNode(item));
					inserted = true;
					size++;
				}else if(item.compareTo( current.getNext().getData()) < 0){
					LinkedListNode temp = new LinkedListNode(item);
					temp.setNext(current.getNext());
					current.setNext(temp);
					inserted = true;
					size++;
				}else{
					current = current.getNext();
				}
			}
		}
	}
	
	/**
	 * @method find
	 * @param item
	 * Method searches a linked list and tries to find an object that matches the parameter object.
	 * @return Comparable<Object> that is the same as parameter Object, unless it is not in the list (null).
	 */
	public Comparable<Object> find(Comparable<Object> item){

		Object o = null;
		if( this.head == null ){ //if the head is empty, list is empty
			o = null; //nothing gets returned
		}else if( head.getNext() == null){ //if there is a head, just 1 node and it is NOT what you are looking for
			if ( !item.equals( head.getData() )){ 
				o = null;
			}
		}else if( item.equals( head.getData() ) ){ //if the item you're looking for is the head, return the head
			o = head.getData();
		}else{
			LinkedListNode current = head;
			Boolean found = false;
			while ( !found ){ //while item hasn't been found
				if ( current.getNext() == null ){
					o = null;
					found = true;
				}else if( item.equals( current.getNext().getData() ) ){ // if the current objects data is the same as the item's data
					o = current.getNext().getData(); //rtnObject is the current's data ( which is a comparable object of days?? )
					found = true; // item has been found, break
				}else{ // if current's data is not same as item
					current = current.getNext(); //go to next item
				}
			}
		}
		return (Comparable<Object>)o; 
	} 
}
