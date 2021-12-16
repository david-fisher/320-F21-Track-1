# 320-F21-Track-1

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

## Structure

-   [game play](src/gamePlay/README.md)
-   [game editor](src/GameEditor)
-   [objects](src/Objects)
-   [state](src/State)

## Setup

### General Installation Guidelines

1. Install [JDK](https://www.oracle.com/java/technologies/downloads/) (version 8)
2. Download [JavaFX .jar files](https://gluonhq.com/products/javafx/).
3. Download [gson .jar file](https://github.com/google/gson).
4. Add JavaFX and gson .jar filepaths to IDE reference library (ensure module imports are correct for JavaFX).
5. Compile program using java from the `/src/gamePlay/mainMenu/main.java` file.

### Linux Installation

Java 8+\
Javafx

```bash
sudo apt-get install openjdk-8-jre
```

zip the jar file

### Mac Installation

Java 8 installation

```bash
brew install --cask adoptopenjdk8
```

## Play

> user stories
