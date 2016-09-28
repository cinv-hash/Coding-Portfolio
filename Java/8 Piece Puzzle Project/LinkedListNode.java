
public class LinkedListNode
{
	private Object data;
	private LinkedListNode next;
	
	public LinkedListNode(Object data)
	{
		this.data=data;
		next = null;
	}
	
	public LinkedListNode getNext()
	{
		return next;
	}
	
	public void setNext(LinkedListNode next)
	{
		this.next = next;
	}
	
	public Object getData()
	{
		return data;
	}
	
	public void setData(Object data)
	{
		this.data = data;
	}
}
