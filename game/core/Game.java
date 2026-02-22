package game.core;

import game.animal.Animal;
import java.awt.Rectangle;

public class Game {

    // ตัวแปรสำหรับโยน
    private int power = 0;
    private boolean charging = false;
    private Item currentItem;
    // ตัวแปรสำหรับผู้เล่น
    private Animal player1;
    private Animal player2;
    private int currentTurn = 1;
    // สถานะเกม
    private boolean gameOver = false;
    private Animal winner = null;

    public boolean isGameOver() {
        return gameOver;
    }

    public Animal getWinner() {
        return winner;
    }

    public Game(Animal p1, Animal p2) {
        this.player1 = p1;
        this.player2 = p2;
        p1.setX_position(250); // กำหนดตำแหน่งเริ่มต้นของผู้เล่น
        p2.setX_position(650);

    }

    public Animal getCurrentPlayer() {
        return currentTurn == 1 ? player1 : player2;
    }

    public Animal getOpponentPlayer() {
        return currentTurn == 1 ? player2 : player1;
    }

    public void switchTurn() {
        currentTurn = (currentTurn == 1) ? 2 : 1;
    }

    public Animal getPlayer1() { // update add method getPlayer1,2
        return player1;
    }

    public Animal getPlayer2() {
        return player2;
    }

    public void throwItem(int power, int groundY) {
        Animal player = getCurrentPlayer();

        boolean facingRight = (player == player1);

        this.currentItem = player.throwItem(power, groundY, facingRight);
    }

    public void startCharging() {
        this.charging = true;
    }

    public void stopChargingAndThrow() {
        this.charging = false;
        if (currentItem != null && currentItem.isActive()) {
            return; // ยังมีไอเท็มอยู่ในอากาศ ไม่สามารถโยนใหม่ได้
        }
        throwItem(power, 500);
        this.power = 0;
    }

    public Item getCurrentItem() {
        return this.currentItem;
    }

    public void update(int groundY) {
        if (gameOver)
            return;
        if (charging) {
            power++;
            if (power > 20) {
                power = 20;
            }
        }

        if (currentItem != null && currentItem.isActive()) {
            currentItem.update();

            // ตกพื้น = จบเทิร์น
            if (currentItem.getY() >= groundY) {
                currentItem.deactivate();
                switchTurn();
            }
        }
    }

    public int getPower() {
        return power;
    }

    public boolean isCharging() {
        return charging;
    }

    public void checkWallCollision(Rectangle wall) {
        if (currentItem == null) {
            return;
        }
        if (!currentItem.isActive()) {
            return;
        }

        if (currentItem.getBounds().intersects(wall)) { // ถ้าไอเท็มชนกำแพง แบบใช้ hitbox
            currentItem.deactivate();
            switchTurn();
        }
    }

    public void checkAnimalCollision(int groundY) {
        if (currentItem == null || !currentItem.isActive()) {
            return;
        }

        Rectangle itemRect = currentItem.getBounds();

        if (itemRect.intersects(getOpponentPlayer().getBounds(groundY))) {
            getOpponentPlayer().takeDamage(20);
            currentItem.deactivate();
            switchTurn();
        }
        // ตรวจสอบว่าตัวเอง HP หมดหรือยัง
        if (getCurrentPlayer().getHp() <= 0) {
            gameOver = true;
            winner = getOpponentPlayer(); // ผู้เล่นที่ยังมี HP เป็นผู้ชนะ
        }

    }

    // skill
    public void useCurrentPlayerSkill() {
        Animal current = getCurrentPlayer();

        if (!current.canUseSkill())
            return;

        current.useSkill();
    }

}
