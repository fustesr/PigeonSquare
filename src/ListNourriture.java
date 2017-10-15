import java.util.ArrayList;

public class ListNourriture {

	ArrayList<Nourriture> listN, listNP;
	
	public ListNourriture() {
		
		listN = new ArrayList<Nourriture>();
		listNP = new ArrayList<Nourriture>();
		
	}
	
	public void add(ArrayList<Nourriture> al, Nourriture n) {
		
		al.add(n);
	}
	public void remove(ArrayList<Nourriture> al, Nourriture n) {
		
		al.remove(n);
	}
	
	public double distanceNourriture(Pigeon p1, Nourriture n2) {
		
		return Math.sqrt((Math.pow(n2.getX()-p1.getX(), 2)) + (Math.pow(n2.getY()-p1.getY(), 2)));
	}
}
