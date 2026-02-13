package game.animal;

public class Cat extends Animal {

    public Cat() {
        super("cat.png","Cat", 80, 15);
    }

    @Override
    public void throwItem(Animal target) {
        target.takeDamage(super.attackDamage);
        System.out.println(name + " throws a fish!");
    }
}
