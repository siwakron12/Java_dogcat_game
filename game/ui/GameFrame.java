package game.ui;

import game.animal.Animal;
import game.core.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    private Game game;

    public GameFrame(Animal p1, Animal p2) {
        game = new Game(p1, p2);

        setTitle("Dog Cat Throw Game");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
