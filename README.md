# TOURNAMENT BASED TIGER ZONE

This is a game rendition inspired and based on the popular board game known as Carcassonne by Klaus-Jurgen Wrede. It is in accordance with the "term" project for Introduction to Software Engineering (CEN3031), taught by Dave Small at the University of Florida. The intention is to have a working game capable of having two players, capable of implementing an Artificial Intelligence, and capable of communicating with a tournament server to compete against the rest of the class' implementations.

Any and all design documents procured during the course of this project have been placed inside of the /documentation/ subdirectory. Some are deprecated, and may have been changed since first implemented. The source code provides a much more in depth commentary and structure, and is the most up to date.

Any and all current source code have been placed inside of /src/.

Any and all resources have been placed inside of /resources/. This mainly contains XML formatted information to generate our tiles via a factory design pattern.

## TEAM INFORMATION
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

## GAME INTERACTION OUTLINE
This is the outline for the interactions of the game

1. TigerZoneClient connects to server
	- Logs in, waits for challenge  
2. TigerZoneClient creates 2 new Game for each round
	- Starts counter, passes game ID and tile stack
3. TigerZoneClient receives tile to place, passes to Game
	- Game passes tile to AI
	- AI decides on tile and meeple placement
	 	- AI looks at possible values from valid tiles
		- AI adds tile to board
	- Game receives AI decision and sends that to TigerZoneClient
4. TigerZoneClient receives move information, passes to Game
 	- Game adds other players tile to BoardObject
	- Passes if player passes
	- Game adds any meeples to tile
	- Increments in TigerZoneClient and Game
5. Repeat 3 and 4 until end of round and clear Games
6. TigerZoneClient ends on end game signal 

## COMPILATION
In order to compile our code, run from the `src` folder:
>javac game/\*.java entities/\*.java network/\*.java

In order to compile our tests, run
>

NOTE: If you do not use Eclipse to compile and run the code, you will have to change the filepath in TileDeck and TigerTile from
> TileDeck Line 43: File file = new File("resources/tiles.xml");  
to  
> File file = new File("../resources/tiles.xml");

and likewise on  
> TigerTile Line 153

## RUN
In order to run our network implementation, from the `root` folder:

>java network/TigerZoneClient <hostname> <port number> <server pasword> <username> <password>

In order to run our game client, from the `root` folder:
> java game/Game

## TESTS
__JUnit__:  
The files in `src/tests` are JUnit tests designed to test the methods of each entity in our system. All JUnit tests were made in Eclipse and can be run in Eclipse, similar to any other JUnit test files. The test code can be built and compiled in the same way as the client. The tests can then be run by pressing `CTRL+F11` or clicking `Run` in the top navigation bar. You can run all tests at once by highlighting the tests package in the package explorer and then running, or you can run one test at a time by highlighting the desired JUnit test and running it.
