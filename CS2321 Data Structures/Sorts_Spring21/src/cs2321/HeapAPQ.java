package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A Adaptable PriorityQueue based on an heap. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Caleb Jacobs (cajacobs)
 * Date Last Modified: 03/23/2021
 */

public class HeapAPQ<K,V> implements AdaptablePriorityQueue<K,V> {
	private ArrayList<IdxEntry<K,V>> A;      // List to store heap
	private Comparator<K> C;                 // Key comparator

	/**
	 * Entry<K,V> with extra array list positional data
	 * 
	 * @param <K> Key data type
	 * @param <V> Value data type
	 */
	private class IdxEntry<K,V> implements Entry<K,V> {
		private K k;		// Key
		private V v;		// Value
		private int idx;	// Index in ArrayList
		
		/**
		 * Create an entry with the specified data
		 * 
		 * @param k - key of entry
		 * @param v - value of entry
		 * @param idx - index in ArrayList
		 */
		public IdxEntry(K k, V v, int idx) {
			this.k = k;
			this.v = v;
			this.idx = idx;
		}
		
		/**
		 * Get key in entry
		 * 
		 * @return key
		 */
		@Override
		public K getKey() {
			return k;
		}

		/**
		 * Get value in entry
		 * 
		 * @return value
		 */
		@Override
		public V getValue() {
			return v;
		}
		
		/**
		 * Get index in array list
		 * 
		 * @return array list index
		 */
		public int getIdx() {
			return idx;
		}
		
		/**
		 * Set key in entry
		 * 
		 * @param k - key to be set
		 */
		public void setKey(K k) {
			this.k = k;
		}
		
		/**
		 * Set value in entry
		 * 
		 * @param v - value to be set
		 */
		public void setValue(V v) {
			this.v = v;
		}
		
		/**
		 * Set index of entry
		 * 
		 * @param idx - index to be set
		 */
		public void setIdx(int idx) {
			this.idx = idx;
		}
	}

	/**
	 * Basic comparator definition for the key of an entry
	 *
	 * @param <K> Data type of key
	 */
	public static class DefaultComparator<K> implements Comparator<K> {
		// This compare method simply calls the compareTo method of the argument. 
		// If the argument is not a Comparable object, and therefore there is no compareTo method,
		// it will throw ClassCastException. 
		public int compare(K a, K b) throws IllegalArgumentException {
			if (a instanceof Comparable ) {
			   return ((Comparable <K>) a).compareTo(b);
			} else {
				throw new  IllegalArgumentException();
			}
		}
	}
	
	/* If no comparator is provided, use the default comparator which simply calls compareTo() method */
	public HeapAPQ() {
		C = new DefaultComparator<K>();
		A = new ArrayList<IdxEntry<K,V>>();
	}
	
	/* use specified comparator */
	public HeapAPQ(Comparator<K> c) {
		C = c;
		A = new ArrayList<IdxEntry<K,V>>();
	}
	
	/**
	 * Get the number of entries in the heap
	 * 
	 * @return number of entries
	 */
	@TimeComplexity("O(1)")
	@Override
	public int size() {
		return A.size();
	}

	/**
	 * Check if heap is empty
	 * 
	 * @return true if heap is empty
	 */
	@TimeComplexity("O(1)")
	@Override
	public boolean isEmpty() {
		return A.isEmpty();
	}

	/**
	 * Insert a new entry into the heap PQ
	 * 
	 * @param key - key of new entry
	 * @param value - value of new entry
	 * @return new entry in the heap PQ
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(log(n))")
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		/* TCJ
		 * The time complexity of insert() is O(n) while the amortized time
		 * complexity is O(log(n)). The O(n) comes from the fact that array
		 * list might need to expand the size of the array list. When expanding
		 * the size of an array list, each entry in the array needs to be
		 * copied to a new array which costs O(n). Now the amortized cost is
		 * only O(log(n)) because we usually don't need to expand the storage of 
		 * the array list. In this case, the highest cost step is the upHeap().
		 * UpHeap() has a time complexity of O(log(n)) and so the amortized cost
		 * of insert() is O(log(n)). See the upHeap() for the TCJ.
		 */
		IdxEntry<K,V> e = new IdxEntry<K,V>(key, value, A.size());
		A.addLast(e);
		upHeap(e.getIdx());			// Fix heap order

