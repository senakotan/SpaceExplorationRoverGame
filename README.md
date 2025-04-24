
# 🚀 Space Exploration Rover Game

The *Space Exploration Rover* game is an exciting space-themed adventure where players control a rover tasked with exploring planets, gathering resources, and avoiding cosmic hazards like black holes. Developed using *Object-Oriented Programming (OOP)* principles, the game consists of several key classes that each play a critical role in the gameplay mechanics and progression.

## Overview

In this game, the player pilots a rover on different planets, each with unique challenges and resources. The player must manage the rover's health, fuel, and collected resources while avoiding dangerous objects like black holes.

## Key Classes

### 1. *UzayKesifAraci (SpaceExplorationRover)*  
This class represents the rover, the central character of the game. It manages key attributes like:

- *Health:* The rover's remaining durability.
- *Fuel:* How much fuel the rover has for exploration.
- *Upgrades:* The ability to enhance the rover's performance with collected resources.
- *Movement:* The rover's actions when navigating between planets and avoiding obstacles.

### 2. *Gezegen (Planet)*  
The *Gezegen* class represents the various planets that the rover can explore. Each planet has:

- *Resources:* Unique materials available for collection.
- *Hazards:* Some planets may be dangerous, featuring obstacles or hostile elements.
- *Missions:* Specific goals that must be completed to progress.

### 3. *Kaynak (Resource)*  
This class handles the resources collected by the rover. These resources are crucial for:

- *Upgrading the Rover:* Resources can be spent on enhancing the rover's equipment and capabilities.
- *Repairing the Rover:* Damaged or low-health rovers can use resources for repairs.

### 4. *Cisim (Object)*  
Represents space objects such as asteroids or floating debris. These objects may:

- *Affect the Rover:* Objects can either damage the rover, block its path, or provide useful resources.
- *Encounters:* The rover must interact with these objects to either avoid or utilize them effectively.

### 5. *Karadelik (BlackHole)*  
This class represents black holes, one of the most dangerous threats in space. If the rover gets too close to a black hole:

- *Game Over:* The player loses, and the rover is pulled into the black hole, resulting in failure.
- *Strategy:* The player must carefully plan their route to avoid black holes and other dangerous cosmic objects.

### 6. *Test (Test)*  
Contains unit tests to ensure the game’s mechanics are working as expected. This class validates:

- *Rover functionality:* Health, fuel, upgrades.
- *Planet interactions:* Resource collection, mission completion.
- *Game progression:* Ensures the game correctly handles the rover’s interactions with planets and black holes.

## Gameplay Mechanics

The gameplay involves exploring different planets, collecting resources, and managing the rover's stats:

1. *Planet Exploration:* The player can travel between planets. Each planet offers unique resources and challenges.
2. *Resource Collection:* The rover collects resources that are essential for survival and upgrades. These resources may come from planets or other space objects.
3. *Upgrades and Repairs:* Collected resources can be used to repair and upgrade the rover to better handle increasingly difficult missions and encounters.
4. *Avoiding Hazards:* The rover must avoid black holes and other hazards, like asteroids, to stay alive.

## Technologies Used

- *Java:* The core programming language for developing the game.
- *Java Swing:* Used for creating the graphical interface (GUI) of the game.

## How to Run the Game

1. *Clone* this repository to your local machine.
2. *Install* Java JDK 17 or a later version if you don’t have it already.
3. *Compile* all the Java files.
4. *Run* the UzayKesifAraci class to start the game.

---
