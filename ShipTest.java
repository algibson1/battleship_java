import org.junit.*;

public class ShipTest {
  Ship cruiser = new Ship("Cruiser", 3);
  Ship submarine = new Ship("Submarine", 2);

  @Test
  public void getName_ReturnsShipName(){
    Assert.assertEquals("Cruiser", cruiser.getName());
    Assert.assertEquals("Submarine", submarine.getName());
  }

  @Test 
  public void getLength_ReturnsShipLength() {
    Assert.assertEquals(3, cruiser.getLength());
    Assert.assertEquals(2, submarine.getLength());
  }

  @Test
  public void getHealth_ReturnsShipHealth() {
    Assert.assertEquals(3, cruiser.getHealth());
    Assert.assertEquals(2, submarine.getHealth());
  }

  @Test 
  public void sunk_FalseByDefault() {
    Assert.assertEquals(false, submarine.sunk());
    Assert.assertEquals(false, cruiser.sunk());
  }

  @Test 
  public void hit_DecreasesShipHealth() {
    Assert.assertEquals(2, submarine.getHealth());
    submarine.hit();
    Assert.assertEquals(1, submarine.getHealth());
  }
  
  @Test
  public void sunk_TrueIfHealthIs0() {
    Assert.assertEquals(false, submarine.sunk());
    Assert.assertEquals(2, submarine.getHealth());
    submarine.hit();
    submarine.hit();
    Assert.assertEquals(0, submarine.getHealth());
    Assert.assertEquals(true, submarine.sunk());
    Assert.assertEquals(false, cruiser.sunk());
    cruiser.hit();
    cruiser.hit();
    Assert.assertEquals(false, cruiser.sunk());
    cruiser.hit();
    Assert.assertEquals(true, cruiser.sunk());
  }
}

