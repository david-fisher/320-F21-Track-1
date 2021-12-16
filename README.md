# 320-F21-Track-1

A simple board game editor, engine, and player written in Java by Track 1 of [David Fisher's](https://people.cs.umass.edu/~dfisher/) fall 2021 CS320 course taught at The University of Massachusetts Amherst.

## Teams

-   [Omnicron](https://github.com/david-fisher/320-F21-Track-1/wiki/Team-Omnicron-Wiki)
    -   Game-play UI
-   [Fisher's Fish](https://github.com/david-fisher/320-F21-Track-1/wiki/Fisher's-Fish-Wiki)
    -   Rule Editor
-   [Te4m](https://github.com/david-fisher/320-F21-Track-1/wiki/TE4M-Wiki)
    -   Game Engine
-   [Binary Brothers](https://github.com/david-fisher/320-F21-Track-1/wiki/Team-Binary-Brothers-Wiki)
    -   Game Board Editor
-   [Team: Team](https://github.com/david-fisher/320-F21-Track-1/wiki/Team:-Team-Wiki)
    -   Data Persistence

## What is a Game Editor/Engine?

A game editor and engine provide tools and an interface for a user to create video games with.

## What is a Game Player

A game player allows a user to run a created game and interact with it through a GUI.

## Structure

-   [Gameplay UI](src/gamePlay/README.md)
-   [Game Editor Files](src/GameEditor)
-   [Core Game Object Files](src/Objects)
-   [Gamestate Specific Files](src/State)

## Setup

### General Installation Guidelines

1. Clone 320-F21-Track-1 from its [GitHub repo](https://github.com/david-fisher/320-F21-Track-1).
1. Install [JDK](https://www.oracle.com/java/technologies/downloads/) (version 8)
1. Download [JavaFX .jar files](https://gluonhq.com/products/javafx/).
1. Download [gson .jar file](https://github.com/google/gson).
1. Add JavaFX and gson .jar filepaths to IDE reference library (ensure module imports are correct for JavaFX).
1. Compile program using java from the `/src/gamePlay/mainMenu/main.java` file.

### Linux Specific Installation

Java 8+\
Javafx

```bash
sudo apt-get install openjdk-8-jre
```

zip the jar file

### Mac Specific Installation

Java 8 installation

```bash
brew install --cask adoptopenjdk8
```
