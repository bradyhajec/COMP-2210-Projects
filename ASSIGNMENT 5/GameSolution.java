import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Arrays;




//my own class that will implement the WordSearchGame interface
public class GameSolution implements WordSearchGame {

//instance fields
   private String[][] twoBoard; //a 2d array storing the gameboard
   private TreeSet<String> lexcion; //list of words to search for on the gameboard
   private SortedSet<String> words; 
   private int dimension; //dimension of the twoBoard
   private SortedSet<String> scorableWords; //SortedSet of scorable words
   
   //constructor
   public GameSolution() {
   //set a defualt board
      lexcion = null;
      
      dimension = 4;
      twoBoard = new String[dimension][dimension];
      twoBoard[0][0] = "E"; 
      twoBoard[0][1] = "E"; 
      twoBoard[0][2] = "C"; 
      twoBoard[0][3] = "A"; 
      twoBoard[1][0] = "A"; 
      twoBoard[1][1] = "L"; 
      twoBoard[1][2] = "E"; 
      twoBoard[1][3] = "P"; 
      twoBoard[2][0] = "H"; 
      twoBoard[2][1] = "N"; 
      twoBoard[2][2] = "B"; 
      twoBoard[2][3] = "O"; 
      twoBoard[3][0] = "Q"; 
      twoBoard[3][1] = "T"; 
      twoBoard[3][2] = "T"; 
      twoBoard[3][3] = "Y";
   
   }
   /**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException();
      }  
      //try catch to see if file can be opened
      try {
      //create a TreeSet data structure that adds values without regard to case
         this.lexcion = new TreeSet<String>();
      //create scanner to read in values from file
         Scanner scan = new Scanner(new File(fileName));
         while (scan.hasNext()) {
            lexcion.add(scan.next().toUpperCase());
         }  
         scan.close();
      }
      catch (FileNotFoundException e) {
         throw new IllegalArgumentException();
      }  
   }
   
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      //checking if its square
      int length = letterArray.length;
      dimension = (int) Math.sqrt(length);
      if (Math.pow(dimension, 2) != length) {
         throw new IllegalArgumentException();
      }
      //store in 2d array
      twoBoard = new String[dimension][dimension];
      int i = 0;
      for (int j = 0; j < dimension; j++) {
         for (int k = 0; k < dimension; k++) {
            twoBoard[j][k] = letterArray[i++];
         }
      }
         
   }
   
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    */
   public String getBoard() {
   
      String board = "";
   
      for (int i = 0; i < dimension; i++) {
         board += "\n|";
         for (int j = 0; j < dimension; j++) {
            board += twoBoard[i][j] + " "; 
             
         }
         board += "|";
      }
   
      return board;
   }
   
   /**
    * Retrieves all scorable words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public SortedSet<String> getAllScorableWords(int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (lexcion == null) {
         throw new IllegalStateException();
      }
      
      scorableWords = new TreeSet<String>();
      LinkedList<Integer> letterIndex = new LinkedList<Integer>();
      
      /*
      *for loop goes through each letter on board, adds it to a list, then the list 
      *is sent to toWord method where all words in the list are combined into one string,
      *then that string is checked to see if it is a valid world on the lexcion, then as its
      *being build it is checked if its a valid prefix as well
      */
      for (int i = 0; i < (dimension * dimension); i++) {
         letterIndex.add(i);
         if (isValidWord(toWord(letterIndex)) 
            && toWord(letterIndex).length() >= minimumWordLength) {
            scorableWords.add(toWord(letterIndex));
         }
         if (isValidPrefix(toWord(letterIndex))) {
            wordBoardSearch(letterIndex, minimumWordLength);
         }
         letterIndex.clear();
      }
      
      return scorableWords;
   }  
   
   
   //DFS FOR SEARCHING THE BOARD FOR WORDS AND RETURNING THE SCORABLE WORDS
   
   private LinkedList<Integer> wordBoardSearch(LinkedList<Integer> letterIndex,
      int min) {
      //array of adjacent letters, adjacent method takes
      Position[] adjArray = new Position(letterIndex.getLast()).adjacent(letterIndex);
      for (Position p : adjArray) {
         if (p ==  null) {
            break;
         }
         //add an adjacent value to the array then if its still not a word recursivly call method again
         letterIndex.add(p.getIndex());
         if (isValidPrefix(toWord(letterIndex))) {
            if (isValidWord(toWord(letterIndex)) && toWord(letterIndex).length() >= min) {
               scorableWords.add(toWord(letterIndex));
            }
            wordBoardSearch(letterIndex, min);
         }
         else {
            letterIndex.removeLast();
         }
      }
      letterIndex.removeLast();
      
      return letterIndex;
   }
   
 /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */  
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }  
      if (lexcion == null) {
         throw new IllegalStateException();
      }  
      int score = 0;
      Iterator<String> itr = words.iterator();
      while (itr.hasNext()) {
         String word = itr.next();
         //if isOnBoard is empty that means there are no matching words on the board so there would be no score
         if (word.length() >= minimumWordLength && isValidWord(word) 
            && !isOnBoard(word).isEmpty()) {
            score += (word.length() - minimumWordLength) + 1;
         }
      }  
   
      return score;
   }
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {
      if (lexcion == null) {
         throw new IllegalStateException();
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      
      return lexcion.contains(wordToCheck);
   }
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck){
      if (lexcion == null) {
         throw new IllegalStateException();
      }
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
   
      String pre = lexcion.ceiling(prefixToCheck);
      if (pre != null) {
         return pre.startsWith(prefixToCheck);
      }
      return false;      }  
       
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lexcion == null) {
         throw new IllegalStateException();
      }
      //temporary container for building the path
      LinkedList<Integer> maintainPath = new LinkedList<Integer>();
      //calls DFS method to find the final path and return a linked list, starts with position 0
      List<Integer> path = boardSearch(wordToCheck, maintainPath, 0);
               
      return path;
   }

