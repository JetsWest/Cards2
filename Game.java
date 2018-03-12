/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.Scanner;
import model.Deck;

/**
 *
 * @author atkins4440j
 */
public class Game {
    private Deck deck;
    private Scanner sc = new Scanner(System.in);
    private Player you;
    private Player dealer;
    private boolean youDone;
    private boolean dealerDone;
    public Game(){
        this.youDone = false;
        this.dealerDone = false;
        this.deck = new Deck();
        this.deck.shuffle();
        this.you = new Player();
        this.you.clearHand();
        this.dealer = new Player();
        this.dealer.clearHand();
    }
    public Deck getDeck(){
        return this.deck;
    }
    public void run(){
        System.out.println("1) Play");
        System.out.println("2) Quit");
        System.out.println("3) Check stats");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                play();
            break;
            case 2:
                System.exit(0);
            break;
            case 3:
                System.out.println("Wins: " + this.you.numWins());
                System.out.println("Losses: " + this.you.numLosses());
                run();
            break;
            default:
                System.out.println("no thanks");
                run();
            break;
        }
    }
    public void play(){
        you.takeCard(deck.drawCard());
        you.takeCard(deck.drawCard());
        dealer.takeCard(deck.drawCard());
        dealer.takeCard(deck.drawCard());
        System.out.println("here is your deck and its value");
        you.printHand();
        System.out.println(you.getHandValue());
        System.out.println("Here is the dealer's first card");
        dealer.peek();
       while(!this.youDone || !this.dealerDone){
            if (!this.youDone){
                this.playerTurn();
            }
            else if (!this.dealerDone){
                this.dealerTurn();
            }
           System.out.println();
        }
        if (!blackjack()){
            getWinner();
        }
        System.out.println("Play again? 1) Yes 2) No");
        int answer = sc.nextInt();
        if (answer == 1){
            run();
        }else{
            System.exit(0);
        }
    }
    public void getWinner() {
        if (youDone && dealerDone) {
            if (you.getHandValue() > dealer.getHandValue() && you.getHandValue() <= 21 || dealer.busted()) {
                System.out.println("You have won!");
                you.win();
                dealer.lose();
            } else if (this.dealer.getHandValue() > 21 && this.you.getHandValue() > 21) {
                System.out.println("No one wins the game!");
            }
        } else if (dealer.getHandValue() > you.getHandValue() && dealer.getHandValue() <= 21 || you.busted()) {
            System.out.println("Dealer won!");
            dealer.win();
            you.lose();
        }
    }
    public void playerTurn(){
        System.out.println("Hit or stay? 1) Hit 2) Stay");
        int hitstay = sc.nextInt();
        if (hitstay == 1) {
            you.takeCard(deck.drawCard());
            System.out.println("Here is your deck and your score");
            you.printHand();
            System.out.println(you.getHandValue());
        }
        if (you.getHandValue() > 21){
            System.out.println("Busted!");;
            this.youDone = true;
        }
        if (hitstay == 2){
            this.youDone = true;
            System.out.println("You chose to stay, or are forced to");
        }
    }
    public void dealerTurn(){
        System.out.println("Dealers turn");
        if (dealer.getHandValue() < 17){
            dealer.takeCard(deck.drawCard());
            System.out.println("The dealer has chosen to hit");
        } else{
            this.dealerDone = true;
            System.out.println("The dealer has chosen to stay, with his final value being " + this.dealer.getHandValue());
        }
    }
    public boolean blackjack(){
        return this.you.getHandValue() == 21 || this.dealer.getHandValue() == 21;
    }
}
