import org.junit.*;

public class BoardTest {
  @Test
  public void board_test() {
    Ship cruiser = new Ship("Cruiser", 3);
    // Ship submarine = new Ship("Submarine", 2);
    Board board = new Board();


    Assert.assertEquals(16, board.cells().length);
    Assert.assertEquals("A1", board.cells()[0].coordinate);
    Assert.assertEquals("D4", board.cells()[15].coordinate);

    Assert.assertEquals(true, board.valid_coordinate("A1"));
    Assert.assertEquals(true, board.valid_coordinate("B3"));
    Assert.assertEquals(false, board.valid_coordinate("B7"));
    Assert.assertEquals(false, board.valid_coordinate("E1"));

    String[] valid_coords = new String[] {"A1", "A2", "A3"};
    String[] invalid_coords = new String[] {"A1", "A2", "B8"};
    Assert.assertEquals(true, board.all_valid_coordinates(valid_coords));
    Assert.assertEquals(false, board.all_valid_coordinates(invalid_coords));

    String[] valid_cruiser_coords = new String[] {"A1", "A2", "A3"};
    String[] invalid_cruiser_coords = new String[] {"A1", "A2"};
    Assert.assertEquals(true, board.length_match(cruiser, valid_cruiser_coords));
    Assert.assertEquals(false, board.length_match(cruiser, invalid_cruiser_coords));

    // String[] consecutive_coords = new String[] {"A1", "A2", "A3"};
    // String[] non_consecutive_coords = new String[] {"A1", "A2", "B2"};
    // Assert.assertEquals(true, board.consecutive(consecutive_coords));
    // Assert.assertEquals(false, board.consecutive(non_consecutive_coords));
  }
}
