import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;
import java.util.PriorityQueue;

public class WordLadder {
    //HashMap containing all possible start to end combinations
    private HashMap<String, String> startEndWords;
    //PriorityQueue containg all completed word ladders sorted by the time taken for completion
    private PriorityQueue<LeaderboardNode> leaderboard;
    //String containing the start word of the current word
    private String start = "";
    //String containing the end word of the current word
    private String end = "";
    //Integer representing the current round (round 1, round 2, etc.)
    private int counter = 1;
    
     /**
     * Initializes a WordLadder object, creating an empty HashMap for start-to-end words before filling
     * it with the file containing these combiations, as well as creating an empty PriorityQueue for the 
     * leaderboard.
     */
    public WordLadder() {
        startEndWords = new HashMap<String, String>();
        leaderboard = new PriorityQueue<LeaderboardNode>();
        fillStartEnd();
    }
    
     /**
     * Starts the game, printing the initial, colored start screen of the program, before asking 
     * the user if they would like to start. If the user enters an input other than 'start', promts
     * them to enter only start, before starting the game. After each round, prompts the user to
     * continue or stop, providing the leaderboard of times if they are finished.
     */
    public void start() {
        //prints the starting screen of the WordLadder game, complete with a grey background
        System.out.println("\u001B[47m\u001B[30m----------------------------------------");
        System.out.println("-                                      -");
        System.out.println("-         Welcome To WordLadder!       -");
        System.out.println("-           'START' to Begin           -");
        System.out.println("-                                      -");
        System.out.println("----------------------------------------\u001B[0m");
        try {
            //takes in the user input, checking if they would like to start
            Scanner scanner = new Scanner(System.in);
            String shouldStart = scanner.nextLine();
            while(!shouldStart.toUpperCase().equals("START")) {
                System.out.println("Please Only Enter 'START'");
                shouldStart = scanner.nextLine();
            }
            String cont = "";
            System.out.println();
            //checks if game has been started. If so, determines whether the game will continue based off of user input
            do {
                if(cont.toUpperCase().equals("C") || shouldStart.toUpperCase().equals("START")) {
                    playOneTurn();
                    cont = scanner.nextLine();
                    System.out.println();
                //promts users to enter a proper input
                } else {
                    System.out.println("Please '[S]TOP' or '[C]ONTINUE'");
                    cont = scanner.nextLine();
                    System.out.println();
                }
                shouldStart = "";
            } while (!cont.toUpperCase().equals("S"));
            //prints leaderboard
            printLeaderboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     /**
     * Fills the ArrayList of start-to-end combinations with the file containing these pairs.
     * 
     * Before: StartEndWords is an empty ArrayList
     * After: StartEndWords is filled with all start-to-end combinations in the provided file
     */
    public void fillStartEnd() {
        try {
            //reads through all provided start and end pairs, filling the assigned HashMap with said pairs
            Scanner scanner = new Scanner(new File("5startend.txt"));
            while(scanner.hasNextLine()) {
                String[] nextTwo = scanner.nextLine().split(" ");
                startEndWords.put(nextTwo[0], nextTwo[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     /**
     * Provides the ArrayList containing the possible start-to-end combinations of this WordLadder
     * game (for testing).
     * 
     * @return Returns the possible start-to-end combinations of the game.
     */
    public HashMap<String, String> getStartEnd() {
        return startEndWords;
    }
    
     /**
     * Prints the ArrayList containing the possible start-to-end combinations of this WordLadder
     * game (for testing).
     */
    public void printStartEnd() {
        printHashMap(startEndWords);
    }
    
     /**
     * Prints a formatted version of the provided String-to-String HashMap (for testing).
     * 
     * @param hm The HashMap to be printed
     */
    public void printHashMap(HashMap<String, String> hm) {
        String print = "";
        boolean first = true;
        //iterates through each entry in the HashMap, printing the key and value for each pair
        for (Map.Entry<String, String> entry : hm.entrySet()) { 
            print += entry.getValue();
            print += ":";
            print += entry.getKey();
            print += "\n";
            first = false;
        }
        System.out.println(print);
    }
    
     /**
     * Retrieves a random pair from the startEndWords HashMap and randomly assigns one
     * word from the pair as the start word, and the other as the end word.
     * 
     * Before: The start and end String variables are blank or the previous round's start-to-end combination.
     * After: The start-to-end are set to the following round's selected combination.
     */
    public void getRandomPair() {
        //determines which random pair will be selected, as well as whether the key or value will be the start/end words
        Random rand = new Random();
        int ForL = rand.nextInt(2);
        int pair = rand.nextInt(startEndWords.size());
        int currentPair = 0;
        //iterates through each entry in the start and end words HashMap until the predetermined random pair is reached
        for (Map.Entry<String, String> entry : startEndWords.entrySet()) {
            currentPair++;
            if(currentPair == pair) {
                //randomly selects the key or value to be selected as the start and the other as the end word
                if(ForL == 0) {
                    start = entry.getKey();
                    end = entry.getValue();
                } else {
                    start = entry.getValue();
                    end = entry.getKey();
                }
            }
        }
    }
    
     /**
     * Plays one round of the WordLadder game, printing the current start-to-end combination, and 
     * keeping track of what the current round is. Checks user's guesses across the edge list of the
     * current word, providing corresponding color cues to aid in ease of access, and asks the
     * user if they would like to continue after each round.
     * 
     * Before: 
     *  - The leaderboard PriorityQueue contains all previously completed rounds, fit with start-to-end 
     *    combinations and time taken for completion.
     *  - The counter is the previous round number.
     * After:
     *  - After completion of a round, the round is added into the PriorityQueue for sorting.
     *  - After each round, the counter is increased by one.
     */
    public void playOneTurn() {
        //retrieves a random pair from the start and end HashMap and prints the intended path to the console
        getRandomPair();
        String goal = start + " --> " + end;
        System.out.println(counter + ": " + goal);
        System.out.println("\u001B[32m" + start);
        long beginTime = 0;
        long endTime = 0;
        try {
            //begins timer once user has been given the problem
            beginTime = System.currentTimeMillis();
            Scanner scanner = new Scanner(System.in);
            //creates a WordNode with the string data of the start word
            WordNode currentGuess = new WordNode(start.toLowerCase());
            //creates a WordNode with the string data of the user's guess
            WordNode nextGuess = new WordNode(scanner.nextLine().toLowerCase());
            //continues to prompt user for guesses until the word is guessed
            while(!currentGuess.getData().equals(end)) {
                //checks if the next guess is an orthographic neighbor of the current word. If so, the next guess becomes the current word/guess
                if(currentGuess.getEdges().contains(nextGuess.getData())) {
                    currentGuess = nextGuess;
                    //checks if the current guess is equal to the end word, If so, prints the time taken for completion and raises the counter by one
                    if(currentGuess.getData().equals(end)) {
                        endTime = System.currentTimeMillis();
                        leaderboard.offer(new LeaderboardNode(endTime - beginTime, goal));
                        System.out.println("\n\u001B[37mYou Won! \nTime: " + (endTime - beginTime)/1000.0 + "s");
                        System.out.println("[S]TOP OR [C]ONTINUE?");
                        counter++;
                    //if current guess is not equal to the end word, continues to read user input for next guess
                    } else {
                        nextGuess = new WordNode(scanner.nextLine().toLowerCase());
                    }
                //if a word is not an orthographic neighbor, prints the guess in red to alert the user it is an invalid guess
                } else {
                    System.out.println("\u001B[41m\u001B[37m" + nextGuess.getData() + "\u001B[0m" + "\n" + "\u001B[32m" + currentGuess.getData());
                    nextGuess = new WordNode(scanner.nextLine().toLowerCase());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     /**
     * Prints the times of the current WordLadder game after each the user is finished playing.
     * The best time is fisplayed in gold, the second best time is displaayed in silver, and the
     * the third best is deisplayed in bronze, while all remaining times are displayed in white.
     */
    public void printLeaderboard() {
        //the current leaderboard position
        int leaderboardNum = 1;
        System.out.println("\nTimes:");
        while(leaderboard.size() > 0) {
            //prints the quickest time in gold
            if(leaderboardNum == 1) {
                LeaderboardNode nextNode = leaderboard.poll();
                System.out.println("\u001B[33m" + leaderboardNum + ": " + nextNode.getStartToEnd() + " : " + nextNode.getTime() + "s");
                leaderboardNum++;
            //prints the second quickest time in silver
            } else if(leaderboardNum == 2) {
                LeaderboardNode nextNode = leaderboard.poll();
                System.out.println("\u001B[38;5;248m" + leaderboardNum + ": " + nextNode.getStartToEnd() + " : " + nextNode.getTime() + "s");
                leaderboardNum++;
            //prints the third quickest time in bronze
            } else if(leaderboardNum == 3) {
                LeaderboardNode nextNode = leaderboard.poll();
                System.out.println("\u001B[38;5;208m" + leaderboardNum + ": " + nextNode.getStartToEnd() + " : " + nextNode.getTime() + "s");
                leaderboardNum++;
            //prints all remaining times in classic white
            } else {
                LeaderboardNode nextNode = leaderboard.poll();
                System.out.println("\u001B[0m" + leaderboardNum + ": " + nextNode.getStartToEnd() + " : " + nextNode.getTime() + "s");
                leaderboardNum++;
            }
        }
    }
}
