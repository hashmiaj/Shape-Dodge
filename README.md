# androidapp-androidapp-team12

<-------------First Iteration------------->

Main Goal: For the first iteration our main goal was to complete all of the user stories pertaining to 
the basic frame work of each activity. Essentially, every activity needed to be made and basic functionality
for each also need to be implemented.

GameActivity.class/activity_game.xml/GameActivityTest

	Breakdown:
	This activity is where the game is to be played. For the first iteration, we set up the UI framework
	so that we can build and develop on a foundation. We tested if the game appears after the user
	clicks the play button.
	
HighscoreActivity.class/activity_highscore.xml/HighscoreActivityTest
	
	Breakdown:
	This activity is where the high scores are stored and shown. For the first iteration, we set up 
	the UI framework so that we can build and develop on a foundation. We tested if the high scores
	activity is shown after the user clicks the high score button in the MainMenu.
	
MainMenu.class/activity_mainmenu.xml/MainMenuTest
	
	Breakdown:
	This activity is where the app initially starts and allows user direction. For the first iteration,
	we set up the UI framework so that we can build and develop on a foundation. We tested if all 
	buttons were click-able. Additionally, we tested if the main menu appeared on start up. 
	
PauseActivity.class/activity_pause.xml/PauseActivityTest
	
	Breakdown:
	This activity is where the user can pause in game and can configure the game. For the first
	iteration, we set up the UI framework so that we could build and develop on a foundation. We
	tested if the pause menu is displayed after the pause button within the game is clicked. Also,
	we tested if the buttons functioned as anticipated.
	
SettingsActivity.class/activity_settings.xml/SettingsActivityTest
	
	Breakdown:
	This activity is where the user can alter the settings of the game and access credits. For the
	first iteration, we set up the UI framework so that we could build and develop on a foundation. 
	We tested if the setting buttons are displayed. Additionally, we tested if the main menu button
	functions correctly.

JoystickActivity.class/
	
	Breakdown:
	This class is responsible for the joystick view. Since this was an outside component, and 
	player movement implementation is not completed, we have not tested it.
	
Difficulties: 
Some difficulties we encountered were figuring out how to traverse between activities.

<-------------Second Iteration------------->

Main Goal: For the second iteration our main goal was to complete most, if not all functionality of the game 
itself. We relied heavily with testing with in game functionality rather than espresso. This iteration was
fundamentally rigorous with implementation of functionality.

HighscoreActivity.class/activity_highscore.xml/HighscoreActivityTest
GameActivityTest
MainMenu.class/activity_mainmenu.xml/MainMenuTest
PauseActivity.class/activity_pause.xml/PauseActivityTest
SettingsActivity.class/activity_settings.xml/SettingsActivityTest

	Breakdown: These classes and files were basically left unchanged, except with a tad bit of refactoring.
	
GameActivity.class/activity_game.xml
	
	Breakdown: These classes saw a few reworks to facilitate efficient ways to display the HUD and joystick.
	Mainly, the score and lives and joystick.
	
