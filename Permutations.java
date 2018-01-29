import java.util.Arrays;
import java.util.ArrayList;
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
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (nums.length > 0) {
            if (nums.length == 1) {
                List<Integer> l = new ArrayList<Integer>();
                l.add(nums[0]);
                results.add(l);
            }
            else {
                for (int i = 0; i < nums.length; i++) {
                    int[] modifiedNums = new int[nums.length - 1];
                    if (i > 0) {
                        System.arraycopy(nums, 0, modifiedNums, 0, i);
                    }
                    if (i < nums.length - 1) {
                        System.arraycopy(nums, i + 1, modifiedNums, i, nums.length - i - 1);
                    }
                    List<List<Integer>> subsolutions = permute(modifiedNums);
                    for (int j = 0; j < subsolutions.size(); j++) {
                        List<Integer> l = new ArrayList<Integer>();
                        l.add(nums[i]);
                        l.addAll(subsolutions.get(j));
                        results.add(l);
                    }
                }
            }
        }
        return results;
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
