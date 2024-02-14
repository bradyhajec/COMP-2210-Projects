import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
  
  /*
  *TESTING THE MIN METHOD
  *
  */
 
  
   @Test public void minTest1() {
      List<Integer> integers = new ArrayList<>();
      integers.add(5);
      integers.add(2);
      integers.add(8);
      integers.add(1);
      integers.add(9);
   
      Integer expected = 1;
     //Integer minValue because return type of method will be Integer
      Integer result = Selector.min(integers, Comparator.naturalOrder());
      
      assertEquals(expected, result);
   }
   
   @Test public void minTest2() {
      List<Integer> integers = new ArrayList<>();
      integers.add(8);
      integers.add(2);
      integers.add(8);
      integers.add(7);
      integers.add(3);
      integers.add(3);
      integers.add(4);
      
   
      Integer expected = 2;
     //Integer minValue because return type of method will be Integer
      Integer result = Selector.min(integers, Comparator.naturalOrder());
      
      assertEquals(expected, result);
   }
  
   @Test public void minTest3() {
      List<Integer> integers = new ArrayList<>();
      integers.add(8);
      integers.add(7);
      integers.add(6);
      integers.add(5);
      integers.add(4);
   
      Integer expected = 4;
     //Integer minValue because return type of method will be Integer
      Integer result = Selector.min(integers, Comparator.naturalOrder());
      
      assertEquals(expected, result);
   }
   
   
  /*
  *TESTING THE MAX METHOD
  *
  */
  
   
   @Test public void maxTest1() {
      List<Integer> integers = new ArrayList<>();
      integers.add(5);
      integers.add(2);
      integers.add(8);
      integers.add(1);
      integers.add(9);
   
      Integer expected = 9;
     //Integer minValue because return type of method will be Integer
      Integer result = Selector.max(integers, Comparator.naturalOrder());
      
      assertEquals(expected, result);
   }
   
   @Test public void maxTest2() {
      List<Integer> integers = new ArrayList<>();
      integers.add(8);
      integers.add(2);
      integers.add(8);
      integers.add(7);
      integers.add(3);
      integers.add(3);
      integers.add(4);
      
   
      Integer expected = 8;
     //Integer minValue because return type of method will be Integer
      Integer result = Selector.max(integers, Comparator.naturalOrder());
      
      assertEquals(expected, result);
   }
   @Test public void maxTest3() {
      List<Integer> integers = new ArrayList<>();
      integers.add(8);
      integers.add(7);
      integers.add(6);
      integers.add(5);
      integers.add(4);
   
      Integer expected = 8;
     //Integer minValue because return type of method will be Integer
      Integer result = Selector.max(integers, Comparator.naturalOrder());
      
      assertEquals(expected, result);
   }

   
   @Test public void kminxTest1() {
      List<Integer> integers = new ArrayList<>();
      integers.add(8);
      integers.add(2);
      integers.add(8);
      integers.add(7);
      integers.add(3);
      integers.add(3);
      integers.add(4);
   
        // k = 3 should return the 3rd distinct minimum element, which is 4
      Integer expected = 4;
      Integer result = Selector.kmin(integers, 3, Comparator.naturalOrder());
   
      assertEquals(expected, result);     
   }
   @Test public void kminxTest2() {
      List<Integer> integers = new ArrayList<>();
      integers.add(8);
      integers.add(7);
      integers.add(6);
      integers.add(5);
      integers.add(4);
   
      Integer expected = 8;
      Integer result = Selector.kmin(integers, 5, Comparator.naturalOrder());
   
      assertEquals(expected, result);     
   }
   @Test public void kminxTest3() {
      List<Integer> integers = new ArrayList<>();
      integers.add(8);
      integers.add(8);
      integers.add(8);
      integers.add(8);
      integers.add(8);
   
      Integer expected = 8;
      Integer result = Selector.kmin(integers, 1, Comparator.naturalOrder());
      assertEquals(expected, result);
   }
        
   @Test public void kmaxTest1() {
      List<Integer> integers = new ArrayList<>();
      integers.add(8);
      integers.add(2);
      integers.add(8);
      integers.add(7);
      integers.add(3);
      integers.add(3);
      integers.add(4);
   
        // k = 3 should return the 3rd distinct minimum element, which is 4
      Integer expected = 4;
      Integer result = Selector.kmax(integers, 3, Comparator.naturalOrder());
   
      assertEquals(expected, result);     
   }
   /*
   @Test public void kmaxTest2() {
      List<Integer> integers = new ArrayList<>();
      integers.add(2);
      integers.add(2);
      integers.add(2);
      integers.add(2);
      integers.add(2);
      integers.add(4);
      integers.add(2);
   
        // k = 3 should return the 3rd distinct minimum element, which is 4
      assertThrows(IllegalArgumentException.class, 
         () -> {
            Selector.kmax(integers, 3, Comparator.naturalOrder());
         });     
   }
   */
   @Test public void kmaxTest3() {
      List<Integer> integers = new ArrayList<>();
      integers.add(5);
      integers.add(7);
   
      Integer expected = 7;
      Integer result = Selector.kmax(integers, 1, Comparator.naturalOrder());
   
      assertEquals(expected, result);     
   }
   @Test public void kmaxTest4() {
      List<Integer> integers = new ArrayList<>();
      integers.add(8);
      integers.add(7);
      integers.add(6);
      integers.add(5);
      integers.add(4);
   
   
      Integer expected = 4;
      Integer result = Selector.kmax(integers, 5, Comparator.naturalOrder());
   
      assertEquals(expected, result);     
   }
   @Test public void kmaxTest5() {
      List<Integer> integers = new ArrayList<>();
      integers.add(-2147483648);
      
      Integer expected = 4;
      Integer result = Selector.kmax(integers, 2, Comparator.naturalOrder());
   
      assertEquals(expected, result);     
   }


   
   @Test public void rangeTest1() {
      List<Integer> integers = new ArrayList<>();
      integers.add(8);
      integers.add(2);
      integers.add(8);
      integers.add(7);
      integers.add(3);
      integers.add(3);
      integers.add(4);
   
      Integer low = 3;
      Integer high = 7;
      List<Integer> arrayExpected = new ArrayList<>();
      arrayExpected.add(7);
      arrayExpected.add(3);
      arrayExpected.add(3);
      arrayExpected.add(4);
      
      //creates a new list that returns and ArrayList from the method range
      List<Integer> result = new ArrayList<>(Selector.<Integer>range(integers, low, high, Comparator.naturalOrder()));
        
      assertEquals(arrayExpected, result);
   }
   
   /*
   //test one of the exceptions meant to fail
   @Test public void rangeTest2() {
      List<Integer> integers = new ArrayList<>();
      integers.add(8);
      integers.add(2);
      integers.add(8);
      integers.add(7);
      integers.add(3);
      integers.add(3);
      integers.add(4);
   
      Integer low = 12;
      Integer high = 20;
   
      Selector.<Integer>range(integers, low, high, Comparator.naturalOrder());
   
   }
   */
   
   /*
   *FLOOR TEST CASES
   *
   */
   
   @Test public void floorTest1() {
      List<Integer> integers = new ArrayList<>();
      integers.add(8);
      integers.add(2);
      integers.add(8);
      integers.add(7);
      integers.add(3);
      integers.add(3);
      integers.add(4);
   
      Integer key = 5;
      Integer expected = 4;
      
      Integer result =  Selector.floor(integers, key, Comparator.naturalOrder());
      
      assertEquals(expected, result);
   
   }
   @Test public void floorTest2() {
      List<Integer> integers = new ArrayList<>();
      integers.add(8);
      integers.add(7);
      integers.add(6);
      integers.add(5);
      integers.add(4);
   
      Integer key = 9;
      Integer expected = 8;
      
      Integer result =  Selector.floor(integers, key, Comparator.naturalOrder());
      
      assertEquals(expected, result);
   
   }
   @Test public void floorTest3() {
      List<Integer> integers = new ArrayList<>();
      integers.add(5);
      integers.add(9);
      integers.add(1);
      integers.add(7);
      integers.add(3);
   
      Integer key = 1;
      Integer expected = 1;
      
      Integer result =  Selector.floor(integers, key, Comparator.naturalOrder());
      
      assertEquals(expected, result);
   
   }
   //meant to fail
   /*
   @Test public void floorTest4() {
      List<Integer> integers = new ArrayList<>();
      integers.add(9);
      integers.add(8);
      integers.add(7);
      integers.add(6);
      integers.add(5);
   
      Integer key = 4;
      
      Selector.floor(integers, key, Comparator.naturalOrder());
      
   
   }*/
   
   //TEST MOTHODS FOR FAILED TEST
   
   @Test public void kminxTest11() {
      List<Integer> integers = new ArrayList<>();
      integers.add(1);
      integers.add(3);
      integers.add(5);
      integers.add(7);
      integers.add(9);
               
      Integer expected = 3;
      Integer result = Selector.kmin(integers, 2, Comparator.naturalOrder());
   
      assertEquals(expected, result);     
   }

   






}
