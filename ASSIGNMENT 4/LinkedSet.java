import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Brady Hajec (bdh0059@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
   
      if (element == null) {
         return false;
      }
      if (contains(element)) {
         return false;
      }
              
      //implement add in using natural order so must iterate through the linked list.
      //first node if bag is empty
      if (isEmpty()) {
        //made node for first element (use linked structure ppt)
         Node n = new Node(element);
         front = n;
         rear = n;
         size++;
         return true;
      }
      
      //element being added
      Node newNode = new Node(element);
      //new node to traverse list
      Node currentNode = front;
      //node set equal to the rear node to check
      Node rearNode = rear;
      
      //traverse list until it finds that the currentNode is greater than newNode, then insert newNode before it
      while (currentNode != null && newNode.element.compareTo(currentNode.element) > 0) {
         currentNode = currentNode.next;
      }  
      //add to front
      if (currentNode == front) {
         //point the new node to the node that was orginally in front
         newNode.next = front;
         //change the orginally front to now point at the newNode
         if (front != null) {
            front.prev = newNode;
         }
         //need to make front the new front is the LinkedSet.
         front = newNode;
         
         size++;
         return true;
      } 
      //insert at rear
      else if (currentNode == null) {
         
         //last in list so nothing after it
         newNode.next = null;
         //do not traverse till end assuming last is updated in other methods when elements addeed to added of the list.
         //have orginally rear node point to newNode
         if (rear != null) {
            rear.next = newNode; 
         }
      
         //point new node to orginally rear
         newNode.prev = rear;
         rear = newNode;
         
         size++;
         return true; 
      }     
      //add somewhere in the middle
      else {       
         //set newNodes previos to what was before currentNode beforehand
         newNode.prev = currentNode.prev;
            
         //set the currentNode's previos to the newNode
         currentNode.prev = newNode;
            
         //tell that the current node is after the newNode
         newNode.next = currentNode;
            
            //change the pointer in front of newNode to point at it
         newNode.prev.next = newNode;
            
         size++;
         return true;
      }
               
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
      if (element == null) {
         return false;
      }
      if (isEmpty() || !contains(element)) {
         return false;
      }
      
   //find element in the linkedSet
      Node find = front;
      
      while (find != null && !find.element.equals(element)) {
         find = find.next;
      }
      //case for if its at the front
      if (find == front) {
         if (size == 1) {
            front = null;
            rear = null;
            size--;
            return true;
         }
         //set new front to second item in list to remove the orginal first
         front = front.next;
         front.prev = null;
         
         size--;
         return true;
      }
      //case for if its at the end
      else if (find == rear) {
         //set node before rear and new rear
         rear = rear.prev;
         //set the prev pointer of orgional last node to null
         rear.next.prev = null;
         //set new rears next to null
         rear.next = null;
         size--;
         return true;
      }
      //case for if its in the middle
      else {
        //set prev pointer of node after element to remove to element before find
         find.next.prev = find.prev;
         //set pointer of node in front of element to point to one after
         find.prev.next = find.next;
         
      
         size--;
         return true;
      }
   
   }


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
    //use locate method from slides
    
      if (element == null) {
         return false;
      }
    
      Node n = front;
      while (n != null) {
         if (n.element.equals(element)) {
            return true;
         } 
         //move n to the next value
         n = n.next; 
      }
      return false;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
   
      if (s == null) {
         return false;
      }
   
      if (size != s.size()) {
         return false;
      }
      
      //use contains method 
      for (T element : s) {
         if (element == null || !this.contains(element)) {
            return false;
         }
      }
      
      return true;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      if (s == null) {
         return false;
      }
   
      if (size != s.size()) {
         return false;
      }
      
      //use iterator because linkedSets will have natrual ascending order
      Iterator<T> thisIterator = iterator();
      Iterator<T> sIterator = s.iterator();
      
      while (thisIterator.hasNext() && sIterator.hasNext()) {
         T thisElement = thisIterator.next();
         T sElement = sIterator.next();
         //check nullity then check that this LinkedSet and S contain the same element
         if (thisElement == null || !thisElement.equals(sElement)) {
            return false;
         }
      }
      
      return !thisIterator.hasNext() && !sIterator.hasNext();
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s){
   
   
      Set<T> newSet = new LinkedSet<T>();
   
      //creating an iterator for this LinkedSet
      Iterator<T> iterator = iterator();
      //add elements from this set to paramater set
      while (iterator.hasNext()) {
         T element = iterator.next();
         newSet.add(element);
      }
      //add elements from parameter set to newSet
      for (T element : s) {
         newSet.add(element);
      }  
   
      return newSet;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s){
      Set<T> newSet = new LinkedSet<T>();
   
      Iterator<T> iterator = iterator();
      Iterator<T> siterator = s.iterator();
   
      //use conditional operator to set elements depending on if they have the next value;
      T thisElement = iterator.hasNext() ? iterator.next() : null;
      T sElement = siterator.hasNext() ? siterator.next() : null;
   
      //add to list if at least one of them is not null
      while (thisElement != null || sElement != null) {
         //add
         if (thisElement != null && sElement != null) {
            newSet.add(thisElement);
            newSet.add(sElement); 
            thisElement = iterator.hasNext() ? iterator.next() : null;
            sElement = siterator.hasNext() ? siterator.next() : null;
         }
         else if (thisElement != null) {
            newSet.add(thisElement);
            thisElement = iterator.hasNext() ? iterator.next() : null;
           
         }
         else {
            newSet.add(sElement); 
            sElement = siterator.hasNext() ? siterator.next() : null;  
         }
      }
   
      return newSet;
   }


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
   
      Set<T> newSet = new LinkedSet<T>();
      
      for (T element : s) {
         if (this.contains(element)) {
            newSet.add(element);
         }
      }  
   
      return newSet;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
      Set<T> newSet = new LinkedSet<T>();
      
      for (T element : s) {
         if (this.contains(element)) {
            newSet.add(element);
         }
      }  
      return newSet;
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
   
      //creating a LinkedSet
      Set<T> complementSet = new LinkedSet<T>();
   
   //iterate through elements in the set
      for (T element : this) {
         if (!s.contains(element)) {
            complementSet.add(element);
         }
      }
      return complementSet;    
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
    //creating a LinkedSet
      Set<T> complementSet = new LinkedSet<T>();
   
   //iterate through elements in the set
      for (T element : this) {
         if (!s.contains(element)) {
            complementSet.add(element);
         }
      }
      return complementSet;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      return new LinkedIterator();
   }
   
   private class LinkedIterator implements Iterator<T> {
      private Node current = front;
      
      public boolean hasNext() {
         return current != null;
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         T result = current.element;
         current = current.next;
         return result;
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return new LinkedIteratorDescending();
   }
   
   private class LinkedIteratorDescending implements Iterator<T> {
      private Node current = rear;
      
      public boolean hasNext() {
         return current != null;
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         T result = current.element;
         current = current.prev;
         return result;
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
   
   }



   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      return new LinkedPowerIterator(front, size);
   }
   
   //new class for power iterator
   private class LinkedPowerIterator implements Iterator<Set<T>> {
     
      private Node current;
      private int currentIndex;
      private int size1;
     
      public LinkedPowerIterator(Node front, int size) {
         current = front;
         size1 = size;
         currentIndex = 0;  
      }
     
     
      public boolean hasNext() {
         if (currentIndex == 0) {
            return true;
         }
         return ((currentIndex < (int) Math.pow(2, size1)) && current != null);
      }
      
      public Set<T> next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         Set<T> newSet = new LinkedSet<T>();
         if (currentIndex == 0) {
            currentIndex++;
            return newSet;
         }
         String binary = Integer.toBinaryString(currentIndex);
         
         for (int i = binary.length() - 1; i >= 0; i--) {
            
            if (binary.charAt(i) == '1') {
               newSet.add(current.element);
            }
            current = current.next;
         }
         currentIndex++;
         current = front;
         return newSet;
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
   
   }



   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   // Feel free to add as many private methods as you need.

   ////////////////////
   // Nested classes //
   ////////////////////

   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}
