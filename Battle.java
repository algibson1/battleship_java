import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Battle {
  int computerHealth = 5;
  int userHealth = 5;
  Board computerBoard = new Board();
  Board userBoard = new Board();
  Ship[] computerShips = new Ship[] {new Ship("Cruiser", 3), new Ship("Submarine", 2)};
  Ship[] userShips = new Ship[] {new Ship("Cruiser", 3), new Ship("Submarine", 2)};

  public void start() {
    System.out.println("Welcome to Battleship");
    // try {
    //   Thread.sleep(500);
    // } catch(InterruptedException error) {
    //   System.out.println("got interrupted!");
    // }
    System.out.println("Enter p to play. Enter q to quit.");
    try (Scanner scanner = new Scanner(System.in)) {
      String input = scanner.nextLine();
      while (!input.toLowerCase().equals("p") && !input.toLowerCase().equals("q")) {
        // '=='' checks to see if object references are the same, .equals() checks to see if string values match
        System.out.println("Please: enter p to play or q to quit");
        input = scanner.nextLine();
      }
      if (input.toLowerCase().equals("p")) {
        System.out.println("Okay, let's play!");
        gameSetup();
      } else {
        System.out.println("k thx bai");
      }
    }
  }

  public void gameSetup() {
    placeComputerShips();
    System.out.println("I have laid out my ships on the grid");
    System.out.println("You now need to lay out your ships");
    System.out.println("The Cruiser is three units long and the Submarine is two units long");
    placeUserShips();
  }

  public void placeComputerShips() {
    // for each ship
    for (Ship ship : computerShips) {
      String[] coordinates = new String[ship.getLength()];
      // until the coordinates calculated are a valid placement:
      while (computerBoard.validPlacement(ship, coordinates) == false) {
        // choose: vertical or horizontal
        int direction = new Random().nextInt(2);
        // If horizontal:
        if (direction == 0) {
          // take horizontal coordinates 
          coordinates = takeHorizontalCoordinates(getHorizontalStarter(ship), ship.getLength());
        } else {
          coordinates = takeVerticalCoordinates(getVerticalStarter(ship), ship.getLength());
        }
        // All of this is wrapped in a while loop
        // So that if the final coordinates end up being invalid
        // It will run again and fetch a different set of coordinates
        // Until they ARE valid
      }
      // I now have an array of valid coordinates and can place a ship
      computerBoard.placeShip(ship, coordinates);
    }
  }

  public String getHorizontalStarter(Ship ship) {
    // create an ArrayList
    ArrayList<String> validStarters = new ArrayList<String>();
    for (String coordinate : computerBoard.getCoordinates() ) {
      // Valid starting coordinate can begin with any letter
      // But it cannot end with any number that would cause the ship to go off the board
      // So, if ship length is 3, coordinate cannot end in 3 or 4
      if ((ship.getLength() == 3 && coordinate.charAt(1) < '3') || 
      // Or if ship length is 2, coordinate cannot end in 4
          (ship.getLength() == 2 && coordinate.charAt(1) < '4')) {
        validStarters.add(coordinate);
      }
    }
    int index = new Random().nextInt(validStarters.size());
    return validStarters.get(index);
  }

  public String getVerticalStarter(Ship ship) {
    // create an ArrayList
    ArrayList<String> validStarters = new ArrayList<String>();
    for (String coordinate : computerBoard.getCoordinates() ) {
      // Valid starting coordinate can end with any number
      // But it cannot begin with any letter that would cause the ship to go off the board
      // So, if ship length is 3, coordinate cannot begin with C or D
      if ((ship.getLength() == 3 && coordinate.charAt(0) < 'C') || 
      // Or if ship length is 2, coordinate cannot end in D
          (ship.getLength() == 2 && coordinate.charAt(0) < 'D')) {
        validStarters.add(coordinate);
      }
    }
    int index = new Random().nextInt(validStarters.size());
    return validStarters.get(index);
  }

  public String[] takeHorizontalCoordinates(Object starterCoordinate, int shipLength) {
    // takes in starter coordinate and ship length, which represents how many additional coordinates to take
    String coordinate = starterCoordinate.toString();
    // split the starter coordinate into letter and integer
    String letter = Character.toString(coordinate.charAt(0));
    int number = Character.getNumericValue(coordinate.charAt(1));
    // put starter coordinate into an array of length determined by ship length
    String[] coordinates = new String[shipLength];
    Array.set(coordinates, 0, coordinate);

    // for as many times as the number representing the length of the ship minus one
    for (int i = 1; i < shipLength; i++) {
      // take the last char of the starter coordinate (an integer)
      // increase it to modify the original coordinate and add that to array at appropriate index
      int num = number + i;
      String nextCoord = letter + num;
      coordinates[i] = nextCoord;
    }
    return coordinates;
    // return an array of string elements representing coordinates
  }

  public String[] takeVerticalCoordinates(Object starterCoordinate, int shipLength) {
    String coordinate = starterCoordinate.toString();
    // split the starter coordinate into letter and integer
    char letter = coordinate.charAt(0);
    int number = Character.getNumericValue(coordinate.charAt(1));
    // put starter coordinate into an array of length determined by ship length
    String[] coordinates = new String[shipLength]; // placeholder ship length
    coordinates[0] = coordinate;
    // for as many times as the number representing the length of the ship minus one
    for (int i = 1; i < shipLength; i++) {
      // take the first char of the starter coordinate (letter)
      // increase it to modify the original coordinate and add that to array at appropriate index
      // String nextCoord = Character.toString(letter) + (number + i);
      String nextLetter = Character.toString(letter + i);
      String nextCoord = nextLetter + Integer.toString(number);
      coordinates[i] = nextCoord;
    }
    return coordinates;
  }


  public void placeUserShips() {

  }

}
