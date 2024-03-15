import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpaceGame extends JPanel {

	// These values can be modified if you would like to change the size of the game window
	public static final int GAME_WIDTH = 500;
	public static final int GAME_HEIGHT = 500;

	// Do not erase or modify these attributes, they are important for the overall game structure
	private static SpaceGame game;
	private JFrame gameWindow;
	private List<GameObject> objectsToAdd;
	private List<GameObject> objectsToRemove;

	// This is a variable that allows us to quickly find the PlayerShip object if we need it
	private PlayerShip player;
	private EnemyShip enemy;
	private EnemyShip enemy2;
	private EnemyShip enemy3;
	private EnemyShip2 enemy4;
	private EnemyShip2 enemy5;
	private EnemyShip2 enemy6;

	// This is the List that contains all the active GameObjects that should
	// be updated and drawn in the game loop
	private List<GameObject> activeGameObjects;

	// Feel free to add new attributes if you need them
	// (for example, if you would like to keep track of the player's score



	public static SpaceGame getGame() {
		return game;
	}
	
	public static void main(String[] args) throws InterruptedException {

		// This code is used to create the main Java object for the game and start running the game
		game = new SpaceGame();
		game.run();
	}

	public SpaceGame() {
		// When the SpaceGame object is created, initialize the GameObjects
		initializeGameObjects();
		// And create the window in which the game will take place
		initializeGameWindow();
	}

	private void run() throws InterruptedException {

		// This is the main game loop and where we will repeatedly update the game
		while (true) {
			
			// This is just to set a small delay between each of the game's "frame"
			// Otherwise it will go too fast
			Thread.sleep(20);

			// DO NOT REMOVE THIS, this allows us to update the List of active game objects
			// It adds any new objects that were created in the last iteration of the game loop
			addGameObjects();

			// Write your code here to update the game every iteration of the loop

			// ADD YOUR CODE HERE
            for (GameObject object: activeGameObjects) {
                object.update();
            }
			checkForCollisions();


			// DO NOT REMOVE THIS, this allows us to update the List of active game objects
			// It removes any objects that needs to be removed from the game window based on
			// this iteration of the game loop
			deleteGameObjects();
			
			repaint();
		}
	}

	private void checkForCollisions(){
		for(int i = 0; i < activeGameObjects.size(); i++) {
			for(int j = 0; j < activeGameObjects.size(); j++) {
				GameObject firstObject = activeGameObjects.get(i);
				GameObject secondObject = activeGameObjects.get(j);

				if(firstObject == secondObject){
					continue;
				}
				if(firstObject.collidesWith(secondObject)) {
					if(firstObject instanceof PlayerProjectile && secondObject instanceof EnemyShip){
						removeGameObject(firstObject);
						removeGameObject(secondObject);
					}
					if(firstObject instanceof PlayerProjectile && secondObject instanceof EnemyShip){
						removeGameObject(firstObject);
						removeGameObject(secondObject);
					}
					if(firstObject instanceof PlayerProjectile && secondObject instanceof EnemyShip2){
						removeGameObject(firstObject);
						removeGameObject(secondObject);
					}
					if(firstObject instanceof PlayerProjectile && secondObject instanceof EnemyShip2){
						removeGameObject(firstObject);
						removeGameObject(secondObject);
					}
					if(firstObject instanceof EnemyProjectile && secondObject instanceof PlayerShip){
						removeGameObject(firstObject);
						removeGameObject(secondObject);
						for(int y = 0; y < activeGameObjects.size(); y++){
							GameObject thirdObject = activeGameObjects.get(y);
							if(activeGameObjects.get(y) instanceof PlayerProjectile){
								removeGameObject(thirdObject);
							}
						}
					}
				}

			}
		}
	}

	@Override
	public void paint(Graphics g) {
		// DO NOT DELETE THIS LINE
		// This performs the basic operation of drawing the game window
		super.paint(g);

		// Add code here to draw your game (i.e., background, GameObjects, etc.)

		// ADD CODE HERE
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        for (GameObject object: activeGameObjects) {
            object.draw(g, gameWindow);
        }

	}

	private void initializeGameObjects() {

		// Initialize the 3 lists that are used to manage game objects
		activeGameObjects = new ArrayList<>();
		objectsToAdd = new ArrayList<>();
		objectsToRemove = new ArrayList<>();

		// Create the player's ship and add it to the game
		// The player's starting position in the middle of the game window (on the x axis) and 125 pixels above the bottom of the window
		player = new PlayerShip(GAME_WIDTH / 2, GAME_HEIGHT - 125);
		addGameObject(player);

		enemy = new EnemyShip(GAME_WIDTH / 2, GAME_HEIGHT - 450);
		addGameObject(enemy);

		enemy2 = new EnemyShip(GAME_WIDTH - 450, GAME_HEIGHT - 300);
		addGameObject(enemy2);

		enemy3 = new EnemyShip(GAME_WIDTH - 100, GAME_HEIGHT - 350);
		addGameObject(enemy3);

		enemy4 = new EnemyShip2(GAME_WIDTH / 3, GAME_HEIGHT - 300);
		addGameObject(enemy4);

		enemy5 = new EnemyShip2(GAME_WIDTH - 400, GAME_HEIGHT - 350);
		addGameObject(enemy5);

		enemy6 = new EnemyShip2(GAME_WIDTH - 50, GAME_HEIGHT - 450);
		addGameObject(enemy6);


		// This is where you will add any other code that is useful to prepare the game before it starts
		// For example, adding the enemy ships to the game

		// ADD YOUR CODE HERE
	}
	
	private void initializeGameWindow() {

		// YOU WILL MOST LIKELY NOT NEED TO MODIFY THIS METHOD KEEP IT AS IS
		// This code creates the game window, feel free to ignore it
		gameWindow = new JFrame("Game window");
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gameWindow.setSize(GAME_WIDTH, GAME_HEIGHT);
		gameWindow.setResizable(false);
		gameWindow.setLocationRelativeTo(null);
		
		this.setSize(GAME_WIDTH, GAME_HEIGHT);

		// This code, makes it possible to use the keyboard to control the PlayerShip object
		gameWindow.addKeyListener(player);
		this.requestFocus();
		
		gameWindow.add(this);
		gameWindow.setVisible(true);
	}

	// THE NEXT 4 METHODS SHOULD NOT BE CHANGED/REMOVED
	// Those methods are useful as a mechanism to avoid modifying the list of activeGameObjects
	// at the same time that we are iterating on that same list
	public void addGameObject(GameObject newObject) {
		objectsToAdd.add(newObject);
	}

	private void addGameObjects() {
		activeGameObjects.addAll(objectsToAdd);
		objectsToAdd.clear();
	}

	public void removeGameObject(GameObject objectToRemove) {
		objectsToRemove.add(objectToRemove);
	}

	private void deleteGameObjects() {
		activeGameObjects.removeAll(objectsToRemove);
		objectsToRemove.clear();
	}
}
