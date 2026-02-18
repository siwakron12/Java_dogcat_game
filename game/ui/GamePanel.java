package game.ui;

import game.animal.Animal;
import game.core.Game;
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {

    private Game game;

    final int WALL_WIDTH = 20;
    final int wallHeight = 130;
    final int GROUND_Y = 500;

    final int wall_X = getWidth() / 2 - WALL_WIDTH / 2;
    final int gapWall = 200;

    public GamePanel(Game game) {
        this.game = game;
        setBackground(new Color(135, 206, 235)); // set bg color
        Timer timer = new Timer(16, e -> {
            game.update(GROUND_Y);
            repaint();
        });
        timer.start();
        getInputMap().put(KeyStroke.getKeyStroke("pressed SPACE"), "charge");
        getActionMap().put("charge", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                game.startCharging();
            }
        });
        getInputMap().put(KeyStroke.getKeyStroke("released SPACE"), "throw");
        getActionMap().put("throw", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                game.stopChargingAndThrow();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //ล้างหน้าจอก่อนวาดใหม่
        Graphics2D g2 = (Graphics2D) g;
        int wall_X = getWidth() / 2 - WALL_WIDTH / 2;
        int wallTopY = GROUND_Y - wallHeight;

        //ตังค่าพื้น 
        g2.setColor(new Color(34, 139, 34));
        g2.fillRect(0, GROUND_Y, getWidth(), 100);

        //ตังค่ากำแพง 
        g2.setColor(new Color(90, 90, 90));
        g2.fillRect(wall_X, wallTopY, WALL_WIDTH, wallHeight);
        g2.setColor(Color.BLACK);
        g2.drawRect(wall_X, wallTopY, WALL_WIDTH, wallHeight);

        // ตั้งค่าสัตว์
        Animal p1 = game.getPlayer1();
        Animal p2 = game.getPlayer2();

        drawAnimal(g2, p1, p1.getX_position(), GROUND_Y - 80);
        drawAnimal(g2, p2, p2.getX_position(), GROUND_Y - 80);

        if (game.isCharging()) {
            int barWidth = 100;
            int barHeight = 20;
            int x = 20;
            int y = 50;

            g2.setColor(Color.GRAY);
            g2.fillRect(x, y, barWidth, barHeight);

            g2.setColor(Color.RED);
            g2.fillRect(x, y, game.getPower(), barHeight);

            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, barWidth, barHeight);
        }
        // trun ของผู้เล่น
        if (game.getCurrentItem() != null && game.getCurrentItem().isActive()) {
            game.getCurrentItem().draw(g2);
            g2.setFont(new Font("Arial", Font.BOLD, 18));
            g2.drawString("Turn: " + game.getCurrentPlayer().getName(), 20, 30);
        } else {
            g2.setFont(new Font("Arial", Font.BOLD, 18));
            g2.drawString("Turn: " + game.getCurrentPlayer().getName(), 20, 30);
        }
        Rectangle wallRect = new Rectangle(wall_X, wallTopY, WALL_WIDTH, wallHeight);
        game.checkWallCollision(wallRect);
        game.checkAnimalCollision(GROUND_Y);
    }

    private void drawAnimal(Graphics2D g2, Animal animal, int x, int y) {

        // วาดรูป
        g2.setColor(Color.RED);
        g2.fillRect(x, y - 35, animal.getHp(), 5);

       
        g2.drawImage(animal.getImage(), x, y, 100, 100, null);

        // ชื่อสัตว์
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString(animal.getName(), x, y - 10);
    }

}

//gamepanel
