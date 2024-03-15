import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class PlayerShip extends GameObject implements KeyListener {
    private boolean rightKeyPressed = false;
	private boolean leftKeyPressed = false;
	private boolean spaceKeyPressed = false;
	private boolean fireProjectile = false;

	public PlayerShip(int startingX, int startingY) {
        x = startingX;
        y = startingY;
        height = 25;
        width = 25;

         /*try {
            playerImage = ImageIO.read(new File("PlayerShip.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } */
		// ADD CODE HERE TO INITIALIZE THE PLAYER SHIP
		// you will need to set the 4 attributes from GameObject: x, y, width, height
	}

	public void update() {
        if (rightKeyPressed) {
            x = x + 10;
        }
		if (leftKeyPressed) {
            x = x - 10;
        }
		if (spaceKeyPressed) {
			fireProjectile = true;
			
		}
		if (fireProjectile) {
			PlayerProjectile projectile = new PlayerProjectile(x, y - 20);
            SpaceGame.getGame().addGameObject(projectile);
			fireProjectile = false;
		}
		// ADD CODE HERE TO UPDATE THE PLAYER SHIP
		// this will be executed once per iteration of the game loop
	}

	public void draw(Graphics g, JFrame gameWindow) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);

		

        //g.drawImage(playerImage, x, y, width, height, gameWindow);
		// ADD CODE HERE TO DRAW THE PLAYER SHIP IN THE GAME WINDOW
		// this will be executed once per iteration of the game loop
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// This method will remain empty
	}

	@Override
	public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightKeyPressed =  true;
        }
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftKeyPressed =  true;
        }
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			spaceKeyPressed = true;
		}

		// This method will be useful when writing the code allowing the user to control the PlayerShip
		// This method is called when a key is pressed on the keyboard
	}

	@Override
	public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightKeyPressed =  false;
        }
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftKeyPressed =  false;
        }
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			spaceKeyPressed = false;
			fireProjectile = false;
		}
		// This method will be useful when writing the code allowing the user to control the PlayerShip
		// This method is called when a key is released on the keyboard
	}
}

