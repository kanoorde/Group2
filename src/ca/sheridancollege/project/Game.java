/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */


import java.util.ArrayList;
import java.util.List;


// This is a game class which represents list of cards in foundation pile, group of cards, list of cards
// in waste pile and tableau pile.
public class Game {
    private final GroupOfCards groupofcards;
    private final List<Card> foundation;
    private final List<Card> wastePile;
    private final List<List<Card>> tableau;

    
    //This is a constructor which initializes the group of cards, foundation pile, waste pile, tableau pile.
    // Also it calls the deal tableau method.
    public Game() {
        groupofcards = new GroupOfCards();
        foundation = new ArrayList<>();
        wastePile = new ArrayList<>();
        tableau = new ArrayList<>();
        dealTableau();
    }

    
    // This method is used to add appropriate number of cards to each pile.
    private void dealTableau() {
        for (int i = 0; i < 7; i++) {
            tableau.add(new ArrayList<>());
            for (int j = 0; j <= i; j++) {
                tableau.get(i).add(groupofcards.drawCard());
            }
        }
    }

    
    // This method is used to draw card from group of cards.
    public void drawCard() {
        Card card = groupofcards.drawCard();
        if (card != null) {
            wastePile.add(card);
        }
    }

    
    // This is a boolean method and it move card to the foundation pile
    public boolean moveCardToFoundation() {
        if (!wastePile.isEmpty()) {
            foundation.add(wastePile.remove(wastePile.size() - 1));
            return true;
        }
        return false;
    }
    
    
    //This is a boolean method which move card from one pile to another pile.
    public boolean moveCardBetweenPiles(int fromPileIndex, int toPileIndex) {
        // Validate pile indices
        if (fromPileIndex < 0 || fromPileIndex >= tableau.size() ||
            toPileIndex < 0 || toPileIndex >= tableau.size()) {
            return false;
        }

        List<Card> sourcePile = tableau.get(fromPileIndex);
        List<Card> destinationPile = tableau.get(toPileIndex);

        // Check if the source pile has cards
        if (sourcePile.isEmpty()) {
            return false;
        }

        // Move the top card from the source pile to the destination pile
        Card cardToMove = sourcePile.remove(sourcePile.size() - 1);
        destinationPile.add(cardToMove);

        return true;
    }
    
    
    // This is a boolean method is used move card from waste pile to tableau pile.
    public boolean moveCardToPile(int pileIndex) {
        if (!wastePile.isEmpty() && pileIndex >= 0 && pileIndex < tableau.size()) {
            tableau.get(pileIndex).add(wastePile.remove(wastePile.size() - 1));
            return true;
        }
        return false;
    }

    
    // This method check the win condition by checking the condition whether the size of foundation pile is 52.
    public boolean checkWin() {
        return foundation.size() == 52;
    }

    
    //This method check the loss condition by checking the condition when the size of group of cards is zero 
    //and the waste pile is empty.    
    public boolean checkLoss() {
        return groupofcards.size() == 0 && wastePile.isEmpty() && !hasMovesLeft();
    }

    
    private boolean hasMovesLeft() {
        // Basic check for moves left; for simplicity, we'll say there are always moves left
        // In a full implementation, you'd check if there are actual valid moves
        return true;
    }

    
    //This method is used for printing the state of the game
    public void printGameState() {
        System.out.println("Deck size: " + groupofcards.size());
        System.out.println("Waste Pile: " + wastePile);
        System.out.println("Foundation: " + foundation);
        System.out.println("Tableau: ");
        for (int i = 0; i < tableau.size(); i++) {
            System.out.println("Pile " + (i + 1) + ": " + tableau.get(i));
        }
    }
}
