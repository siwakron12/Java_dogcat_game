package game.animal;

import game.core.Item;
import game.skill.BoostDmg;

public class Dog extends Animal implements BoostDmg {
    private boolean isBuffActive = false ;

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
        if(this.canUseSkill()) {
            this.setNameSkill("Boost Damage +15!") ;
            this.attackDamage += 15 ;
            this.setCooldown(2);
        }
    }

    @Override
    public void clearSkillEffect() {
        super.clearSkillEffect();

        if(this.isBuffActive) {
            this.attackDamage -= 15 ;
            this.isBuffActive = false ;
        }
    }
}