GameView.class
	Breakdown: This class is the key component of our entire game and saw numerous changes. As of now, this
	class stores key elements of the game such as lives number, score, game screen height, game screen width
	the player object, all of the enemy objects, the style of font of some elements of the HUD, the valid
	surface on which to create the game on, and the custom thread which runs the game. Additionally, we had
	major methods which provided for implementation of the game.
	
	Further Breakdown of Methods:
	init():
	This method is responsible for initializing the game, where the thread is set to null, score is 0, lives is 3
	and the text style is determined. this will eventually be responsible for when the game is restarted.
	
	start():
	This method is responsible for starting the thread of which the game is to run on and to initialize both the
	player object and the enemy control object.
	
	run():
	This method is the main thread run method. While the game is playing, the thread will continuously, update Coordinates
	and states, paint all objects to the screen including the score and lives, and control the thread.
	
	updateCoordinates():
	This method is responsible for all updating functionality. it will move all enemies and check if they are off 
	the screen, move the player based on joystick output values and check if the player is in bounds, increase the
	difficulty based on score, check if the player has been hit and remove lives based on it's hit value, and
	check if lives = 0 so game over is true.
	
	paint():
	This method is responsible for painting all objects to the screen. This includes the lives and score text which
	update regularly. Additionally, it sets the drawable space to the set screen parameters. Which allows the game to
	paint the background black, then paints the player to the screen, then all enemies on top of the player (if
	applicable) and then unlocks where to paint.
	
	threadControl():
	This method is responsible for controlling the thread. It essentially pauses the thread every 17 mili seconds, which 
	equates to 60 FPS (frames per second), and catches if an exception is thrown.
	
	displayText():
	This method prints a centered integer value into a surface view based on the font style declared in the gameView class.
	
	increaseDifficulty():
	At the moment this method increases the spawn rate of enemies based on the score.

	(Gotchu GETTERS AND SETTERS)
	
JoystickView.class
	
	Breakdown: This class was changed a bit to allow us to get values from it (private methods changed to public)
	
Player.class

	Breakdown: This class is responsible for storing parameters and characteristics of the player object. Key elements 
	such as	the x position, y position, x direction speed, y direction speed, player paint style, and game screen bounds.
	Additionally, this class is responsible for player functionality.
	
	Further Breakdown of Methods:
	move():
	This method is responsible for player movement. All movement is based on joystick input. Currently we have have vertical
	and	horizontal movement. Additionally, we based speed on the power of the joystick.
	
	checkBounds():
	This method is responsible for player boundaries. If the player is trying to go off screen, then the player will be
	restricted by the game screen.
	
	paint():
	This method draws the character model to the screen. Currently, the character model is a green circle. We hope to make
	player color customizable in the next iteration.
	
	(Gotchu GETTERS AND SETTERS)
	
ShapeEnemy.class
	
	Breakdown: This class is responsible for storing parameters and characteristics of an individual shape enemy object.
	Elements such as x position, y position, width, height, and boolean direction values.
	
	Further Breakdown of Methods:
	move():
	This method is responsible for enemy movement. movement is based on directional boolean values. Additionally, enemy
	movement is static. 
	
	isMovingRight():
	Checks if enemy is moving right.
	
	isMovingLeft():
	Checks if enemy is moving left.
	
	isMovingUp():
	Checks if enemy is moving up.
	
	isMovingDown():
	Checks if enemy is moving down.
	
	goUp():
	Sets up directional boolean value to true.
	
	goDown():
	Sets down directional boolean value to true.
	
	goRight():
	Sets right directional boolean value to true.
	
	goLeft():
	Sets left directional boolean value to true.
	
	(Gotchu GETTERS AND SETTERS)

ShapeEnemyControl.class

	Breakdown: This class is responsible for storing parameters and characteristics across all enemy objects. Elements including
	the maximum x position possible onScreen, max y position possible onScreen, minimum enemy dimension, direction value, enemy
	paint style. Additionally, this class is responsible for all enemy functionality.
	
	Further Breakdown of Methods:
	paint():
	This method is responsible for painting all enemy objects. Draws the enemy as rectangles.
	
	checkOff():
	This method is responsible for checking if an enemy is drawn off-screen and is then removed from the group of enemies being drawn.
	Should be based on the direction boolean value of the individual enemy.
	
	addEnemy():
	This method is responsible for adding an enemy with random parameters into the group of drawn enemies. This method creates 
	an enemy which starts at a random location off-screen, with random width and random height that is at most 20% of the screen, 
	and randomly set directional boolean value. 
	
	removeAll():
	Removes all shape enemies.
	
	moveAll():
	Moves all enemies based on directional boolean value.
	
	checkHit():
	Checks if the center of the character model is within any bounds of any enemy drawn on the screen. returns a boolean value if 
	true or false.
	
