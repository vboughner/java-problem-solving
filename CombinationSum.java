import java.util.List;

/*

https://leetcode.com/problems/combination-sum/description/

Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

For example, given candidate set [2, 3, 6, 7] and target 7,
A solution set is:

[
  [7],
  [2, 2, 3]
]

*/

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return null;
    }

    public static void main(String[] arg) {
        CombinationSum cs = new CombinationSum();
        int[] candidates = { 2, 3, 6, 7};

        System.out.println(cs.combinationSum(candidates, 7));
    }
}
