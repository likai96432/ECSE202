//this is the code for the assignment 2 which simulate 100 balls with random parameters.
//"a2" indicate that this part is from the documents 
//<Department of Electrical and Computer Engineering> ECSE 202 – 
//    < Introduction to Software Development >
//           <Assignment 2> 
    // <Generalizing via Objects>      
                   
import acm.graphics.GOval;
import java.awt.Color;

public class aBall extends Thread {//thread can make 100 balls move at the same time
    public GOval myBall; //create the object myBall and make it accessible by bSim class
    double Xi;           
	double Yi;           
	double Vo;          
	double theta;       
	double bSize;        
	Color bColor;        
	double bLoss;        
	
	private static final double g=9.8;// MKS gravitational constant 9.8 m/s^2
	private static final double Pi=3.141592654;// To convert degrees to radians
	private static final double TICK = 0.01; // Clock tick duration (sec)
	private static final double ETHR = 0.01; // If either Vx or Vy < ETHR STOP
	private static final double SCALE=6; //600/100(HEIGHT/XMAX
	
    
	
	/** a2
* The constructor specifies the parameters for simulation. They are *
* @param Xi double The initial X position of the center of the ball
* @param Yi double The initial Y position of the center of the ball
* @param Vo double The initial velocity of the ball at launch
* @param theta double Launch angle (with the horizontal plane)
* @param bSize double The radius of the ball in simulation units
* @param bColor Color The initial color of the ball
* @param bLoss double Fraction [0,1] of the energy lost on each bounce */
public aBall(double Xi, double Yi, double Vo, double theta, double bSize, Color bColor, double bLoss) {
//constructor of aBall
this.Xi = Xi; 
this.Yi = Yi; 
this.Vo = Vo; 
this.theta = theta; 
this.bSize = bSize; 
this.bColor = bColor;
this.bLoss = bLoss;
double ScrX=(this.Xi-this.bSize)*SCALE;//Transformation of simulation coordinates to pixel coordinates(on X)
double ScrY=600-(2*this.bSize)*SCALE;//Transformation of simulation coordinates to pixel coordinates(on Y)
    
myBall = new GOval(ScrX,ScrY,this.bSize*2*SCALE,this.bSize*2*SCALE); //create the instance of GOVal
myBall.setFilled(true);
myBall.setColor(this.bColor);


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
	return myBall;
}


public void run() {                       //run the simulation of the ball
	double k = 0.0001;                    // friction parameter          
	double t=0;
	double Vt = g / (4*Pi*bSize*bSize*k); // Terminal velocity
	double Vox=Vo*(Math.cos(theta*Pi/180)); // X component of initial velocity
	double Voy=Vo*Math.sin(theta*Pi/180); // Y component of initial velocity
	double X=Xi;
	double Y=Yi;
	double Xlast= X;                      
	double Ylast =Y;                      
    double Vx = (X-Xlast)/TICK;           // Estimate Vx from difference
	double Vy = (Y-Ylast)/TICK;           //Estimate Vy from difference
	
	
	
	while(true) {                          
		
		
		t=t+TICK;
		Xlast= X;
		X= Xi+Vox*Vt/g*(1-Math.exp(-g*t/Vt));  //position of the ball in X axis
		Ylast =Y;
		Y=Yi+Vt/g*(Voy+Vt)*(1-Math.exp(-g*t/Vt))-Vt*t; //position of the ball in Y axis
		Vx = (X-Xlast)/TICK;
		Vy = (Y-Ylast)/TICK;
		
		if(Vy<0 && Y <= bSize) {
		   if(Vox>0) {                           //when theta is greater then 0(ball bounce from left to right)
			double KEx = 0.5*Vx*Vx*(1-bLoss);   // Kinetic energy in X direction after collision
			double KEy = 0.5*Vy*Vy*(1-bLoss);   // Kinetic energy in Y direction after collision
			Vox =Math.sqrt(2*KEx);             // Resulting horizontal velocity(negative value because it bounces from left right to
		    Voy =Math.sqrt(2*KEy);             // Resulting vertical velocity

			Y=Yi;                           //The initial value of Y doesn't change
			Xi=X;                           //The initial value of X become the position of the ball at last collision
			t=0;
			if (KEx<=ETHR | KEy<=ETHR ) break;}
		   if(Vox<0) {                              //when theta is smaller then 0(ball bounce from right to left)
				double KEx = 0.5*Vx*Vx*(1-bLoss);   // Kinetic energy in X direction after collision
				double KEy = 0.5*Vy*Vy*(1-bLoss);   // Kinetic energy in Y direction after collision
				Vox =-Math.sqrt(2*KEx);             // Resulting horizontal velocity
			    Voy =Math.sqrt(2*KEy);             // Resulting vertical velocity

				Y=Yi;                           //The initial value of Y doesn't change
				Xi=X;                           //The initial value of X become the position of the ball at last collision
				t=0;
				if (KEx<=ETHR | KEy<=ETHR ) break;}
			
			
		}
		myBall.setLocation((X-bSize)*SCALE,600-(Y+bSize)*SCALE);  

	
	try {
		Thread.sleep(5);    //a2 the sleep duration is 5ms
	} catch (InterruptedException e) {
		e.printStackTrace();
	

	
	



}
}
}
}