import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Nourriture extends Objet implements ActionListener {

	
	private boolean comestible;
	
	// Timer avant que la nourriture ne pourrisse
	Timer t = new Timer(8000,this);
	
	public Nourriture(int x, int y, ListObjet lo) {
		
		this.x = x;
		this.y = y;
		this.lo = lo;
		comestible = true;
		t.start();
	}
	
	
	public boolean getComestible() {
		return comestible;
	}
	
	// Fonction qui permet de manger une nourriture et de la faire disparaitre (en gerant la concurrence)
	public synchronized void manger(Nourriture n, Pigeon p) {
		
		synchronized(Main.objectLockN) {
			lo.remove(lo.listN, n);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// Si la nourriture est encore dans la liste, elle n'est plus comestible
		if(lo.listN.indexOf(this) != -1) {
			comestible = false;
			
			// Gestion de la concurrence pour les collections de nourritures
			synchronized(Main.objectLockN) {
				lo.remove(lo.listN, this);
			}
			synchronized(Main.objectLockN) {
				lo.add(lo.listNP,this);
			}

		} 
		t.stop();



	}
}




 