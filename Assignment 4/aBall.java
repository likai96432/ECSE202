//author Kevin Li
//this is the code for the assignment 2 which simulate 100 balls with random parameters.
//"a2" indicate that this part is from the documents 
//<Department of Electrical and Computer Engineering> ECSE 202 – 
//    < Introduction to Software Development >
//           <Assignment 2> 
    // <Generalizing via Objects>      
                   
import acm.graphics.GOval;
import java.awt.Color;

public class aBall extends Thread {//thread can make 100 balls move at the same time
    private GOval myBall; //create the object myBall and make it accessible by bSim class
     private double Xi;           
	private double Yi;           
	private double Vo;          
	private double theta;       
	private double bSize;        
	private Color bColor;        
	private double bLoss;        
	public boolean State = true;       //set 'state' as a boolean value, so we can verify the state of the program in bTree and bSim class
	private static final double g=9.8;// MKS gravitational constant 9.8 m/s^2
	private static final double Pi=3.141592654;// To convert degrees to radians
	public static double TICK = 0.02;
	private static final double TICKmS = TICK*1000;// Clock tick duration (sec)
	private static final double ETHR = 0.01; // If either Vx or Vy < ETHR STOP
	private static final double SCALE=6; //600/100(HEIGHT/XMAX
	private bSim2 link;
	private static final double PD=1;
    
	
	/** a2
* The constructor specifies the parameters for simulation. They are *
* @param Xi double The initial X position of the center of the ball
* @param Yi double The initial Y position of the center of the ball
* @param Vo double The initial velocity of the ball at launch
* @param theta double Launch angle (with the horizontal plane)
* @param bSize double The radius of the ball in simulation units
* @param bColor Color The initial color of the ball
* @param bLoss double Fraction [0,1] of the energy lost on each bounce */
public aBall(double Xi, double Yi, double Vo, double theta, double bSize, Color bColor, double bLoss,bSim2 link) {
//constructor of aBall
this.Xi = Xi; 
this.Yi = Yi; 
this.Vo = Vo; 
this.theta = theta; 
this.setbSize(bSize); 
this.bColor = bColor;
this.bLoss = bLoss;
this.link=link;
double ScrX=(this.Xi-this.getbSize())*SCALE;//Transformation of simulation coordinates to pixel coordinates(on X)
double ScrY=600-(2*this.getbSize())*SCALE;//Transformation of simulation coordinates to pixel coordinates(on Y)
    
setMyBall(new GOval(ScrX,ScrY,this.getbSize()*2*SCALE,this.getbSize()*2*SCALE)); //create the instance of GOVal
getMyBall().setFilled(true);
getMyBall().setColor(this.bColor);


}



// Get simulation parameters
/** a2
* The run method implements the simulation loop from Assignment 1. * Once the start method is called on the aBall instance, the
* code in the run method is executed concurrently with the main
* program.
* @param void
* @return void
*/


public GOval getBall() {           //a2 make the instance accessible outside of the aBall class
	return getMyBall();
}
private void trace(double x, double y) {  //(a2 solution) create a method who add trace to the display
    double ScrX = x*SCALE;
    double ScrY = 600 - y*SCALE;
    GOval pt = new GOval(ScrX,ScrY,PD,PD);
    pt.setColor(bColor);
    pt.setFilled(true);
    link.add(pt);
}


public void run() {                       //run the simulation of the ball
	double k = 0.0001;                    // friction parameter          
	double t=0;
	double Vt = g / (4*Pi*getbSize()*getbSize()*k); // Terminal velocity
	double Vox=Vo*(Math.cos(theta*Pi/180)); // X component of initial velocity
	double Voy=Vo*Math.sin(theta*Pi/180); // Y component of initial velocity
	double X=Xi;
	double Y=Yi;
	double Xlast= X;                      
	double Ylast =Y;                      
    double Vx = (X-Xlast)/TICK;           // Estimate Vx from difference
	double Vy = (Y-Ylast)/TICK;           //Estimate Vy from difference
	int sign=1;
	if(Vox<0) sign=-1;
	
	
	while(State) {                          
		if (link != null) {         //the trace method will only be executed if it existe a link
            trace(X,Y-getbSize());
}
		
		t=t+TICK;
		Xlast= X;
		X= Xi+Vox*Vt/g*(1-Math.exp(-g*t/Vt));  //position of the ball in X axis
		Ylast =Y;
		Y=Yi+Vt/g*(Voy+Vt)*(1-Math.exp(-g*t/Vt))-Vt*t; //position of the ball in Y axis
		Vx = (X-Xlast)/TICK;
		Vy = (Y-Ylast)/TICK;
		getMyBall().setLocation((X-getbSize())*SCALE,600-(Y+getbSize())*SCALE);  
		
		if(Vy<0 && Y <= getbSize()) {
		                          //when theta is greater then 0(ball bounce from left to right)
			double KEx = 0.5*Vx*Vx*(1-bLoss);   // Kinetic energy in X direction after collision
			 double KEy = 0.5*Vy*Vy*(1-bLoss);   // Kinetic energy in Y direction after collision
			Vox =Math.sqrt(2*KEx)*sign;             // Resulting horizontal velocity(negative value because it bounces from left right to
		    Voy =Math.sqrt(2*KEy);             // Resulting vertical velocity

			Y=Yi;                           //The initial value of Y doesn't change
			Xi=X;                           //The initial value of X become the position of the ball at last collision
			t=0;
			if (KEx<=ETHR || KEy<=ETHR ) { //break condition
			       State=false;            //when reach the break condition, state=false, which will change the value of running in isRunning method
				
			}
		 
			
			
		}
		
	
	try {
		Thread.sleep((long) (TICKmS/2));    //(a2 solution)
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
	
}
}



public GOval getMyBall() {
	return myBall;
}



public void setMyBall(GOval myBall) {
	this.myBall = myBall;
}



public double getbSize() {
	return bSize;
}



public void setbSize(double bSize) {
	this.bSize = bSize;
}
}