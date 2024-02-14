import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class AutocompleteTest {


  /** Fixture initialization (common initialization
   *  for all tests). **/
   @Before public void setUp() {
   }

   @Test
   public void testAllMatchesWithPrefixAb() {
      Term[] terms = {
         new Term("a", 4),
         new Term("ab", 8),
         new Term("abc", 10),
         new Term("abcd", 2),
         new Term("abcde", 6)
         };
      Autocomplete autocomplete = new Autocomplete(terms);
   
    // Test for prefix "ab"
      Term[] matches = autocomplete.allMatches("ab");
    
    // Expected output in descending order of weight
      String[] expectedOutput = {"abc\t10", "ab\t8", "abcde\t6", "abcd\t2"};
    
      assertEquals(expectedOutput.length, matches.length);
    
      for (int i = 0; i < expectedOutput.length; i++) {
         assertEquals(expectedOutput[i], matches[i].toString());
      }
   }   
}






 
