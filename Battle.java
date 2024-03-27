import java.lang.reflect.Array;
import java.util.Random;
import java.util.Scanner;

public class Battle {
  int computer_health = 0;
  int user_health = 0;
  Board computer_board = new Board();
  Board user_board = new Board();
  Ship[] computer_ships = new Ship[] {new Ship("Cruiser", 3), new Ship("Submarine", 2)};
  Ship[] user_ships = new Ship[] {new Ship("Cruiser", 3), new Ship("Submarine", 2)};

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
        game_setup();
      } else {
        System.out.println("k thx bai");
      }
    }
  }

  public void game_setup() {
    place_computer_ships();
    System.out.println("I have laid out my ships on the grid");
    System.out.println("You now need to lay out your ships");
    System.out.println("The Cruiser is three units long and the Submarine is two units long");
    place_user_ships();
  }

  public void place_computer_ships() {
    
    Object[] computer_board_keys = computer_board.getCells().keySet().toArray();
    for (Ship ship : computer_ships) {
      Object key = computer_board_keys[new Random().nextInt(computer_board_keys.length)];
      int direction = new Random().nextInt(2);
      if (direction == 0) {
        String[] coords = new String[ship.getLength()];
        do {
          coords = take_horizontal_coordinates(key, ship.getLength());
        }
        while (!computer_board.validPlacement(ship, coords));
        computer_board.placeShip(ship, coords);
      } else {
        String[] coords = new String[ship.getLength()];
        do {
          coords = take_vertical_coordinates(key, ship.getLength());
        }
        while (!computer_board.validPlacement(ship, coords));
        computer_board.placeShip(ship, coords);
      }
    }
  }

  public String[] take_horizontal_coordinates(Object starter_coordinate, int ship_length) {
    // takes in starter coordinate and ship length, which represents how many additional coordinates to take
    String coordinate = starter_coordinate.toString();
    // split the starter coordinate into letter and integer
    String letter = Character.toString(coordinate.charAt(0));
    int number = Character.getNumericValue(coordinate.charAt(1));
    // put starter coordinate into an array of length determined by ship length
    String[] coordinates = new String[ship_length];
    Array.set(coordinates, 0, coordinate);

    // for as many times as the number representing the length of the ship minus one
    for (int i = 1; i < ship_length; i++) {
      // take the last char of the starter coordinate (an integer)
      // increase it to modify the original coordinate and add that to array at appropriate index
      int num = number + i;
      String next_coord = letter + num;
      coordinates[i] = next_coord;
    }
    return coordinates;
    // return an array of string elements representing coordinates
  }

  public String[] take_vertical_coordinates(Object starter_coordinate, int ship_length) {
    String coordinate = starter_coordinate.toString();
    // split the starter coordinate into letter and integer
    char letter = coordinate.charAt(0);
    int number = Character.getNumericValue(coordinate.charAt(1));
    // put starter coordinate into an array of length determined by ship length
    String[] coordinates = new String[ship_length]; // placeholder ship length
    coordinates[0] = coordinate;
    // for as many times as the number representing the length of the ship minus one
    for (int i = 1; i < ship_length; i++) {
      // take the first char of the starter coordinate (letter)
      // increase it to modify the original coordinate and add that to array at appropriate index
      // String next_coord = Character.toString(letter) + (number + i);
      String next_letter = Character.toString(letter + i);
      String next_coord = next_letter + Integer.toString(number);
      coordinates[i] = next_coord;
    }
    return coordinates;
  }


  public void place_user_ships() {

  }

}
