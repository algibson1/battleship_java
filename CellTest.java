import org.junit.*;

public class CellTest {
  @Test
  public void cell_test(){
    Ship cruiser = new Ship("Cruiser", 3);
    Cell a1 = new Cell("A1");
    Cell a2 = new Cell("A2");
    Cell a3 = new Cell("A3");

    Assert.assertEquals("A1", a1.coordinate());
    Assert.assertEquals("A2", a2.coordinate());
    Assert.assertEquals("A3", a3.coordinate());

    Assert.assertEquals(null, a1.ship());
    Assert.assertEquals(true, a1.empty());
    a1.place_ship(cruiser);
    Assert.assertEquals(cruiser, a1.ship());
    Assert.assertEquals(false, a1.empty());
    Assert.assertEquals(null, a2.ship());

    Assert.assertEquals(false, a1.fired_upon());
    a1.fire_upon();
    Assert.assertEquals(true, a1.fired_upon());
    Assert.assertEquals(false, a2.fired_upon());

    Assert.assertEquals(".", a2.render());
    a2.place_ship(cruiser);
    Assert.assertEquals(".", a2.render());
    Assert.assertEquals("S", a2.render(true));
    Assert.assertEquals("H", a1.render());
    a3.fire_upon();
    Assert.assertEquals("M", a3.render());
    cruiser.hit();
    cruiser.hit();
    Assert.assertEquals(true, cruiser.sunk());
    Assert.assertEquals("X", a1.render());
  }
}
