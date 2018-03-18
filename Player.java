package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*;
import org.boof.ListItemInput;
import org.boof.UI;
import org.boof.Output;

public class Player {

    private int wins;
    private int losses;
    private int points;
    private int score;
    public static final int POINTS_PER_GAME = 20;
    private Scanner sc = new Scanner(System.in);
    private List<Card> hand;

    public Player() {
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

    public void takeCard(Card card) {
        this.hand.add(card);
    }

    public boolean busted() {
        if (this.getHandValue() > 21) {
            return true;
        }
        return false;
    }

    public int getHandValue() {
        boolean ace = false;
        points = 0;
        for (int i = 0; i < this.hand.size(); i++) {
            if (this.hand.get(i).getRank().value > 10) {
                points += 10;
            } else if (this.hand.get(i).getRank().value == 1) {
                ace = true;
                points +=1;
            }else {
                points += this.hand.get(i).getRank().value;
            }
        }
        if (ace && points + 10 <= 21) {
            points += 10;
        }
        return points;
    }

    public void clearHand() {
        this.hand.clear();
    }


    public void play(Deck deck) {
        boolean isDone = false;
        System.out.println("Here are your cards and your score:");
        System.out.println(this.hand.toString());
        System.out.println("Score: " + getHandValue());
        ListItemInput hitOrPass = new ListItemInput();
        hitOrPass.setPrompt("Hit or pass?");
        hitOrPass.add("h", "hit");
        hitOrPass.add("p", "pass");
        while (!isDone){
            hitOrPass.run();
            if (hitOrPass.getKey().equalsIgnoreCase("h")) {
                    String result = "";
                    this.takeCard(deck.drawCard());
                    result += "You hand is now " + this.hand.toString() + "\n";
                    result += "Your score is now " + this.getHandValue();
                    System.out.println(result);
                } else {
                    System.out.println("You have chosen to pass.");
                    isDone = true;
                }
            if (this.getHandValue() > 21){
                System.out.println("You have busted!");
                isDone = true;
                this.lose();
                }
            }
        }


    public int numWins() {
        return this.wins;
    }

    public int numLosses() {
        return this.losses;
    }

    public int numScore() {
        return this.score;
    }
    public void peek(){
        System.out.println();
        System.out.println("---------------------------------------------------------------");
        System.out.println("The first card in their hand is " + this.hand.get(0).toString());
        System.out.println("---------------------------------------------------------------");
        System.out.println();
    }
}
