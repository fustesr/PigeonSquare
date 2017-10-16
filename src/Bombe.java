import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Bombe extends Objet implements ActionListener {
	
	
	// Timer d'apparition de la bombe sur le terrain
	Timer t = new Timer(3000,this);
	
	public Bombe(int x, int y, ListObjet lo) {
		
		this.x = x;
		this.y = y;
		this.lo = lo;
		t.start();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		lo.remove(lo.listB, this);
		t.stop();

	}
}
