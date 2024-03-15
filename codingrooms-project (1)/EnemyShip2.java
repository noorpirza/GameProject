import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class EnemyShip2 extends GameObject implements KeyListener{
    private int movingRight = 1;
    private int movingUp = 1;
    private boolean fireProjectile;

    public EnemyShip2(int startingX, int startingY) {
        x = startingX;
        y = startingY;
        height = 25;
        width = 25;
    
    }
    public void update(){
        if (x > (SpaceGame.GAME_WIDTH-width-20)) {
            movingRight = -1;

        }
        if (x < 20) {
            movingRight = 1;
        }
        x += movingRight*2;
        if (y > (SpaceGame.GAME_HEIGHT-height-350)) {
            movingUp = -1;

        }
        if (y <= 50) {
            movingUp = 1;
        }
        y += movingUp*2;
        fireProjectile = Math.random() * 100 > 99;
        if (fireProjectile) {
            EnemyProjectile projectile = new EnemyProjectile (x, y + 20);

            SpaceGame.getGame().addGameObject(projectile);
            fireProjectile = false;
        }
        
    }
    public void draw(Graphics g, JFrame gameWindow){
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e){

    }

    public void keyReleased(KeyEvent e){

    }
    
}


