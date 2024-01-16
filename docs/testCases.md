| Test Case ID | Method            | Description                              | Test Data/Conditions  | Expected Output         |
|--------------|-------------------|------------------------------------------|-----------------------|-------------------------|
| TC01         | `captureStones`   | No stones captured (no condition met).   | `stoppingPoint = 6`   | `capturedStones = 0`    |
| TC02         | `distributeStones`| Stones distribution to pits and stores.  | `startingPoint = 0`   | `stonesAdded > 0`       |
| TC03         | `getNumStones`    | Get stones in a specific pit.            | `pitNum = 3`          | `numStones` (Initial)   |
| TC04         | `isSideEmpty`     | Check if side not empty for a pit.       | `pitNum = 2`          | `false` (Initial)       |
| TC05         | `resetBoard`      | Reset board to initial state.            | N/A                   | No exception thrown     |
| TC06         | `registerPlayers` | Register players to the board.           | `playerOne, playerTwo`| No exception thrown     |
