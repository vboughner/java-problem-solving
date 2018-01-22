import java.util.Arrays;

/*
https://leetcode.com/problems/search-for-a-range/description/

Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].


Notes:
 - runtime complexity is a clue that a binary search is likely to be part of this solution
 - we are searching for boundaries, not unique instances
 - there are potentially duplicates of the searched value, and other values next to it
 - we could use binary search to find any of the copies of our searched value
 - and use subsequent searches on the two sides of it to determine if the boundary needs to be expanded
 - both phases of the search could O(log n) if we keep using binary search techniques

Overall goal: search for a center point copy of the searched value, then search for outer boundaries
Algorithm Phase 1: find any copy of the number
  - define left boundary index to be zero
  - define right boundary index to be length - 1
  - define a midpoint index
  - is the midpoint the value we are looking for?
  - if yes, assign it as midpoint and move on to phase 2
  - if no, choose a side to search, update left and right, and search again
  - is left and/or right out of bounds?
  - if so, we can't find the number at all, give up

 Algorithm Phase 2: find the left boundary
  - split the area remaining and search for the number again
  - if we find it, we can expand the range
  - if we don't find it, we know we can't expand the range yet, cut the search area in half and search again
  - if we have to give up because we couldn't find the number, then range cannot be expanded, period.

 Algorithm Phase 3: find the right boundary
  - similar to left boundary

 */
public class SearchForRange {
    // search an area of array, including the given indexes, return index of one instance of the target number,
    // duplicates are allowed, returns -1 if the index could not be found
    public int findAnIndexForNumber(int[] nums, int leftIndex, int rightIndex, int target) {
        int mid = (leftIndex + rightIndex) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        else if (nums[mid] > target) {
            if (leftIndex < mid) {
                return findAnIndexForNumber(nums, leftIndex, mid - 1, target);
            }
        }
        else {
            if (rightIndex > mid) {
                return findAnIndexForNumber(nums, mid + 1, rightIndex, target);
            }
        }
        return -1;
    }

    // returns the leftmost instance of a number in the range given, returns rightIndex
    // if the number is not found anywhere else in the range (rightIndex is assumed to be the target)
    public int findLeftEdgeForIndex(int[] nums, int leftIndex, int rightIndex, int target) {
        int leftEdgeIndex = rightIndex;

        do {
            int expandIndex = findAnIndexForNumber(nums, leftIndex, leftEdgeIndex - 1, target);
            if (expandIndex == -1) {
                break;
            }
            else {
                leftEdgeIndex = expandIndex;
            }
        } while (true);

        return leftEdgeIndex;
    }

    public int findRightEdgeForIndex(int[] nums, int leftIndex, int rightIndex, int target) {
        int rightEdgeIndex = leftIndex;

        do {
            int expandIndex = findAnIndexForNumber(nums, rightEdgeIndex + 1, rightIndex, target);
            if (expandIndex == -1) {
                break;
            }
            else {
                rightEdgeIndex = expandIndex;
            }
        } while (true);

        return rightEdgeIndex;
    }

    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = { -1, -1 };

        if (nums.length > 0) {
            // phase 1
            int landed = findAnIndexForNumber(nums, left, right, target);

            // phase 2
            int leftEdgeIndex = findLeftEdgeForIndex(nums, left, landed, target);

            // phase 3
            int rightEdgeIndex = findRightEdgeForIndex(nums, landed, right, target);

            result[0] = leftEdgeIndex;
            result[1] = rightEdgeIndex;
        }

        return result;
    }

    public static void main(String[] args) {
        SearchForRange sfr = new SearchForRange();

        int[] case0 = {};
        int[] result0 = sfr.searchRange(case0, 8);
        int[] expected0 = { -1, -1 };

        System.out.println("case0 input    is  " + Arrays.toString(case0) + " with target of 8");
        System.out.println("case0 output   is  " + Arrays.toString(result0));
        System.out.println("case0 expected is  " + Arrays.toString(expected0));

        int[] case1 = { 5, 7, 7, 8, 8, 10 };
        int[] result1 = sfr.searchRange(case1, 8);
        int[] expected1 = { 3, 4 };

        System.out.println("case1 input    is  " + Arrays.toString(case1) + " with target of 8");
        System.out.println("case1 output   is  " + Arrays.toString(result1));
        System.out.println("case1 expected is  " + Arrays.toString(expected1));
    }
}