//DFS FOR BOARD WITH BACKTRACKING AND RETURNING THE PATH OF SCORABLE WORDS

   private LinkedList<Integer> boardSearch(String wordToCheck, 
      LinkedList<Integer> listProgress, int positionIndex) {
      //checks to make sure list is not empty and that the 
      //characters in the list dont already equal the word to check
      if (listProgress.size() > 0 && !wordToCheck.equals(toWord(listProgress))) {
      //make an array full of objects adjacent to the current position
         Position[] adjArray = new Position(positionIndex).adjacent(listProgress);
         //exit if p is null
         for (Position p : adjArray) {
            if (p == null) {
               break;
            }
            //add next adjacent letter to lisProgress then check again to see if it now equals the word
            //if it does it will break and then return listProgress
            listProgress.add(p.getIndex());
            if (wordToCheck.equals(toWord(listProgress))) {
               break;
            }
            //if listProgress letters make up the start of the word necesary to check
            //call method again recursivly 
            if (wordToCheck.startsWith(toWord(listProgress))) {
               //recursivly search again adding the next letter in the path to listProgress
               //and then starting the search at that index
               boardSearch(wordToCheck, listProgress, p.getIndex());
            }  
            else {
               //remove the element added as it is not going to equal the desired path
               listProgress.removeLast();
            }
         }
         
      } 
      //if the progress to find the word is still 0 meaning no letters have been added to listProgress
      if (listProgress.size() == 0) {
         while (positionIndex < (dimension * dimension)) {
            //if first letter on board is the same as first letter in the wordToCheck
            if (wordToCheck.startsWith(new Position(positionIndex).getLetter())) {
               listProgress.add(positionIndex);
               boardSearch(wordToCheck, listProgress, positionIndex);
            }
            positionIndex++;
         }
         return listProgress;
      
      }
      if (toWord(listProgress).equals(wordToCheck)) {
         return listProgress;
      }
      //remove last added and try a different path
      else {
         listProgress.removeLast();
         return listProgress;
      }
   }




//method that takes a list on ints and returns its corresponding word
   public String toWord(LinkedList<Integer> listIn) {
      String word = "";
      for (int i : listIn) {
         //create postion object for letter
         word += new Position(i).getLetter();
      }  
      return word;
   }

//class for positions on the gameboard
   private class Position {
   //position of board
      private int x;
      private int y;
      //row col index position
      private int index;
      private String letter;
      private static final int MAX_ADJ = 8;
   
      //constructor finding elements position using only one index
      Position (int indexIn) {
         this.index = indexIn;
         if (index == 0) {
            this.x = 0;
            this.y = 0;
         }
         else {
            this.x = index % dimension;
            this.y = index / dimension;
         }
         //MADE CORRECTION OF ORDER OF X AND Y HERE
         this.letter = twoBoard[y][x];
       
      }
      Position (int xIn, int yIn) {
         this.x = xIn;
         this.y = yIn;
         this.letter = twoBoard[y][x];
         this.index = (y * dimension) + x;
      
      }  
   
   //finds all the valid adjacent positions on the gameboard
   /*when adjacent is called a list of already visited positions is provided
   *and the method finds a new value to be visited that is not in the list
   */
      public Position[] adjacent(LinkedList<Integer> visited) {
         Position[] adj = new Position[MAX_ADJ];
         int count = 0;
      //generate all eight neighrbor positions
      //add to return value if valid 
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               if (!((i == 0) && (j == 0))) {
               //create a position object for an adjacent value
                  if (isValid(x + i, y + j) && !visited.contains(((y + j) * dimension) + (x + i))) {
                  //add neighbor to the set if its a valid letter
                     Position p = new Position(x + i, y + j);
                     adj[count++] = p;
                  } 
               }
            }  
         }
      //return an array of the valid adjacent letters
         return Arrays.copyOf(adj, count);
      }
      //check if this position is valid in the search area
      private boolean isValid(int x, int y) {
         if (x >= 0 && x < dimension && y >= 0 && y < dimension) {
            return true;
         }
         return false;
      }
      //get letter
      public String getLetter() {
         return letter;
      }
      //get index
      public int getIndex() {
         return index;
      }
   }
}