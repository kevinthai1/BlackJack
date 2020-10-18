package com.company;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    public List<Card> startingDeck = new ArrayList<Card>();
    public List<Card> playerDeck = new ArrayList<Card>();

    //Create our n deck of cards
    public Deck(int n){
        for (int k = 0; k < n; k++){
            for (int i = 1; i < 14; i++){
                for (int j = 0; j < 4; j++){
                    Card objCard = new Card(i,j);
                        startingDeck.add(objCard);
                }
            }
        }
    }

    @Override
    public String toString() {
        String deckofCards = "";
        for (int i = 0; i < startingDeck.size(); i++){
            deckofCards += "\n" + startingDeck.get(i) + " Index: " + i;
        }
        return deckofCards;
    }

    //Players will draw a random card and return the value of that card
    public Integer Draw(){
        int rando = (int) ((Math.random() * startingDeck.size()));
        //System.out.println(rando);
        Card text = startingDeck.get(rando);
        //System.out.println(text);
        //System.out.println(startingDeck);
        startingDeck.remove(rando);
        //System.out.println(startingDeck);
        playerDeck.add(text);
        return text.getIntRank();
    }

    public void HandDisplay(String name){
        System.out.println(name + "'s Hand: " + playerDeck); System.out.println("Starting Deck (both dealer and player have their own deck, needs fixing): " + startingDeck.size());
    }

    public void Clear(){
            playerDeck.clear();
    }
}
