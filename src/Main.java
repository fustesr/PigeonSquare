import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame implements MouseListener {
	
	final static int nbPigeon = 10;
	private MyCanvas canvas = new MyCanvas();
	static Pigeon[] tabPigeon = new Pigeon[nbPigeon];
	static ListNourriture ln;
	
	int MouseX;
	int MouseY;
	

	static Object objectLockN = new Object();
	static Object objectLockNP = new Object();
	

	public Main() {
		
		ln = new ListNourriture();
		setLayout(new BorderLayout());
		setSize(1200,800);
		setTitle("Demo");
		add("Center", canvas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		canvas.addMouseListener(this);

	}
	
	public static void main(String[] args) {
		
		Main fr = new Main();
		
		for(int i =0; i < nbPigeon; i++) {
			tabPigeon[i] = new Pigeon(ln);
		}
		for(Pigeon p : tabPigeon) {
			p.start();
		}
		
		while(true) {
			System.out.println("N: " + ln.listN);
			System.out.println("NP: " +ln.listNP);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	private class MyCanvas extends JPanel {
		
		Image pigeon = new ImageIcon("..\\PigeonSquare\\res\\pigeon2.png").getImage();
		Image burger = new ImageIcon("..\\PigeonSquare\\res\\burger2.png").getImage();
		Image burgerNoir = new ImageIcon("..\\PigeonSquare\\res\\burger-noir2.png").getImage();
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			for(Pigeon p : tabPigeon) {
				g.drawImage(pigeon, (int) (p.getX()-(pigeon.getWidth(canvas)/2)),(int) (p.getY()-(pigeon.getHeight(canvas)/2)), this);
			}
			synchronized(objectLockN){
				for(Nourriture n : ln.listN) {
					g.drawImage(burger, n.getX()-(burger.getWidth(canvas)/2), n.getY()-(burger.getHeight(canvas)/2), this);
				}
			}
			synchronized(objectLockNP) {
				for(Nourriture np : ln.listNP) {
					g.drawImage(burgerNoir, np.getX()-(burgerNoir.getWidth(canvas)/2), np.getY()-(burgerNoir.getHeight(canvas)/2), this);
				}
			}

			repaint();
		}
		
	}



	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		MouseX = e.getX();
		MouseY = e.getY();
		//System.out.println("MouseX: "+ MouseX + " MouseY: " + MouseY);
		Nourriture n = new Nourriture(MouseX, MouseY, ln);
		System.out.print("N: "+ n.hashCode());
		ln.add(ln.listN, n);
		
		synchronized(Pigeon.objectLock) {
			Pigeon.objectLock.notifyAll();
		}
	}

}
	