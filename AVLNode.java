package coronatree;

public class AVLNode {	
	Person data;		// The data in the node
	AVLNode parent;		// The parent
	AVLNode left;       // Left child
	AVLNode right;      // Right child
	int height;       	// Height

	/**
	 * A standard constructor, sets all pointers to null.
	 * 
	 * @param p - the data of the node.
	 */
    AVLNode(Person p) {
    	this.data = p;
    	this.parent = null;
    	this.left = null;
    	this.right = null;
    	this.height = 1;
    }
    
    /**
     * A standard constructor
     * 
     * @param p - the data of the node.
     * @param lt - the left child.
     * @param rt - the right child.
     * @param parent - the parent.
     */
    AVLNode(Person p, AVLNode lt, AVLNode rt, AVLNode parent){
		this.data = p;
		this.left = lt;
		this.right = rt;
		this.parent = parent;
		this.height = 1;
    }

    public String toString(){
    	return this.data.toString();
    }

}
