package game.animal;

import game.core.Item;

public class Dog extends Animal {

    public Dog() {
        super("dog.png","Dog", 100, 10);
    }

    @Override
    public Item throwItem(int power, int groundY, boolean facingRight) {
        int direction = facingRight ? 1 : -1;

         return new Item(
            x_position + 50,
            groundY - 80,
            direction * power * 0.7,
            -power * 0.5
        );
    }
}