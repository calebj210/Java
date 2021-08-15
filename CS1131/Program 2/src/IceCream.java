/**
 * Display and compute the cost of all possible ice cream combinations
 * from Moo Cow's Ice Cream shop
 *
 * Date Last Modified: September 12th, 2020
 * @author Caleb Jacobs
 *
 * Fall 2020
 * Lab Section 3
 */

public class IceCream {
    /**
     * Display and count the number of ice cream combinations
     *
     * @return - the number of ice cream combinations
     */
    public long printMenu( ) {
        long count = 0;                                                        // Combination counter
        String[] iceCreams = {"", "Vanilla", "Strawberry", "Chocolate"};       // Ice cream flavors
        double[] creamCosts = {0.0, 1.15, 1.35, 1.75};                         // Cost of ice cream
        String[] toppings = {"Sprinkles", "Whipped Cream", "Chocolate Chips"}; // Types of toppings
        double[] toppingCosts = {0.15, 0.35, 0.40};                            // Cost of toppings

        /* Begin displaying different ice cream combinations */
        for (int i = 0; i < iceCreams.length; i++) {

            /* Begin iterating through topping combinations */
            for (int j = 0; j < Math.pow(2,toppings.length); j++) {
                count++;                                                       // Increment counter
                double cost = creamCosts[i];                                   // Declare combination cost
                StringBuilder combo = new StringBuilder(iceCreams[i]);         // Current combination string
                boolean commaTrigger = true;                                   // Trigger for first comma placement in output

                /* If no ice cream is selected, remove first comma */
                if (i == 0) {
                    commaTrigger = false;
                }

                /* Construct a string to store the binary form of our ith combination */
                String byteString = String.format("%3s", Integer.toBinaryString(j)).replaceAll(" ", "0");

                /* Check byteString to see which toppings are on our ice cream */
                for (int k = 0; k < toppings.length; k++) {
                    if ('1' == byteString.charAt(k)) {
                        /* Check if comma is needed when adding the topping */
                        if (commaTrigger) {
                            combo.append(", ").append(toppings[k]);
                        } else {
                            combo.append(toppings[k]);
                            commaTrigger = true;
                        }

                        /* Add cost of current topping to ice cream */
                        cost += toppingCosts[k];
                    }
                }

                /* Display ice cream and topping combination along with cost */
                System.out.printf("%02d %-54s$%1.2f\n", count-1, combo.toString(), cost);
            }
        }

        return count;
    }

    /**
     * Test ice cream combinations
     *
     * @param args - unused arguments
     */
    public static void main( String [ ] args ) {
        IceCream obj = new IceCream( );
        long count = obj.printMenu( );
        assert count == 32;
    }
}
