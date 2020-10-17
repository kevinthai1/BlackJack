package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Reading and creating Deck number
        Scanner deckInput = new Scanner(System.in);
        System.out.println("Enter the number of Decks: ");
        int deckNumInput = deckInput.nextInt();
        Deck startingDeck = new Deck(deckNumInput);
        Deck player = new Deck(1);

        //Blackjack Code
        Boolean gameIsRunning = true;

        while (gameIsRunning == true){
            //Deal the hands


            //Reading User's input
            Scanner userInput = new Scanner(System.in);
            System.out.println("Input: end(e), draw(d), hit, stay");
            String userStringInput = userInput.nextLine();

            if (userStringInput.equals("e")){
                gameIsRunning = false;
            }
            else if(userStringInput.equals("d")){
                //startingDeck.Draw();
                player.Draw();
            }
        }
    }
}
