import java.util.List;
import java.util.ArrayList;

/*

https://leetcode.com/problems/spiral-matrix/description/

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

You should return [1,2,3,6,9,8,7,4,5].

 */

public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if (matrix != null && matrix.length > 0 && matrix[0].length > 0) {
            int left = 0;
            int right = matrix[0].length - 1;
            int top = 0;
            int bottom = matrix.length - 1;

            while (top <= bottom || left <= right) {
                if (top <= bottom) {
                    for (int x = left; x <= right; x++) {
                        result.add(matrix[top][x]);
                    }
                    top++;
                }
                if (left <= right) {
                    for (int y = top; y <= bottom; y++) {
                        result.add(matrix[y][right]);
                    }
                    right--;
                }
                if (top <= bottom) {
                    for (int x = right; x >= left; x--) {
                        result.add(matrix[bottom][x]);
                    }
                    bottom--;
                }
                if (left <= right) {
                    for (int y = bottom; y >= top; y--) {
                        result.add(matrix[y][left]);
                    }
                    left++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SpiralMatrix sp = new SpiralMatrix();
        int[][] input1 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                {7, 8, 9 }
        };
        int[][] input2 = {
                { 1, 2, 3}
        };
        System.out.println(sp.spiralOrder(input1));
        System.out.println(sp.spiralOrder(input2));
    }
}
