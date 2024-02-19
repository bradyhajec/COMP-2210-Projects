import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   @Test public void minTest() {
      int[] a = {2, 3, 5, 7, 1};
      int result = Selector.min(a);
      int expected = 1;
      assertEquals(expected, result);
   }
   
   @Test public void minTest2() {
      int[] a = {2, 2, 2, 4, 2};
      int result = Selector.min(a);
      int expected = 2;
      assertEquals(expected, result);
   }

   @Test public void minTest3() {
      int[] a = {4, 4, 4};
      int result = Selector.min(a);
      int expected = 4;
      assertEquals(expected, result);
   }
   
   @Test public void minTest4() {
      int[] a = {82, 129, 31, 34, 25};
      int result = Selector.min(a);
      int expected = 25;
      assertEquals(expected, result);
   }

   @Test public void kminTest1() {
      int[] a = {2, 8, 7, 3, 4};
      int k = 1;
      int result = Selector.kmin(a, k);
      int expected = 2;
      assertEquals(expected, result);
   
   }

   @Test public void kminTest2() {
      int[] a = {5, 9, 1, 7, 3};
      int k = 3;
      int result = Selector.kmin(a, k);
      int expected = 5;
      assertEquals(expected, result);
   
   }
   
   @Test public void kminTest3() {
      int[] a = {8, 7, 6, 5, 4};
      int k = 5;
      int result = Selector.kmin(a, k);
      int expected = 8;
      assertEquals(expected, result);
   
   }
   
   @Test public void kminTest4() {
      int[] a = {8, 2, 8, 7, 3, 3, 4};
      int k = 3;
      int result = Selector.kmin(a, k);
      int expected = 4;
      assertEquals(expected, result);
   }
   
   @Test public void kminTest5() {
      int[] a = {8, 8, 8, 8, 8, 8, 4};
      int k = 2;
      int result = Selector.kmin(a, k);
      int expected = 8;
      assertEquals(expected, result);
   }
   
   

   @Test public void kmaxTest1() {
      int[] a = {2, 8, 7, 3, 4};
      int k = 1;
      int result = Selector.kmax(a, k);
      int expected = 8;
      assertEquals(expected, result);
   
   }
   
   @Test public void kmaxTest2() {
      int[] a = {5, 9, 1, 7, 3};
      int k = 3;
      int result = Selector.kmax(a, k);
      int expected = 5;
      assertEquals(expected, result);
   }
   @Test public void kmaxTest3() {
      int[] a = {8, 2, 8, 7, 3, 3, 4};
      int k = 3;
      int result = Selector.kmax(a, k);
      int expected = 4;
      assertEquals(expected, result);
   }
   @Test public void kmaxTest4() {
      int[] array = { 5, 5, 5, 5, 5 }; // All values are the same
        
      assertThrows(IllegalArgumentException.class, 
         () -> {
            Selector.kmax(array, 3);
         });      }
         
   @Test public void kmaxTest5() {
      int[] a = {7};
      int k = 1;
      int result = Selector.kmax(a, k);
      int expected = 7;
      assertEquals(expected, result);
   }


   
   @Test public void rangeTest1() {
      int[] a = {5, 9, 1, 7, 3};
      int low = 3;
      int high = 5;
      int[] result = Selector.range(a, low, high);
      int[] expected = {5, 3};
      assertArrayEquals(expected, result);
   }
   
   @Test public void rangeTest2() {
      int[] a = {8, 2, 8, 7, 3, 3, 4};
      int low = 3;
      int high = 7;
      int[] result = Selector.range(a, low, high);
      int[] expected = {7, 3, 3, 4};
      assertArrayEquals(expected, result);
   }
   
   @Test public void ceilingTest1() {
      int[] a = {8, 2, 8, 7, 3, 3, 4};
      int key = 5;
      int result = Selector.ceiling(a, key);
      int expected = 7;
      assertEquals(expected, result);
   }
   
   @Test public void ceilingTest2() {
      int[] a = {8, 7, 6, 5, 4};
      int key = 0;
      int result = Selector.ceiling(a, key);
      int expected = 4;
      assertEquals(expected, result);
   }
   
   @Test public void ceilingTest3() {
      int[] a = {5, 9, 1, 7, 3};
      int key = 7;
      int result = Selector.ceiling(a, key);
      int expected = 7;
      assertEquals(expected, result);
   }
   @Test public void ceilingTest4() {
      int[] a = {-3, 3, 9, 7, 0};
      int key = 0;
      int result = Selector.ceiling(a, key);
      int expected = 0;
      assertEquals(expected, result);
   }
   
   @Test public void floorTest1() {
      int[] a = {8, 2, 8, 7, 3, 3, 4};
      int key = 5;
      int result = Selector.floor(a, key);
      int expected = 4;
      assertEquals(expected, result);
   }
   
   @Test public void floorTest2() {
      int[] a = {5, 9, 1, 7, 3};
      int key = 1;
      int result = Selector.floor(a, key);
      int expected = 1;
      assertEquals(expected, result);
   }
   
   @Test public void floorTest3() {
      int[] a = {8, 7, 6, 5, 4};
      int key = 9;
      int result = Selector.floor(a, key);
      int expected = 8;
      assertEquals(expected, result);
   }
   
   @Test public void floorTest4() {
      int[] a = {-3, 3, 9, 7, 0};
      int key = 0;
      int result = Selector.floor(a, key);
      int expected = 0;
      assertEquals(expected, result);
   }
   
   @Test public void floorTest5() {
      int[] array = { 7 };
      int key = -11;
   
      assertThrows(IllegalArgumentException.class, 
         () -> {
            Selector.floor(array, key);
         });
   }
   
   @Test public void floorTest6() {
      int[] a = {9, 7};
      int key = 8;
      int result = Selector.floor(a, key);
      int expected = 7;
      assertEquals(expected, result);
   }


   




}