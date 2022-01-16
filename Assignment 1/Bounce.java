import java.awt.Color;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

public class Bounce extends GraphicsProgram{
	private static final int WIDTH = 600; // defines the width of the screen in pixels
	private static final int HEIGHT = 800;// distance from top of screen to ground plane
	private static final int OFFSET = 200;// distance from bottom of screen to ground plane
	private static final double g=9.8;// MKS gravitational constant 9.8 m/s^2
	private static final double Pi=3.141592654;// To convert degrees to radians
	private static final double TICK = 0.1; // Clock tick duration (sec)
	private static final double ETHR = 0.01; // If either Vx or Vy < ETHR STOP
	private static final double XMAX = 100.0; // Maximum value of X
	private static final double YMAX = 100.0;// Maximum value of Y
	private static final double PD = 1;// Trace point diameter
	private static final double SCALE = WIDTH/XMAX;// Pixels/meter
	private static final boolean TEST = true;
	public void run() {
		this.resize(WIDTH,HEIGHT);
        double bSize = readDouble("Enter the radius of ball in metersis [0.1,5]: ");
        double Vo = readDouble("Enter the initial velocity of the ball in meters/second [0,100]");
        double theta = readDouble("Enter the launch angle of the ball in degree[0,90]");
        double loss = readDouble("Enter energy loss parameters [0,1]: ");
        double Yinit = bSize;
        double Xinit = 5.0;
		GLine ground = new GLine(0,601+bSize*SCALE,WIDTH,601+bSize*SCALE);
		add(ground);
        GOval myBall = new GOval(Xinit*SCALE,600-SCALE*Yinit,2*bSize*SCALE,2*bSize*SCALE);
		myBall.setFilled(true);
		myBall.setColor(Color.RED);
		add(myBall);
		double k = 0.0016;                    
		double t=0;
		double Vt = g / (4*Pi*bSize*bSize*k); // Terminal velocity
		double Vox=Vo*Math.cos(theta*Pi/180); // X component of initial velocity
		double Voy=Vo*Math.sin(theta*Pi/180); // Y component of initial velocity
		double X=Xinit;
		double Y=Yinit;
		double Xlast= X;                      
		double Ylast =Y;                      
	    double Vx = (X-Xlast)/TICK;           // Estimate Vx from difference
		double Vy = (Y-Ylast)/TICK;           // Estimate Vy from difference
		while(true) {
			if (TEST)
			System.out.printf("t: %.2f  X: %.2f Y: %.2f Vx: %.2f Vy:%.2f\n", t,X,Y-bSize ,Vx,Vy);
			pause(100);
			GOval tracepoint = new GOval((X+bSize)*SCALE,600-(Y-bSize)*SCALE,PD,PD);
			add (tracepoint);
			tracepoint.setFilled(true);
			t=t+TICK;
			Xlast= X;
			X= Xinit+Vox*Vt/g*(1-Math.exp(-g*t/Vt));  //position of the ball in X axis
			Ylast =Y;
			Y=Yinit+Vt/g*(Voy+Vt)*(1-Math.exp(-g*t/Vt))-Vt*t; //position of the ball in Y axis
			Vx = (X-Xlast)/TICK;
			Vy = (Y-Ylast)/TICK;
			if(Vy<0 && Y <= bSize) {
			    double KEx = 0.5*Vx*Vx*(1-loss);   // Kinetic energy in X direction after collision
				double KEy = 0.5*Vy*Vy*(1-loss);   // Kinetic energy in Y direction after collision
				Vox =Math.sqrt(2*KEx);             // Resulting horizontal velocity
			    Voy =Math.sqrt(2*KEy);             // Resulting vertical velocity

				Y=Yinit;                           //The initial value of Y doesn't change
				Xinit=X;                           //The initial value of X become the position of the ball at last collision
				t=0;
				if (KEx<=ETHR | KEy<=ETHR ) break;	
				
				
			}
			myBall.setLocation(X*SCALE,600-Y*SCALE);
			
		
			
			



		}
}

		
		
		
	}

