
# 🚀 Space Exploration Game

The Space Exploration game is an exciting space-themed adventure where players control a rover tasked with exploring planets, gathering resources, and avoiding cosmic hazards like black holes. Developed using Object-Oriented Programming (OOP) principles, the game consists of several key classes that each play a critical role in the gameplay mechanics and progression.

## 🌌 Overview

In this game, the player pilots a rover on different planets, each with unique challenges and resources. The player must manage the rover's health, fuel, and collected resources while avoiding dangerous objects like black holes.  

The game **ends** in two possible scenarios:  
- When the rover runs out of fuel.  
- When the player has successfully reached all planets in the galaxy.  

## 🛠️ Key Classes

### 🤖 1. UzayKesifAraci (SpaceExplorationRover)
Represents the rover, the central character of the game. It manages key attributes like:  

- **Fuel**: How much fuel the rover has for exploration.  
- **Upgrades**: The ability to enhance the rover's performance with collected resources.  
- **Movement**: Handles navigation between planets and avoidance of hazards.  

### 🪐 2. Gezegen (Planet)
The `Gezegen` class represents the various planets that the rover can explore. Each planet has:  

- **Resources**: Unique materials available for collection.  
- **Hazards**: Some planets may be dangerous, featuring obstacles or hostile elements.  
- **Missions**: Specific goals that must be completed to progress.  

### 💎 3. Kaynak (Resource)
Handles the resources collected by the rover. Resources are crucial for:  

- **Upgrading the Rover**: Boosting its performance and durability.  
- **Repairing the Rover**: Restoring health when damaged.  

### ☄️ 4. Cisim (Object)
Represents space objects such as asteroids or floating debris. These may:  

- **Affect the Rover**: Damaging it, blocking its path, or offering resources.  
- **Encounters**: The rover must decide whether to avoid or interact with them.  

### 🕳️ 5. Karadelik (BlackHole)
Represents one of the most unpredictable and dangerous cosmic phenomena. If the rover encounters a black hole:  

- The rover is transported to a **random planet**.  
- Two possible outcomes:  
  - No fuel cost, instant teleport.  
  - Half of the rover’s fuel **and** collected resources are consumed before teleportation.  

This mechanic introduces risk and unpredictability, requiring careful planning.  

### 🧪 6. Test (Test)
The entry point of the game. Running the `Test` class starts the game.  
It also validates the mechanics through unit tests, including:  

- **Rover functionality**: Fuel, upgrades, repairs.  
- **Planet interactions**: Resource collection, mission goals.  
- **Black hole behavior**: Random teleport and resource impact.  
- **Game progression**: Ensures correct handling of win/lose conditions.  

## 🎮 Gameplay Mechanics

- **Planet Exploration**: Travel between planets, each offering unique challenges.  
- **Resource Collection**: Gather resources from planets and objects.  
- **Upgrades & Repairs**: Use collected resources to enhance the rover.  
- **Black Hole Events**: Random teleportation with potential heavy costs.  
- **Game End**: Occurs when fuel is depleted or all planets have been visited.  

## ▶️ How to Run the Game

1. Clone this repository to your local machine.  
2. Install **Java JDK 17** or later.  
3. Compile all `.java` files.  
4. Run the **`Test`** class to start the game.  


---