boundsTest.class
	
	Breakdown: This class tests if the player model is within the screen boundaries. It moves left, right, up, and down 5 times 
	and then checks if the x and y coordinates are within bounds. As of now, the test passes as expected but crashes at the end.
	We believe the crash has something to do with not closing the activity; however, we have not implemented any restart, pause,
	or end game for this iteration. According to the logs, the output messages are as expected.
	
JoystickTest.class

	Breakdown: This class tests if the player model is moving when the joystick is used. It moves each direction once and
	determines if its previous coordinate values are greater than or less than the new coordinate values. As of now, the test passes
	as expected but crashes at the end.	We believe the crash has something to do with not closing the activity; however, we have not 
	implemented any restart, pause,	or end game for this iteration. According to the logs, the output messages are as expected.
	
(EnemyTest)

	Breakdown: Since enemy objects created random processes/occurrences, we felt that the best way to test these methods were to use 
	Android	Studio's Debugger and track values of all enemy objects. After countless iterations, we found that the methods 
	corresponding with the enemy objects worked as expected. This can be visualized by pressing the play in the main menu screen 
	and allowing the game to run. The number of shapes should increase as the score increases.
	
(DifficultyTest)

	Breakdown: The difficulty of the game is determined by the occurrence and number of enemies that are tossed at the player.
				The best way to test these methods were to use 	Android	Studio's Debugger and track values of all enemy objects, and
				how often they occur. In addition to this the app was put on an android phone can be visual tested; as the score of the 
				player increases then the number of enemies that are generated also increases.

Difficulties:
This iteration was very intensive with functionality and implementation. Since we all were pretty inexperienced with espresso, we had
trouble coding ways to simulate the actions we wanted to test. Also we could not resolve the crash that is involved with the test above. 
Additionally, we had a lot of random processes which could not be predetermined to code by. Regarding implementation,
we had difficulty figuring out how to get the initial size of the game screen which was a major key in initializing the classes within 
the gameView. Additionally, we wanted to use Textview for score and lives however they updated on a seperate thread which conflicted 
with the main game thread. We also had trouble with figuring out how the surface view and canvas communicated with each other.
holder and surface view worked.

<-------------Third Iteration-------------->

Main Goal: For the third iteration, our main goal was to introduce quality of life, incorporate customizable options, improve aesthetic
design, and add extra functionality overlooked in iteration 2 for completeness. We overhauled preliminary design choices and incorporated 
solidified design decisions for our game in this iteration. This iteration was surprisingly challenging due to how the activities 
communicated between one another.

HighscoreActivityTest.class
GameActivity.class/GameActivityTest.class
activity_game.xml
boundsTest.class
SettingsActivity.class/SettingsActivityTest.class/
MainMenuTest.class/activity_mainmenu.xml
JoystickTest.class/JoystickActivity.class/JoystickView.class
PauseActivity.class/PauseActivityTest.class
	
	Breakdown: These class and xml files were altered by refactoring and adding universal aesthetic design. The pause activity class pretty
	much was deleted.
	
GameActivity.class 
	
	Breakdown: This class was changed dramatically to incorporate quality of life within the game. Music was added to this top level class so
	it could easily be managed and controlled. Shared Preferences were introduced as well to grab saved game values from the settings activity.
	Lastly, the pause button saw changes in functionality. Instead of travelling to another intent, we altered it to display a dialog within the
	Game Activity. This simplified our coding ten fold.
	
