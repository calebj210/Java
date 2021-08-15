package cs2321;
import java.util.Iterator;

import net.datastructures.*;
	

/**
 * Linked binary tree implementation
 * 
 * @author Caleb Jacobs (cajacobs)
 * Date last modified: 04/05/2021
 * Course: CS2321 Spring 2021
 *
 * @param <E> Type of element to be stored in the tree
 */
public class LinkedBinaryTree<E> implements BinaryTree<E>{
	private Node<E> root;			// Root node of binary tree
	private int size = 0;			// Number of nodes in the tree
	
	/**
	 * Binary tree node
	 */
	private class Node<E> implements Position<E> {
		public E element;		// Element of the node
		public Node<E> left;	// Left child of the node
		public Node<E> right;	// Right child of the node
		public Node<E> parent;	// Parent of the node
		
		public Node() {
			element = null;
			left 	= null;
			right   = null;
			parent  = null;
		}
		
		public Node(E e) {
			element = e;
			left    = null;
			right   = null;
			parent  = null;
		}

		/**
		 * Get the element of the node
		 */
		@Override
		public E getElement() throws IllegalStateException {
			return element;
		}
	} 

	
	
	/**
	 * Get the position of the root node
	 * 
	 * @return root node position
	 */
	@Override
	@TimeComplexity("O(1)")
	public Position<E> root() {
		return root;
	}
	
	/**
	 * Default constructor for the linked binary tree
	 */
	public  LinkedBinaryTree( ) {
		root = new Node();
	} 

