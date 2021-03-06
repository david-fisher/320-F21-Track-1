# 320-F21-Track-1

A simple board game editor, engine, and player written in Java by Track 1 of [David Fisher's](https://people.cs.umass.edu/~dfisher/) fall 2021 CS320 course taught at The University of Massachusetts Amherst. Hosted at https://github.com/david-fisher/320-F21-Track-1.

## Demo-or-Die Info

-   Te4m used the branch [`demo-or-die`](https://github.com/david-fisher/320-F21-Track-1/tree/demo-or-die) during their portion of the presentation as it contained the console demo file that was used as the demo frontend for the game engine that they created since the UI frontend was not integrated with it in time for the demonstration.
-   Team Omnicron used the branch [`team-omnicron`](https://github.com/david-fisher/320-F21-Track-1/tree/team-omnicron) during their portion of the presentation as it had some changes to the inventory's functionality and also the game baord itself that was shown today as we were not able to push these changes to main in eenough time to run everything from the main branch.

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

### Requirements
-   Java 17
-   Javafx

### General Installation Guidelines

1. Clone 320-F21-Track-1 from its [GitHub repo](https://github.com/david-fisher/320-F21-Track-1).
1. Install [JDK](https://www.oracle.com/java/technologies/downloads/) (version 17).
1. Download [JavaFX .jar files](https://gluonhq.com/products/javafx/).
2. Download [gson .jar file](https://search.maven.org/artifact/com.google.code.gson/gson/2.8.9/jar). [if not available](#GSON-installation-if-the-jar-file-is-not-available)
3. Add JavaFX and gson .jar filepaths to IDE reference library (ensure module imports are correct for JavaFX).
4. Compile program using java from the `/src/gamePlay/mainMenu/main.java` file.

### Linux Specific Installation

##### Install openjdk-17-jre
```bash
sudo apt-get install openjdk-17-jre
```

### Mac Specific Installation
For installation, we will be using [Homebrew](https://brew.sh).  

##### Install adoptopenjdk17
```bash
brew install --cask adoptopenjdk17
```

### GSON installation if the jar file is not available

Gradle:

```bash
dependencies {
  implementation 'com.google.code.gson:gson:2.8.9'
}
```

Maven:

```bash
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.8.9</version>
</dependency>
```

Or check the [Official Github](https://github.com/google/gson)!
