import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;

import java.util.Random;
import java.util.Stack;


public class SortStackMain 
{
	static final int COUNT = 30;
	static final int MAX_VALUE = 1000;
	
	public static void main(String[] args) 
	{
		boolean sortIsGood = true;
		Random generator = new Random( System.nanoTime() );
		Stack<Integer> stack = new Stack<Integer>();


		for(int i = 0; i < COUNT; i++)
			stack.push(generator.nextInt(MAX_VALUE));


		stack = sortStack(stack);
		
		boolean countIsGood = size(stack) == COUNT;
			
		int tmp = stack.pop();
		while(!stack.isEmpty())
		{
			System.out.print(tmp + ", ");
			
			if(tmp > stack.peek())
				sortIsGood = false;
			
			tmp = stack.pop();
		}
		System.out.println(tmp);
		
		if(!countIsGood)
			System.out.println("Erreur: il manque des elements dans la pile");
		else if(!sortIsGood)
			System.out.println("Erreur: le trie a echoue");
		else
			System.out.println("It's all good");
	}

	private static int size(Stack<Integer> stack) {
		//A completer
		//return stack.size();
		Stack<Integer> temp = new Stack<Integer>();

		int count = 0;
		while(!stack.empty()){
			temp.push(stack.pop());
			count++;
		}
		while(!temp.empty()){
			stack.push(temp.pop());
		}
		return count;
    }

	static Stack<Integer> sortStack(Stack<Integer> stack)
	{
		//A completer
		if(size(stack)<=1)
			return stack;
		// Début du tri:
		Stack<Integer> temp = new Stack<Integer>();
		int malPlace; //Un élèment mal placé dans la stack
		boolean isSorted = false; //Indique si on est rendu au dernier élèment mal palcé dans la stack.

		// Explication de l'algorithme
		// -Le tri est fait sur une stack ayant une taille plus grande que 1
		// Le premier while sert à vider la stack dans le temp et faire le tri tant qeu
		// LE premier recopiage est interrompue au moment ou on detecte un élément mal placé.
		//Le deuxième while sert à remettre les élèments de temp dans la stack originale tant que l'élément
		//qu'on remet est plus grand que l'élèment mal placé.
		while(!isSorted){

			temp.push(stack.pop());
			isSorted = stack.empty(); // <--- si le stack est vide à ce stade, il s'agit de la fin du tri
			//Test pour voir si le prochain élément qu'on va copier dans le temp est mal placé
			if(temp.peek()>stack.peek() ){

				malPlace = stack.pop(); // On sait que cet element est mal place et on arrête le premier recopiage
				isSorted = stack.empty();
				while(!temp.empty()&&temp.peek()> malPlace){

					stack.push(temp.pop());
				}
				//Lorsqu'on arrive à un élèment plus petit que malPlace, on arrête le recopiage temp->stack et on met
				//malPalce dans sa bonne position.
				temp.push(malPlace);
			}
		}
		while(!temp.empty()){
			//Dernier while sert à remettre les élèments de temp dans stack s'il en reste.
			stack.push(temp.pop());
		}
		return stack;
	}
}
