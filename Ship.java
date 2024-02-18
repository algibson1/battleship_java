public class Ship {
  private String name;
  private int length;
  private int health;

  public Ship(String string, int integer){
    this.name = string;
    this.length = integer;
    this.health = integer;
  }

  public String getName() {
    return this.name;
  }

  public int getLength() {
    return this.length;
  }

  public int getHealth() {
    return this.health;
  }

  public boolean sunk() {
    if (this.health == 0) {
      return true;
    }
    return false;
  }

  public void hit() {
    this.health -= 1;
  }

}
