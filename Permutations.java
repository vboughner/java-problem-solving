import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

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


    /*
     * Another approach to the problem, using back-tracking.
     *
     * Algorithm:
     *   - build up the overall results and pass them along
     *   - call for the building of each new permutation, adding a single (unused) element to it in this call
     *   - get the results for that, backtrack and try another
     *
     * Note: it turns out this isn't any faster than the other way, just a different style of writing it
     */
    public List<List<Integer>> backTrackPermute(int[] nums) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (nums.length > 0) {
            List<Integer> tempList = new ArrayList<Integer>();
            backTrackPermuteHelper(results, tempList, nums);
        }
        return results;
    }

    private void backTrackPermuteHelper(List<List<Integer>> results, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            results.add(new ArrayList<Integer>(tempList));
        }
        else {
            for (int i = 0; i < nums.length; i++) {
                if (!tempList.contains(nums[i])) {
                    tempList.add(nums[i]);
                    backTrackPermuteHelper(results, tempList, nums);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }

    /*
     * Faster way, using a queue and iteration, from the solution in the discussion at
     * https://leetcode.com/problems/permutations/discuss/18255
     */
    public List<List<Integer>> queuePermute(int[] num) {
        LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (int n : num) {
            int size = res.size();
            for (; size > 0; size--) {
                List<Integer> r = res.pollFirst();
                for (int i = 0; i <= r.size(); i++) {
                    List<Integer> t = new ArrayList<Integer>(r);
                    t.add(i, n);
                    res.add(t);
                }
            }
        }
        return res;
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
            System.out.println("      " + p.backTrackPermute(cases[i]));
            System.out.println("      " + p.queuePermute(cases[i]));
            System.out.println();
        }
    }
}
