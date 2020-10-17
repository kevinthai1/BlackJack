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
        Deck deck = new Deck(deckNumInput);

        //Blackjack Code
        Boolean gameIsRunning = true;

        while (gameIsRunning == true){
            //Deal the hands


            //Reading User's input
            Scanner userInput = new Scanner(System.in);
            System.out.println("Input: end, draw, hit, stay");
            String userStringInput = userInput.nextLine();

            if (userStringInput.equals("end")){
                gameIsRunning = false;
            }
            else if(userStringInput.equals("draw")){
                deck.Draw();
                System.out.println();
            }
        }
    }
}
