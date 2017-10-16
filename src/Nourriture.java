import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Nourriture extends Objet implements ActionListener {


	ListObjet lo;
	private boolean comestible;
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
	
	
	public synchronized void manger(Nourriture n, Pigeon p) {
		
		synchronized(Main.objectLockN) {
			lo.remove(lo.listN, n);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(lo.listN.indexOf(this) != -1) {
			comestible = false;
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




 