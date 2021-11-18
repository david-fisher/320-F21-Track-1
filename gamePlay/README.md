# Team-Omnicron
## Run
* The main entrance is at the [main](mainMenu/Main.java). After launching, the main menu will show up from creating or loading game.
* Another option to quickly play the game is launch from a [board launcher](boardGrid/boardLauncher.java). A pre-set board will automatically set up for you.
## Structure
* [board](boardGrid)
  * [main](boardGrid/boardLauncher.java)
  * [game play](boardGrid/gamePlayUI.java)
  * [grid](boardGrid/boardGrid.java)
  * [cell](boardGrid/boardCell.java)
  * [score](boardGrid/boardScore.java)
  * [moves](boardGrid/moveInfo.java)
  * [turn](boardGrid/turns.java)
  * [make a scene](boardGrid/boardScene.java)
* [main menu](mainMenu)
  * [main](mainMenu/Main.java)
  * [mode menu](mainMenu/ModeMenu.java)
  * [controller](mainMenu/controller.java)
  * [mode menu controller](mainMenu/ModeMenuController.java)
  * [save game](mainMenu/saveGameViewController.java)