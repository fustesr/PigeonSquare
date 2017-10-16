
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
	
	// Fonction de déplacement vers les coordonnées xo et yo
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
	
	// Fonction de déplacement de frayeur !
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
	
	// Fonction principal du pigeon
	public void run() {

		while(true) {
			Nourriture n = null;
			
			try {
				
				// Tant que des bombes sont presente sur le terrain ...
				while(!(lo.listB.isEmpty())) {
					// ... On effraie les pigeons
					moveAfraid();
				}
				
				// SI il y a de la nourriture sur le terrain, on calcule la nourriture la plus proche (min) ...
				if(!(lo.listN.isEmpty())) {
					n = (Nourriture) lo.listN.get(0);
					double min = lo.distanceObjet(this, n);
					
						for(Objet nou : lo.listN) {
							if(lo.distanceObjet(this, nou) < min ) {
								n = (Nourriture) nou;
								min = (lo.distanceObjet(this, nou));
							}
						}
	
					// ... et quand le pigeons est suffisament proche, il le mange
					if (min < 20) {
						n.manger(n,this);
					}
					
					// Donc si ils sont pas assez proche, il avance vers la nourriture
					move(n.getX(),n.getY());
	
				} 
				// SINON, le pigeon s'endort
				else {
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
