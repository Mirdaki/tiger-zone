# Tiger Zone

Game based on carcassonne for CEN3031

# Team E

Group Members and Usernames

- Matthew Booe - mirdaki
- Charley Chau - charleychau
- Josiah Crepeau - crepeau
- Tana Konda - TanaKonda
- Connor Ward - conward
- Zak Mills - 

## Overall Structure

This is the outline for the interactions of the game

- Network Communication
	- Starts games
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
