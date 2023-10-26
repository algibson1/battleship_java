public class Cell {
  String coordinate;
  Ship ship;
  Boolean fired_upon;

  public Cell(String str) {
    coordinate = str;
    fired_upon = false;
  }

  public String coordinate() {
    return coordinate;
  }

  public Ship ship() {
    return ship;
  }

  public boolean empty() {
    if (ship == null) {
      return true;
    }
    return false;
  }

  public void place_ship(Ship new_ship) {
    ship = new_ship;
  }

  public boolean fired_upon() {
    return fired_upon;
  }

  public void fire_upon() {
    fired_upon = true;
    if (ship != null) {
      ship.hit();
    }
  }

  public String render(boolean show_ships) {
    if (ship != null && ship.sunk()) {
      return "X";
    } else if (fired_upon == true && ship == null) {
      return "M";
    } else if (ship != null && fired_upon == false && show_ships == true) {
      return "S";
    } else if (ship != null && fired_upon == true) {
      return "H";
    }
    return ".";
  }

  public String render() {
    if (ship != null && ship.sunk()) {
      return "X";
    } else if (fired_upon == true && ship == null) {
      return "M";
    } else if (ship != null && fired_upon == true) {
      return "H";
    }
    return ".";
  }
}