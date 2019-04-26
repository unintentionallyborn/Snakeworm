import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameboard extends JPanel implements KeyListener, ActionListener {

    private int[] snakeXCoor = new int[750];
    private int[] snakeYCoor = new int[750];

    private int[] enemyXCoor = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] enemyYCoor = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon leftMouth;
    private ImageIcon rightMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;

    private ImageIcon snakeImage;

    private ImageIcon enemyImage;

    private Random random = new Random();

    private int xPos = random.nextInt(34);
    private int yPos = random.nextInt(23);

    private int score = 0;

    private int moves = 0;

    private int snakeLength = 3;

    private Timer timer;


    private ImageIcon titleImage;

    public Gameboard() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.timer = new Timer(80, this);
        timer.start();

    }

    public void paint(Graphics graphics) {
        if (moves == 0) {
            snakeXCoor[2] = 50;
            snakeXCoor[1] = 75;
            snakeXCoor[0] = 100;

            snakeYCoor[2] = 100;
            snakeYCoor[1] = 100;
            snakeYCoor[0] = 100;
        }

        // draws title image border
        graphics.setColor(Color.white);
        graphics.drawRect(24, 10, 851, 55);

        // draws the title image
        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, graphics, 25, 11);

        // draw border for playing area
        graphics.setColor(Color.WHITE);
        graphics.drawRect(24, 74, 851, 577);

        //draw scores
        graphics.setColor(Color.white);
        graphics.setFont(new Font("arial", Font.PLAIN, 14));
        graphics.drawString("Scores: " + score, 780, 30);

        //draw length
        graphics.setColor(Color.white);
        graphics.setFont(new Font("arial", Font.PLAIN, 14));
        graphics.drawString("Length: " + snakeLength, 780, 50);


        // draw background for playing area
        graphics.setColor(Color.black);
        graphics.fillRect(25, 75, 850, 575);

        //draw snake
        if (moves == 0) {
            rightMouth = new ImageIcon("rightmouth.png");
            rightMouth.paintIcon(this, graphics, snakeXCoor[0], snakeYCoor[0]);
        }

        for (int i = 0; i < snakeLength; i++) {
            if (i == 0 && right) {
                rightMouth = new ImageIcon("rightmouth.png");
                rightMouth.paintIcon(this, graphics, snakeXCoor[i], snakeYCoor[i]);
            }
            if (i == 0 && left) {
                leftMouth = new ImageIcon("leftmouth.png");
                leftMouth.paintIcon(this, graphics, snakeXCoor[i], snakeYCoor[i]);
            }
            if (i == 0 && down) {
                downMouth = new ImageIcon("downmouth.png");
                downMouth.paintIcon(this, graphics, snakeXCoor[i], snakeYCoor[i]);
            }
            if (i == 0 && up) {
                upMouth = new ImageIcon("upmouth.png");
                upMouth.paintIcon(this, graphics, snakeXCoor[i], snakeYCoor[i]);
            }

            if (i != 0) {
                snakeImage = new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this, graphics, snakeXCoor[i], snakeYCoor[i]);
            }
        }

        enemyImage = new ImageIcon("enemy.png");
        enemyImage.paintIcon(this, graphics, enemyXCoor[xPos], enemyYCoor[yPos]);

        if (enemyXCoor[xPos] == snakeXCoor[0] &&
                enemyYCoor[yPos] == snakeYCoor[0]) {
            score++;
            snakeLength++;
            xPos = random.nextInt(34);
            yPos = random.nextInt(23);
        }

        for (int i = 1; i < snakeLength; i++) {
            if (snakeXCoor[i] == snakeXCoor[0]
                    && snakeYCoor[i] == snakeYCoor[0]) {
                right = false;
                left = false;
                up = false;
                down = false;

                graphics.setColor(Color.red);
                graphics.setFont(new Font("arial", Font.BOLD, 50));
                graphics.drawString("GAME OVER", 300, 300);

                graphics.setColor(Color.WHITE);
                graphics.setFont(new Font("arial", Font.PLAIN, 25));
                graphics.drawString("Press 'spacebar' to restart", 300, 340);

                graphics.drawString("Final Score: " + score, 300, 380);
            }
        }
        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

        if (right) {
            for (int i = snakeLength - 1; i >= 0; i--) {
                snakeYCoor[i + 1] = snakeYCoor[i];

            }
            for (int i = snakeLength; i >= 0; i--) {
                if (i == 0) {
                    snakeXCoor[i] = snakeXCoor[i] + 25;
                } else {
                    snakeXCoor[i] = snakeXCoor[i - 1];
                }
                if (snakeXCoor[i] > 850) {
                    snakeXCoor[i] = 25;
                }
            }

        }

        if (left) {
            for (int i = snakeLength - 1; i >= 0; i--) {
                snakeYCoor[i + 1] = snakeYCoor[i];

            }
            for (int i = snakeLength; i >= 0; i--) {
                if (i == 0) {
                    snakeXCoor[i] = snakeXCoor[i] - 25;
                } else {
                    snakeXCoor[i] = snakeXCoor[i - 1];
                }
                if (snakeXCoor[i] < 25) {
                    snakeXCoor[i] = 850;
                }
            }

        }

        if (up) {
            for (int i = snakeLength - 1; i >= 0; i--) {
                snakeXCoor[i + 1] = snakeXCoor[i];

            }
            for (int i = snakeLength; i >= 0; i--) {
                if (i == 0) {
                    snakeYCoor[i] = snakeYCoor[i] - 25;
                } else {
                    snakeYCoor[i] = snakeYCoor[i - 1];
                }
                if (snakeYCoor[i] < 75) {
                    snakeYCoor[i] = 625;
                }
            }

        }

        if (down) {
            for (int i = snakeLength - 1; i >= 0; i--) {
                snakeXCoor[i + 1] = snakeXCoor[i];

            }
            for (int i = snakeLength; i >= 0; i--) {
                if (i == 0) {
                    snakeYCoor[i] = snakeYCoor[i] + 25;
                } else {
                    snakeYCoor[i] = snakeYCoor[i - 1];
                }
                if (snakeYCoor[i] > 625) {
                    snakeYCoor[i] = 75;
                }
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            moves = 0;
            score = 0;
            snakeLength = 3;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves++;
            if (!left) {
                right = true;
            } else {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moves++;
            if (!right) {
                left = true;
            } else {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moves++;
            if (!down) {
                up = true;
            } else {
                down = true;
                up = false;
            }

            right = false;
            left = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moves++;
            if (!up) {
                down = true;
            } else {
                down = false;
                up = true;
            }
            left = false;
            right = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void printSnakeX() {
        for (int i = 0; i < 3; i++) {
            System.out.println(snakeXCoor[i] + " ");
        }
    }

    public void printSnakeY() {
        for (int i = 0; i < 3; i++) {
            System.out.println(snakeYCoor[i] + " ");
        }
    }
}
