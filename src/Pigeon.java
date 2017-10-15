import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.sun.org.apache.bcel.internal.generic.LNEG;

class Pigeon extends Thread {
	
	private double x;
	private double y;
	double speed = 2;
	
	static Object objectLock = new Object();
	ListNourriture ln;
	
	public Pigeon(ListNourriture ln){


		x = (int) (Math.random()*1100);
		y = (int) (Math.random()*700);
		this.ln = ln;
		
		//Debug
		//System.out.println(this.hashCode() + "x: " + x +", y: "+ y);

	}
	
	public double getX() {
		
		return x;
	}
	public double getY() {
		
		return y;
	}
	
	public void move(Nourriture n) {
		
		double xVel = n.getX() - x;
		double yVel = n.getY() - y;
		double mag = Math.sqrt(xVel * xVel + yVel * yVel);
		xVel = xVel * speed / mag;
		yVel = yVel * speed / mag;

		if (x < n.getX() && (x + xVel) > n.getX()) {
			this.x = n.getX();
		} else if (x != n.getX()) {
			x += xVel;
		}
		if (y < n.getY() && (y + yVel) > n.getY()) {
			y = n.getY();
		} else if (y != n.getY()) {
			y += yVel;
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void moveAfraid() {
		System.out.println("ok");
	}
	
	@Override
	public void run() {

		while(true) {
			Nourriture n = null;
			
			try {
				while(!(Main.lb.isEmpty())) {
					moveAfraid();
				}
				if(!(ln.listN.isEmpty())) {
					n = ln.listN.get(0);
					double min = ln.distanceNourriture(this, n);
					
						for(Nourriture nou : ln.listN) {
							if(ln.distanceNourriture(this, nou) < min ) {
								n = nou;
								min = (ln.distanceNourriture(this, nou));
							}
						}
	
					if (min < 20) {
						n.manger(n,this);
					}
					
					move(n);
	
				} else {
					synchronized(objectLock) {
						try {
							objectLock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			} catch(Exception e) {
				
			}
		}
	}
}
