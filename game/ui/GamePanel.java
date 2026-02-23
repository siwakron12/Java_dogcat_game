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

    // final int wall_X = getWidth() / 2 - WALL_WIDTH / 2;
    final int gapWall = 200;

    public GamePanel(Game game) {
        this.game = game;
        setBackground(new Color(135, 206, 235));

        // set bg color
        Timer timer = new Timer(16, e -> { //ทุก ๆ 16 ms ให้เรียกโค้ดใน lambda
            int wall_X = getWidth() / 2 - WALL_WIDTH / 2; // กำแพง x กลาง
            int wallTopY = GROUND_Y - wallHeight; // กำแพง y บนสุด
            Rectangle wallRect = new Rectangle(wall_X, wallTopY, WALL_WIDTH, wallHeight);
            game.update(GROUND_Y, wallRect);
            repaint();
        });
        timer.start(); // เริ่มต้น timer

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

        getInputMap().put(KeyStroke.getKeyStroke("H"), "skill");
        getActionMap().put("skill", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                game.useCurrentPlayerSkill();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // ล้างหน้าจอก่อนวาดใหม่
        Graphics2D g2 = (Graphics2D) g;
        int wall_X = getWidth() / 2 - WALL_WIDTH / 2;
        int wallTopY = GROUND_Y - wallHeight;

        // ตังค่าพื้น
        g2.setColor(new Color(34, 139, 34));
        g2.fillRect(0, GROUND_Y, getWidth(), 100);

        // ตังค่ากำแพง
        g2.setColor(new Color(90, 90, 90));
        g2.fillRect(wall_X, wallTopY, WALL_WIDTH, wallHeight);
        g2.setColor(Color.BLACK);
        g2.drawRect(wall_X, wallTopY, WALL_WIDTH, wallHeight);

        // ตั้งค่าสัตว์
        Animal p1 = game.getPlayer1();
        Animal p2 = game.getPlayer2();

        drawAnimal(g2, p1, p1.getX_position(), GROUND_Y - 80, "P1");
        drawAnimal(g2, p2, p2.getX_position(), GROUND_Y - 80, "P2");

        // แสดงพลังการโยน ของใครของมัน
        if (game.isCharging()) {
            System.out.println("Power ตอนนี้คือ: " + game.getPower());

            Animal chargingPlayer = game.getCurrentPlayer();
            int charx = chargingPlayer.getX_position();
            int chary = GROUND_Y - 80;

            int maxPower = 20;
            int barWidth = 60;
            int barHeight = 10;
            int x = charx + 20;
            int y = chary - 50;

            int currentPower = (game.getPower() * barWidth) / maxPower;
            currentPower = Math.min(currentPower, barWidth);

            g2.setColor(Color.GRAY);
            g2.fillRect(x, y, barWidth, barHeight);

            g2.setColor(Color.RED);
            g2.fillRect(x, y, currentPower, barHeight);

            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, barWidth, barHeight);

        }

        // update turn player
        Animal currentTurnPlayer = game.getCurrentPlayer();
        String playerTurnTag = (currentTurnPlayer == p1) ? "Player 1" : "Player 2";
        String turnText = "Turn : " + playerTurnTag + " (" + currentTurnPlayer.getName() + ") ";
        // turn ของผู้เล่น
        if (game.getCurrentItem() != null && game.getCurrentItem().isActive()) {
            game.getCurrentItem().draw(g2);
        }
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        g2.setColor(Color.RED);
        g2.drawString(turnText, 20, 30);



        // ปุ่มสกิล
        Animal current = game.getCurrentPlayer();
        int btnX = 20;
        int btnY = 80;
        int btnW = 160;
        int btnH = 40;

        // สีปุ่ม skill
        if (current.canUseSkill()) {
            g2.setColor(new Color(70, 130, 180)); // ใช้ได้
        } else {
            g2.setColor(Color.GRAY); // ใช้แล้ว
        }

        g2.fillRoundRect(btnX, btnY, btnW, btnH, 15, 15);

        // ขอบ
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(btnX, btnY, btnW, btnH, 15, 15);

        // ข้อความ
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 14));

        String text;
        if (current.canUseSkill()) {
            text = "Press H : Use Skill";
        } else {
            text = "Cooldown : " + current.getSkillCooldown() + " turn";
        }
        //ชื่อสกิล
        g2.drawString(text, btnX + 15, btnY + 25);
        if (!current.getNameSkill().equals("")) {
            g2.setColor(Color.YELLOW);
            g2.drawString(current.getNameSkill(), current.getX_position(), GROUND_Y - 150);
        }
        // ข้อความจบเกม
        if (game.isGameOver()) {
            g2.setColor(new Color(0, 0, 0, 180));
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 36));

            Animal winnerAnimal = game.getWinner();
            String playerTag = (winnerAnimal == p1) ? "Player 1 " : "Player 2";
            String winner = "WINNER IS : " + playerTag + " (" + winnerAnimal.getName() + ")";

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(winner)) / 2;
            int y = getHeight() / 2;

            g2.drawString(winner, x, y);
        }
    }

    private void drawAnimal(Graphics2D g2, Animal animal, int x, int y, String tag) {

        // วาดรูป
        g2.setColor(Color.RED);
        g2.fillRect(x, y - 35, animal.getHp(), 5);

        g2.drawImage(animal.getImage(), x, y, 100, 100, null);

        // ชื่อสัตว์
        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString(tag + " " + animal.getName(), x, y - 10);

    }

}

// gamepanel
