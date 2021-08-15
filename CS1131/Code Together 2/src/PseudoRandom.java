/**
 * Two random number generators based on the Middle Square Algorithm
 * and the Linear Congruential Generator Algorithm
 *
 * Date Last Modified: September 12th, 2020
 * @author Caleb Jacbs
 *
 * Fall 2020
 * Lab Section 3
 */

public class PseudoRandom {
    /**
     * Middle square algorithm for generating a random sequence of numbers in [0,1).
     *
     * @param seed - seed for random number generation
     * @param n - number of random numbers to generate
     * @return random sequence of floating point numbers
     */
    public float[] middleSquare(long seed, int n) {
        float[] sequence = new float[n];                               // Sequence of random numbers
        String seedString;                                             // Seed converted to a string so we can extract middle numbers

        for (int i = 0; i < n; i++) {
            seed *= seed;                                              // Square the seed
            seedString = String.format("%016d", seed);              // Convert seed^2 to a string
            seed = Long.parseLong(seedString.substring(
                     seedString.length()/2 - 4,
                    seedString.length()/2 + 4));                    // extract new middle seed

            sequence[i] = (float) (seed/Math.pow(10,8));               // Compute float from new seed
        }

        return sequence;
    }

    /**
     * Linear Congruential Generator Algorithm for generating sequences
     * of random, floating point numbers in [0,1)
     *
     * @param seed - seed for random number generation
     * @param n - number of random numbers to generate
     * @param m - modulus for Linear Congruential Algorithm
     * @param a - multiplier for Linear Congruential Algorithm
     * @param c - increment for Linear Congruential Algorithm
     * @return random sequence of floating point numbers
     */
    public float[] linCong(long seed, int n, int m, int a, int c) {
        float[] sequence = new float[n];                  // Array for storing random numbers

        // Create n random numbers
        for (int i = 0; i < n; i++) {
            seed = Math.abs((a * seed + c) % m);          // Compute next random number

            sequence[i] = (float) (seed/Math.pow(10,8));  // Scale random number to [0,1) and store it
        }

        return sequence;
    }


    /**
     * Driver for testing the random number generators
     *
     * @param args -  Unused arguments
     */
    public static void main(String[] args) {
        PseudoRandom rands = new PseudoRandom();   // Create PseudoRandom object
        long seed = 123456234567878123L;           // Seed to be used for random number generation
        int n = 10;                                // Number of random numbers to generate per sequence
        int m = 99999999;                          // Modulo for Linear Congruential Algorithm
        int a = 12354;                             // Multiplier Linear Congruential Algorithm
        int c = 1234123;                           // Increment Linear Congruential Algorithm

        System.out.println("10 random numbers generated using the Middle Square Algorithm are with \n" +
                "seed = " + seed + " are:");
        float[] randomNumbers = rands.middleSquare(seed, n);
        for (float i: randomNumbers) {
            System.out.println(i);
        }

        System.out.println("\n10 random numbers generated using the Linear Congruential Algorithm with \n" +
                "seed = " + seed + ", m = " + m + ", a = " + a + ", and c = " + c + " are:");
        randomNumbers = rands.linCong(seed, n, m, a, c);
        for (float i: randomNumbers) {
            System.out.println(i);
        }
    }
}
