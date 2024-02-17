import org.junit.*;

public class BattleTest {
  @Test
  public void battle_test() {
    Battle battle = new Battle();
    // battle.start();
    // Assert.assertEquals("Welcome to Battleship", battle.welcome());
    // Assert output to terminal?
    // Assert.assertEquals("")

    //test take_horizontal_coordinates
    String[] horizontal_coords_1 = new String[] {"A1", "A2", "A3"};
    String[] horizontal_coords_2 = new String[] {"B3", "B4"};
    Assert.assertArrayEquals(horizontal_coords_1, battle.take_horizontal_coordinates("A1", 3));
    Assert.assertArrayEquals(horizontal_coords_2, battle.take_horizontal_coordinates("B3", 2));


    //test take_vertical_coordinates
    String[] vert_coords_1 = new String[] {"A1", "B1", "C1"};
    String[] vert_coords_2 = new String[] {"B3", "C3"};
    Assert.assertArrayEquals(vert_coords_1, battle.take_vertical_coordinates("A1", 3));
    Assert.assertArrayEquals(vert_coords_2, battle.take_vertical_coordinates("B3", 2));
  }
}