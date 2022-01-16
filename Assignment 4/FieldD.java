//author Kevin Li
//this method is to create a constructor who have a Doublefield in the panel(3 elements in one line)

import acm.gui.TableLayout;
import acm.gui.IntField;
import acm.gui.DoubleField;
import acm.program.GraphicsProgram;
import javax.swing.*;

public class FieldD extends GraphicsProgram{
    JPanel myPanel;
	JLabel name1;
	JLabel domaine;
	IntField value;
	DoubleField valueD;
	
	
	 

	 public  FieldD(String name,double minvalue,double maxvalue,double defvalue){
	     myPanel=new JPanel();
    	myPanel.setLayout(new TableLayout(1,3));                   //3 elements in one line
    	name1=new JLabel(name);                                    //first element is the name of the variable
    	domaine=new JLabel("["+minvalue +"-"+maxvalue+"]");         //second element is the domain of the variable
    	valueD=new DoubleField(defvalue);                          //third element is the field with the default value
    	myPanel.add(name1,"width=70");
    	myPanel.add(domaine,"width=80");
    	myPanel.add(valueD,"width=100");
	 
	 
	 
	 
	 
	 
	 
 }
}
