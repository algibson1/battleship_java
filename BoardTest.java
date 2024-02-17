import org.junit.*;

public class BoardTest {
  @Test
  public void board_test() {
    Ship cruiser = new Ship("Cruiser", 3);
    Ship submarine = new Ship("Submarine", 2);
    Board board = new Board();

    Assert.assertEquals(16, board.cells().size());
    Assert.assertEquals("A1", board.cells().get("A1").coordinate);
    Assert.assertEquals("D4", board.cells().get("D4").coordinate);

    Assert.assertEquals(true, board.valid_coordinate("A1"));
    Assert.assertEquals(true, board.valid_coordinate("B3"));
    Assert.assertEquals(false, board.valid_coordinate("B7"));
    Assert.assertEquals(false, board.valid_coordinate("E1"));

    String[] valid_coords = new String[] {"A1", "A2", "A3"};
    String[] invalid_coords = new String[] {"A1", "A2", "B8"};
    Assert.assertEquals(true, board.all_valid_coords(valid_coords));
    Assert.assertEquals(false, board.all_valid_coords(invalid_coords));

    String[] valid_cruiser_coords = new String[] {"A1", "A2", "A3"};
    String[] invalid_cruiser_coords = new String[] {"A1", "A2"};
    Assert.assertEquals(true, board.length_match(cruiser, valid_cruiser_coords));
    Assert.assertEquals(false, board.length_match(cruiser, invalid_cruiser_coords));

    String[] consecutive_coords1 = new String[] {"A1", "A2", "A3"};
    String[] non_consecutive_coords1 = new String[] {"A1", "A2", "B2"};
    Assert.assertEquals(true, board.consecutive(consecutive_coords1));
    Assert.assertEquals(false, board.consecutive(non_consecutive_coords1));

    String[] consecutive_coords2 = new String[] {"A1", "A2"};
    String[] non_consecutive_coords2 = new String[] {"A1", "A3"};
    Assert.assertEquals(true, board.consecutive(consecutive_coords2));
    Assert.assertEquals(false, board.consecutive(non_consecutive_coords2));

    String[] consecutive_coords3 = new String[] {"A1", "B1", "C1"};
    String[] non_consecutive_coords3 = new String[] {"A1", "B1", "D1"};
    Assert.assertEquals(true, board.consecutive(consecutive_coords3));
    Assert.assertEquals(false, board.consecutive(non_consecutive_coords3));

    String[] backwards_coords = new String[] {"A3", "A2", "A1"};
    Assert.assertEquals(false, board.consecutive(backwards_coords));

    Assert.assertEquals(true, board.all_unoccupied(valid_cruiser_coords));
    Assert.assertEquals(true, board.cells().get("A1").empty());
    board.cells().get("A1").placeShip(cruiser);
    Assert.assertEquals(false, board.cells().get("A1").empty());
    Assert.assertEquals(false, board.all_unoccupied(valid_cruiser_coords));

    String[] cruiser_coords1 = new String[] {"A2", "A3", "A4"};
    String[] cruiser_coords2 = new String[] {"B2", "C2", "D2"};
    String[] sub_coords1 = new String[] {"A2", "A3"};
    String[] sub_coords2 = new String[] {"C2", "D2"};
    Assert.assertEquals(true, board.valid_placement(cruiser, cruiser_coords1));
    Assert.assertEquals(true, board.valid_placement(cruiser, cruiser_coords2));
    Assert.assertEquals(true, board.valid_placement(submarine, sub_coords1));
    Assert.assertEquals(true, board.valid_placement(submarine, sub_coords2));

    String[] bad_cruiser_coords1 = new String[] {"A1", "A2", "A3"}; // "A1" is occupied
    String[] bad_cruiser_coords2 = new String[] {"B2", "C2", "C3"};
    String[] bad_sub_coords1 = new String[] {"A1", "A2"};
    String[] bad_sub_coords2 = new String[] {"A2", "C2"};
    Assert.assertEquals(false, board.valid_placement(cruiser, bad_cruiser_coords1));
    Assert.assertEquals(false, board.valid_placement(cruiser, bad_cruiser_coords2));
    Assert.assertEquals(false, board.valid_placement(submarine, bad_sub_coords1));
    Assert.assertEquals(false, board.valid_placement(submarine, bad_sub_coords2));

    board.place_ship(cruiser, bad_cruiser_coords1);
    Assert.assertEquals(true, board.cells().get("A2").empty());
    board.place_ship(cruiser, cruiser_coords1);
    Assert.assertEquals(false, board.cells().get("A2").empty());

    Assert.assertEquals("  1 2 3 4 \nA . . . . \nB . . . . \nC . . . . \nD . . . . \n", board.render());
    Assert.assertEquals("  1 2 3 4 \nA S S S S \nB . . . . \nC . . . . \nD . . . . \n", board.render(true));
  }
}
