import java.util.Arrays;
import java.util.List;

/*

https://leetcode.com/problems/permutations/description/

Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:

[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

Notes:
  - no duplicates among these numbers (because it said distinct)
  - permutations are all about the order (not like combinations)

Algorithm:
  - is the list empty or has only one element?  return that
  - loop through every element in the list
  - create a new list without that element, use it to recurse to get all permutations of the smaller list
  - add all of the solutions from the return to the solution for the current call (placing the element in front)

 */

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        return null;
    }


    public static void main(String[] args) {
        Permutations p = new Permutations();
        int[][] cases = {
            {
            },
            {
                1
            },
            {
                1, 2
            },
            {
                1, 2, 3
            },
            {
                4, 3, 2, 1
            }
        };

        for (int i = 0; i < cases.length; i++) {
            System.out.println("case" + i + ": solution for " + Arrays.toString(cases[i]) + " is");
            System.out.println("      " + p.permute(cases[i]));
            System.out.println();
        }
    }
}
