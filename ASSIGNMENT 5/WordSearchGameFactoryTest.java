import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class WordSearchGameFactoryTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Test public void setUp() {
   
      WordSearchGame game = WordSearchGameFactory.createGame();
      game.loadLexicon("words.txt");
      game.setBoard(new String[]{"E", "E", "C", "A", "A", "L", "E", "P", "H", 
                                  "N", "B", "O", "Q", "T", "T", "Y"});
      System.out.print("LENT is on the board at the following positions: ");
      System.out.println(game.isOnBoard("TOECAP"));
      System.out.print("POPE is not on the board: ");
      System.out.println(game.isOnBoard("POPE"));
      System.out.println("All words of length 6 or more: ");
      System.out.println(game.getAllScorableWords(6));     
   
   }


}
