import java.util.ArrayList;
import java.util.List;

/**
 * A Map Interface. Associates Keys with Values.
 *
 * @author Caleb Jacobs, Jake Chunn
 *  * Date Last Modified 11/17/2020
 *  *
 *  * Lab Section: L03
 *  * CS1131 Fall 2020
 */
public interface MapInterface<K,V> {

   /**
    * Associates a key with a value and stores the association.
    * @param key an unique object acting as an identifier
    * @param value a value to be associated with the key
    */
   public void put( K key, V value );

   /**
    * @param key an unique object acting as an identifier
    * @return the value associated with the key
    *         or null if not found
    */
   public V get( K key );

   /**
    * Removes the Twople whose first element is key,
    * if such a tuple exists in the map
    * @param key an unique object acting as an identifier
    * @return the value of the Twople removed
    *         or null if not found
    */
   public V remove( K key );

   /**
    * @return the number of Twoples in the map.
    */
   public int size( );

   /**
    * @return true if no Twoples exist in the map, otherwise false
    */
   public boolean isEmpty( );

   /**
    * @return A list of all keys in the map
    */
   public List keySet( );
}
