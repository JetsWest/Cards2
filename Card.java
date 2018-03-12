package model;

public class Card implements Comparable<Card> {
    private final Suit suit;
    private final Rank rank;

    public Card(Rank rank, Suit suit) {
        this.suit = suit;
        this.rank = rank;
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Card) {
            Card otherCard = (Card) other;
            return this.suit == otherCard.suit && this.rank == otherCard.rank;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.rank.text + " of " + this.suit.text;
    }

    @Override
    public int compareTo(Card other) {
        return this.rank.value - other.rank.value;
    }

    public static enum Suit {
        CLUBS("Clubs"),
        DIAMONDS("Diamonds"),
        HEARTS("Hearts"),
        SPADES("Spades");

        public final String text;

        Suit(String text) {
            this.text = text;
        }
    }

    public static enum Rank {
        ACE("Ace", 1),
        TWO("2", 2),
        THREE("3", 3),
        FOUR("4", 4),
        FIVE("5", 5),
        SIX("6", 6),
        SEVEN("7", 7),
        EIGHT("8", 8),
        NINE("9", 9),
        TEN("10", 10),
        JACK("Jack", 11),
        QUEEN("Queen", 12),
        KING("King", 13);

        public final String text;
        public final int value;

        Rank(String text, int value) {
            this.text = text;
            this.value = value;
        }
    }
}