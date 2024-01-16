Pit Class:
Encapsulation: The stoneCount field is properly encapsulated, and getter and setter methods are appropriately provided.
Methods: The addStones, addStone, removeStones, and toString methods are straightforward and follow good naming conventions.

Store Class:
Encapsulation: The owner and stoneCount fields are properly encapsulated.
Methods: The methods (setOwner, getOwner, addStones, getTotalStones, emptyStore, and toString) are clear and follow good naming conventions.

Player Class:
Encapsulation: The name and store fields are properly encapsulated.
Methods:The class provides necessary getter and setter methods (getName, setName, getStore, getStoreCount, setStore, and toString).

Board Class:
Encapsulation:All fields are private, and getter methods are provided, ensuring proper encapsulation.
Methods: Methods like captureStones, getNumStones, distributeStones, getPits, getStores, isSideEmpty, moveStones, registerPlayers, resetBoard, setUpPits, initializeBoard, and setUpStores are well-organized and follow good coding practices.
Initialization:The board is initialized and players are registered in the constructor, ensuring the game starts with a valid state.

MancalaGame Class:
Encapsulation: The playerOne, playerTwo, currentPlayer, and board fields are appropriately encapsulated.
Methods: Methods like getBoard, getCurrentPlayer, getNumStones, getPlayers, getStoreCount, getWinner, isGameOver, move, setBoard, setCurrentPlayer, setPlayers, startNewGame, toString, and switchPlayer are well-organized and follow good coding practices.

TextUI Class:
Encapsulation: The game and scanner fields are properly encapsulated.
Constructor: The constructor initializes a new MancalaGame and a Scanner, providing a clean way to start a game.
Methods: Methods like startGame, setupPlayer, printBoard, and main are well-structured and follow good coding practices. The user input is appropriately validated.

Game Loop:The game loop is well-organized, providing a clear flow for each player's turn until the game is over.