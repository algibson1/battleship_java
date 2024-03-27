import java.util.*;

public class Board {
  private HashMap<String, Cell> cells;
  private String[] coordinates;

  public Board() {
    this.cells = new HashMap<String, Cell>();
    this.coordinates = new String[] {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "C1", "C2", "C3", "C4", "D1", "D2", "D3", "D4"};
    for (String element : coordinates) {
      cells.put(element, new Cell(element));
    }
  }

  public HashMap<String, Cell> getCells(){
    return this.cells;
  }

  public boolean validCoordinate(String coordinate) {
    return this.cells.containsKey(coordinate);
  }

  public boolean allValidCoordinates(String[] coordinates) {
    boolean allValid = true;
    for (String coordinate : coordinates) {
      if (this.cells.containsKey(coordinate) == false) {
        allValid = false;
      }
    }
    return allValid;
  }

  public boolean lengthMatch(Ship ship, String[] coords) {
    if (ship.getLength() == coords.length) {
      return true;
    }
    return false;
  }

  public boolean horizontallyConsecutive(char[] letters, int[] numbers) {
    for (int i = 1; i < letters.length; ++i) {
      if (letters[i] != letters[0] || numbers[i] != numbers[i-1] + 1) {
        return false;
      }
    }
    return true;
  }

  public boolean verticallyConsecutive(char[] letters, int[] numbers) {
    for (int i = 1; i < letters.length; ++i) {
      if (numbers[i] != numbers[0] || letters[i] != letters[i-1] + 1) {
        return false;
      }
    }
    return true;
  }

  public boolean consecutive(String[] coords) {
    char[] letters = new char[coords.length];
    int[] numbers = new int[coords.length];

    int index = 0;
    for (String element : coords) {
      letters[index] = element.charAt(0);
      numbers[index] = element.charAt(1);
      index += 1;
    }
    return verticallyConsecutive(letters, numbers) || horizontallyConsecutive(letters, numbers);
  }

  public boolean allUnoccupied(String[] coords) {
    boolean unoccupied = true;
    for (String element : coords) {
      if (cells.get(element).empty() == false) {
        unoccupied = false;
      }
    }
    return unoccupied;
  }

  public boolean validPlacement(Ship ship, String[] coords) {
    if (allValidCoordinates(coords) && lengthMatch(ship, coords) && consecutive(coords) && allUnoccupied(coords)) {
      return true;
    }
    return false;
  }

  public void placeShip(Ship ship, String[] coords) {
    if (validPlacement(ship, coords)) {
      for (String element : coords) {
        cells.get(element).placeShip(ship);
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

  public String render(boolean showShips) {
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