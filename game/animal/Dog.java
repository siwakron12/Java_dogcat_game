package game.animal;

public class Dog extends Animal {

    public Dog() {
        super("Dog", 100, 10);
    }

    @Override
    public void throwItem(Animal target) {
        target.takeDamage(super.attackDamage);
        System.out.println(name + " throws a bone!");
    }
}