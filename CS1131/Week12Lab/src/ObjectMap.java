import java.util.ArrayList;
import java.util.List;

/**
 * Implements a Map using immutable Twoples.
 *
 * @author Caleb Jacobs, Jake Chunn
 *  * Date Last Modified 11/17/2020
 *  *
 *  * Lab Section: L03
 *  * CS1131 Fall 2020
 */
public class ObjectMap implements MapInterface {
   /**
    * A List of Twoples.
    * The first element in the Twople represents the key.
    * The second element represent the value associate with the key.
    */
   private ArrayList< ObjectTwople > pairs =
        new ArrayList< ObjectTwople >( );

   /**
    * Associates a key with a value and stores the association
    * as a Twople.
    * @param key an unique object acting as an identifier
    * @param value a value to be associated with the key
    */
   public void put( Object key, Object value ) {
      ObjectTwople kvp = find( key );
      if ( kvp != null ) {
         pairs.remove( kvp );
      }
      pairs.add( new ObjectTwople( key, value ) );
   }

   /**
    * @param key an unique object acting as an identifier
    * @return the value associated with the key
    *         or null if not found
    */
   public Object get( Object key ) {
      ObjectTwople kvp = find( key );
      if ( kvp != null ) {
         return kvp.getSecond( );
      }
      return null;
   }

   /**
    * @param key an unique object acting as an identifier
    * @return the Twople containing the key as its first element.
    */
   private ObjectTwople find( Object key ) {
      for ( ObjectTwople kvp : pairs ) {
         if ( kvp.getFirst( ).equals( key ) ) {
            return kvp;
         }
      }
      return null;
   }

   /**
    * Removes the Twople whose first element is key,
    * if such a tuple exists in the map
    * @param key an unique object acting as an identifier
    * @return the value of the Twople removed
    *         or null if not found
    */
   public Object remove( Object key ) {
      ObjectTwople kvp = find( key );
      if ( kvp != null ) {
         pairs.remove( kvp );
         return kvp.getSecond( );
      }
      return null;
   }

   /**
    * @return the number of Twoples in the map.
    */
   public int size( ) { return pairs.size( ); }

   /**
    * @return true if no Twoples exist in the map, otherwise false
    */
   public boolean isEmpty( ) { return size( ) == 0; }

   /**
    * @return A list of all keys in the map
    */
   public List keySet( ) {
      ArrayList keys = new ArrayList( );
      for ( ObjectTwople kvp : pairs ) {
         keys.add( kvp.getFirst( ) );
      }
      return keys;
   }
}