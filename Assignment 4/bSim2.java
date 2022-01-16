//author Kevin Li
import java.awt.Color;

import acm.graphics.GRect;

import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SliderUI;
import acm.gui.IntField;
import acm.gui.DoubleField;

import acm.gui.TableLayout;



public class bSim2 extends GraphicsProgram implements ActionListener,ItemListener {
	
	
	 
	//a2
	 Field Numballs;     
	 FieldD Minsize;
	 FieldD Maxsize;
	 FieldD Emin;
	 FieldD Emax;
	 FieldD Vomin;
	 FieldD Vomax;
	 FieldD Thetamin;
	 FieldD Thetamax;
	 boolean simEnable=false;
	 bSim2 traceon;                     //a variable who decide if the trace is to be added
	 private JComboBox<String> bSimC;   
	 private JComboBox<String> File;
	 private JComboBox<String> Edit;
	 private JComboBox<String> Help;
	JToggleButton mybutton;
	private static final int WIDTH = 1200;//The width of the display screen
	 int HEIGHT = 800;//The height of the display screen
	 int NUMBALLS = 60;//The number of the balls 
	 double MINSIZE = 1.0;//The minimum radius of the ball
	 double  MAXSIZE = 7.0;//The maximum radius of the ball
	 double EMIN = 0.2;//The minimum energy loss 
	 double EMAX = 0.6;//The maximum energy loss
	 double VoMIN = 40.0;//The minimum initial velocity of the ball
	double VoMAX = 50.0;//The maximum initial velocity of the ball
	double ThetaMIN = 80.0;//The minimum launching angle
    double ThetaMAX = 100.0;//The maximum launching angle
    RandomGenerator rgen;
    bTree myTree =new bTree();
	
	
	
    

	
	
	
	public void run() {
	
		setField();
		setbutton();
		setcombobox();
		this.resize(1480,HEIGHT);
		GRect gPlane = new GRect(0,600,WIDTH,3);     //(a1 solution) A thick line HEIGHT pixels down from the top
	    gPlane.setColor(Color.BLACK);                
	    gPlane.setFilled(true);
	    add(gPlane);
		addActionListeners();
		
        addMouseListeners();
        
        addJComboListeners();
       
   			 
   			 
   		 
        

      while(true) {                                   //*a4
      pause(200);
       
       if (simEnable) { 
    	   if(mybutton.isSelected()) traceon=this;  //if button is selected,traceon will be equaled to this so the trace will be added
    	   else traceon=null;                
    	  
    	   dosim();
           bSimC.setSelectedIndex(0);
           simEnable=false; }
       
       }

			
	}

	

	
	public void dostack() {
		
		myTree.stackballs();
		
		
	}
		
		
	
