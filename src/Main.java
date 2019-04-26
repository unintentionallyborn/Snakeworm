import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Gameboard gameboard = new Gameboard();

        frame.setTitle("SnakeGame");
        frame.setBounds(10, 10, 920, 750);
        frame.setBackground(Color.DARK_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        frame.add(gameboard);
        frame.setVisible(true);


        //testing commit
    }
}
