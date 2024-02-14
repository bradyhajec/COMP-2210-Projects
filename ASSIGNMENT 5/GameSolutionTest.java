import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class GameSolutionTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   
   @Test public void boardMethods1() {
      GameSolution game = new GameSolution();
      game.setBoard(new String[]{"E", "E", "C", "A", "A", "L", "E", "P", "H", 
                                  "N", "B", "O", "Q", "T", "T", "Y", "W", "Z"});
   }
   @Test public void loadLexcion1() {
      GameSolution game = new GameSolution();
      game.loadLexicon("words_small.txt");
   
   }
   @Test public void isValidPrefixTest1() {
      GameSolution game = new GameSolution();
      game.loadLexicon("testWords.txt");
      game.isValidPrefix("ea");
   }
   @Test public void position()  {
      GameSolution game = new GameSolution();
      game.loadLexicon("testWords.txt");
      
      game.isOnBoard("ABA");
   }
   
   




}