	/**
	 * Helper method for validating and converting positional data into a node
	 * 
	 * @param p - position to test
	 * @return node representation of the position
	 * @throws IllegalArgumentException	if position is not a valid node
	 */
	@TimeComplexity("O(1)")
	private Node<E> validateNode(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node)) {
			throw new IllegalArgumentException("Position is not a node in the tree!");
		}
		
		return (Node<E>) p;
	}
	
	/**
	 * Get the parent of a given position
	 * 
	 * @return the parent of a node at the specified position
	 * @throws IllegalArgumentException if position is not valid
	 */
	@Override
	@TimeComplexity("O(1)")
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> n = validateNode(p);		// Get node representation of position
		
		return n.parent;
	}

	/**
	 * Get the left node of a given position
	 * 
	 * @param p - position to get the left child of
	 * @return left child
	 * @throws IllegalArgumentException if position is not valid
	 */
	@Override
	@TimeComplexity("O(1)")
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> n = validateNode(p);		// Get node representation of position
		
		return n.left;
	}

	/**
	 * Get the right node of a given position
	 * 
	 * @param p - position to get the right child of
	 * @return right child
	 * @throws IllegalArgumentException if position is not valid
	 */
	@Override
	@TimeComplexity("O(1)")
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> n = validateNode(p);		// Get node representation of position
		
		return n.right;
	}
	
	/**
	 * Check if a node is internal
	 * 
	 * @param p - position to check if node is internal
	 * @return true if position is internal
	 * @throws IllegalArgumentException if position is not valid
	 */
	@Override
	@TimeComplexity("O(1)")
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		Node<E> n = validateNode(p);		// Get node representation of position
		
		return (n.left != null || n.right != null);
	}

	/**
	 * Check if a node is external
	 * 
	 * @param p - position to check if node is external
	 * @return true if position is external
	 * @throws IllegalArgumentException if position is not valid
	 */
	@Override
	@TimeComplexity("O(1)")
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		Node<E> n = validateNode(p);		// Get node representation of position
		
		return (n.left == null && n.right == null);
	}

	/**
	 * Get the number of nodes in a tree
	 * 
	 * @return number of nodes
	 */
	@Override
	@TimeComplexity("O(1)")
	public int size() {
		return size;
	}

	@Override
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		return (size == 0);
	}
	
	/** 
	 * Creates a root for an empty tree, storing e as element, and returns the 
	 * position of that root. An error occurs if tree is not empty. 
	 * 
	 * @param e - element to add at the root
	 * @return position of the root node
	 * @throws IllegalArgumentException if root is already filled
	 */
	@TimeComplexity("O(1)")
	public Position<E> addRoot(E e) throws IllegalStateException {
		// Check if tree is empty
		if (size != 0) {
			throw new IllegalStateException("Tree is not emtpy, can not add root!");
		}
		
		root = new Node<E>(e);				// New root node
		size++;								// Increment size of tree
		
		return root;
	}
	
	
	/** 
	 * Creates a new left child of Position p storing element e, return the left child's position.
	 * If p has a left child already, throw exception IllegalArgumentExeption. 
	 * 
	 * @param p - Position to add to the left child
	 * @param e - Element of the left child to add
	 * @return position of the left child
	 * @throws IllegalArgumentException if position is not valid
	 */
	@TimeComplexity("O(1)")
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> n = validateNode(p);		// Get node representation of position
		
		// Check if node has a left child
		if (n.left != null) {
			throw new IllegalArgumentException("Node already has left child!");
		}
		
		Node<E> l = new Node<E>(e);			// Create left child
		l.parent = n;						// Add parent to child
		n.left = l;							// Add child to the tree
		size++;								// Increment size of tree
		
		return l;
	}

	/** 
	 * Creates a new right child of Position p storing element e, return the right child's position.
	 * If p has a right child already, throw exception IllegalArgumentExeption. 
	 * 
	 * @param p - Position to add to the right child
	 * @param e - Element of the right child to add
	 * @return position of the right child
	 * @throws IllegalArgumentException if position is not valid
	 */
	@TimeComplexity("O(1)")
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> n = validateNode(p);		// Get node representation of position
		
		// Check if node has a right child
		if (n.right != null) {
			throw new IllegalArgumentException("Node already has right child!");
		}
		
		Node<E> r = new Node<E>(e);			// Create right child
		r.parent = n;						// Add parent to child
		n.right = r;						// Add child to the tree
		size++;								// Increment size of tree
		
		return r;
	}
	
	/**
	 * Set the element of a given node
	 * 
	 * @param p - position of node to change
	 * @param element - element to change to
	 * @throws IllegalArgumentException if position is not valid
	 */
	@TimeComplexity("O(1)")
	public void setElement(Position<E> p, E element) throws IllegalArgumentException {
		Node<E> n = validateNode(p);		// Get node representation of position
		
		n.element = element;				// Update element of the node
	}

	
	/**
	 * Return the elements in the subtree of node P, including the data in node P. 
	 * The data in the return list need to match the in-order traversal.  
	 * 
	 * @param p who has at most one child. 
	 * @return the List of elements in subtree of P following the in-order traversal. 
	 */
	@TimeComplexity("O(n)")
	public List<E> inOrderElements(Position<E> p) {
		/* TCJ
		 * We need to traverse through each entry in the binary tree 
		 * which takes O(n) time due to the n items in the tree.
		 */
		Node<E> n = validateNode(p);			// Node representation of the position
		
		ArrayList<E> list = new ArrayList<E>();	// In order list
		
		inOrderElements(list, n);				// Run recursive call to populate list
		
		return list;
	}
	
	/**
	 * Helper method for getting the inorder elements of the binary tree
	 * 
	 * @param list - list to add in order elements to
	 * @param n - parent node to start inorder at
	 */
	@TimeComplexity("O(n)")
	private void inOrderElements(ArrayList<E> list, Node<E> n) {
		/* TCJ
		 * See public inOrderElements TCJ
		 */
		// Stop inorder of this subtree if null
		if (n == null || n.element == null) {
			return;
		}
		
		inOrderElements(list, n.left);			// Inorder left subtree
		
		list.addLast(n.element);				// Store current node
		
		inOrderElements(list, n.right);			// Inorder right subtree
	}

	/**
	 * If p has two children, throw IllegalAugumentException. 
	 * If p is an external node ( that is it has no child), remove it from the tree.
	 * If p has one child, replace it with its child. 
	 * If p is root node, update the root accordingly. 
	 * 
	 * @param p who has at most one child. 
	 * @return the element stored at position p if p was removed.
	 */
	@TimeComplexity("O(1)")
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> n = validateNode(p);		// Get node representation of position
		
		if (n.left != null && n.right != null) {
			throw new IllegalArgumentException("Node has two children, can't remove!");
		}
		
		// Set w to the non null child of n
		Node<E> w = null;
		if (n.left == null) {
			w = n.right;
		} else if (n.right == null) {
			w = n.left;
		}
		
		// Fix parent pointer
		if (n == root) {
			root = w;
		} else if (n.parent.left == n) {
			n.parent.left = w;
		} else {
			n.parent.right = w;
		}
		
		// Fix w's parent
		if (w != null) {
			w.parent = n.parent;
		}
		
		size--;								// Decrement size of tree
		
		return n.element;
	}
}
