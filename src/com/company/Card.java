package com.company;
import java.util.HashMap;

public class Card {
    static final String[] Rank = {"","A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    static final String[] Suit = {"Clubs", "Diamonds", "Hearts", "Spades"};
    String rank, suit;
    int intRank;

    public Card(int rank, int suit){
            this.rank = Rank[rank];
            this.suit = Suit[suit];

            switch(this.rank){
                case "A":
                    intRank = 1;
                case "2":
                    intRank = 2;
                case "3":
                    intRank = 3;
                case "4":
                    intRank = 4;
                case "5":
                    intRank = 5;
                case "6":
                    intRank = 6;
                case "7":
                    intRank = 7;
                case "8":
                    intRank = 8;
                case "9":
                    intRank = 9;
                case "10":
                    intRank = 10;
                case "J":
                    intRank = 10;
                case "Q":
                    intRank = 10;
                case "K":
                    intRank = 10;
            }
    }

    @Override
    public String toString() {
        return "Card{" + "rank=" + rank + ", suit=" + suit + '}';
    }
}
