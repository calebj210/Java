import java.util.Calendar;

/**
 * Program 1: Solves problems in RadioCarbon Dating
 *
 * Date Last Modified: 09/06/2020
 * @author Caleb Jacobs
 *
 * CS1131, Fall 2020
 * Lab Section 3
 */
public class Program001 {

    /**
     * Given the age of an artifact in years, return the percent of C14 left in
     * it. Here a percentage is a double in the range [0.0, 1.0]. 
     * Remember that N / N0 is the percent of C14 remaining in the object.

     * @param age - the age of an artifact in years
     * @return the percent of C14 remaining in the artifact
     */
    double radiocarbonPercent ( int age ) {
        // Compute percent of C14 remaining
        double percentOfCarbonRemaining = Math.pow(Math.E, -(0.693/5730.0) * age);
        return percentOfCarbonRemaining;
    }

    /**
     * Given the percentage of C14 remaining in an artifact, return the age 
     * of the artifact in years.
     * Here a percentage is a double in the range [0.0, 1.0].
     * Your result should be truncated, not rounded.

     * @param p - the percentage of C14 remaining in an artifact
     * @return the age of the artifact in years
     */
    int radiocarbonAge ( double p ) {
        // Compute age of artifact given percent of C14 remaining
        double ageOfArtifact = Math.log(p) * 5730.0 / -0.693;
        return (int) ageOfArtifact;
    }

    /**
     * Given the percentage of C14 remaining in an artifact, return the year of 
     * the artifact was created or the
     * organism died. The date is the current year minus the age of the artifact.
     * Your result should be truncated, not rounded.

     * @param p the percentage of C14
     * @return The date is the current year minus the age of the artifact.
     */
    int radiocarbonDate ( double p ) {
        // Use current year to compute the artifact date
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int artifactYear = currentYear - radiocarbonAge(p);
        return artifactYear;
    }

    /**
     * Test cases for checking the validity of our methods.
     * 
     * @param args - unused string
     */
    public static void main ( String [ ] args ) {

        Program001 self = new Program001( );

        System.out.println( "Test radiocarbonPercent( age )." );
        int age = 1742;
        double percentage = self.radiocarbonPercent( age );
        System.out.printf( "radiocarbonPercent( %d ) = %f%%. ", age, percentage );
        if ( (int) (percentage * 100) == 81 ) {
            System.out.println( "Success!\n" );
        } else {
            System.out.println( "FAILED!\n" );
        }

        System.out.println( "Test radiocarbonAge( percentage )." );
        percentage = 0.81;
        age = self.radiocarbonAge( percentage );
        System.out.printf( "radiocarbonAge( %f ) = %d years old. ", percentage, age );
        if ( age == 1742 ) {
            System.out.println( "Success!\n" );
        } else {
            System.out.println( "FAILED!\n" );
        }

        System.out.println( "Test radiocarbonDate( percentage )." );
        percentage = 0.81;
        int year = self.radiocarbonDate( percentage );
        System.out.printf( "radiocarbonDate( %f ) = the year %d. ", percentage, year );
        if ( year == Calendar.getInstance().get(Calendar.YEAR) - age ) {
            System.out.println( "Success!\n" );
        } else {
            System.out.println( "FAILED!\n" );
        }
    }

}
