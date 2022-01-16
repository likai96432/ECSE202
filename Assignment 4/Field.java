//author Kevin Li
//this method is to create a constructor who have a Intfield in the panel(3 elements in one line)


import acm.gui.TableLayout;
import acm.gui.IntField;
import acm.gui.DoubleField;
import acm.program.GraphicsProgram;
import javax.swing.*;

public class Field extends GraphicsProgram{
    JPanel myPanel;
	JLabel name1;
	JLabel domain;
	IntField value;
	DoubleField valueD;
	
	
	 
	public  Field(String name,int minvalue,int maxvalue,int defvalue){
		     myPanel=new JPanel();
	    	myPanel.setLayout(new TableLayout(1,3));               //3 elements in one line
	    	name1=new JLabel(name);                                //first element is the name of the variable
	    	domain=new JLabel("["+minvalue +"-"+maxvalue+"]");    //second element is the domain of the variable
	    	value=new IntField(defvalue);                          //third element is the field with the default value
	    	myPanel.add(name1,"width=70");
	    	myPanel.add(domain,"width=80");
	    	myPanel.add(value,"width=100");
		 
		 
		 
		 
		 
		 
		 
	 }
	 
}
