package com.company;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    public List<Card> startingDeck = new ArrayList<Card>();
    public List<Card> playerDeck = new ArrayList<Card>();
    static Card drawnCard = new Card(0,0);

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

    //Draws a random card, removes it from the original deck and returns the int value of that card
    public Integer Draw(){
        int rando = (int) ((Math.random() * startingDeck.size()));
        //System.out.println(rando);
        drawnCard = startingDeck.get(rando);
        //System.out.println(rando);
        //System.out.println(startingDeck);
        startingDeck.remove(rando);
        //System.out.println(startingDeck);
        return drawnCard.getIntRank();
    }

    public void addCard(){
        playerDeck.add(drawnCard);
    }

    public void HandDisplay(String name){
        System.out.println(name + "'s Hand: " + playerDeck);
    }

    public void Clear(){
            playerDeck.clear();
    }
}
