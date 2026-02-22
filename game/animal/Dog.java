package game.animal;

import game.core.Item;
import game.skill.BoostDmg;

public class Dog extends Animal implements BoostDmg {

    public Dog() {
        super("dog.png", "Dog", 100, 10);
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
    public void boostDmg(int amount) {
        super.attackDamage += amount;
    }

    @Override
    public void useSkill() {
        boostDmg(15);
        super.markSkillUsed();
        super.setNameSkill("Boost Damage + 15");
    }
}