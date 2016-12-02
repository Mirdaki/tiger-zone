# TOURNAMENT BASED TIGER ZONE

This is a game rendition inspired and based on the popular board game known as Carcassonne by Klaus-Jurgen Wrede. It is in accordance with the "term" project for Introduction to Software Engineering (CEN3031), taught by Dave Small at the University of Florida. The intention is to have a working game capable of having two players, capable of implementing an Artificial Intelligence, and capable of communicating with a tournament server to compete against the rest of the class' implementations.

Any and all design documents procured during the course of this project have been placed inside of the /documentation/ subdirectory. Some are deprecated, and may have been changed since first implemented. The source code provides a much more in depth commentary and structure, and is the most up to date.

Any and all current source code have been placed inside of /src/. 

Any and all resources have been placed inside of /resources/. This mainly contains XML formatted information to generate our tiles via a factory design pattern. 

# TEAM INFORMATION
__COURSE__: CEN3031  
__TEAM__: E  
__MEMBERS__: 

- Matthew Booe - _mirdaki_
- Charley Chau - _charleychau_
- Josiah Crepeau - _crepeau_
- Tana Konda - _TanaKonda_
- Connor Ward - _conward_
- Zak Mills - _N/A_

## OVERALL ARCHITECTURAL STRUCTURE

There are three main architectural systems, each maintaing sub systems integral to the overall design. There is also two additional systems to handle unit testing (through JUnit) and acceptance testing (FITNesse). 

The three main architectural systems, alongside their relevant entities and value objects are as follows: 

- Tournament Network Handler: instantiates two games and communicates between the tournament server and the game
	- TigerZoneClient
	- TigerZoneServer (own implementation test)
	
- Entities: 
	- BoardObject: handles the board
	- AI: artificial intelligence system that creates 
	- Terrain: individual terrains specific to a tile
		- DenTerrain
		- LakeTerrain
		- JungleTerrain
		- TrailTerrain
	- Region: contains all board regions
		- DenRegion
		- LakeRegion
		- JungleRegion
		- TrailRegion
	- TileStack: generates our tiles
		- TileObject
			- TigerTile
			- TileEdges
			- TilePair
	- TileDeck: maintains deck (and can generate randomly)
	- Player
	- TigerObject
	- CrocodileObject
	- Animal
	
- Game: integrates all of our entity objects and provides communication medium between server 
- Tests: all JUnits and FITNesse fixture code

# GAME INTERACTION OUTLINE
This is the outline for the interactions of the game

- Network Communication
	- Instantiates communication between server
	- Starts
	- Receives commands  
	- Sends commands
- Game
	- Start game
		- Initialize everything
		- Tile stack, gets tiles from server
		- Board adds default first tile
	- Play turn
		- Network receives play turn command
		 	- Includes tile from other player (if applicable)
		- Board adds tile form other player (if applicable)
		- Tile stack presents new tile
		- AI Evaluates options for new tile
			- Board can provide legal placement options
			- Score can provide possible scoring
			- Chooses to adds tiger to terrain (if applicable)
			- If no legal tile placement
				- Tile stack removes tile and reevaluate
		- Board adds AI choice to board
			- Terrains create or are added to regions
				- Regions update
		- If region complete
			- Board moves region to completed list
			- If region has tiger
				- Board returns tiger to player
				- Score turn receives region
		- Network sends AI choice
		- If no tiles in stack
			- Call end game
	- Score turn
		- Board provides region
		- Scoring system evaluates points
		- Player receives points
	- End game
		- Score given remaining incomplete regions from board
		- Score turn per region
		- Ends game 
		
# COMPILATION
In order to compile our code, run from the `src` folder: 
>javac game/*.java entities/*.java network/*.java

In order to compile our tests, run 
>

# RUN
In order to run our network implementation, from the `root` folder: 

>java network/TigerZoneClient <hostname> <port number> <server pasword> <username> <password>

In order to run our game client, from the `root` folder: 
> java game/Game
