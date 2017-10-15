import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Bombe implements ActionListener {
	
	private int x;
	private int y;

	Timer t = new Timer(3000,this);
	
	public Bombe(int x, int y) {
		
		this.x = x;
		this.y = y;
		t.start();
	}
	
	public int getX() {
		
		return x;
	}
	public int getY() {
		
		return y;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Main.lb.remove(this);
		t.stop();

	}
}
