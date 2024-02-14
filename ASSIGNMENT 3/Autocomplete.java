import java.util.Arrays;


/**
 * Autocomplete.
 */
public class Autocomplete {

   private Term[] terms;

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
   public Autocomplete(Term[] terms) {
   
      if (terms == null) {
         throw new NullPointerException();
      }  
      
      this.terms = terms;
      
      Arrays.sort(terms);
   
   }

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
   public Term[] allMatches(String prefix) {
   
      if (prefix == null) {
         throw new NullPointerException();
      }
      
      //length of prefix
      int pLength = prefix.length();
      //need to be a Term object because the compare method in Term compares two terms
      Term key = new Term(prefix, 0);
      
      int firstRange = BinarySearch.firstIndexOf(terms, key, Term.byPrefixOrder(pLength));
      int lastRange =  BinarySearch.lastIndexOf(terms, key, Term.byPrefixOrder(pLength));
      
      if (firstRange == -1 || lastRange == -1) {
         return new Term[0];
      }
      
      Term[] matches = Arrays.copyOfRange(terms, firstRange, lastRange + 1);
      
      
      Arrays.sort(matches, Term.byDescendingWeightOrder());
      
            
      return matches;
   
   }

}

