import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.*;

public class Pigeon extends Thread {
	public String cheminImage = "res\\pigeon.png";
	public int posx;
	public int posy;
	public BufferedImage image;
	
	public Pigeon(){
		
		try {
			image = ImageIO.read(new File(cheminImage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		posx = (int)Math.random()*1200;
		posy = (int)Math.random()*800;

	}
	
}
