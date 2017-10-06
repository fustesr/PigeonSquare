import java.awt.Dimension;

import javax.swing.*;
public class Main {

	public static void main(String[] args) {
		
		JFrame jf = new JFrame("PigeonSquare");
		jf.setSize(new Dimension(1200,800));
		jf.setVisible(true);
		
		ImageIcon icone = new ImageIcon("res\\pigeon2.png");
		JLabel image = new JLabel(icone);
		jf.add(image);
		jf.setVisible(true);
		
	
	}
	

}
