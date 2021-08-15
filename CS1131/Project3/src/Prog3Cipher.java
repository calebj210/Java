/**
 * Program to encode and decode a message using a Vigenere Cipher
 *
 * @author Caleb Jacobs
 * Date Last Modified 9/20/2020
 *
 * Fall 2020
 * Lab Section 3
 */
public class Prog3Cipher {
   char [ ] keyList;        // Key for Vigenere Cipher
   char [ ][ ] cipherTable; // Cipher table for Vigenere Cipher

   /**
    * Encode a message using Vigenere Cipher
    *
    * @param message - Message to encode using Vigenere Cipher
    * @return - Encoded message
    */
   String encode(String message) {
      StringBuilder result = new StringBuilder(); // Encoded message
      int keyIdx = 0;                                  // Key index
      char currentChar;                                // Current working character in message

      // Begin encoding message
      for (int i = 0; i < message.length(); i++) {
         // Get current character in message and convert it to upper case
         currentChar = Character.toUpperCase(message.charAt(i));

         if (message.charAt(i) != ' ') {
            // If character is not a space, append the corresponding character in the cipher table
            result.append(cipherTable[keyList[keyIdx] % 26][currentChar%26]);
         } else {
            // Append space
            result.append(' ');
         }

         // Increment key index
         keyIdx = (++keyIdx)%keyList.length;
      }

      return result.toString();
   }

   /**
    * Decode Vignenere encoded message
    *
    * @param message - Encoded message to be decoded
    * @return - Decoded message
    */
   String decode(String message) {
      StringBuilder result = new StringBuilder();  // Decoded message
      int keyIdx = 0;                              // Key index
      char currentChar;                            // Current working character
      int columnIdx;                           // cipherTable column index

      // Begin decoding message
      for (int i = 0; i < message.length(); i++) {
         // Get current working character in message and convert it to uppercase
         currentChar = Character.toUpperCase(message.charAt(i));

         if (currentChar != ' ') {
            // If current character is not a space, compute the column that the current character is in
            columnIdx = currentChar - cipherTable[0][0] - (keyList[keyIdx]-65);

            // Shift idx by 26 until idx >= 0
            while (columnIdx < 0) {
               columnIdx += 26;
            }

            // Add the character of the corresponding column
            result.append((char)(columnIdx + 65));
         } else {
            // If current character is a space, add a space
            result.append(' ');
         }

         keyIdx = (++keyIdx)%keyList.length;
      }

      return result.toString();
   }

   /**
    * Construct cipher table and store the key
    *
    * @param code - First entry in our cipher table
    * @param key - key to encode and decode our message with
    */
   public Prog3Cipher(char code, String key) {
      keyList = key.replace(" ", "").toUpperCase().toCharArray(); // Store given key without spaces

      // Begin populating the cipher table
      cipherTable = new char[26][26];
      for (int i = 0; i < cipherTable.length; i++) {
         for (int j = 0; j < cipherTable[i].length; j++) {
            cipherTable[i][j] = (char)((code - 65 + j + i)%26 + 65);
         }
      }
   }

   /**
    * No parameter constructor
    */
   public Prog3Cipher() {
      this('A',"A");
   }

   /**
    * Driver for testing the cipher
    *
    * @param args - unused array of strings
    */
   public static void main( String[ ] args ) {
      Prog3Cipher self = new Prog3Cipher( 'H', "BABBAGE" );
      assert "PHXXF MQYBPKNJ".equals( self.encode( "Happy Birthday" ) );
      assert "HAPPY BIRTHDAY".equals( self.decode( "PHXXF MQYBPKNJ" ) );
   }
}