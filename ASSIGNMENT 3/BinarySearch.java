import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

   /**
    * Returns the index of the first key in a[] that equals the search key, 
    * or -1 if no such key exists. This method throws a NullPointerException
    * if any parameter is null.
    */
   public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
   
      if (a == null || key == null || comparator == null) {
         throw new NullPointerException();
      }
      
      int left = 0, right = a.length -1;
      
      int result = -1;
      
      while (left <= right) {
      
         int middle = ((left + right) / 2);
         int cmp = comparator.compare(key, a[middle]);
         
                
         if (cmp < 0) {
            right = middle - 1;
         }
         else if (cmp > 0) {
            left = middle + 1;
         }
         else {
            result = middle;
            right = middle - 1;
         }
      }
   
      return result;
   }

   /**
    * Returns the index of the last key in a[] that equals the search key, 
    * or -1 if no such key exists. This method throws a NullPointerException
    * if any parameter is null.
    */
   public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
   
      if (a == null || key == null || comparator == null) {
         throw new NullPointerException();
      }
      
      int left = 0, right = a.length -1;
      
      int result = -1;
   
      while (left <= right) {
      
         int middle = ((left + right) / 2);
         int cmp = comparator.compare(key, a[middle]);
                  
         if (cmp < 0) {
            right = middle - 1;
         }
         else if (cmp > 0) {
            left = middle + 1;
         }
         else {
            result = middle;
            left = middle + 1;
         }
      }
   
      return result;
   }

}
