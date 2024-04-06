import java.util.*;


// Main class named Solution
class Solution {


   // Declaring two Player objects to represent the two players in the game and a round counter
   public Player playerOne;
   public Player playerTwo;
   public int round;


   // Main method to run the program
   public static void main(String args[]) {
       Solution solution = new Solution();
       solution.run(args); // Start the game
   }
   // Method to run the game logic
   public void run(String args[]) {
       Scanner in = new Scanner(System.in);


       // Prompting for the number of cards for player 1
       System.out.println("Enter the number of cards for player 1:");
       int n = in.nextInt();
       round = 0;
       LinkedList<String> cards = new LinkedList<String>();
       System.out.println("Enter the cards for player 1 (space-separated or line-by-line):");
       for (int i = 0; i < n; i++) {
           // Reading each card of player 1
           String cardp1 = in.next();
           cards.add(cardp1);
       }
       playerOne = new Player(cards); // Initializing player one with their cards


       // Prompting for the number of cards for player 2
       System.out.println("Enter the number of cards for player 2:");
       cards = new LinkedList<String>(); // Reusing the cards list for player 2
       int m = in.nextInt(); // Reading the number of cards for player 2
       System.out.println("Enter the cards for player 2 (space-separated or line-by-line):");
       for (int i = 0; i < m; i++) {
           // Reading each card of player 2
           String cardp2 = in.next();
           cards.add(cardp2);
       }
       playerTwo = new Player(cards); // Initializing player two with their cards


       int winner = war(); // Starting the war and getting the winner
       String result;


       // Setting the result based on the outcome of the war
       if (winner == 0) {
           result = "PAT"; // A tie
       }
       else {
           result = Integer.toString(winner) + " " + round; // Winner and number of rounds
       }


       // Outputting the result
       System.out.println(result);
   }


   // Method to conduct the war (gameplay)
   public int war() {
       int result = 0;


       // Loop until one player runs out of cards
       while (!playerOne.cards.isEmpty() && !playerTwo.cards.isEmpty()) {
           round += 1;
           result = 0;
           Table table = new Table(); // Table to hold cards in play
           // Getting the top card from each player
           String p1card = playerOne.cards.poll();
           String p2card = playerTwo.cards.poll();
           // Adding cards to the table
           table.playerOneCards.add(p1card);
           table.playerTwoCards.add(p2card);
           // Determining the winner of the round
           int roundResult = determineWinner(p1card, p2card);
           if (roundResult == 0) { // In case of a tie, a war happens
               while (roundResult == 0 && !playerOne.cards.isEmpty() && !playerTwo.cards.isEmpty()) {
                   if (playerOne.cards.size() < 4 || playerTwo.cards.size() < 4) { // Check for insufficient cards to continue the war
                       playerOne.cards.clear();
                       playerTwo.cards.clear();
                       result = 0; // No winner (pat situation)
                       break;
                   }


                   // Drawing three cards from each player as part of the war
                   for (int i = 0; i < 3; ++i) {
                       table.playerOneCards.add(playerOne.cards.poll());
                       table.playerTwoCards.add(playerTwo.cards.poll());
                   }
                   // Getting the next card to compare
                   p1card = playerOne.cards.poll();
                   p2card = playerTwo.cards.poll();
                   // Adding these cards to the table
                   table.playerOneCards.add(p1card);
                   table.playerTwoCards.add(p2card);
                   // Determining the winner of this war round
                   roundResult = determineWinner(p1card, p2card);
               }
           }


           // Assigning the collected cards to the winner of the round
           if (roundResult == 1) { // If player one wins
               playerOne.cards.addAll(table.playerOneCards);
               playerOne.cards.addAll(table.playerTwoCards);
               result = 1; // Set player one as the winner
           }
           else if (roundResult == 2) { // If player two wins
               playerTwo.cards.addAll(table.playerOneCards);
               playerTwo.cards.addAll(table.playerTwoCards);
               result = 2; // Set player two as the winner
           }
       }


       return result; // Returning the winner of the game
   }


   // Method to determine the winner of a round based on card values
   public int determineWinner(String playerOneCard, String playerTwoCard) {
       int result = 0;
       // Card rankings from lowest to highest
       String cardNumbers[] = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k", "a" };
       // Extracting the rank of each card (ignoring the suit) and converting to lowercase
       String p1cardValue = playerOneCard.substring(0, playerOneCard.length() - 1).toLowerCase();
       String p2cardValue = playerTwoCard.substring(0, playerTwoCard.length() - 1).toLowerCase();


       // Comparing card rankings to determine the winner
       if (Arrays.asList(cardNumbers).indexOf(p1cardValue) > Arrays.asList(cardNumbers).indexOf(p2cardValue)) {
           result = 1; // Player one wins
       }
       else if (Arrays.asList(cardNumbers).indexOf(p1cardValue) < Arrays.asList(cardNumbers).indexOf(p2cardValue)) {
           result = 2; // Player two wins
       }


       return result; // Returning the result of the round
   }


   // Inner class to represent a player
    public class Player {
       public Queue<String> cards; // Queue of cards the player holds


       public Player(Queue<String> cards) {
           this.cards = new LinkedList<>(cards); // Initializing with the given cards
       }
   }


   // Inner class to represent the table where cards are placed during play
   public class Table {
       public Queue<String> playerOneCards; // Cards played by player one
       public Queue<String> playerTwoCards; // Cards played by player two


       public Table() {
           this.playerOneCards = new LinkedList<String>();
           this.playerTwoCards = new LinkedList<String>();
       }
   }
}
}
