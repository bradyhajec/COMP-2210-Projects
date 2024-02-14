import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Iterator;


import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Your Name (you@auburn.edu)
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   /////////////////////////////////////////////////////////////////////////////
   // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
   // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
   // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
   // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
   // table with chaining).
   /////////////////////////////////////////////////////////////////////////////
   private TreeSet<String> lexicon;
   private static final List<String> EMPTY_LADDER = new ArrayList<String>();
   

   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         //////////////////////////////////////
         // INSTANTIATE lexicon OBJECT HERE  //
         //////////////////////////////////////
         
         //java already implements a red-black tree for TreeSet
         lexicon = new TreeSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            /////////////////////////////////////////////////////////////
            // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
            /////////////////////////////////////////////////////////////
            lexicon.add(str.toLowerCase());
            
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////
   /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount() {
      return lexicon.size();
   }


   /**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
   public boolean isWord(String str) {
      str = str.toLowerCase();
      if (lexicon.contains(str)) {
         return true;
      }
      return false;
   }


   /**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: https://en.wikipedia.org/wiki/Hamming_distance
    *
    * @param  str1 the first string
    * @param  str2 the second string
    * @return      the Hamming distance between str1 and str2 if they are the
    *                  same length, -1 otherwise
    */
   public int getHammingDistance(String str1, String str2) {
      if (str1.length() != str2.length()) {
         return -1;
      }
      
      //distance increments when the characters at a point in the two words are different
      int distance = 0;
      for (int i = 0; i < str1.length(); i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            distance++;
         }
      }
      
      return distance;
   }


   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  word the given word
    * @return      the neighbors of the given word
    */
   public List<String> getNeighbors(String word) {
   
      List<String> neighbors = new ArrayList<String>();
      
   //make sure that the word is valid
      if (!isWord(word)) {
         return neighbors;
      }
   
      Iterator<String> itr = lexicon.iterator();
      
      String str;
      while (itr.hasNext()) {
         str = itr.next(); 
         if (getHammingDistance(word, str) == 1) {
            neighbors.add(str);
         }
      }   
      
      return neighbors;
   }


   /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
   public boolean isWordLadder(List<String> sequence) {
   
      if (sequence == null || sequence.isEmpty()) {
         return false;
      }
   //compare validity of words
   //hamming distance between two words must be 1
      for (int i = 0; i < sequence.size() - 1; i++) {
         String prev = sequence.get(i).toLowerCase();
         String next = sequence.get(i + 1).toLowerCase();
         if (!isWord(prev) || !isWord(next) || getHammingDistance(prev, next) != 1) {
            return false;
         }
      }
      return true;
   }


  /**
   * Returns a minimum-length word ladder from start to end. If multiple
   * minimum-length word ladders exist, no guarantee is made regarding which
   * one is returned. If no word ladder exists, this method returns an empty
   * list.
   *
   * Breadth-first search must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder from start to end
   */
   public List<String> getMinLadder(String start, String end) {
   
      if (!isWord(start) || !isWord(end)) {
         return EMPTY_LADDER;
      }
      
      if (start.length() != end.length()) {
         return EMPTY_LADDER;
      }  
      
      if (start == null || end == null) {
         return EMPTY_LADDER;
      }
      
      String ladderStrt = start.toLowerCase();
      String ladderEd = end.toLowerCase();
   
      //set up a queue
      
      Deque<Node> queue = new ArrayDeque<>(); //will eventually add every word in lexicon to it
      List<String> ladder = new ArrayList<String>();
      
      //if first and last word are same then the minLadder is just that word
      if (ladderStrt == ladderEd) {
         ladder.add(ladderStrt);
         return ladder;
      }  
      
      //prep for BFS
      
      HashSet<String> visited = new HashSet<String>(); //to add visited words as we BFS, HashSet does not allowed same to be visisted twice 
      
      Node startNode = new Node(ladderStrt);
      queue.add(startNode);
      while (!queue.isEmpty()) {
         //look at first node in queue as well as its neighbors
         Node current = queue.removeFirst();
         List<String> neighbors = current.neighbors;
         for (String neighbor : neighbors) { //look at all the neighbors 
            if (!visited.contains(neighbor)) {
               Node neighborN = new Node(neighbor, current); //creating node that points to current node
               visited.add(neighbor);
               queue.add(neighborN); //will eventuall add all neighbors to queue and BFS will check all of these
               if (neighbor.equals(ladderEd)) {
                  return toList(new Node(neighbor, current));
               }
               
            }
         }  
         
      }
   
      return EMPTY_LADDER;
   }
   
   private List<String> toList(Node n) {
      
      List<String> ladder = new ArrayList<String>();
      
      String word = n.word;
      Node prev = n.next;
      ladder.add(word);
      while (prev != null) {
         word = prev.word;
         ladder.add(0, word); //adding word to beginning of the list
         prev = prev.next;
      }  
      
      
     
   
      return ladder;
   }

   //node class
   private class Node {
   
      private String word;
      private Node next;
      private List<String> neighbors;
   
   
      Node(String word) {
         this.word = word;
         this.neighbors = getNeighbors(word);
      }
      
      Node(String word, Node next) {
         this.word = word;
         this.neighbors = getNeighbors(word);
         this.next = next;
      }
      
   }
}

