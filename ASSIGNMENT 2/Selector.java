import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Brady Hajec (bdh0059@auburn.edu)
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
     * Returns the minimum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the minimum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      //create iterator object to traverse through list
      Iterator<T> itr = coll.iterator();
      
      T min = itr.next();
      
      while (itr.hasNext()) {
      //min is first in list, then current is the one after that
         T current = itr.next();
         if (comp.compare(current, min) < 0) {
            min = current;
         }
      }
      
      return min;
   }


    /**
     * Selects the maximum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the maximum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      //create iterator object to traverse through list
      Iterator<T> itr = coll.iterator();
      
      T max = itr.next();
      
      while (itr.hasNext()) {
      //min is first in list, then current is the one after that
         T current = itr.next();
         if (comp.compare(current, max) > 0) {
            max = current;
         }
      }
      
      return max;
   }


    /**
     * Selects the kth minimum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth minimum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth minimum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      
      
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }  
   
      if (coll.isEmpty() || k < 1 || k > coll.size()) {
         throw new NoSuchElementException();
      }
      
      //converting the collection to an ArrayList
      List<T> list = new ArrayList<>(coll);
      //sorting the ArrayList
      java.util.Collections.sort(list, comp);
       //if key is one it will be first element of the array
      if (k == 1) {
         return list.get(0);
      }
   
      int diff = 1;
      T store = null;
      for (int i = 0; i < list.size(); i++) {
         if (i + 1 < list.size() && list.get(i) != list.get(i + 1)) {
            store = list.get(i + 1);
            diff++;
            if (diff == k) {
               break;
            }
         }
      }
      
      if (diff < k) {
         throw new NoSuchElementException();
      }
   
      
      return store;
   }


    /**
     * Selects the kth maximum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth maximum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth maximum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }  
   
      if (coll.isEmpty() || k < 1 || k > coll.size()) {
         throw new NoSuchElementException();
      }
      
      //converting the collection to an ArrayList
      List<T> list = new ArrayList<>(coll);
      //sorting the ArrayList
      java.util.Collections.sort(list, comp);
      //reverse list 
      java.util.Collections.reverse(list);
       //if key is one it will be first element of the array
      if (k == 1) {
         return list.get(0);
      }
      int diff = 1;
      T store = null;
      for (int i = 0; i < list.size(); i++) {
         if (i + 1 < list.size() && list.get(i) != list.get(i + 1)) {
            store = list.get(i + 1);
            diff++;
            if (diff == k) {
               break;
            }
         }
      }
      
      if (diff < k) {
         throw new NoSuchElementException();
      }
   
      
      return store;
   }


    /**
     * Returns a new Collection containing all the values in the Collection coll
     * that are greater than or equal to low and less than or equal to high, as
     * defined by the Comparator comp. The returned collection must contain only
     * these values and no others. The values low and high themselves do not have
     * to be in coll. Any duplicate values that are in coll must also be in the
     * returned Collection. If no values in coll fall into the specified range or
     * if coll is empty, this method throws a NoSuchElementException. If either
     * coll or comp is null, this method throws an IllegalArgumentException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the range values are selected
     * @param low     the lower bound of the range
     * @param high    the upper bound of the range
     * @param comp    the Comparator that defines the total order on T
     * @return        a Collection of values between low and high
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                                      Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      //create an iterator object
      Iterator<T> itr = coll.iterator();
      List<T> rangeColl = new ArrayList<>();
   
      while (itr.hasNext()) {
         T current = itr.next();
         if (comp.compare(current, low) >= 0 && comp.compare(current, high) <= 0) {
            rangeColl.add(current);                   
         }
      }
      if (rangeColl.isEmpty()) {
         throw new NoSuchElementException();
      }
                                          
       
      return rangeColl;
   }


    /**
     * Returns the smallest value in the Collection coll that is greater than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the ceiling value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the ceiling value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
   
      Iterator<T> itr = coll.iterator();
      List<T> moreThanKey = new ArrayList<>();
   
     //create an array list that includes values of T that are less than T key
      while (itr.hasNext()) {
         T current = itr.next();
         if (comp.compare(current, key) >= 0 ) {
            moreThanKey.add(current);
         }
      }
      //if no values are in lessThanKey throw NoSuchElementException
      if (moreThanKey.isEmpty()) {
         throw new NoSuchElementException("No values less than key");
      }
      //create an iterator for the lessThanKey ArrayList
      Iterator<T> listItr = moreThanKey.iterator();
      
      T ceiling = listItr.next();
      while (listItr.hasNext()) {
         T current = listItr.next();
         if (comp.compare(current, ceiling) <= 0) {
            ceiling = current;
         }
      }
   
      return ceiling;
   }


    /**
     * Returns the largest value in the Collection coll that is less than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the floor value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the floor value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
   
      Iterator<T> itr = coll.iterator();
      List<T> lessThanKey = new ArrayList<>();
   
     //create an array list that includes values of T that are less than T key
      while (itr.hasNext()) {
         T current = itr.next();
         if (comp.compare(current, key) <= 0 ) {
            lessThanKey.add(current);
         }
      }
      //if no values are in lessThanKey throw NoSuchElementException
      if (lessThanKey.isEmpty()) {
         throw new NoSuchElementException("No values less than key");
      }
      //create an iterator for the lessThanKey ArrayList
      Iterator<T> listItr = lessThanKey.iterator();
      
      T floor = listItr.next();
      while (listItr.hasNext()) {
         T current = listItr.next();
         if (comp.compare(current, floor) >= 0) {
            floor = current;
         }
      }
   
   
      return floor;
   }

}
