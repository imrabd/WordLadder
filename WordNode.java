import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class WordNode {
    //ArrayList containing all orthographic neighbors of the string data of the node found in the WORDLE dictionary
    private ArrayList<String> edges;
    //String containing the String word representation of the current node
    private String data;
    
     /**
     * Initializes a WordNode object, assigning the inputted word sa the String data for 
     * the node, and fills the edges ArrayList by calling the fillEdges method on the word.
     * 
     * @param d The inputted String that will represent this node.
     */
    public WordNode(String d) {
        data  = d;
        edges = fillEdges(d);
    }
    
     /**
     * Fills the edges of a word with all possible guesses that are valid, meaning words
     * that are found in the official WORDLE dictionary and are orthographic neighbors of the
     * word (varies by only one letter and is the same length), by searching through the complete
     * WORDLE dictionary and adding words deemed appropriate.
     * 
     * @param input The word from which the orthographic neighbors will be placed into an
     *              ArrayList of Strings
     * @return Returns an ArrayList of all possible edge words of the inputted word
     * Before: Edges is an empty ArrayList that contains no elements
     * After: Edges is filled wiith all orthographic neighbors of the inputted word, or the string
     *        data of the current word, that are found in the WORDLE dictionary
     */
    public ArrayList<String> fillEdges(String input) {
        ArrayList<String> edges = new ArrayList<String>();
        try {
            //reads through the first dictionary file, searching for othrographic neigbors of this node's string data
            Scanner scanner = new Scanner(new File("5dictionary.txt"));
            while(scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if(orthographicNeighbor(input, nextLine)) {
                    edges.add(nextLine);
                }
            }
            //reads through the second dictionary file, searching for othrographic neigbors of this node's string data
            scanner = new Scanner(new File("5dictionary2.txt"));
            while(scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if(orthographicNeighbor(input, nextLine)) {
                    edges.add(nextLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return edges;
    }
    
     /**
     * Determines if two provided words are orthographic neighbors, meaning that they
     * differ by only one letter and are the same length, by first checking if the two
     * words are the same length, and then counting the number of different leters.
     * 
     * @param one The first word to be compared
     * @param two The second word to be compared
     * @return Returns true if the two words are orthographic neighbors, and false if
     *         not
     */
    public boolean orthographicNeighbor(String one, String two) {
        int diff = 0;
        //checks if the two Strings are the same length
        if(one.length() == two.length()) {
            //iterates through the first string, comparing it to the second string, counting the number of differences
            for(int i = 0; i < one.length(); i++) {
                if(!one.substring(i, i+1).equals(two.substring(i, i+1))) {
                    diff++;
                }
            }
        } else {
            return false;   
        }
        //if there is one difference or less, returns true, otherwise returns false
        if(diff <= 1) {
            return true;
        }
        return false;
    }
    
     /**
     * Provides the String data contained by the node.
     * 
     * @return Returns the string representation of the node
     */
    public String getData() {
        return this.data;
    }
    
     /**
     * Provides the edge list of the node, meaning the found orthographic neighbors of
     * the String data contained by the node.
     * 
     * @return Returns the edges of the node
     */
    public ArrayList<String> getEdges() {
        return this.edges;
    }
}
