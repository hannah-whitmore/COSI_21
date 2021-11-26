/**
 * This class provides the functionality of a splay tree node 
 * Known bugs: del method
 * 
 * @auhtor Hannah Whitmore
 * hwhitmore@brandeis.edu
 * 10/8/20
 * COSI 21 PA2
 */


package main;

public class WorkEntrySearchNode implements Comparable<WorkEntrySearchNode> {
	
	public WorkEntrySearchNode left; // KEEP THIS PUBLIC 

	public WorkEntrySearchNode right; // KEEP THIS PUBLIC 

	public WorkEntrySearchNode parent; // KEEP THIS PUBLIC
	
	public Queue <WorkEntry> entries;
	
	public int entrySize;
	
	public String activity;
	
	public boolean successfulSearch;
	
	public int treeSize;

	
	public WorkEntrySearchNode(String activity) {
		this.activity = activity;
		this.entries = new Queue();
		this.entrySize = 0; 
		this.left = null;
		this.right = null;
		this.parent = null;
		this.successfulSearch = false;
		
		this.treeSize = 0;
	}
	
	/**
	 *this compares two nodes based on their activity keys
	 *@param: other, node to compare this to 
	 * @return -1 if this node is less than other node, 1 if this node is greater than other node, 0 if they are equal
	 */
	public int compareTo(WorkEntrySearchNode other) {
		if (this.activity.equals(other.activity)) {
			return 0;
		}
		
		int i = 0;
		char thisChar = this.activity.charAt(i);
		char otherChar = other.activity.charAt(i);
		
		while (thisChar == otherChar) {
			i++;
			thisChar = this.activity.charAt(i);
			otherChar = other.activity.charAt(i);
		}	
		
		int a1 = (int) thisChar;
		int a2 = (int) otherChar;
	
		if (a1 < a2) {
			return -1;
		} else {
			return 1;
		}
		// negative value -- this is less than other
		// positive value -- this is greater than other
	}
	
	/**
	 * searches for a node in the tree, node that is returned is splayed 
	 * @param node to search for 
	 * @return the node that matches or the last node encountered in the search
	 */
	public WorkEntrySearchNode search(WorkEntrySearchNode node) {
		if (node == null) { // if passed an empty node, return root (this node) 
			return this;
		}
		
		// the node is found 
		if (compareTo(node)==0) {
			this.successfulSearch = true;
			this.splay();
			return this;
			
		}
		
		// have reached the end without finding the node
		if (this.left == null && this.right == null){
			this.splay();
			return this;
		}
		
		// node < this node, so search left 
		if (compareTo(node)>0) {
			if (this.left!=null){
				return this.left.search(node);
			} else {
				this.splay();
				return this;
			}
			
		} else { // node > this node, so search right
			if (this.right != null) {
				return this.right.search(node);
			} else {
				this.splay();
				return this;
			}
				
		}
	}
	 
	public boolean getSuccessfulSearch() {
		return this.successfulSearch;
	}
	
	/**
	 * inserts a node into the splay tree, node that is returned is splayed 
	 * @param node to be inserted
	 * @return node that is inserted or a node that matches this key 
	 */
	public WorkEntrySearchNode insert(WorkEntrySearchNode node) {
		WorkEntrySearchNode curr = this;
		WorkEntrySearchNode pointer = null;
		
		// search for where to insert 
		while (curr!= null) {
			pointer = curr;
			if (curr.compareTo(node)==0) { 
				curr.splay();
				return curr;
			}
			// negative value -- this is less than other
			// positive value -- this is greater than other
			if (node.compareTo(pointer)>0) {
				curr = curr.right;
			} else {
				curr = curr.left;
			}
			
		}
		curr = pointer;
		node.parent = curr;
		if (node.compareTo(pointer)>0) {
			curr.right = node;
		} else {
			curr.left = node;
		}
		this.treeSize++;
		node.splay();
		return node;
	}
	
	/**
	 * @return an inorder traversal of the tree with the nodes' keys on separate lines
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		toStringHelper(str);
		return str.toString();
	}
	public void toStringHelper(StringBuilder str) {
		if (this.left!= null) {
			this.left.toStringHelper(str);
		}
		str.append(this.activity);
		str.append("\n");
		if (this.right!= null) {
			this.right.toStringHelper(str);
		}
	}
	
	/**
	 * @return a String representation using parentheses to display the structure of the tree 
	 */
	public String getStructure() {
		StringBuilder str = new StringBuilder();
		getStructureHelper(str, this);
		return str.toString();
		
	}
	public void getStructureHelper(StringBuilder str, WorkEntrySearchNode node) {
		str.append("(");
		if (node.left != null) {
			getStructureHelper(str, node.left);		
		}
		str.append(node.activity);
		if (node.right != null) {
			getStructureHelper(str, node.right);
		}
		str.append(")");
	}
	
