import java.awt.*;

public class Plane {
    int ship_size = 30;
    int x = GamePanel.screenWidth/2, y = GamePanel.screenHeight/2;
    int direction;
    KeyHandler keyH;

    Plane(KeyHandler keyH) {
        this.keyH = keyH;
    }

    public void update() {
        if (keyH.upPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed)
                accelerate();
            else if (keyH.leftPressed)
                rotateLeft();
            else
                rotateRight();
        }
    }

    public void accelerate() {
        int speed = 5;
        x += (int) (speed * Math.cos(Math.toRadians(direction)));
        y += (int) (speed * Math.sin(Math.toRadians(direction)));
    }

    public void rotateLeft() {
        direction -=2;
        x += (int) Math.cos(Math.toRadians(direction));
        y += (int) Math.sin(Math.toRadians(direction));
    }

    public void rotateRight() {
        direction += 2;
    }

    public void draw(Graphics g) {
        int[] xPoints = {x , x-ship_size, x+ship_size};
        int[] yPoints = {y, y+ship_size, y+ship_size};
        g.setColor(Color.WHITE);
        g.drawPolygon(xPoints, yPoints, 3);
    }
}
