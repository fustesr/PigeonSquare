import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Nourriture implements ActionListener{

	private int x;
	private int y;
	ListNourriture ln;
	private boolean comestible;
	Timer t = new Timer(7000,this);
	
	public Nourriture(int x, int y, ListNourriture ln) {
		
		this.x = x;
		this.y = y;
		this.ln = ln;
		comestible = true;
		t.start();
	}
	
	public int getX() {
		
		return x;
	}
	public int getY() {
		
		return y;
	}
	public boolean getComestible() {
		return comestible;
	}
	
	
	public synchronized void manger(Nourriture n, Pigeon p) {
		
		System.out.println("Pigeon : " + p.hashCode() + " mange!");
		synchronized(Main.objectLockN) {
			ln.remove(ln.listN, n);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(ln.listN.indexOf(this) != -1) {
			comestible = false;
			synchronized(Main.objectLockN) {
				ln.remove(ln.listN, this);
			}
			synchronized(Main.objectLockN) {
				ln.add(ln.listNP,this);
			}

		} 
		t.stop();



	}
}




 