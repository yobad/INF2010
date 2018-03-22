import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maitr
 */
public class Node {

	public int ordre;
	public Node parent;

	private int valeur;
	private ArrayList<Node> enfants;

	public Node(int valeur) {
		this.valeur = valeur;
		ordre = 0;
		this.parent = null;
		enfants = new ArrayList<Node>();
	}

	public Node(int valeur, Node parent) {
		ordre = 0;
		this.valeur = valeur;
		this.parent = parent;
		enfants = new ArrayList<Node>();
	}

	public int getVal() {
		return valeur;
	}

	public ArrayList<Node> getEnfants() {
		return enfants;
	}

	public void addEnfant(Node enfant) {
		enfants.add(enfant);
	}

	public boolean removeEnfant(Node enfant) {
		if (enfants.contains(enfant)) {
			enfants.remove(enfant);
			return true;
		}
		return false;
	}

	public void removeEnfants(ArrayList<Node> enfants) {
		this.enfants.removeAll(enfants);
	}

	public Node fusion(Node autre) throws DifferentOrderTrees {
		// A completer
		if(autre.ordre != this.ordre)
			throw new DifferentOrderTrees();
		if(this.parent == null && autre.parent ==null){
			if(this.valeur > autre.valeur){
				this.enfants.add(autre);
				autre.parent = this;
				this.ordre++;
				return this;
			}
			else {
				autre.enfants.add(this);
				this.parent = autre;
				autre.ordre++;
				return autre;
			}
		}

		return this;
	}

	private void moveUp() {
		// A completer
		int temp = this.valeur;
		if(parent!=null){
			this.valeur = parent.valeur;
			parent.valeur = temp;
		}
	}

	public ArrayList<Node> delete() {
		// A completer
		Node actuel=this;
		while(actuel.parent!=null) {
			actuel.moveUp();
			actuel=actuel.parent;
		}
		for (int i = 0; i < enfants.size() ; i++)
			actuel.enfants.get(i).parent=null;

		return  actuel.enfants;
	}

	public Node findValue(int valeur) {
		// A completer
		Node element = null;
		if (this.valeur == valeur)
			element=this;
		else{
			for (int i = 0; i < enfants.size() && enfants.get(i).valeur >= valeur; i++)
				element=  enfants.get(i).findValue(valeur);
			}
		return element;
	}


	public ArrayList<Integer> getElementsSorted() {
		// A completer
		ArrayList<Integer> sortedArray=new ArrayList<Integer>();
		sortedArray.add(this.valeur);


		return null;
	}

	public void print() {
        print("", true);
    }

    private void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "'-- " : "|-- ") + valeur);
        for (int i = 0; i < enfants.size() - 1; i++) {
            enfants.get(i).print(prefix + (isTail ? "    " : "|   "), false);
        }
        if (enfants.size() > 0) {
            enfants.get(enfants.size() - 1)
                    .print(prefix + (isTail ?"    " : "|   "), true);
        }
    }
}