GameView.class

	Breakdown: This class saw tremendous changes as well. Quality of life, customizable elements, and complete functionality were introduced
	with the addition of the player color, minimum dimensions of the enemy shapes, maximum dimensions of the enemy shape in the form of ratio,
	number of possible enemy directions, power up functionality, countdown functionality, and a game over condition.
	
	
	Further Breakdown of Methods:
	init():
	This method was changed to incorporate the initial counter, where boolean values and paint styles were set for the countdown.
	
	restart():
	This method is responsible for restarting the game. Added removal of all powerups as well and gameover conditions are reset.
	
	start():
	This method was changed so that the saved setting values are incorporated in the game.
	
	stop():
	This method is responsible for generating the gameOver dialog screen on the UIthread.
	
	resume():
	This method was changed to incorporate the countdown on resume.
	
	pause():
	This method was changed to incorporate the cancellation of the countdown on pause.
	
	run():
	This method was changed to incorporate the countdown in the beginning, additionally, the gameover functionality was added to the end of
	the run method where stop is called.
	
	setUpCount():
	This method is responsible for setting up the counter of the countdown.
	
	updateCount():
	This method is responsible for updating the counting down sequence and attempts to make it seem like real seconds.
	
	updateCoordinates():
	This method was changed to incorporate the frozen powerup, powerup duration counter, powerup hit detection, removal of power ups on hit,
	and game over checker with unlock colors functionality. 
	
	paint():
	Unchanged
	
	threadControl():
	Unchanged
	
	displayText():
	Unchanged
	
	displayCounter():
	This method is responsible for displaying the counter in the middle of the gameView.
	
	increaseDifficulty():
	This method was changed to incorporate a steady generation of power ups based on shared preferences. Additionally, this method was changed
	to incorporate other shared preference saved data.
	
	onStateChange():
	This method is responsible for creating a custom dialog based on the game_over.xml file. If the score fits the conditional means, then the 
	user unlocks a color and is notified in the dialog. The final score is shown. Lastly the submit button allows the user to pass the score
	and return to the main menu.
	
Instructions.class/instructions.xml
	
	Breakdown: This class is responsible for control of the instructions activity. The only controllable object to add functionality was the
	main menu button. This activity displays the instructions of the game and teaches the user how to play.
	
MainMenu.class
	
	Breakdown: This class saw minor changes. The main change was adding the necessary button to reach the instructions activity.
	
Player.class
	
	Breakdown: This class saw minor changes as well. The main change was adding the customizable array of colors and the corresponding chosen
	color index from the saved shared preferences data.
	
SettingsActivity.class
	
	Breakdown: This class saw massive changes. Basically, all of the buttons needed its corresponding functionality. Additionally, the 
	values are cross referenced by the shared preferences.
	
	Further Breakdown of Methods:
	MaimMenuButton():
	Unchanged
	
	PlayerColorButton():
	This button onclick action is responsible for setting the corresponding index of the player to a color the user selects by incrementing the index.
	In doing so, this changes the font color to the selected player color.
	Colors: green, gray, yellow, cyan, magenta, blue, pink, purple, orange, gold, white 
	
	DifficultyButton():
	This button onclick action is responsible for setting the values of the gameView based on difficulty level. Additionally it changes the text of the 
	button to the corresponding difficulty.
	Easy:
	minimum enemy dimension is 10 pixels
	initial enemy spawn rate 100
	maximum enemy dimension is 16% of the screen
	allowable directions north south
	power up spawn rate 1000 score
	Medium:
	minimum enemy dimension is 20 pixels
	initial enemy spawn rate 100
	maximum enemy dimension is 20% of the screen
	allowable directions all
	power up spawn rate 750 score
	Hard:
	minimum enemy dimension is 30 pixels
	initial enemy spawn rate 75
	maximum enemy dimension is 25% of the screen
	allowable directions all
	power up spawn rate 500 score
	Insane:
	minimum enemy dimension is 50 pixels
	initial enemy spawn rate 50
	maximum enemy dimension is 25% of the screen
	allowable directions all
	power up spawn rate 300 score
	
	MusicButton():
	This button onclick action is responsible for toggle the musics. Text is changed as well to the current setting.
	
