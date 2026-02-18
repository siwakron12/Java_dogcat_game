package game.core;

import java.awt.*;

public class Item {

    private double x, y; // ตำแหน่งของไอเท็ม
    private double vx, vy;  // ความเร็วในแนว x และ y
    private final int SIZE = 20;
    private boolean active = true;

    public Item(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    

    public void update() {
        if (active == false) {
            return;
        }
        // vy จากติดลบเป็นบวกเพราะแกน y ในจาวาเพิ่มลงไปข้างล่าง
        vy += 0.5;   // gravity
        x += vx;
        y += vy;
    }

    public void draw(Graphics2D g2) {
        if (active == false) {
            return;
        }
        g2.setColor(Color.ORANGE);
        g2.fillOval((int) x, (int) y, SIZE, SIZE);
    }

    public Rectangle getBounds() { // hitbox ของไอเท็ม
        return new Rectangle((int) x, (int) y, SIZE, SIZE);
    }

    public void deactivate() { // เมื่อไอเท็มตกพื้นหรือชนกำแพง จะถูกยกเลิกการใช้งาน
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public double getY() {
        return y;
    }
}
