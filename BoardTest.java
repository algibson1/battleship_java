import org.junit.*;

public class BoardTest {
  Ship cruiser = new Ship("Cruiser", 3);
  Ship submarine = new Ship("Submarine", 2);
  Board board = new Board();

  String[] horizontalValid3Coordinates = new String[] {"A1", "A2", "A3"};
  String[] horizontalValid3Coordinates2 = new String[] {"B2", "C2", "D2"};
  String[] horizontalValid2Coordinates = new String[] {"A1", "A2"};
  String[] horizontalValid2Coordinates2 = new String[] {"C1", "C2"};
  String[] verticalValid3Coordinates = new String[] {"A1", "B1", "C1"};

  String[] invalidCoordinates = new String[] {"A1", "A2", "B8"};
  String[] nonConsecutiveCoordinates1 = new String[] {"A1", "A2", "B2"};
  String[] nonConsecutiveCoordinates2 = new String[] {"A1", "A3"};
  String[] nonConsecutiveCoordinates3 = new String[] {"C1", "B1", "D1"};
  String[] backwardsCoordinates = new String[] {"A3", "A2", "A1"};

  char[] verticalLetters = new char[] {'A', 'B', 'C'}; // chars must be single quotes!
  int[] verticalNumbers = new int[] {1, 1, 1};
  char[] horizontalLetters = new char[] {'A', 'A', 'A'};
  int[] horizontalNumbers = new int[] {1, 2, 3};

  @Test
  public void getCells_ReturnsCells() {
    Assert.assertEquals(16, board.getCells().size());
    Assert.assertEquals("A1", board.getCells().get("A1").getCoordinate());
    Assert.assertEquals("D4", board.getCells().get("D4").getCoordinate());
  }

  @Test
  public void validCoordinate_ReturnsTrueIfExists(){
    Assert.assertEquals(true, board.validCoordinate("A1"));
    Assert.assertEquals(true, board.validCoordinate("B3"));
    Assert.assertEquals(false, board.validCoordinate("B7"));
    Assert.assertEquals(false, board.validCoordinate("E1"));
  }

  @Test
  public void allValidCoordinates_ReturnsTrueIfAllValid(){
    Assert.assertEquals(true, board.allValidCoordinates(horizontalValid3Coordinates));
    Assert.assertEquals(false, board.allValidCoordinates(invalidCoordinates));
  }

  @Test
  public void lengthMatch_ReturnsTrueIfCoordinatesMatchShipLength() {
    Assert.assertEquals(false, board.lengthMatch(cruiser, horizontalValid2Coordinates));
    Assert.assertEquals(true, board.lengthMatch(cruiser, horizontalValid3Coordinates));
  }

  @Test
  public void verticallyConsecutive_ReturnsTrueIfCoordinatesAreVerticallyConsecutive() {
    Assert.assertEquals(true, board.verticallyConsecutive(verticalLetters, verticalNumbers));
    Assert.assertEquals(false, board.verticallyConsecutive(horizontalLetters, horizontalNumbers));
  }

  @Test
  public void horizontallyConsecutive_ReturnsTrueIfCoordinatesAreHorizontallyConsecutive() {
    Assert.assertEquals(false, board.horizontallyConsecutive(verticalLetters, verticalNumbers));
    Assert.assertEquals(true, board.horizontallyConsecutive(horizontalLetters, horizontalNumbers));
  }

  @Test
  public void consecutive_ReturnsTrueIfCoordinatesAreConsecutive() {
    Assert.assertEquals(true, board.consecutive(horizontalValid3Coordinates));
    Assert.assertEquals(true, board.consecutive(horizontalValid2Coordinates));
    Assert.assertEquals(true, board.consecutive(verticalValid3Coordinates));
    Assert.assertEquals(false, board.consecutive(nonConsecutiveCoordinates1));
    Assert.assertEquals(false, board.consecutive(nonConsecutiveCoordinates2));
    Assert.assertEquals(false, board.consecutive(nonConsecutiveCoordinates3));
    Assert.assertEquals(false, board.consecutive(backwardsCoordinates));
  }

  @Test
  public void allUnoccupied_ReturnsTrueIfCoordinatesHaveNoShip() {
    Assert.assertEquals(true, board.allUnoccupied(horizontalValid3Coordinates));
    Assert.assertEquals(true, board.getCells().get("A1").empty());
    board.getCells().get("A1").placeShip(cruiser);
    Assert.assertEquals(false, board.getCells().get("A1").empty());
    Assert.assertEquals(false, board.allUnoccupied(horizontalValid3Coordinates));
  }

  @Test
  public void validPlacement_ReturnsTrueIfCellsAreValidConsecutiveAndUnoccupied() {
    Assert.assertEquals(true, board.validPlacement(cruiser, horizontalValid3Coordinates));
    Assert.assertEquals(true, board.validPlacement(cruiser, horizontalValid3Coordinates2));
    Assert.assertEquals(true, board.validPlacement(submarine, horizontalValid2Coordinates));
    Assert.assertEquals(true, board.validPlacement(submarine, horizontalValid2Coordinates2));

    board.getCells().get("A1").placeShip(cruiser);

    Assert.assertEquals(false, board.validPlacement(cruiser, horizontalValid3Coordinates)); // "A1" is now occupied
    Assert.assertEquals(false, board.validPlacement(cruiser, nonConsecutiveCoordinates3));
    Assert.assertEquals(false, board.validPlacement(submarine, nonConsecutiveCoordinates2));
    Assert.assertEquals(false, board.validPlacement(submarine, horizontalValid3Coordinates2)); // length doesn't match
  }

  @Test
  public void placeShip_PlacesShipOnGivenCoordinatesOnlyIfValidPlacement() {
    board.placeShip(cruiser, nonConsecutiveCoordinates1);
    Assert.assertEquals(true, board.getCells().get("A1").empty());
    board.placeShip(cruiser, horizontalValid3Coordinates);
    Assert.assertEquals(false, board.getCells().get("A2").empty());
  }

  @Test 
  public void render_RendersStringRepresentingBoard() {
    Assert.assertEquals("  1 2 3 4 \nA . . . . \nB . . . . \nC . . . . \nD . . . . \n", board.render());
    Assert.assertEquals("  1 2 3 4 \nA . . . . \nB . . . . \nC . . . . \nD . . . . \n", board.render(true));
    board.placeShip(cruiser, horizontalValid3Coordinates);
    board.placeShip(submarine, horizontalValid2Coordinates2);
    Assert.assertEquals("  1 2 3 4 \nA . . . . \nB . . . . \nC . . . . \nD . . . . \n", board.render());
    Assert.assertEquals("  1 2 3 4 \nA S S S . \nB . . . . \nC S S . . \nD . . . . \n", board.render(true));
    board.getCells().get("A1").fireUpon();
    board.getCells().get("D1").fireUpon();
    Assert.assertEquals("  1 2 3 4 \nA H . . . \nB . . . . \nC . . . . \nD M . . . \n", board.render());
  }
}
