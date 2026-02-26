package game.animal;

import game.core.Item;
import game.skill.CanHeal;

public class Cat extends Animal implements CanHeal {

    public Cat() {
        super("cat.png", "Cat", 80, 15);
    }

    @Override
    public Item throwItem(int power, int groundY, boolean facingRight) {
        int direction = facingRight ? 1 : -1;

        return new Item(
                x_position + 50,
                groundY - 80,
                direction * power * 0.5,
                -power * 0.8);
    }

    @Override
    public void heal(int amount) {
        super.hp += amount;
    }

    @Override
    public void useSkill() {
        if(this.canUseSkill()) {
            this.setNameSkill("Heal +10 HP") ; //แก้ Text ไม่ขึ้น
            this.hp += 10 ;
            this.setCooldown(2);
        }
    }
}
