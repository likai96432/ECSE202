//author Kevin Li
import java.awt.Color;

import acm.graphics.GRect;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import java.awt.event.*;


public class bSim extends GraphicsProgram {
	
	
	//a2
	
	private static final int WIDTH = 1200;//The width of the display screen
	private static final int HEIGHT = 800;//The height of the display screen
	private static final int NUMBALLS = 60;//The number of the balls 
	private static final double MINSIZE = 1.0;//The minimum radius of the ball
	private static final double  MAXSIZE = 7.0;//The maximum radius of the ball
	private static final double EMIN = 0.2;//The minimum energy loss 
	private static final double EMAX = 0.6;//The maximum energy loss
	private static final double VoMIN = 40.0;//The minimum initial velocity of the ball
	private static final double VoMAX = 50.0;//The maximum initial velocity of the ball
	private static final double ThetaMIN = 80.0;//The minimum launching angle
	private static final double ThetaMAX = 100.0;//The maximum launching angle
	

	RandomGenerator rgen = new RandomGenerator();
	bTree myTree = new bTree();
	public void init() {
			addMouseListeners();
	}

public void run() {
	this.resize(WIDTH,HEIGHT);
	GRect gPlane = new GRect(0,600,WIDTH,3);     //(a1 solution) A thick line HEIGHT pixels down from the top
    gPlane.setColor(Color.BLACK);
    gPlane.setFilled(true);
    add(gPlane);
    rgen.setSeed((long) 424242);                             //setseed to ensure the simulations are identical

	for(int i = 0; i < NUMBALLS; i++) {           //Create random parameters for 100 balls
		
		  double bSize = rgen.nextDouble(MINSIZE,MAXSIZE); //a2 get the parameters by random generator
          Color bColor = rgen.nextColor(); 
          double bLoss = rgen.nextDouble(EMIN,EMAX); 
            double Vo = rgen.nextDouble(VoMIN,VoMAX); 
          double theta = rgen.nextDouble(ThetaMIN,ThetaMAX);
		
		
		
		
		
		aBall myBall = new aBall(100,bSize,Vo,theta,bSize,bColor,bLoss);
		add(myBall.getBall());     //Call the method in aBall class
		myBall.start();
		myTree.addNode(myBall);
		
	
	
	
}
	while(myTree.isRunning()) ;  //this loop will run when running = true,and when running =false, which indicate the balls stop bouncing, next line will start
	GLabel Notification= new GLabel("CLICK TO STRAT",1100,500);//Set the notification
	add(Notification);
	waitForClick();                                            //wait for click, and after the mouse clicked the next line code will start to run
	Notification.setLabel("All stacked");                      //label will be changed
	myTree.stackballs();                                       //stack the balls by the method defined in bTree class
	

}
}
	
	