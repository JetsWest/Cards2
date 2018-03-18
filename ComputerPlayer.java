/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.List;
import model.Card;
import model.Deck;

/**
 *
 * @author atkins4440j
 */
public class ComputerPlayer extends Player{
    public static final int HARD = 2;
    public static final int EASY = 1;
    private int difficulty;

    public ComputerPlayer(int difficulty){
        this.difficulty
                = (difficulty > 0 && difficulty < 3) ? difficulty:EASY;
    }
    @Override
    public void play(Deck deck){
        if (this.difficulty == EASY){
            if (Math.random() > 0.5){
                this.takeCard(deck.drawCard());
            }
        }else if (this.difficulty == HARD){
            if (this.getHandValue() < 17){
                this.takeCard(deck.drawCard());
            }
        }
    }



}
