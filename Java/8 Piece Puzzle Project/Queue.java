
public class Queue extends LinkedList implements QueueInterface
{
	protected int size;
	
	public Queue()
	{
		super();
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	public Object front() throws EmptyQueueException
	{
		if(isEmpty())
		{
			throw new EmptyQueueException("Queue is empty!");
		}
		return head.getData();
	}
	
	public Object dequeue() throws EmptyQueueException
	{
		if(isEmpty())
		{
			throw new EmptyQueueException("Queue is empty!");
		}
		size--;
		return removeFront();
	}
	
	public void enqueue(Object item)
	{
		insertBack(item);
		size++;
	}
	
	
	
	
}
