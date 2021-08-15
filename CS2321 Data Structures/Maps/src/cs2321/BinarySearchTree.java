package cs2321;

import net.datastructures.Entry;
import net.datastructures.SortedMap;
import net.datastructures.*;

/**
 * Binary Search Tree implementation of a Ordered MAP
 * @author Caleb Jacobs (cajacobs)
 * Date last modified: 04/05/2021
 * Course: CS2321 Spring 2021
 *
 * @param <K> - type for the key
 * @param <V> - type for the value
 */
public class BinarySearchTree<K extends Comparable<K>,V> extends AbstractMap<K,V>  {
	/* all the data will be stored in the tree*/
	LinkedBinaryTree<Entry<K,V>> tree; 
	int size;  							// The number of entries (mappings)
	
	/** 
	 * default constructor
	 */
	public BinarySearchTree() {
		tree = new LinkedBinaryTree<Entry<K,V>>();
	}
	
	/** 
	 * Return the tree. The purpose of this method is purely for testing. 
	 * You don't need to make any change. Just make sure to use variable tree to store your entries. 
	 */
	public LinkedBinaryTree<Entry<K,V>> getTree() {
		return tree;
	}
	
	/**
	 * Get the size of map
	 */
	@Override
	@TimeComplexity("O(1)")
	public int size(){
		return size;
	}


	/**
	 * Find the value of an entry with a given key
	 * 
	 * @param key - key to search for
	 * @return the value of the entry with key or return null
	 */
	@Override
	@TimeComplexity("O(n)")
	public V get(K key) {
		/* TCJ
		 * Using a binary tree to get an entry can potentially make us a traverse
		 * a tree that in every child is to the right of the parent. Traversing
		 * such a tree is equivalent to checking each entry in a linked list and so
		 * the time it takes to find the key is O(n) just like traversing a linked list.
		 */
		Position<Entry<K,V>> v = search(tree.root(), key);
		if (tree.isExternal(v)) {
			return null;
		} else {
			return v.getElement().getValue();
		}
	}

	/**
	 * Put an entry into the unordered table
	 * 
	 * @param key - key to search for
	 * @param value - value to set entry to
	 * @return null if the key/value pair was new or return the old value assigned to the key
	 */
	@Override
	@TimeComplexity("O(n)")
	public V put(K key, V value) {
		/* TCJ
		 * Using a binary tree to put an entry can potentially make us a traverse
		 * a tree in which every child is to the right of the parent. Traversing
		 * such a tree is equivalent to checking each entry in a linked list and so
		 * the time it takes to find the key is O(n) just like traversing a linked list.
		 */
		Position<Entry<K,V>> w = search(tree.root(), key);	// Position of potential match
		
		// Handle swapping or adding a new node
		if (tree.isExternal(w)) {
			tree.setElement(w, new mapEntry<K,V>(key, value));
			tree.addLeft(w, null);
			tree.addRight(w, null);
			size++;											// Increment the size of the tree
			return null;
		} else {
			V oldV = w.getElement().getValue();
			((mapEntry<K,V>)w.getElement()).setValue(value);
			return oldV;
		}
	}

	/**
	 * Remove the entry at a given key and get the value.
	 * 
	 * @param key - key to search for
	 * @return the value of the removed entry else return null
	 */
	@Override
	@TimeComplexity("O(n)")
	public V remove(K key) {
		/* TCJ
		 * Using a binary tree to put an entry can potentially make us a traverse
		 * a tree in which every child is to the right of the parent. Traversing
		 * such a tree is equivalent to checking each entry in a linked list and so
		 * the time it takes to find the key is O(n) just like traversing a linked list.
		 */

		Position<Entry<K,V>> v = search(tree.root(), key);	// Position of key in the tree
		
		// Key could not be found so return null
		if (tree.isExternal(v)) {
			return null;
		}
		
		V oldValue = v.getElement().getValue();		// Value at the found node

		Position<Entry<K,V>> l = tree.left(v);		// Left child of node
		Position<Entry<K,V>> r = tree.right(v);		// Right child of node
		
		// Remove node from the tree
		if (tree.isExternal(l)) {
			tree.remove(l);
			tree.remove(v);
		} else if (tree.isExternal(r)) {
			tree.remove(r);
			tree.remove(v);
		} else {
			Position<Entry<K,V>> w = r;				// Successor of node
			while (tree.isInternal(w)) {
				w = tree.left(w);
			}
			w = tree.parent(w);
			tree.setElement(v, w.getElement());
			tree.remove(tree.left(w));
			tree.remove(w);
		}
		
		size--;										// Decrement the size of the tree
		return oldValue;
	}

	/**
	 * Produce an iterable of the Binary Tree Map entries
	 */
	@Override
	@TimeComplexity("O(n)")
	public Iterable<Entry<K, V>> entrySet() {
		return ((ArrayList<Entry<K,V>>)tree.inOrderElements(tree.root())).elements();
	}
	
	/**
	 * Helper method for finding a node in the tree
	 */
	@TimeComplexity("O(n)")
	private Position<Entry<K, V>> search(Position<Entry<K,V>> v, K k) {
		/* TCJ
		 * Using a binary tree to search for an entry can potentially make us a traverse
		 * a tree in which every child is to the right of the parent. Traversing
		 * such a tree is equivalent to checking each entry in a linked list and so
		 * the time it takes to find the key is O(n) just like traversing a linked list.
		 */
		// Check if we are at the end of our search
		if (tree.isExternal(v)) {
			return v;
		}
		
		// Find the key
		K key = v.getElement().getKey();
		if (k.equals(key)) {
			return v;
		} else if (k.compareTo(key) < 0) {
			return search(tree.left(v), k);
		} else {
			return search(tree.right(v), k);
		}
	}


	/**
	 * Check if the map is empty
	 */
	@Override
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		return (size == 0);
	}

	

}
