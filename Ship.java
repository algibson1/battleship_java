public class Ship {
  String name;
  int length;
  int health;

  public Ship(String str, int integer){
    name = str;
    length = integer;
    health = integer;
  }

  public String name() {
    return name;
  }

  public int length() {
    return length;
  }

  public int health() {
    return health;
  }

  public boolean sunk() {
    if (health == 0) {
      return true;
    }
    return false;
  }

  public void hit() {
    health -= 1;
  }

}
