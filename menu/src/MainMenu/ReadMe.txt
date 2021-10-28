This package is used to create the main menu for the program. This is done by setting the scene of a stage to the scene
returned by the MainMenu.makeScene() function.

All other classes in this package must have a protected makeScene() function that returns a scene.  MainMenu is the exception in that its makeScene() function is public.
