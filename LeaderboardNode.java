import java.util.Comparator;

public class LeaderboardNode implements Comparable<LeaderboardNode> {
    //Long containing the time taken to complete the word path of the LeaderboardNode in milliseconds
    private long time;
    //String containing the start-to-end combination of the current LeaderboardNode
    private String startToEnd;
    
     /**
     * Initializes a LeaderboardNode object, setting the time and start-to-end combination
     * of the node.
     * 
     * @param t The time taken to complete the word ladder path
     * @param s The start-to-end combination of the LeaderboardNode/time.
     */
    public LeaderboardNode(long t, String s) {
        time = t;
        startToEnd = s;
    }
    
     /**
     * Provides the time of the current LeaderboardNode, converted from milliseconds
     * to seconds.
     * 
     * @return Returns a double containing the time taken to complete the start-to-end
     *         path of the node.
     */
    public double getTime() {
        return time/1000.0;
    }
    
     /**
     * Provides the start-to-end combination of the node.
     * 
     * @return Returns the start-to-end combination of the node.
     */
    public String getStartToEnd() {
        return startToEnd;
    }
    
     /**
     * Compares the times of the current node to another node, providing an integer representaiton
     * of which is larger. Returns -1 if this node's time is smaller, 0 if the two times are 
     * equal, and 1 if this node's time is larger.
     * 
     * @param other The node to be compared to this node 
     * @return Returns an integer value representing which node has a larger time.
     */
    public int compareTo(LeaderboardNode other) {
        if(this.time < other.time){
            return -1;
        } else if(this.time > other.time){
            return 1;
        } else {
            return 0;
        }
    }
}

 /**
 * Creates a class so that nodes in a priority queue can be compared by the desired parameter.
 */
class LeaderboardNodeComparator implements Comparator<LeaderboardNode> {
    /**
     * Compares two nodes in terms of their times 
     * 
     * @param n1 The first node to be compared
     * @param n2 The secondd node to be compared
     * @return Returns a value corresponding to which node's time is greater 
     */
    public int compare(LeaderboardNode n1, LeaderboardNode n2) {
        return n1.compareTo(n2);
    }
}
