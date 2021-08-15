package cs2321;

import net.datastructures.*;

/**
 * Hashmap implementation using separate chaining 
 * 
 * @author Caleb Jacobs (cajacobs)
 * Date last modified: 04/05/2021
 * Course: CS2321 Spring 2021
 *
 * @param <K> - type for the key
 * @param <V> - type for the value
 */
public class HashMap<K, V> extends AbstractMap<K,V> implements Map<K, V> {
	
	/* Use Array of UnorderedMap<K,V> for the Underlying storage for the map of entries.
	 * 
	 */
	private UnorderedMap<K,V>[]  table;
	int 	size;  					// number of mappings(entries) 
	int 	capacity; 				// The size of the hash table. 
	int     DefaultCapacity = 17; 	//The default hash table size
	
	/* Maintain the load factor <= 0.75.
	 * If the load factor is greater than 0.75, 
	 * then double the table, rehash the entries, and put then into new places. 
	 */
	double  loadfactor= 0.75;  
	
	/**
	 * Constructor that takes a hash size
	 * @param hashtable size: the number of buckets to initialize 
	 */
	public HashMap(int hashtablesize) {
		// Instantiate hashmap
		table = (UnorderedMap<K,V>[]) new UnorderedMap[hashtablesize];

		for (int i = 0; i < hashtablesize; i++) {
			table[i] = new UnorderedMap<K,V>();
		}

		capacity = hashtablesize;	// Update capacity
	}
	
	/**
	 * Constructor that takes no argument
	 * Initialize the hash table with default hash table size: 17
	 */
	public HashMap() {
		this(17);
	}
	
	/* This method should be called by map an integer to the index range of the hash table 
	 */
	@TimeComplexity("O(1)")
	private int hashValue(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}
	
	/*
	 * The purpose of this method is for testing if the table was doubled when rehashing is needed. 
	 * Return the the size of the hash table. 
	 * It should be 17 initially, after the load factor is more than 0.75, it should be doubled.
	 */
	@TimeComplexity("O(1)")
	public int tableSize() {
		return table.length;
	}
	
	/**
	 * Create a new hash map
	 */
	@TimeComplexity("O(n)")
	private void makeMap() {
		table = (UnorderedMap<K,V>[]) new UnorderedMap[capacity];
		for (int i = 0; i < capacity; i++) {
			table[i] = new UnorderedMap<K,V>();
		}
	}
	
	/**
	 * Helper method for rehashing the table to a specified capacity
	 * 
	 * @param capacity - capacity to rehash to
	 */
	@TimeComplexity("O(n)")
	private void resize(int capacity) {
		/* TCJ
		 * To resize a hashtable, we need to move each entry. 
		 * Moving each entry takes O(n) time
		 */
		// Copy the old hashmap
		UnorderedMap<K,V> buffer[] = (UnorderedMap<K,V>[]) new UnorderedMap[this.capacity];
		for (int i = 0; i < this.capacity; i++) {
			buffer[i] = table[i];
		}
		
		this.capacity = capacity;							// Update capacity
		makeMap();											// Create new hashmap
		size = 0;											// Reset size of hashmap
		
		// Rehash all the old entries in to the new hashmap
		for (int i = 0; i < buffer.length; i++) {
			for (Entry<K,V> e : buffer[i].entrySet()) {
				put(e.getKey(), e.getValue());
			}
		}
	}
	
	/**
	 * Get the number of entries in the MAP
	 */
	@Override
	@TimeComplexity("O(1)")
	public int size() {
		return size;
	}

	/**
	 * Check if the MAP is empty
	 */
	@Override
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		return (size() == 0);
	}

	/**
	 * Get an entry at a specified key
	 * 
	 * @param key - key to find entry of
	 * @return the value of entry with the specified key or return null
	 */
	@Override
	@TimeComplexityExpected("O(1)")
	public V get(K key) {
		/* TCJ
		 * To get an entry from a key, we just need to compute the hashcode to index
		 * through the array which take O(1). Once we find our place in the array,
		 * we need to search through the Unordered MAP which will take on average
		 * O(1) because there should only be one entry in the MAP. Thus, the total
		 * time to run get is O(1) + O(1) = O(1).
		 */
		int idx = hashValue(key);	// Hash code to search at
		
		return table[idx].get(key);
	}

	/**
	 * Put an entry into the unordered table
	 * 
	 * @param key - key to search for
	 * @param value - value to set entry to
	 * @return null if the key/value pair was new or return the old value assigned to the key
	 */
	@Override
	@TimeComplexityExpected("O(1)")
	public V put(K key, V value) {
		/* TCJ
		 * To put an entry, we just need to compute the hashcode to index
		 * through the array which take O(1). Once we find our place in the array,
		 * we need to search through the Unordered MAP which will take on average
		 * O(1) because there should only be one entry in the MAP. Thus, the total
		 * time to run put is O(1) + O(1) = O(1).
		 */
		int idx = hashValue(key);
		
		V val = table[idx].put(key, value);
		if (val == null) {
			size++;			// Update size of map
			
			// Check for rehashing
			if ((double) size / capacity > loadfactor) {
				resize(2 * capacity);
			}
		}
		
		return val;
	}

	/**
	 * Remove the entry at a given key and get the value.
	 * 
	 * @param key - key to search for
	 * @return the value of the removed entry else return null
	 */
	@Override
	@TimeComplexityExpected("O(1)")
	public V remove(K key) {
		/* TCJ
		 * To remove an entry, we just need to compute the hashcode to index
		 * through the array which take O(1). Once we find our place in the array,
		 * we need to search through the Unordered MAP which will take on average
		 * O(1) because there should only be one entry in the MAP. Thus, the total
		 * time to remove put is O(1) + O(1) = O(1).
		 */
		int idx = hashValue(key); 		// Hash code to search at
		
		V val = table[idx].remove(key);	// Removed value
		
		// Check if we need to update the size of the map
		if (val != null) {
			size--;
			System.out.print("" + size);
		}
		
		return val;
	}

	/**
	 * Produce an iterable of each entries in the hashmap
	 * 
	 * @return Iterable for the entries in the hashmap
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// Temporarily load each entry consecutively into an array list
		ArrayList<Entry<K,V>> tmpArray = new ArrayList<Entry<K,V>>();
		
		// Load entries
		for (int i = 0; i < capacity; i++) {
			for (Entry<K,V> e : table[i].entrySet()) {
				tmpArray.addLast(e);
			}
		}
		
		return tmpArray.elements();
	}
	

}