HighscoreActivity.class

	Breakdown: This class saw tremendous changes as well. We altered this class so that it could effectively communicate with the isGameOver 
	state and correctly sort the high scores in an orderly fashion. 
	
	Further Breakdown of Methods:
	onCreate():
	This method was changed greatly from the previous iteration. Instead of having constant template values, we changed this to read values and
	data from the Shared Preferences and incorporated these values into an ordered table sorted by player score. We utilized the hash map and for
	loops to easily format the data into the table. 
	
	updateData():
	This method is responsible for updating data across the gameView gameover state and the highscore activity shared preferences.
	
	MainMenuButton:
	Unchanged
	
ShapeEnemy.class

	Breakdown: This class saw minor changes. The main change was adding an integer responsible for designating a powerUp type for the top level 
	power up array. In doing so, we "recycled and reused" the ShapeEnemy class to represent powerups.
	
ShapeEnemyControl.class

	Breakdown: This class saw many changes. In order to accommodate the customizable settings, and added powerup functionality, we created
	dedicated variables and constants holding values such as the power color array, minimum dimensions, maximum dimensions ratio, direction 
	value, is frozen boolean variables, and powerup constants.
	
	Further Breakdown of Methods:
	paint():
	This method was changed a bit to accommodate the power ups. additionally, the color index is incremented so it produces the flickering effect.
	
	checkOff():
	Unchanged
	
	addEnemy():
	Unchanged
	
	addPowerUp():
	This method is responsible for generating a powerUp at a random location. This reuses aspects of the shape enemy class to save resources.
	In addition it randomly generates the type of power up.
	
	removeAll();
	Unchanged
	
	removePowerUps();
	Removes all powerups.
	
	moveAll():
	Unchanged
	
	checkHit():
	Unchanged
	
	checkPowerUpHit():
	This method is responsible for detecting when the hitbox of the player comes in contact with a power up. it will then return the type of
	power up it came into contact with and perform the power up.
	Freeze: makes the player invulnerable, and all shape enemies stop movement.
	life: grants one extra life.
	clear: nukes  all shape enemies.
	
	updatePowerCounter():
	This method is responsible for keeping track of the duration of the frozen power up. uses a counter to count the duration.
	
	getIsFrozen():
	returns if the power up isFrozen is active or not.
	
game_over.xml
	
	Breakdown: This xml layout provided the layout of the gameover screen where the user can enter a username which potentially could be stored
	into the high score if the value is a highscore. Additionally this layout will notify when a user has unlocked a new color.
		
difficultTest.class
	
	Breakdown: This class is responsible for testing the functionality of the difficulty button in the settings activity. This will click
	the difficulty button a certain amount of time and then check if it is equal to an expected difficulty.
	
playerColorTest.java

	Breakdown: This class is responsible for testing the functionality of the player color button in the settings activity. This will check
	the player color button a certain number of times and then check if it is equal to the expected player color.
	
Difficulties:
This iteration was difficult in  many areas. The main areas were the high score activity and functionality, the game over dialog, and the pause
dialog. The high score activity was problematic because we had to figure a way to effectively communicate between the game over game view state
and the high score data. We remedied the situation by utilizing the hashmap. The game over dialog was problematic as well because at first we
had the pause button travel to a new dedicated intent for pause. The problem we had was that when the game was paused and resumed, the thread 
would not start again when called because the gamescreen.start() was not being called within onResume() and onStart(). Threading issues was the main
problem when dealing with multiple intents and traversing them. We fixed this by creating a dialog with the same layout as the pause activity 
within the game activity. The game over activity was a problem as well since we wanted to create a dialog when isGameOver is true. Since knowing
when game over is true is dependent on the state of the gameView, we could not create the dialog in top level, Oncreate Onresume etc methods
so we had to pass the context and cast it as an Activity in order to manipulate and create a dialog in the game activity. When we did this we 
noticed that this could not be run on the same thread as the game and needed to be run on the UI thread since the dialog is a UI element/object. Here 
we had to overwrite the runOnUIThread to facilitate the creation of the dialog box. A lot of thread understanding was learned in this iteration.
Extra functionality was surprisingly easy as we came to understand these difficulties.