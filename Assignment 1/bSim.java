import java.awt.Color;
import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class bSim extends GraphicsProgram {
	
	
	//a2
	
	private static final int WIDTH = 1200;//The width of the display screen
	private static final int HEIGHT = 800;//The height of the display screen
	private static final int NUMBALLS = 100;//The number of the balls 
	private static final double MINSIZE = 1.0;//The minimum radius of the ball
	private static final double  MAXSIZE = 10.0;//The maximum radius of the ball
	private static final double EMIN = 0.1;//The minimum energy loss 
	private static final double EMAX = 0.6;//The maximum energy loss
	private static final double VoMIN = 40.0;//The minimum initial velocity of the ball
	private static final double VoMAX = 50.0;//The maximum initial velocity of the ball
	private static final double ThetaMIN = 80.0;//The minimum launching angle
	private static final double ThetaMAX = 100.0;//The maximum launching angle


	RandomGenerator rgen = new RandomGenerator();
	

public void run() {
	this.resize(WIDTH,HEIGHT);
	GRect gPlane = new GRect(0,600,WIDTH,3);     //(a1 solution) A thick line HEIGHT pixels down from the top
    gPlane.setColor(Color.BLACK);
    gPlane.setFilled(true);
    add(gPlane);
	rgen.setSeed(0);                              //setseed to ensure the simulations are identical

	for(int i = 0; i < NUMBALLS; i++) {           //Create random parameters for 100 balls
		
		  double bSize = rgen.nextDouble(MINSIZE,MAXSIZE); //a2 get the parameters by random generator
          Color bColor = rgen.nextColor(); 
          double bLoss = rgen.nextDouble(EMIN,EMAX); 
          double Vo = rgen.nextDouble(VoMIN,VoMAX);   
          double theta = rgen.nextDouble(ThetaMIN,ThetaMAX);
		
		
		
		
		
		aBall myball = new aBall(100,bSize,Vo,theta,bSize,bColor,bLoss);
		add(myball.getBall());     //Call the method in aBall class
		myball.start();
		
		
	
	
	
	
}
}
}
	
	