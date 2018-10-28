import java.util.NoSuchElementException;

/**
 * 
 * @author Brandon Chambers
 *
 * @param <T>
 * 
 *            my data structure implementation of 'Stack'
 * 
 *            turn MyStack into a custom linked list
 */
public class MyStack<T>
{
	private int size; // size of the stack
	private Node top; // top of stack

	private class Node
	{
		private T item;
		private Node next;
	}

	// constructor that initializes an empty MyStack
	public MyStack()
	{
		top = null;
		size = 0;
	}

	// adds the item to the top of the MyStack
	public void push(T item)
	{
		Node oldTop = top;
		top = new Node();
		top.item = item;
		top.next = oldTop;
		size++;
	}

	// return true if MyStack implementation is empty
	public boolean isEmpty()
	{
		return (top == null);

	}

	// removes and returns the item on the top of the MyStack
	public T pop()
	{
		if (isEmpty())
			throw new NoSuchElementException("Stack Underflow");
		T item = top.item; // create new object to save item to return
		top = top.next; // delete the top/head node
		size--; // decrement size since we've now removed one node
		return item;
	}

	// this method returns the item on the top of the MyStack
	// but does not alter MyStack
	public T peek()
	{
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		return top.item;
	}

	// returns the number of items in the MyStack
	public int size()
	{
		return size;
	}

	// converts the contents of the MyStack to a String for display
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		Node current = top;
		sb.append("[");
		while (current != null)
		{
			sb.append(current.item).append(", ");
			current = current.next;
		}
		if (sb.charAt(sb.length() - 1) == ' ')
			sb.deleteCharAt(sb.length() - 1);
		if (sb.charAt(sb.length() - 1) == ',')
			sb.deleteCharAt(sb.length() - 1);

		sb.append("]");
		return sb.toString();
	}

}
