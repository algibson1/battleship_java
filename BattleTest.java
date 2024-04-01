import org.junit.*;

public class BattleTest {
  Battle battle = new Battle();
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
    // Run method
    // Prove that two ships are on the computer's board (5 cells are occupied)
  }

  // Break down placeComputerShips into several helper methods later,
  // test each

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