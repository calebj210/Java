package cs2321;

import net.datastructures.*;

/**
 * Array list based Sorted MAP implementation
 * 
 * @author Caleb Jacobs (cajacobs)
 * Date last modified: 04/05/2021
 * Course: CS2321 Spring 2021
 *
 * @param <K> - type for the key
 * @param <V> - type for the value
 */
public class SortedTable<K extends Comparable<K>, V> extends AbstractMap<K,V>  {
	private ArrayList<mapEntry<K,V>> table; 		// Sorted table to store entries

	/**
	 * Default constructor for the Sorted MAP
	 */
	public SortedTable(){
		table = new ArrayList<mapEntry<K,V>>();
	}
	
	/**
	 * Perform binary search algorithm to find the key
	 * 
	 * @param A - ArrayList of entries to find key in
	 * @param k - key to find
	 * @return the index of the entry with the specified key or where
	 *         an insertion is required
	 */
	@TimeComplexity("O(log(n))")
	private int binarySearch(ArrayList<mapEntry<K,V>> A, K k) {
		/* TCJ
		 * To find a key using a binary search on a sorted array list,
		 * we eliminate half of the entries at each iteration. So, if
		 * we have n entries, the most entries that we could have to compare
		 * is given by log(n). So we need at most log(n) comparison to find
		 * the entry and so binary searching takes O(log(n)) time. 
		 */
		int l = 0;						// Initial left bound
		int r = A.size() - 1;			// Initial right bound
		
		// Begin searching for key
		while (l <= r) {
			int mid = (l + r) / 2;		// Middle index of region
			
			// Check if key has been found
			if (A.get(mid).getKey().compareTo(k) == 0) {
				return mid;
			} else if (A.get(mid).getKey().compareTo(k) < 0) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}

		return l;
		
	}
	
	/**
	 * Find the value of an entry with a given key
	 * 
	 * @param key - key to search for
	 * @return the value of the entry with key or return null
	 */
	@Override
	@TimeComplexity("O(log(n))")
	public V get(K key) {
		/* TCJ
		 * To get a key, we need to use a binary search which runs in O(log(n)) time.
		 * So, get runs in O(log(n)) time. See the binarySearch TCJ for more information.
		 */
		if (table.isEmpty()) {
			return null;
		}
		
		int idx = binarySearch(table, key);		// Index of potential key match
		
		if (table.get(idx).getKey().equals(key)) {
			return table.get(idx).getValue();
		} else {
			return null;
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
		 * To put an entry, we need to use a binary search to find where to put the entry
		 * which runs in O(log(n)) time and then we may need to expand the array list which 
		 * runs in O(n) time. So, the worst case time complexity is when we need to expand 
		 * or shift each element in the array which takes a total of 
		 * O(log(n)) + O(n) = O(n) time.
		 */
		
		// Handle an empty map
		if (table.isEmpty()) {
			table.addLast(new mapEntry<K,V>(key, value));
			return null;
		}
		
		int idx = binarySearch(table, key);		// Index of potential key match
		
		// Check if we need to replace a value or add a new entry
		if (idx < table.size() && table.get(idx).getKey().equals(key)) {
			V old = table.get(idx).getValue();
			table.get(idx).setValue(value);
			return old;
		} else {
			table.add(idx, new mapEntry<K,V>(key, value));
			return null;
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
		 * To remove an entry, we need to use a binary search to find the entry to remove
		 * which runs in O(log(n)) time. But, we also have to shift each element in an array
		 * list which take O(n) So, remove runs in O(log(n)) + O(1) = O(1) time.
		 */
		if (table.isEmpty()) {
			return null;
		}
		
		int idx = binarySearch(table, key);		// Index of potential key match
		
		if (table.get(idx).getKey().equals(key)) {
			V old = table.get(idx).getValue();
			table.remove(idx);
			return old;
		} else {
			return null;
		}
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
		
		// Copy each from the original ArrayList to the temporary Array list
		for (int i = 0; i < table.size(); i++) {
			
			tmpArray.set(i, table.get(i));
		}
		
		return tmpArray.elements();
	}

	/**
	 * Get the number of entries in the MAP
	 */
	@Override
	@TimeComplexity("O(1)")
	public int size() {
		return table.size();
	}

	/**
	 * Check if the MAP is empty
	 */
	@Override
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		return table.isEmpty();
	}


}
