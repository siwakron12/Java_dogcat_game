package game.animal;

import game.core.Item;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public abstract class Animal {
<<<<<<< Updated upstream

    private Image image;
    protected String name;
    protected int hp;
    protected int attackDamage;
    protected int x_position;

    public Animal(String imagePath, String name, int hp, int attackDamage) {
        java.net.URL url = getClass().getResource("/assets/" + imagePath);
        this.image = new ImageIcon(url).getImage();
        this.name = name;
        this.hp = hp;
        this.attackDamage = attackDamage;
    }

    public int getX_position() {
        return x_position;
    }

    public void setX_position(int x_position) {
        this.x_position = x_position;
    }

    public abstract Item throwItem(int power, int groundY, boolean facingRight);

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public boolean isAlive() { //update condition
        if (hp > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    public Image getImage() {
        return this.image;
    }

    public Rectangle getBounds(int groundY) {
        return new Rectangle((int) x_position, groundY - 80, 100, 100);
    }
}
