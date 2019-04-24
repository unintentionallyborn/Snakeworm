import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameboard extends JPanel implements KeyListener, ActionListener {

    private int[] snakeXLength = new int[750];
    private int[] snakeYLength = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon leftMouth;
    private ImageIcon rightMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;

    private ImageIcon snakeImage;

    private int moves = 0;

    private int snakeLength = 3;

    private Timer timer;
    private int delay = 100;

    private ImageIcon titleImage;

    public Gameboard() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.timer = new Timer(delay, this);
        timer.start();

    }

    public void paint(Graphics graphics) {
        if (moves == 0) {
            snakeXLength[2] = 50;
            snakeXLength[1] = 75;
            snakeXLength[0] = 100;

            snakeYLength[2] = 100;
            snakeYLength[1] = 100;
            snakeYLength[0] = 100;
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

        // draw background for playing area
        graphics.setColor(Color.black);
        graphics.fillRect(25, 75, 850, 575);


        rightMouth = new ImageIcon("rightmouth.png");
        rightMouth.paintIcon(this, graphics, snakeXLength[0], snakeYLength[0]);

        for (int i = 0; i < snakeLength; i++) {
            if (i == 0 && right) {
                rightMouth = new ImageIcon("rightmouth.png");
                rightMouth.paintIcon(this, graphics, snakeXLength[i], snakeYLength[i]);
            }
            if (i == 0 && left) {
                leftMouth = new ImageIcon("leftmouth.png");
                leftMouth.paintIcon(this, graphics, snakeXLength[i], snakeYLength[i]);
            }
            if (i == 0 && down) {
                downMouth = new ImageIcon("downmouth.png");
                downMouth.paintIcon(this, graphics, snakeXLength[i], snakeYLength[i]);
            }
            if (i == 0 && up) {
                upMouth = new ImageIcon("upmouth.png");
                upMouth.paintIcon(this, graphics, snakeXLength[i], snakeYLength[i]);
            }

            if (i != 0) {
                snakeImage = new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this, graphics, snakeXLength[i], snakeYLength[i]);
            }

        }

        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right) {
            for (int i = snakeLength - 1; i >= 0; i--) {
                snakeYLength[i + 1] = snakeYLength[i];

            }
            for (int i = snakeLength; i >= 0; i--) {
                if (i == 0) {
                    snakeXLength[i] = snakeXLength[i] + 25;
                } else {
                    snakeXLength[i] = snakeXLength[i - 1];
                }
                if(snakeXLength[i] > 850) {
                    snakeXLength[i] = 25;
                }
            }
            repaint();
        }

        if (left) {
            for (int i = snakeLength - 1; i >= 0; i--) {
                snakeYLength[i + 1] = snakeYLength[i];

            }
            for (int i = snakeLength; i >= 0; i--) {
                if (i == 0) {
                    snakeXLength[i] = snakeXLength[i] - 25;
                } else {
                    snakeXLength[i] = snakeXLength[i - 1];
                }
                if(snakeXLength[i] < 25) {
                    snakeXLength[i] = 850;
                }
            }
            repaint();
        }

        if(up) {
            for (int i = snakeLength - 1; i >= 0; i--) {
                snakeXLength[i + 1] = snakeXLength[i];

            }
            for (int i = snakeLength; i >= 0; i--) {
                if (i == 0) {
                    snakeYLength[i] = snakeYLength[i] - 25;
                } else {
                    snakeYLength[i] = snakeYLength[i - 1];
                }
                if(snakeYLength[i] < 75) {
                    snakeYLength[i] = 625;
                }
            }
            repaint();
        }

        if(down) {
            for (int i = snakeLength - 1; i >= 0; i--) {
                snakeXLength[i + 1] = snakeXLength[i];

            }
            for (int i = snakeLength; i >= 0; i--) {
                if (i == 0) {
                    snakeYLength[i] = snakeYLength[i] + 25;
                } else {
                    snakeYLength[i] = snakeYLength[i - 1];
                }
                if(snakeYLength[i] > 625) {
                    snakeYLength[i] = 75;
                }
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves++;
            //right = true;
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
            //left = true;
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
            //up = true;
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
            //down = true;
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
}
