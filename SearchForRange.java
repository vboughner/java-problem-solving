import java.util.Arrays;

/*
https://leetcode.com/problems/search-for-a-range/description/

Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
 */
public class SearchForRange {
    public int[] searchRange(int[] nums, int target) {
        return null;
    }

    public static void main(String[] args) {
        SearchForRange sfr = new SearchForRange();

        int[] case1 = { 5, 7, 7, 8, 8, 10 };
        int[] result1 = sfr.searchRange(case1, 8);
        int[] expected1 = { 3, 4 };

        System.out.println("case1 input    is  " + Arrays.toString(case1) + " with target of 8");
        System.out.println("case1 output   is  " + Arrays.toString(result1));
        System.out.println("case1 expected is  " + Arrays.toString(expected1));
    }
}