	/**
	 * adds a workEntry to this node
	 * @param e entry to add
	 */
	public void add(WorkEntry e) {
		if (e.getActivity().equals(this.activity)){
			this.entrySize++;
			this.entries.enqueue(e);	
		}
	}
	
	/**
	 * removes the ith WorkEntry stored in this node
	 * if removal results in node containing no more entries, node is removed from tree
	 * @param i: index to remove 
	 * @return node deleted from 
	 */
	public WorkEntrySearchNode del(int i) {
		if (this.entries.isEmpty() || i>this.entrySize-1) {
			throw new IndexOutOfBoundsException();
		}
		this.entries.removeElement(i);
		if (this.entries.getSize() == 0) { // removal results in node having no more entries -- remove node from tree
			this.splay();
			WorkEntrySearchNode leftSubTree = this.left;
			WorkEntrySearchNode rightSubTree = this.right;
			if (leftSubTree == null && rightSubTree == null) {
				return null;
			}
			// detach left and right subtrees 
			if (leftSubTree != null) {
				leftSubTree.parent = null;
			}
			if (rightSubTree != null) {
				rightSubTree.parent = null;
			}
			if (leftSubTree != null) {
				WorkEntrySearchNode max = findMax(leftSubTree);
				max.splay();
				leftSubTree.right = rightSubTree;
				max.right = rightSubTree;
				return max;
			} else { // no left subtree -- right is the new root
				return rightSubTree;
			}
		}
			
		// normal deletion -- deletion results in node still having entries
			
		return this;
	}
	/**
	 * @return maximum node rooted at the node passed in
	 */

	public WorkEntrySearchNode findMax(WorkEntrySearchNode n) {
		while (n.right != null) {
			n = n.right;
		}
		return n;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getEntryData() {
		if (this.entries.isEmpty()) { // no WorkEntry objects associated with this node
			return "";
		}
		String res = "";
		double totalTime = 0.0; 
		for (int i=0; i<this.entries.getSize(); i++) {
			WorkEntry e = this.entries.getElement(i);
			totalTime += e.getTimeSpent();
			res += e.toString() + "\n";
		}
		return res + "Total: " + totalTime + " h";
	}
	
	
	/**
	 * @return level order traversal of the tree
	 */
	public String getByRecent() {
		Queue <WorkEntrySearchNode> recent = new Queue();
		StringBuilder str = new StringBuilder();
		recent.enqueue(this);
		while (!recent.isEmpty()) {
			WorkEntrySearchNode curr = recent.dequeue();
			str.append(curr.activity).append("\n");
			if (curr.left != null) {
				recent.enqueue(curr.left);
			}
			if (curr.right != null) {
				recent.enqueue(curr.right);
			}
		}
		return str.toString();
	}
	
	/**
	 * splay functionality  
	 */
	 public void splay() {
		while (this.parent != null) { // node we are splaying is not at the root yet
			WorkEntrySearchNode parent = this.parent;
			WorkEntrySearchNode grandparent = parent.parent;
			if (grandparent == null) { // node is the child of the root
				if (this == parent.left) { // zig 
					parent.rotateRight();
				} else {
					parent.rotateLeft(); 
				}
				
			} else { // zag 
				// left child of a left child  --> 2 right rotations
				if (parent.left == this && grandparent.left == parent) {
					grandparent.rotateRight(); //!
					parent.rotateRight();
					
				// right child of a right child --> 2 left rotations
				} else if (parent.right == this && grandparent.right == parent) {
					grandparent.rotateLeft();
					parent.rotateLeft();
					
				// right child of a left child --> rotate left then right 
				} else if (parent.right == this && grandparent.left == parent) {
					parent.rotateLeft();
					grandparent.rotateRight();
				
				// left child of a right child --> rotate right then left
				} else if (parent.left == this && grandparent.right.equals(parent)) {
					parent.rotateRight();
					grandparent.rotateLeft();
				}
			}
		}
				
	}
	
	/**
	 * rotate left helper method 
	 */
	private void rotateLeft() {
		WorkEntrySearchNode y = this.right;
		this.right = y.left;
		if (y.left != null) {
			y.left.parent = this;
		}
		y.parent = this.parent;
		if (this.parent == null) {
			y.parent = null; 
		} else if (this == this.parent.left) {
			this.parent.left = y;
		} else {
			this.parent.right = y;
		}
		y.left = this;
		this.parent = y;	
	}
	
	/**
	 * rotate right helper method 
	 */
	private void rotateRight() {
		WorkEntrySearchNode y = this.left;
		this.left = y.right;
		if (y.right != null) {
			y.right.parent = this;
		}
		y.parent = this.parent;
		if (this.parent == null) {
			y.parent = null;
		} else if (this == this.parent.right) { 
			this.parent.right = y;
		} else {
			this.parent.left = y;
		}
		y.right = this;
		this.parent = y;
		
	}
	
	


}

