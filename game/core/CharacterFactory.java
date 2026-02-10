package game.core;
import game.animal.Animal;
import game.animal.Cat;
import game.animal.Dog;
public class CharacterFactory {
     public static Animal create(String type) {
        switch (type) {
            case "Dog": return new Dog();
            case "Cat": return new Cat();
            default: throw new IllegalArgumentException("Unknown character: " + type);
        }
    }
}
