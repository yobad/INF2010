import java.util.ArrayList;

public class Monceau {
	ArrayList<Node> arbres;

	public Monceau() {
		arbres = new ArrayList<Node>();
	}

	public void fusion(Monceau autre) {
		// A completer
		Monceau monceauMax, monceauMin;

		int resultSize, i_limit;
		if(this.arbres.size() >= autre.arbres.size()) {
			monceauMax = this;
			monceauMin = autre;
		}
		else {
			monceauMax = autre;
			monceauMin = this;
		}

		ArrayList<Node> arbres_d_odre_j = new ArrayList<Node>();
		Node retenue = null;
		int j_max = monceauMax.arbres.size();
		for (int j =0; j<j_max; j++) {
			//Trouver tous les arbres d'orbre j
			if(retenue!=null)
				arbres_d_odre_j.add(retenue);
			if(monceauMax.arbres.get(j)!=null)
				arbres_d_odre_j.add(monceauMax.arbres.get(j));
			if(j<monceauMin.arbres.size()) {
				if(monceauMin.arbres.get(j) != null)
					arbres_d_odre_j.add(monceauMin.arbres.get(j));
			}
			//Fusionner selon le cas 0, 1, 2 ou 3 arbres à fusionner
			switch (arbres_d_odre_j.size()) {
				case 0:
					//Rien à faire monceauMax contient déjà rien à j
					break;
				case 1:
					//On copie le seul arbre qu'il y a à l'indice j.
					monceauMax.arbres.set(j,arbres_d_odre_j.get(0));
					if(retenue!=null) retenue = null; //Si la retenue a été utilisée, il faut la vider
					break;
				case 2:
					monceauMax.arbres.set(j,null);
					try {
						retenue = arbres_d_odre_j.get(0).fusion(arbres_d_odre_j.get(1));
					}
					catch (DifferentOrderTrees differentOrderTrees) {
						differentOrderTrees.printStackTrace();
					}
					break;
				case 3:
					monceauMax.arbres.set(j,arbres_d_odre_j.get(0));
					try {
						retenue = arbres_d_odre_j.get(1).fusion(arbres_d_odre_j.get(2));
					}
					catch (DifferentOrderTrees differentOrderTrees) {
					differentOrderTrees.printStackTrace();
				}
					break;
			}
			//Gestion de la dernière fusion si elle a lieu:
			if(j == j_max-1 && retenue != null) {
				monceauMax.arbres.add(retenue);
			}
			//Vider la liste d'arbres d'ordre j pour passer à j+1:
			arbres_d_odre_j.clear();
		}

		this.arbres = monceauMax.arbres;
	}

	public void insert(int val) {
		// A completer
		Node newNode = new Node(val);
		Monceau newMonceau = new Monceau();
		newMonceau.arbres.add(newNode);

		this.fusion(newMonceau);
	}

	public boolean delete(int val) {
		// A completer
		ArrayList<Node> childrenToRemove = new ArrayList<Node>();
		Node current;
		for(Node node:arbres) {

			if(node!=null) {
				current = node;
				while(current.findValue(val) != null) {
					//Keep searching

				}
			}
		}
		return false;
	}

	public void print() {

		for (Node node : arbres) {
			if(node != null) {
				System.out.println("\n\nordre : " + node.ordre);
				node.print();
			}
		}
	}

}
