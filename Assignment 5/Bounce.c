
//author Kevin Li
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int main(void) {
	//scan the input
 	double Vo,bSize,Theta,loss;
	printf("Enter the initial velocity of the ball in meters/second [0,100]: ");
	scanf("%lf",&Vo);
	printf("Enter the radius of ball in meters is [0.1,5]: ");
	scanf("%lf",&bSize);
	printf("Enter the launch angle of the ball in degree[0,90] ");
	scanf("%lf",&Theta);
	printf("Enter energy loss parameters [0,1]: ");
	scanf("%lf",&loss);
            double g =9.8;
            double Pi =3.141592654;     // To convert degrees to radians
            double TICK = 0.1; // Clock tick duration (sec)
            double ETHR= 0.01; // If either Vx or Vy < ETHR STOP
	        double Yinit = bSize;
	        double Xinit = 5.0;
	        double k = 0.0016;
	        		double t=0;
	        		double Vt = g / (4*Pi*bSize*bSize*k); // Terminal velocity
	        		double Vox=Vo*cos(Theta*Pi/180); // X component of initial velocity
	        		double Voy=Vo*sin(Theta*Pi/180); // Y component of initial velocity
	        		double X=Xinit;
	        		double Y=Yinit;
	        		double Xlast= X;
	        		double Ylast =Y;
	        	    double Vx = (X-Xlast)/TICK;           // Estimate Vx from difference
	        		double Vy = (Y-Ylast)/TICK;       // Estimate Vy from difference
			while(1){
				printf("t: %.2f  X: %.2f Y: %.2f Vx: %.2f Vy:%.2f\n", t,X,Y ,Vx,Vy);
				t=t+TICK;
							Xlast= X;
							X= Xinit+Vox*Vt/g*(1-exp(-g*t/Vt));  //position of the ball in X axis
							Ylast =Y;
							Y=Yinit+Vt/g*(Voy+Vt)*(1-exp(-g*t/Vt))-Vt*t; //position of the ball in Y axis
							Vx = (X-Xlast)/TICK;
							Vy = (Y-Ylast)/TICK;
							if(Vy<0 && Y <= bSize) {
							   printf("Bounce\n");
								double KEx = 0.5*Vx*Vx*(1-loss);   // Kinetic energy in X direction after collision
								double KEy = 0.5*Vy*Vy*(1-loss);   // Kinetic energy in Y direction after collision
								Vox =sqrt(2*KEx);             // Resulting horizontal velocity
							    Voy =sqrt(2*KEy);             // Resulting vertical velocity

								Y=Yinit;                           //The initial value of Y doesn't change
								Xinit=X;                           //The initial value of X become the position of the ball at last collision
								t=0;
								if (KEx<=ETHR | KEy<=ETHR ) break;
							}

			}

}
