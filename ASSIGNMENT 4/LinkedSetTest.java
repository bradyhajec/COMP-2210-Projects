import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class LinkedSetTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }




  //test the ADD method 
   @Test public void addTest() {
      LinkedSet<Integer> set = new LinkedSet<Integer>();
      
      assertTrue(set.add(6)); // Adding the first element
      assertTrue(set.add(4));
      assertTrue(set.add(3));
      assertTrue(set.add(9));   
      assertTrue(set.add(2));  
      assertTrue(set.add(1));
      assertTrue(set.add(15));   
   
      assertFalse(set.add(3));
            
   }
   
   //testing remove use canvas 1
   @Test public void removeTest() { 
      LinkedSet<Integer> set = new LinkedSet<Integer>();
      
      assertTrue(set.add(6)); // Adding the first element
      assertTrue(set.add(4));
      assertTrue(set.add(3));
      assertTrue(set.add(9));   
      assertTrue(set.add(2));  
      assertTrue(set.add(1));
      assertTrue(set.add(15));   
      
      assertTrue(set.remove(6)); // Adding the first element
      assertTrue(set.remove(4));
      assertTrue(set.remove(3));
      assertTrue(set.remove(9));   
      assertTrue(set.remove(2));  
      assertTrue(set.remove(1));
      assertTrue(set.remove(15));
   }
   //testing union use canvas 2
   @Test public void testUnionWithSet() {
      LinkedSet<Integer> set1 = new LinkedSet<>();
      set1.add(1);
      set1.add(12);
      set1.add(3);
   
      LinkedSet<Integer> set2 = new LinkedSet<>();
      set2.add(3);
      set2.add(4);
      set2.add(5);
      set2.add(2);
      set2.add(9);  
      //use canvas to check operation
      Set<Integer> result = set1.union(set2);
   
   }
   //testing intersection use canvas 3
   @Test public void testIntersecetion() {
      LinkedSet<Integer> set1 = new LinkedSet<>();
      set1.add(1);
      set1.add(12);
      set1.add(3);
      set1.add(8);
   
      
   
      LinkedSet<Integer> set2 = new LinkedSet<>();
      set2.add(3);
      set2.add(1);
      set2.add(5);
      set2.add(12);
      set2.add(9);  
      //use canvas to check operation
      Set<Integer> result = set1.intersection(set2);
   
   }
   //testing complement 
   @Test public void testComplement() {
      LinkedSet<Integer> set1 = new LinkedSet<>();
      set1.add(1);
      set1.add(12);
      set1.add(3);
      set1.add(8);
   
      
   
      LinkedSet<Integer> set2 = new LinkedSet<>();
      set2.add(3);
      set2.add(1);
      set2.add(5);
      set2.add(12);
      set2.add(9);  
      //use canvas to check operation
      Set<Integer> result = set1.complement(set2);
   
   }



  /*
   Iterator<T> iterator = iterator();
      //add elements from this set to paramater set
      while (iterator.hasNext()) {
         T element = iterator.next();
         newSet.add(element);
      }
      //add elements from parameter set to newSet
      Iterator<T> siterator = s.iterator();
      //add elements from this set to paramater set
      while (siterator.hasNext()) {
         T element = siterator.next();
         newSet.add(element);
      }
      */
}
