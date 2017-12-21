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

    // returns true if char is at location,
    // returns false quietly if location is not valid
    private boolean charAtLocation(char[][] board, boolean[][] visited, int x, int y, char c) {
        if (y >= 0 && board.length > y && x >= 0 && board[y].length > x) {
            if (!visited[y][x] && board[y][x] == c) {
//                System.out.println("found char " + c + " at (" + x + "," + y + ")");
            }
            return (!visited[y][x] && board[y][x] == c);
        }
        return false;
    }

    private boolean existsAdjacentToLocation(char[][] board, boolean[][] visited, int x, int y, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
//            System.out.println("In location search, the board or word is empty, search cannot continue.");
            return false;
        }

        // the first time we start looking at a location, we initialize the visited array,
        // create a copy of the visit flag array that keeps track of which squares are visited
        int height = board.length;
        int width = board[0].length;
        boolean[][] copyOfVisited = new boolean[height][width];
        if (visited != null) {
            // if there is already one, we need a copy of it
            for (int iy = 0; iy < height; iy++) {
                for (int ix = 0; ix < width; ix++) {
                    copyOfVisited[iy][ix] = visited[iy][ix];
                }
            }
        }

        // this square is being visited right now, make sure we never return to it
        copyOfVisited[y][x] = true;

        // for debugging
//        System.out.println("exists check at " + x + "," + y + " and visited board is");
//        for (int iy = 0; iy < height; iy++) {
//            for (int ix = 0; ix < width; ix++) {
//                System.out.print(copyOfVisited[iy][ix] + " ");
//            }
//            System.out.println();
//        }

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
//            System.out.println("The board or word is empty, no search is possible.");
            return false;
        }

        char wc = word.charAt(0);
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                char b = board[y][x];
                if (b  == wc) {
//                    System.out.println("found char " + wc + " in board x=" + x + " and y=" + y);
                    if (word.length() == 1 || existsAdjacentToLocation(board, null, x, y, word.substring(1))) {
                        return true;
                    }
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

        System.out.println("RESULT A: " + ws.exist(board0, word1));
        System.out.println("RESULT B: " + ws.exist(board1, word0));
        System.out.println("RESULT C: " + ws.exist(board1, word1));
        System.out.println("RESULT D: " + ws.exist(board1, word2));
        System.out.println("RESULT E: " + ws.exist(board1, word3));
        System.out.println("RESULT F: " + ws.exist(board2, word4));
    }
}
