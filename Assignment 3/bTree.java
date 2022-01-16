//author Kevin Li


public class bTree {
	/** a2
* The constructor specifies the parameters for simulation. They are *
* @param lastsize is the size of the last ball which is used to compare with the current ball
* @param X indicate the x coordinate of the ball
* @param Y indicate the y coordinate of the ball
 */
	
	bNode root=null;     //the root will have the value null at the beginning
   private volatile boolean running;
    private double lastsize;          
   private double X;
   private double Y;
    private static final double DLETASIZE = 0.1;  // *a3 when the differences between two balls' size are greater than 0,1, we will start a new stack 
	

	
	public void addNode(aBall iBall) {       //This part of code is mainly from the bTree class defined on class with some changes
		
		bNode current;


		
		if (root == null) {                  //if there is nothing in the tree, we create a new tree
			root = makeNode(iBall);
		}
		

		
		else {
			current = root;
			while (true) {
				if (iBall.bSize < current.iBall.bSize) {      //if the size of the ball is smaller than the root,we move it to the left
					

					if (current.left == null) {				// if there is nothing at the root's left, we crate a new leaf node
						current.left = makeNode(iBall);		// attach new node here
						break;
					}
					else {									// if there is something at left
						current = current.left;				// we keep traversing 
					}
				}
				else {

					
					if (current.right == null) {			//  if there is nothing at the root's right, we crate a new leaf node	
						current.right = makeNode(iBall);		// attach
						break;
					}
					else {									// otherwise 
						current = current.right;			// keep traversing
					}
				}
			}
		}
		
	}
	
/**
 * makeNode
 * 
 * Creates a single instance of a bNode
 * 
 * @param	int data   Data to be added
 * @return  bNode node Node created
 */
	
	bNode makeNode(aBall iBall) {                           //this part of code is from the code on class
		bNode node = new bNode();							// create new object
		node.iBall = iBall;									// initialize data field
		node.left = null;									// set both successors
		node.right = null;									// to null
		return node;										// return handle to new object
	}

	
	
	

	
	
	
   private void Verify( bNode root) {                       //this part of code is use to verify if the program is still running
	   if (root.left != null) Verify(root.left);           //using recursion 
       if (root.iBall.State == true) running=true;         //if there is nothing at left, verify the value of "state" , if it's true, it means that the program is still running,so running will equal to true, and the while loop in bSim class will kep running 
       if (root.right != null) Verify(root.right);
   }
	
   
	public boolean isRunning() {                         //this part of code return the value of "running"
		 running=false;                                  //we set the value of running equals to false
		Verify(root);                                    //running will equal to true if the program is still running
		return running;
	}
 void stackballs() {                                     //this method is used to place the balls
    	
    	 moveball(root);
  
    }
    void moveball(bNode root) {
    
    	if(root.left!=null)moveball(root.left);         //recursion, we will get the smallest ball
    	
    	if(root.iBall.bSize-lastsize<=DLETASIZE) {      //if the difference between two balls are smaller then 0.1, we will stack the balls on the last balls, which mean the y coordinate of the ball will change
    	
    	 Y+=root.iBall.bSize*2;
    	
    	}
    	else {                                         //if the difference between two balls are greater then 0.1, we will start a new stack ,the balls x coordinate of the ball will change
        	
    		
    		 X+= 2*lastsize;                           
    		Y=root.iBall.bSize*2;
    		
    		
    	}
    	                   //the size of the last ball
    	root.iBall.myBall.setLocation(X*6,600-Y*6);   //the location of the balls
    	lastsize=root.iBall.bSize; 
    	if(root.right!=null)moveball(root.right);     //recursion
    	
    }
   
	
	

	
	

}



class bNode {                                         
	 aBall iBall;
	bNode left;
	bNode right;
}


