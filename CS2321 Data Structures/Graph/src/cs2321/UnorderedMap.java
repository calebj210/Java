package cs2321;


import net.datastructures.Entry;
import net.datastructures.Map;

/**
 * Array list based Unordered MAP implementation
 * 
 * @author Caleb Jacobs (cajacobs)
 * Date last modified: 04/05/2021
 * Course: CS2321 Spring 2021
 *
 * @param <K> - type of the key
 * @param <V> - type of the value
 */

public class UnorderedMap<K,V> extends AbstractMap<K,V> {
	private ArrayList<mapEntry<K,V>> table; 	// Array list of the map

	/**
	 * Default constructor for Unordered MAP
	 */
	public UnorderedMap() {
		table = new ArrayList<mapEntry<K,V>>();
	}
		

	/** 
	 * Get the number of elements in the map
	 */
	@Override
	@TimeComplexity("O(1)")
	public int size() {
		return table.size();
	}

	/**
	 * Check if the map is empty
	 */
	@Override
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		return table.isEmpty();
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
		 * To get a key in an unordered map, we must traverse through each entry
		 * of the table until we find the key. Traversing through each entry
		 * runs in O(n) time and so get has a time complexity of O(n).
		 */
		V val = null;			// Value to search for
		
		// Begin linear search
		for (int i = 0; i < table.size(); i++) {
			if (key.equals(table.get(i).getKey())) {
				val = table.get(i).getValue();	// Assign found value
				break;							// Value found so stop searching
			}
		}
		
		return val;
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
		 * To put an entry in to an unordered map, we must traverse through each entry
		 * of the table to check if the entry already exists. Traversing through each entry
		 * runs in O(n) time and so put has a time complexity of O(n).
		 */
		V val = null;			// Value to search for
		
		// Begin linear search
		for (int i = 0; i < table.size(); i++) {
			if (key.equals(table.get(i).getKey())) {
				val = table.get(i).getValue();	// Assign found value
				table.get(i).setValue(value);	// Update value
				break;							// Value found so stop searching
			}
		}
		
		// Add entry if it doesn't already exist
		if (val == null) {
			table.addLast(new mapEntry<K,V>(key, value));
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
	@TimeComplexity("O(n)")
	public V remove(K key) {
		/* TCJ
		 * To remove an entry with a specific key in an unordered map, we must traverse through each entry
		 * of the table until we find the key. Traversing through each entry
		 * runs in O(n) time and so remove has a time complexity of O(n).
		 */
		V val = null;			// Found value
		
		// Begin linear search
		for (int i = 0; i < table.size(); i++) {
			if (key.equals(table.get(i).getKey())) {
				val = table.get(i).getValue();		// Assign found value
				table.remove(i);					// Remove found entry
				break;								// Value found so stop searching
			}
		}
		
		return val;
	}

	/**
	 * Produce an iterable of each entry in the map
	 * 
	 * @return iterable of entries in the map
	 */
	@Override
	@TimeComplexity("O(n)")
	public Iterable<Entry<K, V>> entrySet() {
		/* TCJ
		 * To produce an iterable with an Entry<K,V> type, we need to copy each mapEntry in
		 * the ArrayList which takes O(n) time.
		 */
		ArrayList<Entry<K,V>> tmpArray = new ArrayList<Entry<K,V>>(table.size());	// Entry ArrayList
		
		// Copy each entry from the original ArrayList to the temporary Array list
		for (int i = 0; i < table.size(); i++) {
			tmpArray.add(i, table.get(i));
		}
		
		return tmpArray.elements();
	}

}
