import java.util.ArrayList;

public class ListObjet {

	ArrayList<Objet> listN, listNP, listB;
	
	public ListObjet() {
		
		listN = new ArrayList<Objet>();
		listNP = new ArrayList<Objet>();
		listB = new ArrayList<Objet>();

	}
	
	public void add(ArrayList<Objet> al, Objet n) {
		
		al.add(n);
	}
	public void remove(ArrayList<Objet> al, Objet n) {
		
		al.remove(n);
	}
	
	public double distanceObjet(Pigeon p1, Objet n2) {
		
		return Math.sqrt((Math.pow(n2.getX()-p1.getX(), 2)) + (Math.pow(n2.getY()-p1.getY(), 2)));
	}
}
