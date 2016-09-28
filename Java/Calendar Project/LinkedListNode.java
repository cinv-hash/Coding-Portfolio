
public class LinkedListNode {

	private Comparable<Object> data;
	private LinkedListNode next;
	
	public LinkedListNode( Comparable<Object> item ){
		this.data = item;
		this.next = null;
	}

	public Comparable<Object> getData() {
		return data;
	}

	public void setData(Comparable<Object> data) {
		this.data = data;
	}/*
		if( this.data == null ){
			this.data = data;
		}else{
			this.setNext( new LinkedListNode(data) );
		}
	}*/

	public LinkedListNode getNext() {
		return next;
	}

	public void setNext(LinkedListNode next) {
		this.next = next;
	}

	public String toString() {
		return data.toString();
	}
	
	

}
