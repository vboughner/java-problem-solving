import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

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


Notes:
  - no duplicates among the candidates
  - there are multiple answers possible, we're returning a list of them
  - any number in the candidates that is larger than the target can be ignored/dicarded
  - any number the same as the target is an answer (there should only be one like that)
  - don't have to worry about zero, it can't be among the candidates

Brute Force Algorithm:
  - recursive algorithm
  - if we already have a memorized solution for this target, use it
  - go through all the numbers among the candidates:
  - if larger than target, skip it (it won't be part of the solution),
  - if equal to the target, use it (it will be one of the solutions),
  - if less than the target, re-call this method again with a lower target number and use
    all the sub-solutions as part of a solution involving that number
  - save all the solutions for this target for re-use
  - anything that was used in a solution should be marked for non-reuse in next loops

How to make this faster, use dynamic programming, and save the solutions as we find them.
*/

public class CombinationSum {
    private List<List<Integer>> comboSumHelper(Map<Integer, List<List<Integer>>> memory, int[] candidates, int target) {
        List<List<Integer>> result = memory.get(target);
        Map<Integer,Boolean> usedThisTime = new HashMap<Integer,Boolean>();

        if (result == null) {
            result = new ArrayList<List<Integer>>();
            for (int i = 0; i < candidates.length; i++) {
                int candi = candidates[i];
                if (candi == target) {
                    // add this single number to the results, it is alone
                    List<Integer> l = new ArrayList<Integer>();
                    l.add(candi);
                    result.add(l);
                }
                else if (candi > target || usedThisTime.containsKey(candi)) {
                    // do nothing, eliminates some extra work and the re-use of some combos
                }
                else {
                    // make sure we don't re-use (later in this loop) any of the numbers we've already tried,
                    // and this will eliminate duplicates
                    usedThisTime.put(candi, true);

                    // when less than the target, use this number and grab sub-solutions on a smaller target
                    List<List<Integer>> sublist = comboSumHelper(memory, candidates, target - candi);
                    for (int j = 0; j < sublist.size(); j++) {
                        List<Integer> subsolution = sublist.get(j);
                        List<Integer> additionalResult = new ArrayList<Integer>();
                        additionalResult.add(candi);
                        additionalResult.addAll(subsolution);
                        result.add(additionalResult);
                    }
                }
            }
            memory.put(target, result);
        }
        return result;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Map<Integer,List<List<Integer>>> memory = new HashMap<Integer,List<List<Integer>>>();
        return comboSumHelper(memory, candidates, target);
    }

    public static void main(String[] arg) {
        CombinationSum cs = new CombinationSum();
        int[] candidates1 = { 2, 3, 6, 7 };
        int[] candidates2 = { 2, 3, 6, 7, 11, 14, 20, 28, 45, 62 };
        int[] candidates3 = { 2, 3, 4, 9, 12 };

        System.out.println(cs.combinationSum(candidates1, 1));
        System.out.println(cs.combinationSum(candidates1, 5));
        System.out.println(cs.combinationSum(candidates1, 7));
        System.out.println(cs.combinationSum(candidates2, 28));
        System.out.println(cs.combinationSum(candidates2, 29));
        System.out.println(cs.combinationSum(candidates3, 12));
    }
}
