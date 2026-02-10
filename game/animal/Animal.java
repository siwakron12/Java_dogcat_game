package game.animal;

public abstract class Animal {

    protected String name;
    protected int hp;
    protected int attackDamage;
    
    public Animal(String name, int hp, int attackDamage) {
        this.name = name;
        this.hp = hp;
        this.attackDamage = attackDamage;
    }

    // polymorphism → แต่ละตัวละครโจมตีต่างกันได้
    public abstract void throwItem(Animal target);

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttackDamage() {
        return attackDamage;
    }
}
