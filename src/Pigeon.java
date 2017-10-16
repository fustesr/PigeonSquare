import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.sun.org.apache.bcel.internal.generic.LNEG;

class Pigeon extends Thread {
	
	private double x;
	private double y;
	double speed = 2;
	static Object objectLock = new Object();
	ListObjet lo;
	
	public Pigeon(ListObjet lo){


		x = (int) (Math.random()*1100);
		y = (int) (Math.random()*700);
		this.lo = lo;
		
		//Debug
		//System.out.println(this.hashCode() + "x: " + x +", y: "+ y);

	}
	
	public double getX() {
		
		return x;
	}
	public double getY() {
		
		return y;
	}
	
	public void move(int xo, int yo) {
		
		double xVel = xo - x;
		double yVel = yo - y;
		double mag = Math.sqrt(xVel * xVel + yVel * yVel);
		xVel = xVel * speed / mag;
		yVel = yVel * speed / mag;

		if (x < xo && (x + xVel) > xo) {
			this.x = xo;
		} else if (x != xo) {
			x += xVel;
		}
		if (y < yo && (y + yVel) > yo) {
			y = yo;
		} else if (y != yo) {
			y += yVel;
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void moveAfraid() {
		int cpt = 0;
		speed = 8;
		int x =(int)(Math.random()*1200);
		int y = (int)(Math.random()*800);
		while(cpt < 70) {
			cpt++;
			move(x,y);
			move(x,y);
			move((int)(Math.random()*1200),(int)(Math.random()*800));
		}
		speed = 2;
	}
	
	@Override
	public void run() {

		while(true) {
			Nourriture n = null;
			
			try {
				while(!(lo.listB.isEmpty())) {
					moveAfraid();
				}
				if(!(lo.listN.isEmpty())) {
					n = (Nourriture) lo.listN.get(0);
					double min = lo.distanceObjet(this, n);
					
						for(Objet nou : lo.listN) {
							if(lo.distanceObjet(this, nou) < min ) {
								n = (Nourriture) nou;
								min = (lo.distanceObjet(this, nou));
							}
						}
	
					if (min < 20) {
						n.manger(n,this);
					}
					
					move(n.getX(),n.getY());
	
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
