/**
 * Immutable 2-tuple type.
 *
 * @author Caleb Jacobs, Jake Chunn
 *  * Date Last Modified 11/17/2020
 *  *
 *  * Lab Section: L03
 *  * CS1131 Fall 2020
 */
public class GenericTwople <E1, E2> {

   private final E1 first;
   private final E2 second;

   /**
    * Create an KeyValuePair with the provided objects.
    *
    * @param first The first object.
    * @param second The second object.
    */
   public GenericTwople(E1 first, E2 second ) {
      this.first = first;
      this.second = second;
   }

   /**
    * @return the first
    */
   public E1 getFirst() {
      return first;
   }

   /**
    * @return the second
    */
   public E2 getSecond() {
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