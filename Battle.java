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
    
    Object[] computer_board_keys = computer_board.cells().keySet().toArray();
    Object key = computer_board_keys[new Random().nextInt(computer_board_keys.length)];
    int direction = new Random().nextInt(2);
    if (direction == 0) {
      take_horizontal_coordinates(key); // plus ship length as argument
    } else {

    }
  }

  public void take_horizontal_coordinates(Object starter_coordinate) {
    
  }

  public void place_user_ships() {

  }

}
