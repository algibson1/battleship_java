public class Board {
  Cell[] cells;

  public Board() {
    String[] coordinates = new String[] {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "C1", "C2", "C3", "C4", "D1", "D2", "D3", "D4"};
    Cell[] generated_cells = new Cell[16];
    int i = 0;
    for (String element : coordinates) {
      generated_cells[i] = new Cell(element);
      i += 1;
    }
    cells = generated_cells;
  }

  public Cell[] cells() {
    return cells;
  }

  public boolean valid_coordinate(String str) {
    boolean valid = false;
    for (Cell element : cells) {
      if (element.coordinate == str) {
        valid = true;
      }
    }
    return valid;
  }

  public boolean all_valid_coordinates(String[] coords) {
    boolean all_valid = true;
    for (String element : coords) {
      if (valid_coordinate(element) == false) {
        all_valid = false;
      }
    }
    return all_valid;
  }

  // public boolean valid_placement(Ship ship, String[] coords) {
    // if (all_valid_coords && length_match && all unoccupied, consecutive, not diagonal
  // }

  public boolean length_match(Ship ship, String[] coords) {
    if (ship.length == coords.length) {
      return true;
    }
    return false;
  }

  // public boolean consecutive(String[] coords) {
  //   String[] letters = new String[coords.length];

  // }
}