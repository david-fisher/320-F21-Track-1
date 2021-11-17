# Team-Omnicron

## board

### Before Running
* launch from `boardLauncher` class
* require functions from `MainMenuSceneBuilder/src/Helper`
* board is in GridPane with adjustable size
* Maximum players are two

### Functions
* If you click the cell, the score will add for current player.
* One player only has one chance to make any changes on the board in each round.
* If you drag the cell, the image will be gone, but other part of the cell will remain at the position where they were.
* Current player will have a negative gain in score if they drag the cell

### Structure
* The play board is right at the middle. You resize it if you want, but you can't minimize to a much smaller size.
* On the top, you will see who's turn is right now.
* On the left, there's a brief description about the current possible move.
* On the bottom left, all the players enrolled will have their score displayed.
* The edit game and save game buttons are on the top right of the screen.
