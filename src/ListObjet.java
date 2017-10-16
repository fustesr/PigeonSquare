import java.util.ArrayList;

public class ListObjet {

	// ListN : nourritures  /  listNP : nourritures pourries  /  listB : bombes
	ArrayList<Objet> listN, listNP, listB;
	
	public ListObjet() {
		
		listN = new ArrayList<Objet>();
		listNP = new ArrayList<Objet>();
		listB = new ArrayList<Objet>();

	}
	
	// Ajouter l'objet n dans la liste al
	public void add(ArrayList<Objet> al, Objet n) {
		
		al.add(n);
	}
	
	// Supprimer l'objet n de la liste al
	public void remove(ArrayList<Objet> al, Objet n) {
		
		al.remove(n);
	}
	
	// Calcul la distance euclidienne entre un pigeon et un objet (nourriture ou bombe)
	public double distanceObjet(Pigeon p1, Objet n2) {
		
		return Math.sqrt((Math.pow(n2.getX()-p1.getX(), 2)) + (Math.pow(n2.getY()-p1.getY(), 2)));
	}
}
