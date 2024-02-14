import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Brady Hajec (bdh0059@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  8/27/2023
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int store = a[0];
      for (int i = 0; i < a.length; i++) {
         
         if (store > a[i]) {
            store = a[i];
         }
      
      }
      return store;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
   
      int store = a[0];
      for (int i = 0; i < a.length; i++) {
         
         if (store < a[i]) {
            store = a[i];
         }
      
      }
      return store;
   
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      
      int[] copyArray = Arrays.copyOf(a, a.length); 
   
      int store = copyArray[0];
      int j = 1;
      Arrays.sort(copyArray);
      
      //check for different values
      int diff = 1;
      for (int i = 1; i < copyArray.length; i++) {
         if (copyArray[i] != copyArray[i - 1]) {
            diff++;
         }
      } 
      if (diff < k) {
         throw new IllegalArgumentException();              
      }
      
      if (k == 1) {
         return copyArray[0];
      }
      
      for (int i = 0; i < copyArray.length; i++) {
         
         if (i + 1 < copyArray.length && copyArray[i] != copyArray[i + 1]) {
            store = copyArray[i + 1];
            j++;
            if (j == k) {
               break;
            }
         }
      }
      
      return store;
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
          
      int[] copyArray = Arrays.copyOf(a, a.length);
      
      int j = 1;
      Arrays.sort(copyArray);
      
      int diff = 1;
      for (int i = 1; i < copyArray.length; i++) {
         if (copyArray[i] != copyArray[i - 1]) {
            diff++;
         }
      } 
      if (diff < k) {
         throw new IllegalArgumentException();              
      }
   
      
      int max = copyArray.length - 1;
      
      if (k == 1) {
         return copyArray[copyArray.length - 1];
      }
      
      for (int i = copyArray.length - 1; i >= 0; i--) {
         if (i - 1 >= 0 && copyArray[i] != copyArray[i - 1]) {
            j++;
            if (j == k) {
               max = copyArray[i - 1];
            }
         }
      } 
            
      return max;
   }



   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
   
   
      int length = 0;
      
      for (int number : a) {
         if (number >= low && number <= high) {
            length++;
         }
      }
      int j = 0;
      int[] rangeArray = new int[length];
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            rangeArray[j] = a[i];
            j++;
         }
      }
      
      return rangeArray;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int length = 0;
      int[] moreThanKey = new int[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= key) {
            length++;
            moreThanKey = Arrays.copyOf(moreThanKey, length);
            moreThanKey[length - 1] = a[i];
         }  
      }
      
      if (moreThanKey.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int target = moreThanKey[0];
      for (int j = 0; j < moreThanKey.length; j++) {
         if (moreThanKey[j] < target) {
            target = moreThanKey[j];
         }
      
      }
      return target;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      
      int length = 0;
      int[] lessThanKey = new int[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= key) {
            length++;
            lessThanKey = Arrays.copyOf(lessThanKey, length);
            lessThanKey[length - 1] = a[i];
         }  
      }
      
      if (lessThanKey.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int target = lessThanKey[0];
      for (int j = 0; j < lessThanKey.length; j++) {
         if (lessThanKey[j] > target) {
            target = lessThanKey[j];
         }
      
      }
      return target;
   }
}
