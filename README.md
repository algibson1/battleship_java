# Java Battleship

[An old school project involved creating a terminal game for Battleship using Ruby](https://github.com/algibson1/battleship).

This is an attempt at translating that same project into Java for my own fun and learning.


### Notes for further refactoring and improvement:
- The game currently uses a 4x4 board and two ships. Several methods are built upon this. In a future iteration, where a user can choose their board size and quantity of ships, most of these methods will need to be refactored. Such as:
  - How cells are generated in `Board`
  - Computer health and user health in `Battle` will need to be calculated by summing the health of all ships that the user chooses to add
  - How to calculate a valid starter coordinate in `Battle`

- The efficiency of the method calculating a valid starter coordinate can be improved by keeping track of:
  - Coordinates that have already been attempted during each loop, so they aren't randomly chosen again
  - Coordinates that the first ship was placed on, and are therefore no longer valid before the second loop starts