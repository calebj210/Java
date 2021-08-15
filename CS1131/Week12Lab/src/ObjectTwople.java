/**
 * Immutable 2-tuple type.
 *
 * @author Caleb Jacobs, Jake Chunn
 *  * Date Last Modified 11/17/2020
 *  *
 *  * Lab Section: L03
 *  * CS1131 Fall 2020
 */
public class ObjectTwople {

   private final Object first;
   private final Object second;

   /**
    * Create an KeyValuePair with the provided objects.
    *
    * @param first The first object.
    * @param second The second object.
    */
   public ObjectTwople( Object first, Object second ) {
      this.first = first;
      this.second = second;
   }

   /**
    * @return the first
    */
   public Object getFirst() {
      return first;
   }

   /**
    * @return the second
    */
   public Object getSecond() {
      return second;
   }

   /**
    * @return Stringified representation of the KeyValuePair instance
    */
   @Override
   public String toString() {
      return "<" + first.toString() +
           ", " + second.toString() + ">";
   }

}