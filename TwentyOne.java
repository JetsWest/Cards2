/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Game.*;

/**
 *
 * @author atkins4440j
 */
public class TwentyOne {
    public static void main(String[] args){
        Game game = new Game();
        Player player = new Player();
        game.run();
        //Each game starts and adds a player and a computer player
        //Game game creates a new deck for the game to run off of, and also creates the run method
        //After that, a method run is called from Game
        //The run method has everything within it, and from there it calls methods from all classes to fulfill the game
    }
}
