package game.ui;

import game.animal.Animal;
import game.core.*;
import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    private Game game;

public GameFrame(Animal p1, Animal p2) {
    game = new Game(p1, p2);

    setTitle("Dog Cat Throw Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

   setLayout(new BorderLayout());

    GamePanel gamePanel = new GamePanel(game);
    gamePanel.setPreferredSize(new Dimension(1000, 800)); // ขนาดสนามจริง

       add(gamePanel);


    pack();
setResizable(false);        // ⭐ ล็อกขนาดหน้าต่าง
setLocationRelativeTo(null);
setVisible(true);
}
}
