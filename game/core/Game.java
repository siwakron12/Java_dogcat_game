package game.core;

import game.animal.Animal;

public class Game {

    private Animal player1;
    private Animal player2;

    public Game(Animal p1, Animal p2) {
        this.player1 = p1;
        this.player2 = p2;
    }
}