	public void dosim() {                                  
		//all the value is set by the value in the Intfield and Doublefield
		 
		rgen = new RandomGenerator();              
		  
    rgen.setSeed((long) 424242);                             //setseed to ensure the simulations are identical

	for(int i = 0; i < Numballs.value.getValue(); i++) {           //Create random parameters for 100 balls
		
		  double bSize = rgen.nextDouble(Minsize.valueD.getValue(),Maxsize.valueD.getValue()); //a2 get the parameters by random generator
          Color bColor = rgen.nextColor(); 
          double bLoss = rgen.nextDouble(Emin.valueD.getValue(),Emax.valueD.getValue()); 
            double Vo = rgen.nextDouble(Vomin.valueD.getValue(),Vomax.valueD.getValue()); 
          double theta = rgen.nextDouble(Thetamin.valueD.getValue(),Thetamax.valueD.getValue());
		
		
		
		
		
		aBall myBall = new aBall(100,bSize,Vo,theta,bSize,bColor,bLoss,traceon);
		add(myBall.getBall());     //Call the method in aBall class
		myBall.start();
		myTree.addNode(myBall);
		
	
	
	
}

}
void setField() {                              //this is a method which add the panel on the east side of the display, the constructor in the method id defiend in th Field class and FieldD class
		
		
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new TableLayout(11, 1));
		myPanel.add(new JLabel("General Simulation Parameters"));
		myPanel.add(new JLabel("       "));
		Numballs=new Field("Numballs:",1,225,NUMBALLS);
		myPanel.add(Numballs.myPanel);
		Minsize=new FieldD("Minsize:",1.0,25.0,MINSIZE);
		myPanel.add(Minsize.myPanel);
		Maxsize=new FieldD("Maxsize:",1.0,25.0,MAXSIZE);
		myPanel.add(Maxsize.myPanel);
		Emin=new FieldD("Emin:",0.0,1.0,EMIN);
		myPanel.add(Emin.myPanel);
		Emax=new FieldD("Emax:",0.0,1.0,EMAX);
		myPanel.add(Emax.myPanel);
		Vomin=new FieldD("Vomin:",0.0,200.0,VoMIN);
		myPanel.add(Vomin.myPanel);
	    Vomax=new FieldD("Vomax:",0.0,200.0,VoMAX);
		myPanel.add(Vomax.myPanel);
		Thetamin=new FieldD("Thetamin:",1.0,180.0,ThetaMIN);
		myPanel.add(Thetamin.myPanel);
		Thetamax=new FieldD("Thetamax:",1.0,180.0,ThetaMAX);
		myPanel.add(Thetamax.myPanel);
		add(myPanel,EAST);
		
		
		
		
		
	}


	
	
	 void addJComboListeners() {                    
	bSimC.addItemListener((ItemListener)this);      //add the itemlistener so the bsimC combobox can read our action
}
	
	    	
	    	
	 void setbutton() {                             //method to add a togglebutton on the south side of the display
		 mybutton=new JToggleButton("trace");
		 mybutton.setActionCommand("trace");
		 mybutton.addActionListener(this);
		 add(mybutton,SOUTH);
		 
	 }
	
			 
			 
		 
		 
	 
	 
	 void setcombobox() {                             //the method to set the combobox
    bSimC = new JComboBox<String>();
   
    bSimC.addItem("bSim");
    bSimC.addItem("Run");
    bSimC.addItem("Clear");
    bSimC.addItem("Stop");
    bSimC.addItem("Quit");
    bSimC.addItem("Stack");
    
    add(bSimC,NORTH);
    File = new JComboBox<String>();
    File.addItem("File");
    add(File,NORTH);
    Edit = new JComboBox<String>();
    Edit.addItem("Edit");
    add(Edit,NORTH);
    Help = new JComboBox<String>();
    Help.addItem("Help");
    add(Help,NORTH);
}
	 public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED) {
		 JComboBox source = (JComboBox)e.getSource();
		 if (source==bSimC) { 
			
		if (bSimC.getSelectedIndex()==1) {
			 System.out.println("start simulation");        //run the simulation with selected value
				simEnable=true;
				
				
			}
		 if (bSimC.getSelectedIndex()==2) {
			 System.out.println("Remove all the ball");
			 myTree.clearTree();                          //clear all the thing in the tree
			
			removeAll();                                 //remove all the ball from the display
			GRect gPlane = new GRect(0,600,WIDTH,3);     //(a1 solution) A thick line HEIGHT pixels down from the top
		    gPlane.setColor(Color.BLACK);
		    gPlane.setFilled(true);
		    add(gPlane);
			}
		 if (bSimC.getSelectedIndex()==3) {
			 System.out.println("stop simluation");
			 myTree.stopAll();                          //stop the simulation
			}
		 if (bSimC.getSelectedIndex()==4) {
			 System.out.print("System terminated");    //exit the system
			 System.exit(0); 
			}
		 if (bSimC.getSelectedIndex()==5) {
			 System.out.println("stack ball");       //stack the ball
				dostack();
		 }
		 }
		
		 }
		 
	 }
	}
	
	