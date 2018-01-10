/*

https://leetcode.com/problems/valid-sudoku/description/

Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules. (http://sudoku.com.au/TheRules.aspx)

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.


Algorithm:
  - write a function that can check 9 array elements, given the coordinates of start and end row and column,
    re-use it below for every check, the sub-routine can use a simple array of booleans for each number to
    insure it doesn't appear twice in the given area of the array
  - go across each row and make sure no number is repeated and all are between 0-9
  - go down every column and do the same
  - write a subroutine do the same for every 3 by 3 area
 */
public class ValidSudoku {

    private boolean isValidNine(char[][] board, int startColumn, int startRow, int endColumn, int endRow) {
        if (startRow < 0 || startRow >= board.length ||
                board.length < 1 || startColumn < 0 || startColumn >= board[0].length) {
            System.out.println("Error: row or column out of bounds");
            return false;
        }
        boolean[] digitUsed = new boolean[10];  // all start as false
        for (int row = startRow; row <= endRow; row++) {
            for (int column = startColumn; column <= endColumn; column++) {
                if (board[row][column] != '.') {
                    int digit = board[row][column] - '0';
                    // System.out.println('row ' + row + ', column ' + column + ', digit ' + digit + ', isDigitUsed ' + digitUsed[digit]);
                    if (digitUsed[digit]) {
                        // already used once, so this is invalid
                        return false;
                    }
                    digitUsed[digit] = true;
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        // check rows and columns
        for (int i = 0; i < 8; i++) {
            if (!isValidNine(board, 0, i, 8, i) ||
                    !isValidNine(board, i, 0, i, 8)) {
                return false;
            }
        }

        // check the nice boxes
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isValidNine(board, i * 3, j * 3, i * 3 + 2, j * 3 + 2)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidSudoku vs = new ValidSudoku();
        char[][] board1 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},

                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},

                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        char[][] board2 = {
                {'5', '5', '.', '.', '7', '.', '.', '.', '.'},
                {'5', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},

                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},

                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        char[][] board3 = {
                {'.', '.', '4', '.', '.', '.', '6', '3', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '9', '.'},

                {'.', '.', '.', '5', '6', '.', '.', '.', '.'},
                {'4', '.', '3', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '7', '.', '.', '.', '.', '.'},

                {'.', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };


        char[][] board4 = {
                {'.', '.', '.', '9', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '3', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'1', '.', '.', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '.', '2', '.', '6', '.', '.'},
                {'.', '9', '.', '.', '.', '.', '.', '7', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'8', '.', '.', '8', '.', '.', '.', '.', '.'}
        };


        System.out.println("board1: " + vs.isValidSudoku(board1) + " (should be true)");
        System.out.println("board2: " + vs.isValidSudoku(board2) + " (should be false)");
        System.out.println("board3: " + vs.isValidSudoku(board3) + " (should be false)");
        System.out.println("board4: " + vs.isValidSudoku(board4) + " (should be false)");
    }
}