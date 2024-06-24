**************************************************************************************
 * AUTHOR: Imran Abdullah
 * ------------------------------------------------------------------------------------
 * STEP-BY-STEP INSTRUCTIONS:
 *  - Run the program by pressing the green "Run" button.
 *  - Enter "START" to begin playing.
 *  - Following the provided start-to-end problem, begin changing the word one letter at
 *    a time, ensuring that your guess is the same number of letters and only varies
 *    by one letter.
 *  - After each round, decide whether you want to stop or continue, entering "STOP" or 
 *    "CONTINUE" accordingly.
 *  - If you decide to stop, check out your times with the provided leaderboard. Otherwise,
 *    keep playing!
 * ------------------------------------------------------------------------------------
 * CONTRIBUTIONS:
 *  - Developed all program files and logic:
 *     - Created initial screen of game, including promting users to start
 *     - Printed the start and end goal of the current round.
 *     - Developed logic for each turn, continuing to prompt the user until they have
 *       reached the end word, keeping track of the total time taken.
 *     - Ensures user-friendliness by asking is they want to stop or continue after 
 *       every round.
 *     - Created way to store round times in a node with the start-to-end of the
 *       round and sort them for printing after the user is done playing
 *  - Searched for database of valid words
 *     - Utilized the availible wordle dictionary text file to provide comprehensive 
 *       list of guessable words.
 * ------------------------------------------------------------------------------------
 * CITATIONS:
 *  - Valid Wordle List: https://gist.github.com/dracos/dd0668f281e685bad51479e5acaadb93
 *  - HashSets: https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html
 *  - WordLadder Start-to-Ends: https://www.cut-the-knot.org/SimpleGames/WordLadders.shtml
 *  - EntrySets: https://docs.oracle.com/javase/8/docs/api/java/util/Map.Entry.html
 *  - Keeping Track of Time: https://www.tutorialspoint.com/java/lang/system_currenttimemillis.
 *                           htm#:~:text=currentTimeMillis()%20method%20returns%20the,system%20and
 *                           %20may%20be%20larger.
 * 
 **************************************************************************************/
