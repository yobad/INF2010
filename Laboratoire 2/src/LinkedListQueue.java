import sun.invoke.empty.Empty;

public class LinkedListQueue<AnyType> implements Queue<AnyType>
{	
	// Un noeud de la file
	@SuppressWarnings("hiding")
	private class Node<AnyType> 
	{
		private AnyType data;
		private Node next;
		
		public Node(AnyType data, Node next) 
		{
			this.data = data;
			this.next = next;
		}

		public void setNext(Node next) 
		{
			this.next = next;
		}
		
		public Node<AnyType> getNext() 
		{
			return next;
		}
		
		public AnyType getData() 
		{
			return data;
		}
	}
   
	private int size = 0;		//Nombre d'elements dans la file.
	private Node<AnyType> last;	//Dernier element de la liste
	
	//Indique si la file est vide
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	//Retourne la taille de la file
	public int size() 
	{ 
		return size; 
	}
	
	//Retourne l'element en tete de file
	//Retourne null si la file est vide
	//complexit� asymptotique: O(1)
	public AnyType peek()
	{
		//A completer
		if(empty())
			return null;
		return last.getNext().getData();
	}
	
	//Retire l'element en tete de file
	//complexit� asymptotique: O(1)
	public void pop() throws EmptyQueueException {
		//A completer
		if(empty())
			throw  new EmptyQueueException();
		last.setNext(last.next.next);
		size--;
	}
	//Ajoute un element a la fin de la file
	//complexit� asymptotique: O(1)
	public void push(AnyType item)
	{		
		//A completer
		Node<AnyType> newLast = new Node(item, null);
		if(this.empty()){
			last = newLast;
			last.setNext(last);
		}
		else {
			newLast.setNext(last.getNext());
			last.setNext(newLast);
			last = newLast;
		}


		size++;
	}  
}
