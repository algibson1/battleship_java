import java.util.*;

public class Board {
  HashMap<String, Cell> cells = new HashMap<String, Cell>();
  String[] coordinates = new String[] {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "C1", "C2", "C3", "C4", "D1", "D2", "D3", "D4"};

  public Board() {
    for (String element : coordinates) {
      cells.put(element, new Cell(element));
    }
  }

  public HashMap<String, Cell> cells() {
    return cells;
  }

  public boolean valid_coordinate(String str) {
    boolean valid = false;
    for (String element : coordinates) {
      if (element == str) {
        valid = true;
      }
    }
    return valid;
  }

  public boolean all_valid_coords(String[] coords) {
    boolean all_valid = true;
    for (String element : coords) {
      if (valid_coordinate(element) == false) {
        all_valid = false;
      }
    }
    return all_valid;
  }

  public boolean valid_placement(Ship ship, String[] coords) {
    if (all_valid_coords(coords) && length_match(ship, coords) && consecutive(coords) && all_unoccupied(coords)) {
      return true;
    }
    return false;
  }

  public boolean length_match(Ship ship, String[] coords) {
    if (ship.length == coords.length) {
      return true;
    }
    return false;
  }

  public boolean consecutive(String[] coords) {
    char[] letters = new char[coords.length];
    int[] numbers = new int[coords.length];

    int x = 0;
    for (String element : coords) {
      letters[x] = element.charAt(0);
      numbers[x] = element.charAt(1);
      x += 1;
    }

    int y = coords.length;
    boolean same_column = true;
    boolean cons_letters = true;
    for (int i = 0; i < y - 1; ++i) {
      if (letters[i] != letters[0]) {
        same_column = false;
      }
      if (letters[i+1] != letters[i] + 1) {
        cons_letters = false;
      }
    }

    boolean same_row = true;
    boolean cons_numbers = true;
    for (int i = 0; i < y - 1; ++i) {
      if (numbers[i] != numbers[0]) {
        same_row = false;
      }
      if (numbers[i+1] != numbers[i] + 1) {
        cons_numbers = false;
      }
    }

    if ((same_row && cons_letters) || (same_column && cons_numbers)) {
      return true;
    }
    return false;
  }

  public boolean all_unoccupied(String[] coords) {
    boolean unoccupied = true;
    for (String element : coords) {
      if (cells.get(element).empty() == false) {
        unoccupied = false;
      }
    }
    return unoccupied;
  }

  public void place_ship(Ship ship, String[] coords) {
    if (valid_placement(ship, coords)) {
      for (String element : coords) {
        cells.get(element).place_ship(ship);
      }
    }
  }

  public String render() {
    String board = "  1 2 3 4 \nA ";
    for (int i = 0; i < cells.size(); ++i ) {
      board += cells.get(coordinates[i]).render() + " ";
      if (i == 3) {
        board += "\nB ";
      } else if (i == 7) {
        board += "\nC ";
      } else if (i == 11) {
        board += "\nD ";
      }
    }
    board += "\n";
    return board;
  }

  public String render(boolean show_ships) {
    String board = "  1 2 3 4 \nA ";
    for (int i = 0; i < cells.size(); ++i ) {
      board += cells.get(coordinates[i]).render(true) + " ";
      if (i == 3) {
        board += "\nB ";
      } else if (i == 7) {
        board += "\nC ";
      } else if (i == 11) {
        board += "\nD ";
      }
    }
    board += "\n";
    return board;
  }
}