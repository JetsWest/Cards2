/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import java.util.Scanner;
import model.Deck;
import org.boof.Output;
import org.boof.ListItemInput;
/**
 *
 * @author atkins4440j
 */
public class Game {
    private Deck deck;
    private static ArrayList<Player> players;
    private Scanner sc = new Scanner(System.in);
    
    public Game(){
        this.deck = new Deck();
        this.deck.shuffle();
        this.players = new ArrayList<>();
    }
    public Deck getDeck(){
        return this.deck;
    }
    public void run(){
        boolean playing = true;
        System.out.println("Welcome to the 21 game!");
        ListItemInput wantToPlay = new ListItemInput();
        wantToPlay.add("y", "Yes");
        wantToPlay.add("n", "No");
        while(playing){
            System.out.println("Want to play?");
            wantToPlay.run();
            if (wantToPlay.getKey().equalsIgnoreCase("y")){
                for (Player player : players){
                    player.play(this.deck);
                }   
            }else{
                System.out.println("Thanks for playing!");
                playing = false;
            }
        }
    }

    public static void main(String[] args){
        Game game = new Game();
        Player user = new Player();
        players.add(user);
        game.run();
    }
}
