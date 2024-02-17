import org.junit.*;

public class CellTest {
  Ship cruiser = new Ship("Cruiser", 3);
  Cell a1 = new Cell("A1");
  Cell a2 = new Cell("A2");
  Cell a3 = new Cell("A3");
  
  @Test
  public void CellConstructor_CellHasACoordinate(){
    Assert.assertEquals("A1", a1.getCoordinate());
    Assert.assertEquals("A2", a2.getCoordinate());
    Assert.assertEquals("A3", a3.getCoordinate());
  }

  @Test
  public void getShip_NullByDefault() {
    Assert.assertEquals(null, a1.getShip());
    Assert.assertEquals(true, a1.empty());
  }

  @Test 
  public void placeShip_PlacesShipOnCell() {
    a1.placeShip(cruiser);
    Assert.assertEquals(cruiser, a1.getShip());
    Assert.assertEquals(false, a1.empty());
    Assert.assertEquals(null, a2.getShip());
  }

  @Test
  public void fireUpon_FiresUponTheCell() {
    Assert.assertEquals(false, a1.firedUpon());
    a1.fireUpon();
    Assert.assertEquals(true, a1.firedUpon());
    Assert.assertEquals(false, a2.firedUpon());
  }

  @Test
  public void render_RendersDetailsOfCellToTerminal() {
    // Cells are all '.' by default
    Assert.assertEquals(".", a2.render());
    a2.placeShip(cruiser);
    // Cells are still '.' even with a ship
    // So user cannot see the computer's ships
    Assert.assertEquals(".", a2.render());
    // Cells with a ship and with showShips set to true are 'S'
    // So user can see their own ships
    Assert.assertEquals("S", a2.render(true));
    // Cells with ships that have been hit but not sunk display as 'H'
    a1.placeShip(cruiser);
    a1.fireUpon();
    Assert.assertEquals("H", a1.render());
    // Cells with no ships that have been fired upon are 'M' for a "missed shot"
    a3.fireUpon();
    Assert.assertEquals("M", a3.render());
    // Cells with a ship that has been completely sunk are 'X'
    cruiser.hit();
    cruiser.hit();
    Assert.assertEquals(true, cruiser.sunk());
    Assert.assertEquals("X", a1.render());
  }
}