		return e;
	}

	/**
	 * Get the minimum entry in the PQ
	 * 
	 * @return minimum entry in the PQ
	 */
	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> min() {
		if (A.isEmpty()) {
			return null;
		}
		return A.get(0);
	}

	/**
	 * Return and remove the minimum entry in the PQ
	 * 
	 * @return minimum entry in the PQ
	 */
	@TimeComplexity("O(log(n))")
	@Override
	public Entry<K, V> removeMin() {
		/* TCJ
		 * removeMin() runs in O(log(n)) due the use of downheap. Downheap() runs in O(log(n)) which is justified 
		 * in the downheap method. The rest of the actions in removeMin() are all on the order of O(1) and so the
		 * downheap() method dominates the time complexity. See the downheap method for a justification of the 
		 * time complexity
		 */
		if (A.isEmpty()) {
			return null;
		}
		Entry<K,V> min = A.get(0);
		IdxEntry<K,V> last = A.removeLast();
		if (!(A.isEmpty())) {
			A.set(0, last);				// Set root to last node
			A.get(0).setIdx(0);			// Fix index of root
			downHeap(0);				// Restore heap order
		}
		return min;
	}

	/**
	 * Remove the specified entry from the PQ
	 * 
	 * @param entry - entry to be removed from the PQ
	 * @throws IllegalArgumentException if the entry is not valid
	 */
	@TimeComplexity("O(log(n))")
	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		/* TCJ
		 * The time complexity of remove() is O(log(n)). Each action used in remove() has a cost of O(1) except for the
		 * upheap and downheap methods which have a cost of O(log(n)). Thus, the dominant term is the upheap or downheap
		 * terms and so the time complexity of remove() is O(log(n)). See upHeap() and downHeap() for TCJ.
		 */
		IdxEntry<K,V> e = validateEntry(entry);
		int idx = e.getIdx();			// Index of entry to remove
		K oldKey = e.getKey();

		// Check if heap only has one entry
		if (A.size() == 1) {
			A.removeLast();
			return;
		}

		// Remove entry and replace with the last entry
		A.set(idx, A.removeLast());
		A.get(idx).setIdx(idx);
		K key = A.get(idx).getKey();
		if (C.compare(key,  oldKey) < 0) {
			upHeap(idx);
		} else {
			downHeap(idx);
		}
	}

	/**
	 * Replace the key of a specified entry in the PQ
	 * 
	 * @param entry - entry to change the key of
	 * @param key - key to set to
	 * @throws IllegalArgumentException if entry is not valid
	 */
	@TimeComplexity("O(log(n))")
	@Override
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		/* TCJ
		 * Similar to remove(), replaceKey() has a time complexity of O(log(n)). Just as remove(), the dominant terms in 
		 * replaceKey() come from the upheap and downheap methods when restoring heap order after changing a key. The cost
		 * of upHeap() and downHeap() are both O(log(n)). See TCJ under the upHeap() and downHeap() methods
		 */
		IdxEntry<K,V> e = validateEntry(entry);
		
		// Get old key value and then replace it while fixing heap order
		K oldKey = e.getKey();
		e.setKey(key);
		if (C.compare(key,  oldKey) < 0) {
			upHeap(e.getIdx());
		} else {
			downHeap(e.getIdx());
		}
	}

	/**
	 * Replace the value an entry in PQ
	 * 
	 * @param entry - entry to change the value of
	 * @param value - value to change to
	 * @throws IllegalArgumentException if entry is not valid
	 */
	@TimeComplexity("O(1)")
	@Override
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		IdxEntry<K,V> e = validateEntry(entry);
		e.setValue(value);
	}
	
	/* Heap implementation */
	/**
	 * Get the index of a parent of an entry in the heap
	 * 
	 * @param i - index of entry to get the parent of
	 * @return index of the parent
	 */
	@TimeComplexity("O(1)")
	public int parent(int i) {
		return (i - 1) / 2;
	}
	
	/**
	 * Get the index of the left child of a parent
	 * 
	 * @param i - index of parent
	 * @return index of the left child
	 */
	@TimeComplexity("O(1)")
	public int left(int i) {
		return 2 * i + 1;
	}
	
	/**
	 * Get the index of the right child of a parent
	 * 
	 * @param i - index of parent
	 * @return index of the right child
	 */
	@TimeComplexity("O(1)")
	public int right(int i) {
		return 2 * i + 2;
	}
	
	/**
	 * Check if entry has a parent in the heap
	 * 
	 * @param i - index of entry
	 * @return true in entry has a parent
	 */
	@TimeComplexity("O(1)")
	public boolean hasParent(int i) {
		return i > 0;
	}
	
	/**
	 * Check if an entry has a left child
	 * 
	 * @param i - index of entry
	 * @return index of left child
	 */
	@TimeComplexity("O(1)")
	public boolean hasLeft(int i) {
		return left(i) < A.size();
	}
	
	/**
	 * Check if an entry has a right child
	 * 
	 * @param i - index of entry
	 * @return index of right child
	 */
	@TimeComplexity("O(1)")
	public boolean hasRight(int i) {
		return right(i) < A.size();
	}

	/* Heap helper methods */
	/**
	 * Swap two given entries in the heap
	 * 
	 * @param i - index of first entry
	 * @param j - index of second entry
	 */
	@TimeComplexity("O(1)")
	private void swap(int i, int j) {
		IdxEntry<K,V> tmp = A.get(i);
		tmp.setIdx(j);				// Fix index of first entry
		A.get(j).setIdx(i);			// Fix index of second entry
		A.set(i, A.get(j));
		A.set(j, tmp);
	}
	
	/**
	 * Perform downheap on an entry in the heap to restore heap order
	 * 
	 * @param i - index of entry to perform downheap on
	 */
	@TimeComplexity("O(log(n))")
	private void downHeap(int i) {
		/* TCJ
		 * downHeap() has a time complexity of O(log(n)). This complexity is due to the possible number
		 * of swaps needed to perform downHeap(). The most swaps that can be made by downHeap() is equal to the height
		 * of the binary tree housing the heap. Because a heap is a complete binary tree, the height is given by log(n).
		 * Thus the maximum number of swaps needed to restore heap order using downHeap() is log(n). The rest of the
		 * actions used in downHeap are all O(1). Thus, the dominant term is the recursive swapping and so the total
		 * time complexity is O(log(n)).
		 */
		if (!hasLeft(i)) {
			return;
		}

		int s = i;				// Parent node
		int l = left(i);		// Left child
		int r = right(i);		// Right child

		// Get the smallest entry of s, l, and r
		if (C.compare(A.get(s).getKey(), A.get(l).getKey()) > 0) {
			s = l;
		}

		if (hasRight(i) && C.compare(A.get(s).getKey(), A.get(r).getKey()) > 0) {
			s = r;
		}

		// Swap parent with the smallest
		if (s != i) {
			swap(s, i);
			downHeap(s);
		}
	}

	/**
	 * Perform upheap on an entry in the heap to restore heap order
	 * 
	 * @param i - index of entry to perform upheap on.
	 */
	@TimeComplexity("O(log(n))")
		/* TCJ
		 * upHeap() has a time complexity of O(log(n)). This complexity is due to the possible number
		 * of swaps needed to perform upHeap(). The most swaps that can be made by upHeap() is equal to the height
		 * of the binary tree housing the heap. Because a heap is a complete binary tree, the height is given by log(n).
		 * Thus the maximum number of swaps needed to restore heap order using upHeap() is log(n). The rest of the
		 * actions used in upHeap are all O(1). Thus, the dominant term is the recursive swapping and so the total
		 * time complexity is O(log(n)).
		 */
	private void upHeap(int i) {
		if (i == 0) {
			return;
		}

		int p = parent(i);			// Parent node

		if (C.compare(A.get(i).getKey(), A.get(p).getKey()) < 0) {
			swap(i, p);
			upHeap(p);
		}
	}
	
	/**
	 * Check if an entry is a valid entry already in the heap
	 * 
	 * @param entry - entry to check the validity of
	 * @return the positionally aware entry version of the given entry
	 * @throws IllegalArgumentException if the entry is not a valid entry in the heap
	 */
	@TimeComplexity("O(1)")
	private IdxEntry<K,V> validateEntry(Entry<K,V> entry) throws IllegalArgumentException {
		if (!(entry instanceof IdxEntry)) {
			throw new IllegalArgumentException("Entry has no positional data!");
		}
		IdxEntry<K,V> e = (IdxEntry<K,V>)entry;
		int i = e.getIdx();			// Index of entry
		K k = e.getKey();			// Key of entry
		V v = e.getValue();			// Value of entry
		
		// Check validity of index, key, and value
		if (i >= A.size() || !(k.equals(A.get(i).getKey())) || !(v.equals(A.get(i).getValue()))) {
			throw new IllegalArgumentException("Entry is not and element in the heap!");
		}
		
		return e;
	}
}
