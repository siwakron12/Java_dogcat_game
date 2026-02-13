package game.core;

import game.animal.Animal;

public class Game {

    private Animal player1;
    private Animal player2;

    public Game(Animal p1, Animal p2) {
        this.player1 = p1;
        this.player2 = p2;
        player1.setX_position(400);
        player2.setX_position(900);
    }

    public Animal getPlayer1() { //update add method getPlayer1,2
        return player1 ;
    }
    public Animal getPlayer2() {
        return player2 ;
    }
}