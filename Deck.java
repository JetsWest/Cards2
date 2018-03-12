package model;

import java.util.Random;

public class Deck {
    private Card[] cards;
    private int marker;

    public Deck() {
        this.cards = new Card[52];
        int pos = 0;
        for (Card.Suit suit: Card.Suit.values()){
            for (Card.Rank rank: Card.Rank.values()){
                this.cards[pos] = new Card(rank, suit);
                pos++;
            }
        }
        marker = 0;
    }
    public void shuffle(){
        //Shuffles the order of the cards in this.cards
        //Also re-introduces all 'drawn' cards back into deck
        int index;
        Card temp;
        Random r = new Random();
        for (int i = this.cards.length-1; i > 0; i--){
            index = r.nextInt(i+1);
            temp = this.cards[i];
            this.cards[i] = this.cards[index];
            this.cards[index] = temp;
        }
    }

    public Card drawCard() throws NullPointerException{
        //Draws "top" card off the deck, then discards the card
        if (marker == 0){
            this.shuffle();
        }

        if (hasNext()){
            Card res = this.cards[marker];
            marker++;
            return res;
        }
        return null;
    }
    public boolean hasNext(){
        //Checks if there are any more cards to draw!
        return marker < 52;
    }
    public void printCards(){
        for (Card card: this.cards){
            System.out.println(card);
        }
    }
    public int bruteForceSearch(Card card){
        int i = 0;
        for (Card next: cards){
            if (card.equals(next)){
                System.out.println("The index is: " + i);
                return i;
            }else{
                i++;
            }
        }
        return -1;
    }

    public int binarySearch(Card card){
        //copy cards to dummy and sorts it
        Card[] dummy = new Card[cards.length];
        for (int i = 0; i < cards.length; i++){
            dummy[i] = cards[i];
        }

        dummy = mergeSortR(dummy);

        int left = 0;
        int right = dummy.length-1;

        while(left <= right){
            int pivot = (right+left) / 2;
            if (dummy[pivot].equals(card)){
                System.out.println(pivot);
                return pivot;
            }

            if (dummy[pivot].compareTo(card) > 0){
                right = pivot-1;
            }else{
                left = pivot+1;
            }
        }

        return -1;
    }
    public int binarySearchR(Card card){
        return -1;
    }
    private void swap(int ndx1, int ndx2){
        Card temp = this.cards[ndx1];
        this.cards[ndx1] = this.cards[ndx2];
        this.cards[ndx2] = temp;
    }
    public void selectionSort(){
        for (int i = 0; i < this.cards.length-1; i++){
            int pos = i;
            for (int j = i+1; j < this.cards.length; j++){
                if (this.cards[j].getRank().value < this.cards[pos].getRank().value){
                    pos = j;
                }
                swap(pos, i);
            }
        }
    }
    public void insertionSort(){
        for (int i = 0; i < this.cards.length; i++){
            for (int k = i; k >= 1 && this.cards[k].compareTo(this.cards[k-1]) < 0; k--){
                swap (k, k-1);
            }
        }
    }
    public Deck mergeSort(){
        this.cards = mergeSortR(this.cards);
        return this;
    }
    private Card[] mergeSortR(Card[] cards){
        //Stops an infinite recursion from happening
        //based on idea that list of 1 or less things is already sorted
        if (cards.length <= 1){
            return cards;
        }

        //Spltting lists
        Card[] left = new Card[cards.length/2];
        for (int i = 0; i < cards.length/2; i++){
            left[i] = cards[i];
        }
        Card[] right = new Card[cards.length-left.length];
        for (int i = left.length; i < cards.length; i++){
            right[i - left.length] = cards[i];
        }

        //Sorting
        left = mergeSortR(left);
        right = mergeSortR(right);

        //Combining
        Card[] temp = new Card[left.length+right.length]; //STEP ONE
        int newMark = 0; //STEP TWO
        int lMark = 0;
        int rMark = 0;

        //Combine until at least one list is all gone
        while (lMark < left.length && rMark < right.length){
            if (left[lMark].compareTo(right[rMark]) < 0){
                temp[newMark] = left[lMark];
                lMark++;
            }else{
                temp[newMark] = right[rMark];
                rMark++;
            }
            newMark++;
        }
        //Select remaining list and add the rest to back of reslt
        Card[] remaining = (lMark < left.length)?left:right; //in () is if statement, first after ? is true, second after ? is false (to select)
        int endx = (lMark < left.length)?lMark:rMark;
        while(newMark < temp.length){
            temp[newMark] = remaining[endx];
            newMark++;
            endx++;
        }
        return temp;
    }
}
