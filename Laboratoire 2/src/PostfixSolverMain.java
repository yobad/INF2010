import java.io.*;
import java.util.Stack;

public class PostfixSolverMain 
{
	public static void main(String[] args) throws IOException 
	{
		Stack<Double> stack = new Stack<Double>();
		
		String s = "25 5 2 * + 15 3 / 5 - +";
		
		//L'expression est separee en tokens selon les espaces
		double val1 = 0;
		double val2 = 0;

		for(String token : s.split("\\s")) 
		{

			//A completer
			switch(token.charAt(0)){
				//Note: Pour des raisons inconnues, Java refuse de faire un switch case sur des string
				// donc on est obligé de prendre que le premier char de token.
				case '+':
					val1 = stack.pop();
					val2 = stack.pop();
					stack.push(new Double(val1+val2));
					break;
				case '-':
					val1 = stack.pop();
					val2 = stack.pop();
					stack.push(new Double(val2-val1));
					break;
				case '*':
					val1 = stack.pop();
					val2 = stack.pop();
					stack.push(new Double(val1*val2));
					break;
				case '/':
					val1 = stack.pop();
					val2 = stack.pop();
					stack.push(new Double (val2/val1));
					break;
				default:
					stack.push(new Double(Double.parseDouble(token)));
					break;
			}
		}

		System.out.println("25 + 5*2 + 15/3 - 5 = "+stack.peek());
		if(stack.peek() == 35)
			System.out.println("It's all good");
		else
			System.out.println("Erreur: mauvais resultat");
     }
}
