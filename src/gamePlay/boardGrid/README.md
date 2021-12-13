# Team-Omnicron

## board

### Before Running
* launch from [boardLauncher](boardLauncher.java) class
* requires functions from [MainMenuSceneBuilder/src](src), especially from the [Helper](..) class
* any modification on the loading data can only be edited from [boardLauncher](boardLauncher.java)
* board is in GridPane with adjustable size
* The maximum number of players is two

### Functions
* If you click the cell, the score will be added for the current player.
* Only one player can make changes on the board in any given round.
* If you drag a cell, the image will be gone, but other part of the cell will remain at the position where it was.
* The current player will "gain" negative score if they drag the cell

### Structure
* The play board is in the middle of the window. You can resize it if you want, but you can't minimize it to a much smaller size.
* On the top, the name of the player who needs to take their [turn](turns.java) it displayed.
* On the left, there is a brief description about the [current possible move](moveInfo.java).
* On the bottom left, all players will have their [score](boardScore.java) displayed.
* The edit game and save game buttons are on the top right of the screen.

### TODO
* add mouse drag and drop motion in [cell](boardCell.java)
* use [Tile](../../Objects/Tile.java) rather than `StackPane` for the cell objects
* create another scene for a user to input players' names

