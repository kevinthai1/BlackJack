package com.company;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    public List<Card> startingDeck = new ArrayList<Card>();
    public List<Card> hand = new ArrayList<Card>();
    private Card drawnCard = new Card(0,0);
    int handValue = 0, ace = 0, aceValue11 = 0;

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
    public void Draw(){
        int rando = (int) ((Math.random() * startingDeck.size()));
        drawnCard = startingDeck.get(rando);
        startingDeck.remove(rando);
        handValue += drawnCard.getIntRank();

        //check for an ace
        if (drawnCard.getIntRank() == 11){
            ace++;
            for (int i = 0; i < ace; i++){
                handValue -= 11;
            }
            for (int i = 0; i < ace; i++){
                if (handValue < 11){
                    handValue += 11;
                    aceValue11++;
                }
                else{
                    handValue += 1;
                }
            }
        }
        if (aceValue11 > 0 && handValue > 21){
            for (int i = 0; i < aceValue11; i++){
                handValue -= 11;
                handValue += 1;
            }
        }
    }

    public void addCard(){
        hand.add(drawnCard);
    }

    public void handDisplay(String name){
        System.out.println(name + "'s Hand: " + hand);
    }

    public void clear(){
            hand.clear();
            handValue = 0;
    }
}
