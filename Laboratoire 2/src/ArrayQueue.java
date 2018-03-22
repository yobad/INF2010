

public class ArrayQueue<AnyType> implements Queue<AnyType>
{
	private int size = 0;		//Nombre d'elements dans la file.
	private int capacity = 0;
	private int startindex = 0;	//Index du premier element de la file
	private AnyType[] table;
   
	@SuppressWarnings("unchecked")
	public ArrayQueue() 
	{
		//A completer
		//Il n'y a rien à initialiser
		
	}
	
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
		if(this.empty()){
			return null;
		} else {
			return table[startindex];
		}
	}
	
	//Retire l'element en tete de file
	//complexit� asymptotique: O(1)
	public void pop() throws EmptyQueueException {

		//A completer
		if(empty())
			throw new EmptyQueueException();
		startindex = (startindex +1 )%(table.length);
		size--;
	}
	//Ajoute un element a la fin de la file
	//Double la taille de la file si necessaire (utiliser la fonction resize definie plus bas)
	//complexit� asymptotique: O(1) ( O(N) lorsqu'un redimensionnement est necessaire )
	public void push(AnyType item)
	{
		//A completer
		if(capacity == (size+startindex)){
			resize(2);
		}

		table[(startindex+size)%(table.length)] = item;
		size++;


	}
   
	//Redimensionne la file. La capacite est multipliee par un facteur de resizeFactor.
	//Replace les elements de la file au debut du tableau
	//complexit� asymptotique: O(N)
	@SuppressWarnings("unchecked")
	private void resize(int resizeFactor)
	{
		//A completer
		if(capacity == 0)
			capacity++;
		capacity = capacity*resizeFactor;
		AnyType[] temp =  (AnyType[])new Object[capacity];
		for(int i = 0; i<(size+startindex); i++){
			temp[i] = table[i];
		}

		table = null;
		table = temp;
		
	}   
}

