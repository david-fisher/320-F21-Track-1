# Team-Omnicron

## board

### Before Running
* launch from [boardLauncher](boardLauncher.java) class
* require functions from [MainMenuSceneBuilder/src](../MainMenuSceneBuilder/src), especially from the [Helper](../MainMenuSceneBuilder/src/Helpers/Helper.java) class
* any modification on the loading data can only edit from [boardLauncher](boardLauncher.java)
* board is in GridPane with adjustable size
* Maximum players are two

### Functions
* If you click the cell, the score will add for current player.
* One player only has one chance to make any changes on the board in each round.
* If you drag the cell, the image will be gone, but other part of the cell will remain at the position where they were.
* Current player will have a negative gain in score if they drag the cell

### Structure
* The play board is right at the middle. You resize it if you want, but you can't minimize to a much smaller size.
* On the top, you will see who's [turn](turns.java) is right now.
* On the left, there's a brief description about the [current possible move](moveInfo.java).
* On the bottom left, all players enrolled will have their [score](boardScore.java) displayed.
* The edit game and save game buttons are on the top right of the screen.

### TODO
* add mouse drag and drop motion in [cell](boardCell.java)
* use [Tile](../Objects/Tile.java) rather than `StackPane` for the cell objects
* create another scene for user input players names

