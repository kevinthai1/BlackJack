package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


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
    static int time = 2, handNumber = 0;
    static float endRound, winrate, amountOfWins;

    public static void main(String[] args) throws InterruptedException {

        //Reading and creating Deck number
        System.out.println("Enter the number of Decks: ");
        Scanner deckInput = new Scanner(System.in);
        int deckNumInput = deckInput.nextInt();
        startingDeck = new Deck(deckNumInput);

        //Blackjack Code
        boolean gameIsRunning = true;

        while (gameIsRunning == true){
            //Take user's bet input
            System.out.println("\r\n" + "Balance: $" + player.totalMoney + "\n" + "Enter your bet amount" + "\r\n");
            Scanner playerBet = new Scanner(System.in);
            int betValue = playerBet.nextInt();
            player.totalMoney -= betValue;

            //Deal the hands
            handNumber++;
            System.out.println(ANSI_GREEN + "Hand Number " + handNumber + ANSI_RESET);
            dealerDraw();
            playerDraw();
            playerDraw();

            displayHandValue();
            boolean currentHand = true;
            boolean doubleDown = true;

            //Check for player blackjack
            if (player.handValue == 21){
                TimeUnit.SECONDS.sleep(time);
                dealerDraw();
                displayHandValue();
                if (dealer.handValue == 21){
                    System.out.println(ANSI_BLUE + "PUSH" + ANSI_RESET + "\r\n");
                    player.totalMoney += betValue;
                    amountOfWins++;
                }
                else{
                    System.out.println(ANSI_BLUE + "WIN, BLACKJACK" + ANSI_RESET + "\r\n");
                    player.totalMoney += betValue * 2.5;
                    amountOfWins++;
                }
                currentHand = false;
                resetHands();
            }

            while (currentHand == true){
                //Checking for player bust
                if (player.handValue > 21){
                    System.out.println(ANSI_RED + "LOSE, Bust over 21" + ANSI_RESET + "\r\n");
                    currentHand = false;
                }
                //Checking for dealer bust
                else if (dealer.handValue > 21){
                    System.out.println(ANSI_BLUE + "WIN, Dealer over 21" + ANSI_RESET + "\r\n");
                    currentHand = false;
                    amountOfWins++;
                }
                else{
                    //Reading User's input for bet value and hit/stay
                    if (doubleDown == true){
                        System.out.println("\r\n" + "Input: end(e), hit(h), stay(s), double down(d)" + "\r\n");
                    }
                    else{
                        System.out.println("\r\n" + "Input: end(e), hit(h), stay(s)" + "\r\n");
                    }
                    doubleDown = false;
                    Scanner userInput = new Scanner(System.in);
                    String userStringInput = userInput.nextLine();

                    //End game
                    if (userStringInput.equals("e")){
                        currentHand = false;
                        gameIsRunning = false;
                    }
                    //Hit
                    else if (userStringInput.equals("h")){
                        playerDraw();
                        displayHandValue();
                    }
                    //double down same as hit and stay but with adjusted pay rate
                    else if (userStringInput.equals("d")){
                        player.totalMoney -= betValue;
                        playerDraw();
                        if (player.handValue > 21){
                            displayHandValue();
                            System.out.println(ANSI_RED + "LOSE, Bust over 21" + ANSI_RESET + "\r\n");
                        }
                        else{
                            while (dealer.handValue <= 16){
                                dealerDraw();
                                displayHandValue();
                                TimeUnit.SECONDS.sleep(time);
                            }
                            if (dealer.handValue > 21){
                                System.out.println(ANSI_BLUE + "WIN, Dealer over 21" + ANSI_RESET + "\r\n");
                                player.totalMoney += betValue * 4;
                                amountOfWins++;
                            }
                            else if (player.handValue > dealer.handValue){
                                System.out.println(ANSI_BLUE + "WIN, Higher hand" + ANSI_RESET + "\r\n");
                                player.totalMoney += betValue * 4;
                                amountOfWins++;
                            }
                            else if (dealer.handValue > player.handValue){
                                System.out.println(ANSI_RED + "LOSE, Smaller hand" + ANSI_RESET + "\r\n");
                            }
                            else{
                                System.out.println(ANSI_BLUE + "PUSH" + ANSI_RESET + "\r\n");
                                player.totalMoney += betValue * 2;
                                amountOfWins++;
                            }
                        }
                        currentHand = false;
                    }
                    //Stay, Dealer has to hit if under 17. Then determines who has a higher hand
                    else if (userStringInput.equals("s")){
                        while (dealer.handValue <= 16){
                            dealerDraw();
                            displayHandValue();
                            TimeUnit.SECONDS.sleep(time);
                        }
                        if (dealer.handValue > 21){
                            System.out.println(ANSI_BLUE + "WIN, Dealer over 21" + ANSI_RESET + "\r\n");
                            player.totalMoney += betValue * 2;
                            amountOfWins++;
                        }
                        else if (player.handValue > dealer.handValue){
                            System.out.println(ANSI_BLUE + "WIN, Higher hand" + ANSI_RESET + "\r\n");
                            player.totalMoney += betValue * 2;
                            amountOfWins++;
                        }
                        else if (dealer.handValue > player.handValue){
                            System.out.println(ANSI_RED + "LOSE, Smaller hand" + ANSI_RESET + "\r\n");
                        }
                        else{
                            System.out.println(ANSI_BLUE + "PUSH" + ANSI_RESET + "\r\n");
                            player.totalMoney += betValue;
                            amountOfWins++;
                        }
                        currentHand = false;
                    }
                }
            }
            endRound++;
            resetHands();
        }
    }

    public static void displayHandValue(){
        dealer.handDisplay("Dealer");
        player.handDisplay("Player");
        System.out.println("Dealer#: " + ANSI_RED + dealer.handValue + ANSI_RESET);
        System.out.println("Player#: " + ANSI_RED + player.handValue + ANSI_RESET);
        winrate = (amountOfWins / endRound) * 100;
        System.out.println("Running Count: " + "True Count: " + "WinRate: %" + Math.round(winrate));
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
