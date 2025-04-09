import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class RotateTriangle extends JPanel {
    private static final int screenWidth = 400;
    private static final int screenHeight = 400;
    private static final int centerX = 200;
    private static final int centerY = 200;
    private double angle = 0;
    private final int[] xPoints = { -20, 0, 20 };
    private final int[] yPoints = { 20, -20, 20 };
    private int yPos = 0;
    private int xPos = 0;
    private double direction = 270;


    public RotateTriangle() {
        init();
        bindKeyEvents();
    }

    private void init() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    private void bindKeyEvents() {
        Action rotateLeftAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotateTriangle(-5.0);
            }
        };
        Action rotateRightAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotateTriangle(5.0);
            }
        };
        Action moveForwardAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveTriangle();
            }
        };
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "rotateLeft");
        getActionMap().put("rotateLeft", rotateLeftAction);

        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "rotateRight");
        getActionMap().put("rotateRight", rotateRightAction);

        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveForward");
        getActionMap().put("moveForward", moveForwardAction);

        setFocusable(true);
    }

    private void rotateTriangle(double rotation) {
        angle += rotation;
        direction += rotation;

        if (direction >= 360) direction -= 360;
        if (direction < 0) direction += 360;
        repaint();
    }

    private void moveTriangle() {
        double radian = Math.toRadians(direction);
        xPos += (int) (5 * Math.cos(radian));
        yPos += (int) (5 * Math.sin(radian));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.translate(centerX + xPos, centerY + yPos);
        g2d.rotate(Math.toRadians(angle));
        g2d.setColor(Color.BLUE);
        g2d.fillPolygon(xPoints, yPoints, 3);

//        g2d.drawLine(-100, 0, 100, 0);
//        g2d.drawLine(0, -100, 0, 100);

        g2d.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rotate Triangle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            RotateTriangle triangle = new RotateTriangle();
            frame.add(triangle);
            frame.pack();

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
