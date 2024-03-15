import java.awt.Graphics;
import java.awt.*;
import javax.swing.JFrame;


public class EnemyProjectile extends GameObject{


    public EnemyProjectile(int startingX, int startingY) {
        x = startingX;
        y = startingY;
        width = 10;
        height = 10;

        /*try {
            EnemyProjectileImage = ImageIO.read(new File("EnemyShip2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } */
    
    }

    public void draw(Graphics g, JFrame gameWindow){
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
        g.setColor(Color.ORANGE);
        g.fillOval(x+1, y+1, width-2, height-2);
    }

    public void update() {
        y = y +5;
        if (y >= SpaceGame.GAME_HEIGHT) {
            SpaceGame.getGame().removeGameObject(this);
        }
    }


    
}
