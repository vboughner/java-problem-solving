/*
 * WordSearch Problem
 * https://leetcode.com/problems/word-search/description/
 *
 *  Given a 2D board and a word, find if the word exists in the grid.
 *  The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

    For example, given board =
      [
        ['A','B','C','E'],
        ['S','F','C','S'],
        ['A','D','E','E']
      ]

    word = "ABCCED", -> returns true,
    word = "SEE", -> returns true,
    word = "ABCB", -> returns false.


 * The algorithm we'll use here is:
 *   - look through all the squares in the board for the first letter of the word
 *   - if we can't find it anywhere, the search fails and we return false
 *   - if we do find it, start a location-based recursive search there with
 *     the location specified, along with the rest of the word (ommitting the first letter)
 *   - keep search the rest of the board if this one doesn't work out
 *
 * At the beginning every location-based search, start with a empty set of visited flags for the board.
 *
 * The location-based search has a different algorithm
 *   - look in adjacent squares for the first letter of the word (if they have not already been visited)
 *   - if found,
 *       - mark this square as visited
 *       - start a new, recursive location-based search in the new location
 *         with the rest of the word (not including first letter)
 *   - if not found, give up this search and return false
 *
 */
public class WordSearch {

    // returns true if the given location on the board is valid and the given char is found there,
    // returns false quietly if location is not valid
    private boolean charAtLocation(char[][] board, boolean[][] visited, int x, int y, char c) {
        if (y >= 0 && board.length > y && x >= 0 && board[y].length > x) {
            return (!visited[y][x] && board[y][x] == c);
        }
        return false;
    }

    private boolean existsAdjacentToLocation(char[][] board, boolean[][] visited, int x, int y, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            System.out.println("In location search, the board or word is empty, search cannot continue.");
            return false;
        }

        // the first time we start looking at a location, we initialize the visited array,
        // otherwise, we create a copy of the visit flag array so that this new path can be
        // searched without interfering with the searching on other paths the parent might explore
        int height = board.length;
        int width = board[0].length;
        boolean[][] copyOfVisited = new boolean[height][width];
        if (visited != null) {
            for (int iy = 0; iy < height; iy++) {
                for (int ix = 0; ix < width; ix++) {
                    copyOfVisited[iy][ix] = visited[iy][ix];
                }
            }
        }

        // this square is being visited right now, make sure we never return to it
        // during searches made by descendants
        copyOfVisited[y][x] = true;

        char wc = word.charAt(0);

        // search in square above this location
        if (charAtLocation(board, copyOfVisited, x, y - 1, wc) &&
                (word.length() == 1 || existsAdjacentToLocation(board, copyOfVisited, x, y - 1, word.substring(1)))) {
            return true;
        }
        // search in square right of this location
        if (charAtLocation(board, copyOfVisited, x + 1, y, wc) &&
                (word.length() == 1 || existsAdjacentToLocation(board, copyOfVisited, x + 1, y, word.substring(1)))) {
            return true;
        }
        // search in square below this location
        if (charAtLocation(board, copyOfVisited, x, y + 1, wc) &&
                (word.length() == 1 || existsAdjacentToLocation(board, copyOfVisited, x, y + 1, word.substring(1)))) {
            return true;
        }
        // search in square left of this location
        if (charAtLocation(board, copyOfVisited, x - 1, y, wc) &&
                (word.length() == 1 || existsAdjacentToLocation(board, copyOfVisited,x - 1, y, word.substring(1)))) {
            return true;
        }

        return false;
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            System.out.println("The board or word is empty, no search is possible.");
            return false;
        }

        char wc = word.charAt(0);
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                char b = board[y][x];
                if (b  == wc) {
                    if (word.length() == 1 || existsAdjacentToLocation(board, null, x, y, word.substring(1))) {
                        return true;
                    }
                }
            }
        }

        return false;
	}

	// exist2 methods are a re-do of the solution, attempting to do it in a more streamlined fashion
    // after getting some new ideas by looking at other people's solutions,
    // this way of doing it turns out to be faster because we don't create a visited array and copy it

	private boolean exist2(char[][] board, int x, int y, char[] word, int index) {
        if (index == word.length) {
            // we've reached the end of the word, this was a successful search!
            return true;
        }
        if (x < 0 || y < 0 || y >= board.length || x >= board[y].length) {
            // ignore checks for locations off of the board
            return false;
        }
        if (word[index] != board[y][x]) {
            // this search fails if this location doesn't have the right character in it
            return false;
        }

        // this changes the character on the board so that it cannot match again when
        // our descendents are searching for a valid path
        board[y][x] ^= 256;

        // check all squares around us for valid paths
        boolean retval = exist2(board, x, y - 1, word, index + 1) ||
                exist2(board, x + 1, y, word, index + 1) ||
                exist2(board, x, y + 1, word, index + 1) ||
                exist2(board, x - 1, y, word, index + 1);

        // this changes the character on the board back again,
        // so that our caller isn't messed up when it tries looking down other paths
        board[y][x] ^= 256;

        return retval;
    }

	public boolean exist2(char[][] board, String word) {
        char[] cword = word.toCharArray();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (exist2(board, x, y, cword, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
        char[][] board0 = {};
        char[][] board1 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        char[][] board2 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word0 = "";
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        String word4 = "ABCEFSADEESE";

        // results for each combination is in this form - (what it should be): what it really is
        System.out.println("RESULT 1A (false): " + ws.exist(board0, word1));
        System.out.println("RESULT 1B (false): " + ws.exist(board1, word0));
        System.out.println("RESULT 1C (true):  " + ws.exist(board1, word1));
        System.out.println("RESULT 1D (true):  " + ws.exist(board1, word2));
        System.out.println("RESULT 1E (false): " + ws.exist(board1, word3));
        System.out.println("RESULT 1F (true):  " + ws.exist(board2, word4));
        System.out.println();
        System.out.println("RESULT 2A (false): " + ws.exist2(board0, word1));
        System.out.println("RESULT 2B (true):  " + ws.exist2(board1, word0));
        System.out.println("RESULT 2C (true):  " + ws.exist2(board1, word1));
        System.out.println("RESULT 2D (true):  " + ws.exist2(board1, word2));
        System.out.println("RESULT 2E (false): " + ws.exist2(board1, word3));
        System.out.println("RESULT 2F (true):  " + ws.exist2(board2, word4));
    }
}
