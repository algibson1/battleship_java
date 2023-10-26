import org.junit.*;

public class ShipTest {
  @Test
  public void test(){
    Ship cruiser = new Ship("Cruiser", 3);
    Assert.assertEquals("Cruiser", cruiser.name());
    Assert.assertEquals(3, cruiser.length());
    Assert.assertEquals(3, cruiser.health());

    Ship submarine = new Ship("Submarine", 2);
    Assert.assertEquals("Submarine", submarine.name());
    Assert.assertEquals(2, submarine.length());
    Assert.assertEquals(2, submarine.health());

    Assert.assertEquals(false, submarine.sunk());
    submarine.hit();
    Assert.assertEquals(1, submarine.health());
    Assert.assertEquals(false, submarine.sunk());
    submarine.hit();
    Assert.assertEquals(0, submarine.health());
    Assert.assertEquals(true, submarine.sunk());
    Assert.assertEquals(false, cruiser.sunk());
  }
}
