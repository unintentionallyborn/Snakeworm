import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Gameboard gameboard = new Gameboard();

        frame.setTitle("SnakeGame");
        frame.setBounds(10, 10, 905, 700);
        frame.setBackground(Color.DARK_GRAY);
        //frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(gameboard);
        //frame.setVisible(true);
        frame.setVisible(true);
        //testing commit
    }
}
