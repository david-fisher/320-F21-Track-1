# Helpers
## Functions
This package is used to create the `MainMenu` for the program. This is done by setting the scene of a stage to the scene
returned by the `MainMenu.makeScene()` method.

## Caution
All other classes in this package must have a protected `makeScene()` method that returns a scene.  `MainMenu` is the exception in that its `makeScene()` method is public.
