import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.*;

public class BattleTest {
  Battle battle = new Battle();
  HashMap<String, Cell> computerBoardCells = battle.computerBoard.getCells();
  String[] computerBoardCoordinates = battle.computerBoard.getCoordinates();
  String[] horizontalCoords1 = new String[] {"A1", "A2", "A3"};
  String[] horizontalCoords2 = new String[] {"B3", "B4"};
  String[] vertCoords1 = new String[] {"A1", "B1", "C1"};
  String[] vertCoords2 = new String[] {"B3", "C3"};

  // start() cannot be tested because it has no return value;
  // It prints things to terminal

  // gameSetup() cannot be tested because it is a void method
  // it prints things to terminal and calls other methods

  @Test
  public void placeComputerShips_PlacesShipsOnComputerBoard() {
    // first prove that the computer's board is blank (all cells empty)
    for (String coordinate : computerBoardCoordinates ) {
      Cell cell = computerBoardCells.get(coordinate);
      Assert.assertEquals(cell.empty(), true);
    }
    // // Run method
    battle.placeComputerShips();
    // Prove that two ships are on the computer's board (5 cells are occupied)
    int occupiedCells = 0;
    for (String coordinate : computerBoardCoordinates ) {
      Cell cell = computerBoardCells.get(coordinate);
      if (!cell.empty()) {
        occupiedCells += 1;
      }
    }
    Assert.assertEquals(5, occupiedCells);
  }

  // Break down placeComputerShips into several helper methods later,
  // test each

  @Test 
  public void getHorizontalStarter_ReturnsACoordinateToStartAt() {
    String[] validHorizontalCruiserStarters = new String[] {"A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2"};
    String[] validHorizontalSubStarters = new String[] {"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3", "D1", "D2", "D3"};

    // Randomly select a starter using the method,
    // and assert at least ten times
    // That it always returns as a valid coordinate
    for (int i = 0; i < 10; i++) {
      String cruiserStarter = battle.getHorizontalStarter(battle.computerShips[0]);
      String subStarter = battle.getHorizontalStarter(battle.computerShips[1]);
      Assert.assertTrue(Arrays.asList(validHorizontalCruiserStarters).contains(cruiserStarter));
      Assert.assertTrue(Arrays.asList(validHorizontalSubStarters).contains(subStarter));
    }
  }

  @Test
  public void getVerticalStarter_ReturnsACoordinateToStartAt() {
    String[] validVerticalCruiserStarters = new String[] {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4"};
    String[] validVerticalSubStarters = new String[] {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "C1", "C2", "C3", "C4"};

    for (int i = 0; i < 10; i++) {
      String cruiserStarter = battle.getVerticalStarter(battle.computerShips[0]);
      String subStarter = battle.getVerticalStarter(battle.computerShips[1]);
      Assert.assertTrue(Arrays.asList(validVerticalCruiserStarters).contains(cruiserStarter));
      Assert.assertTrue(Arrays.asList(validVerticalSubStarters).contains(subStarter));
    }
  }

  @Test
  public void takeHorizontalCoordinates_FindsValidHorizontalCoordinatesToPlaceShip() {
    Assert.assertArrayEquals(horizontalCoords1, battle.takeHorizontalCoordinates("A1", 3));
    Assert.assertArrayEquals(horizontalCoords2, battle.takeHorizontalCoordinates("B3", 2));
  }

  @Test
  public void takeVerticalCoordinates_FindsValidVerticalCoordinatesToPlaceShip() {
    Assert.assertArrayEquals(vertCoords1, battle.takeVerticalCoordinates("A1", 3));
    Assert.assertArrayEquals(vertCoords2, battle.takeVerticalCoordinates("B3", 2));
  }

  // @Test
  // public void placeUserShips_PlacesUserShips() {
  //   // find a way to test a method that needs user input?
  // }
}