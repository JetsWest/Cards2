/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*;

public class Player {
    private int wins;
    private int losses;
    private int points;
    private int score;
    public static final int POINTS_PER_GAME = 20;
    private Scanner sc = new Scanner(System.in);
    private List<Card> hand;
   // private Game game;

    public Player(){
        //this.game = game;
        this.score = 0;
        this.points = 0;
        this.hand = new ArrayList<>();
        this.wins = 0;
        this.losses = 0;
    }

    public void win() {
        this.wins++;
        this.score += POINTS_PER_GAME;
    }

    public void lose() {
        this.losses--;
        this.score -= POINTS_PER_GAME;
    }

    public void takeCard(Card card){
        this.hand.add(card);
    }

    public boolean busted(){
        if (getHandValue() > 21){
            return true;
        }
        return false;
    }

    public int getHandValue() {
        boolean ace = false;
        for (int i = 0; i < this.hand.size(); i++) {
            if (this.hand.get(i).getRank().value >= 10) {
                points += 10;
            }
            else if (this.hand.get(i).getRank().value == 1) {
                ace = true;
                if (ace == true && points + 11 <= 21) {
                    points += 11;
            }
            } else {
                points += this.hand.get(i).getRank().value;
            }
        }
        return points;
    }

    public void clearHand(){
        this.hand.clear();
    }
    public void peek(){
        System.out.println(this.hand.get(0));
    }
    public void printHand(){
        for (int i = 0; i < this.hand.size(); i++){
            System.out.println((i+1) + (":")+this.hand.get(i));
        }
    }
    public void play(){

    }
    public int numWins(){
        return this.wins;
    }

    public int numLosses(){
        return this.losses;
    }

    public int numScore(){
        return this.score;
    }
}
