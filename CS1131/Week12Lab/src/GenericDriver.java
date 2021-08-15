import java.util.ArrayList;

/**
 * Test driver for the ObjectPair class.
 *
 * @author Caleb Jacobs, Jake Chunn
 * Date Last Modified 11/17/2020
 *
 * Lab Section: L03
 * CS1131 Fall 2020
 */
public class GenericDriver{

   /**
    * Create several stadium associations,
    * then print the name of the stadium
    * with the largest capacity.
    *
    * @param args Not used.
    */
   public static void main(String[] args) {

      GenericMap<String, Integer> stadiums = new GenericMap<String, Integer>();
      stadiums.put("Bridgeforth Stadium", 25000);
      stadiums.put("Michigan Stadium", 109901);
      stadiums.put("Lane Stadium", 66233);

      System.out.println("Bridgeforth Stadium: " +
           stadiums.get( "Bridgeforth Stadium" ));

      String largestStadium = largestStadium( stadiums );
      System.out.println(largestStadium + ": " +
           stadiums.get( largestStadium ));
   }

   /**
    * Returns the name of the stadium with the largest capacity.
    *
    * @param stadiumsMap A map of GenericTwoples where each Twople
    *                    contains a stadium name followed by an
    *                    integer capacity
    * @return The name of the stadium with the largest capacity
    */
   public static String largestStadium( GenericMap<String, Integer> stadiumsMap ) {
      ArrayList<String> keys = stadiumsMap.keySet();
      String name = keys.get(0);
      int tmp = stadiumsMap.get(keys.get(0));

      for(int i = 0; i < keys.size(); i++) {
         if (tmp < stadiumsMap.get(keys.get(i))) {
            tmp = stadiumsMap.get(keys.get(i));
            name = keys.get(i);
         }
      }

      return name;
   }

}