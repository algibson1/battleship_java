public class Cell {
  String coordinate;
  Ship ship;
  Boolean firedUpon;

  public Cell(String string) {
    this.coordinate = string;
    this.firedUpon = false;
  }

  public String getCoordinate() {
    return coordinate;
  }

  public Ship getShip() {
    return ship;
  }

  public boolean empty() {
    if (ship == null) {
      return true;
    }
    return false;
  }

  public void placeShip(Ship newShip) {
    this.ship = newShip;
  }

  public boolean firedUpon() {
    return firedUpon;
  }

  public void fireUpon() {
    firedUpon = true;
    if (ship != null) {
      ship.hit();
    }
  }

  public String render(boolean showShips) {
    if (ship != null && ship.sunk()) {
      return "X";
    } else if (firedUpon == true && ship == null) {
      return "M";
    } else if (ship != null && firedUpon == false && showShips == true) {
      return "S";
    } else if (ship != null && firedUpon == true) {
      return "H";
    }
    return ".";
  }

  public String render() {
    if (ship != null && ship.sunk()) {
      return "X";
    } else if (firedUpon == true && ship == null) {
      return "M";
    } else if (ship != null && firedUpon == true) {
      return "H";
    }
    return ".";
  }
}