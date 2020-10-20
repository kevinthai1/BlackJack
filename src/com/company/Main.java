package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {

    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_BLACK = "\u001B[30m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_PURPLE = "\u001B[35m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_WHITE = "\u001B[37m";
    static Deck startingDeck = new Deck(0);
    static Deck dealer = new Deck(1);
    static Deck player = new Deck(1);
    static Card drawnCard = new Card(0,0);

    public static void main(String[] args) {

        //Reading and creating Deck number
        Scanner deckInput = new Scanner(System.in);
        System.out.println("Enter the number of Decks: ");
        int deckNumInput = deckInput.nextInt();
        startingDeck = new Deck(deckNumInput);

        //Blackjack Code
        boolean gameIsRunning = true;
        int handNumber = 0;

        while (gameIsRunning == true){
            //Deal the hands
            handNumber++;
            System.out.println(ANSI_GREEN + "Hand Number " + handNumber + ANSI_RESET);
            dealerDraw();
            playerDraw();
            playerDraw();
            dealer.handDisplay("Dealer");
            player.handDisplay("Player");
            displayHandValue();
            boolean currentHand = true;

            while (currentHand == true){
                //Checking for player bust and clearing hands
                if (player.handValue > 21){
                    System.out.println(ANSI_RED + "LOSE, Bust over 21" + ANSI_RESET + "\r\n");
                    resetHands();
                    currentHand = false;
                }
                //Checking for dealer bust and clearing hands
                else if (dealer.handValue > 21){
                    System.out.println(ANSI_BLUE + "WIN, Dealer over 21" + ANSI_RESET + "\r\n");
                    resetHands();
                    currentHand = false;
                }
                else{
                    //Reading User's input
                    Scanner userInput = new Scanner(System.in);
                    System.out.println("\r\n" + "Input: end(e), hit(h), stay(s)" + "\r\n");
                    String userStringInput = userInput.nextLine();

                    //End game
                    if (userStringInput.equals("e")){
                        currentHand = false;
                        gameIsRunning = false;
                    }
                    //Hit
                    else if (userStringInput.equals("h")){
                        playerDraw();
                        dealer.handDisplay("Dealer");
                        player.handDisplay("Player");
                        displayHandValue();
                    }
                    //Stay, Dealer has to hit if under 17. Then determines who has a higher hand
                    else if (userStringInput.equals("s")){
                        while (dealer.handValue <= 16){
                            dealerDraw();
                            dealer.handDisplay("Dealer");
                            player.handDisplay("Player");
                            displayHandValue();
                        }
                        if (dealer.handValue > 21){
                            System.out.println(ANSI_BLUE + "WIN, Dealer over 21" + ANSI_RESET + "\r\n");
                        }
                        else if (player.handValue > dealer.handValue){
                            System.out.println(ANSI_BLUE + "WIN, Higher hand" + ANSI_RESET + "\r\n");
                        }
                        else if (dealer.handValue > player.handValue){
                            System.out.println(ANSI_RED + "LOSE, Smaller hand" + ANSI_RESET + "\r\n");
                        }
                        else{
                            System.out.println(ANSI_BLUE + "PUSH" + ANSI_RESET + "\r\n");
                        }
                        resetHands();
                        currentHand = false;
                    }
                }
            }
        }
    }

    public static void displayHandValue(){
        System.out.println("Dealer#: " + ANSI_RED + dealer.handValue + ANSI_RESET);
        System.out.println("Player#: " + ANSI_RED + player.handValue + ANSI_RESET);
    }

    public static void resetHands(){
        dealer.handValue = 0;
        player.handValue = 0;
        player.clear();
        dealer.clear();
    }

    public static void dealerDraw(){
        drawnCard = startingDeck.Draw();
        dealer.handCalculate(drawnCard);
        dealer.addCard(drawnCard);
    }

    public static void playerDraw(){
        drawnCard = startingDeck.Draw();
        player.handCalculate(drawnCard);
        player.addCard(drawnCard);
    }
}
