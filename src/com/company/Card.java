package com.company;
import java.util.HashMap;

public class Card {
    static final String[] Rank = {"","ACE","2","3","4","5","6","7","8","9","10","JACK","QUEEN","KING"};
    static final String[] Suit = {"", "Clubs", "Diamonds", "Hearts", "Spades"};
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_RESET = "\u001B[0m";
    String rank, suit;
    int intRank, intCount;

    public Card(int rank, int suit){
            this.rank = Rank[rank];
            this.suit = Suit[suit];
    }

    public int getIntRank() {
        if (this.rank.equals("ACE")) {
            intRank = 11;
        } else if (this.rank.equals("2")) {
            intRank = 2;
        } else if (this.rank.equals("3")) {
            intRank = 3;
        } else if (this.rank.equals("4")) {
            intRank = 4;
        } else if (this.rank.equals("5")) {
            intRank = 5;
        } else if (this.rank.equals("6")) {
            intRank = 6;
        } else if (this.rank.equals("7")) {
            intRank = 7;
        } else if (this.rank.equals("8")) {
            intRank = 8;
        } else if (this.rank.equals("9")) {
            intRank = 9;
        } else if (this.rank.equals("10")) {
            intRank = 10;
        } else if (this.rank.equals("JACK")) {
            intRank = 10;
        } else if (this.rank.equals("QUEEN")) {
            intRank = 10;
        } else if (this.rank.equals("KING")) {
            intRank = 10;
        }
        return intRank;
    }

    public int getIntCount() {
        if (this.rank.equals("ACE")) {
            intCount = -1;
        } else if (this.rank.equals("2")) {
            intCount = 1;
        } else if (this.rank.equals("3")) {
            intCount = 1;
        } else if (this.rank.equals("4")) {
            intCount = 1;
        } else if (this.rank.equals("5")) {
            intCount = 1;
        } else if (this.rank.equals("6")) {
            intCount = 1;
        } else if (this.rank.equals("7")) {
            intCount = 0;
        } else if (this.rank.equals("8")) {
            intCount = 0;
        } else if (this.rank.equals("9")) {
            intCount = 0;
        } else if (this.rank.equals("10")) {
            intCount = -1;
        } else if (this.rank.equals("JACK")) {
            intCount = -1;
        } else if (this.rank.equals("QUEEN")) {
            intCount = -1;
        } else if (this.rank.equals("KING")) {
            intCount = -1;
        }
        return intCount;
    }

    @Override
    public String toString() {
        return ANSI_BLUE + rank + ANSI_RESET + " of " + suit;
    }
}
