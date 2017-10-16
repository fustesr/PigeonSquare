import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	final static int nbPigeon = 7;
	private MyCanvas canvas = new MyCanvas();
	static Pigeon[] tabPigeon = new Pigeon[nbPigeon];
	static ListObjet lo;
	
	int MouseX;
	int MouseY;
	
	//Lock utilisé pour synchroniser l'acces aux collections de nourritures
	static Object objectLockN = new Object();
	static Object objectLockNP = new Object();
	

	public Main() {
		
		lo = new ListObjet();
		
		canvas.setBackground(Color.GREEN);
		
		setLayout(new BorderLayout());
		setSize(1200,800);
		setTitle("PigeonSquare");
		add("Center", canvas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		canvas.addMouseListener(this);

	}
	
	public static void main(String[] args) {
		
		new Main();
		
		// Creation et lancements des threads pigeons 
		for(int i =0; i < nbPigeon; i++) {
			tabPigeon[i] = new Pigeon(lo);
		}
		for(Pigeon p : tabPigeon) {
			p.start();
		}

	}
	
	
	// Classe privée qui est le JPanel d'affichage
	private class MyCanvas extends JPanel {
		
		private static final long serialVersionUID = 1L;
		Image pigeon = new ImageIcon("..\\PigeonSquare\\res\\pigeon3.png").getImage();
		Image burger = new ImageIcon("..\\PigeonSquare\\res\\burger2.png").getImage();
		Image burgerNoir = new ImageIcon("..\\PigeonSquare\\res\\burger-noir2.png").getImage();
		Image bombe = new ImageIcon("..\\PigeonSquare\\res\\bombe.png").getImage();
		
		// Fonction principale de dessin
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			
			for(Pigeon p : tabPigeon) {
				g.drawImage(pigeon, (int) (p.getX()-(pigeon.getWidth(canvas)/2)),(int) (p.getY()-(pigeon.getHeight(canvas)/2)), this);
			}
			// Gestion de la concurrence pour ne pas itérer sur une collection modifiée par un autre Thread
			synchronized(objectLockN){
				for(Objet n : lo.listN) {
					g.drawImage(burger, n.getX()-(burger.getWidth(canvas)/2), n.getY()-(burger.getHeight(canvas)/2), this);
				}
			}
			synchronized(objectLockNP) {
				for(Objet np : lo.listNP) {
					g.drawImage(burgerNoir, np.getX()-(burgerNoir.getWidth(canvas)/2), np.getY()-(burgerNoir.getHeight(canvas)/2), this);
				}
			}
			
			for(Objet b: lo.listB) {
				g.drawImage(bombe, b.getX()-(bombe.getWidth(canvas)/2), b.getY()-(bombe.getHeight(canvas)/2), this);
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

	// Fonction qui gere le clique gauche et droit de la souris et qui reveil les threads endormis
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(SwingUtilities.isLeftMouseButton(e)) {
			MouseX = e.getX();
			MouseY = e.getY();
			Nourriture n = new Nourriture(MouseX, MouseY, lo);
			lo.add(lo.listN, n);
			
		} else if(SwingUtilities.isRightMouseButton(e)) {
			MouseX = e.getX();
			MouseY = e.getY();
			Bombe c = new Bombe(MouseX, MouseY, lo);
			lo.add(lo.listB, c);
		}
		synchronized(Pigeon.objectLock) {
			Pigeon.objectLock.notifyAll();
		}

	}


}
	