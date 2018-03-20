
package Game;

import java.util.ArrayList;
import java.util.Scanner;
import model.Deck;
import org.boof.Output;
import org.boof.ListItemInput;
import org.boof.NumberInput;
/**
 *
 * @author atkins4440j
 */
public class Game {
    private Deck deck;
    private static ArrayList<Player> players;
    private Scanner sc = new Scanner(System.in);
    boolean replay = true;
    public Game(){
        this.deck = new Deck();
        this.deck.shuffle();
        this.players = new ArrayList<>();
    }
    public Deck getDeck(){
        return this.deck;
    }
    public void run(){
        NumberInput numHumanPlayers = new NumberInput();
        numHumanPlayers.setPrompt("How many human players do you want?");
        numHumanPlayers.setNumberType("Integer");

        NumberInput numComputerPlayers = new NumberInput();
        numComputerPlayers.setPrompt("How many computer players do you want?");
        numComputerPlayers.setNumberType("Integer");

        NumberInput cpuDifficulty = new NumberInput();
        cpuDifficulty.setPrompt("What level do you want the CPU to be? 1) Easy 2) Hard");
        cpuDifficulty.setNumberType("Integer");

        System.out.println("Welcome to the 21 game!");
        ListItemInput wantToPlay = new ListItemInput();
        wantToPlay.setPrompt("Want to continue?");
        wantToPlay.add("y", "Yes");
        wantToPlay.add("n", "No");
        boolean playing = true;
        numHumanPlayers.run();
        numComputerPlayers.run();
        cpuDifficulty.run();
        for (int i = 0; i < (int)numHumanPlayers.get(); i++){
            this.players.add(new Player());
            this.players.get(i).takeCard(deck.drawCard());
            this.players.get(i).takeCard(deck.drawCard());
        }
        for (int i = 0; i < (int)numComputerPlayers.get(); i++){
            this.players.add(new ComputerPlayer((int) cpuDifficulty.get()));
            this.players.get(i+(int)numHumanPlayers.get()).takeCard(deck.drawCard());
            this.players.get(i+(int)numHumanPlayers.get()).takeCard(deck.drawCard());
            this.players.get(i + (int) numHumanPlayers.get()).peek();
        }
        while(playing){
            wantToPlay.run();
            if (wantToPlay.getKey().equalsIgnoreCase("y")){
                for (Player player : players){
                    player.play(this.deck);
                }
                for (int i = 0; i < players.size()-1; i++){
                    checkWinner(players.get(0), players.get(i+1));
                }
                again();
            }else{
                System.out.println("Thanks for playing!");
                playing = false;
                System.exit(0);
            }
        }

    }
    public void again(){
        if (playAgain()){
            for (int i = 0; i < players.size()-1; i++){
                players.get(i).clearHand();
            }
            Game g = new Game();
            g.run();
        }
    }
    public boolean playAgain(){
        System.out.println("Play again? y/n");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")){
            replay = true;
        }else if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")){
            System.out.println("Bye! Now exiting...");
            System.exit(0);
        }else{
            System.out.println("Incorrect input");
        }
        return replay;
    }
    public void checkWinner(Player player, Player dealer) {
        if (player.getHandValue() <= 21 && player.getHandValue() > dealer.getHandValue() || dealer.busted() && !player.busted()){
            System.out.println("The CPU's score was " + dealer.getHandValue() + " and your score was " + player.getHandValue());
            System.out.println();
            System.out.println("You win!");
            player.win();
            dealer.lose();
            System.out.println("Wins: " + player.numWins());
            System.out.println("Losses: " + player.numLosses());
        }else if (dealer.getHandValue() <= 21 && dealer.getHandValue() > player.getHandValue() || player.busted() && !dealer.busted()){
            System.out.println("The CPU's score was " + dealer.getHandValue() + " and your score was " + player.getHandValue());
            System.out.println();
            System.out.println("You lose cpu wins!");
            dealer.win();
            player.lose();
            System.out.println("Wins: " + player.numWins());
            System.out.println("Losses: " + player.numLosses());
        }else{
            System.out.println("The CPU's score was " + dealer.getHandValue() + " and your score was " + player.getHandValue());
            System.out.println();
            System.out.println("It is a tie!");
            System.out.println("Wins: " + player.numWins());
            System.out.println("Losses: " + player.numLosses());
        }
    }

    public static void main(String[] args){
        Game game = new Game();
        game.run();
    }
}
