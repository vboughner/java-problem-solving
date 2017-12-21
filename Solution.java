
public class Solution {

    /*
     * Find index in the array where the item is, or it should be inserted.
     * When array is empty, the answer should always be index 0.
     * When the array contains the element, that should be the index.
     *
     * When the array doesn't contain the element, give the index that is one greater
     * than the last element that is less that it. In fact, that's the criteria that
     * will always work.
     *
     * Use a binary search (later).
     * Assumptions: input array is sorted, and there are no duplicates.
     * (would add error checking for this later)
     */
    public int searchInsert(int[] nums, int target) {
	// System.out.println();
	return this.searchInsertRecurse(nums, 0, nums.length, target);
    }

    public int searchInsertRecurse(int[] nums, int first, int last, int target) {
	// System.out.println("searchInsertRecurse(array, " + first + ", " +  last + ", " + target + ")");
	if (first >= last) {
	    return first;
	}
	else if (nums[first] == target) {
	    return first;
	}
	else {
	    int halfIndex = first + ((last - first) / 2);
	    // System.out.println("halfIndex is " + halfIndex + " with value of " + nums[halfIndex]);
	    if (nums[halfIndex] == target) {
		return halfIndex;
	    }
	    else if (halfIndex == first) {
		return (nums[halfIndex] < target) ? halfIndex + 1 : halfIndex;
	    }
	    else if (nums[halfIndex] < target) {
		return searchInsertRecurse(nums, halfIndex, last, target);
	    }
	    else {
		return searchInsertRecurse(nums, first, halfIndex, target);
	    }
	}
    }

    public int bruteForceSearchInsert(int[] nums, int target) {
	int retval = -1;

	for (int i = 0; i < nums.length; i++) {
	    if (nums[i] >= target) {
		retval = i;
		break;
	    }
	}

	if (retval < 0) {
	    retval = nums.length;
	}

	return retval;
    }

    public static void main(String[] args) {
	System.out.println("solution for searchInsert");
	Solution sol = new Solution();

	int[] nums1 = { 1, 3, 5, 6 };
        int target1 = 5;
        int output1 = sol.searchInsert(nums1, target1);
	System.out.println("output1 = " + output1 + " (should be 2)");

	int[] nums2 = new int[] { 1, 3, 5, 6 };
        int target2 = 2;
        int output2 = sol.searchInsert(nums2, target2);
	System.out.println("output2 = " + output2 + " (should be 1)");

	int output3 = sol.searchInsert(new int[] { 1, 3, 5, 6 }, 7);
	System.out.println("output3 = " + output3 + " (should be 4)");

	int output4 = sol.searchInsert(new int[] { 1, 3, 5, 6 }, 0);
	System.out.println("output4 = " + output4 + " (should be 0)");

	int output5 = sol.searchInsert(new int[] { }, 3);
	System.out.println("output5 = " + output5 + " (should be 0)");

	int output6 = sol.searchInsert(new int[] { 4 }, 0);
	System.out.println("output6 = " + output6 + " (should be 0)");

	int output7 = sol.searchInsert(new int[] { 4 }, 5);
	System.out.println("output7 = " + output7 + " (should be 1)");

	int output8 = sol.searchInsert(new int[] { 1, 3, 5, 6, 8 }, 5);
	System.out.println("output8 = " + output8 + " (should be 2)");

	int output9 = sol.searchInsert(new int[] { 1, 3, 5, 6, 8 }, 10);
	System.out.println("output9 = " + output9 + " (should be 5)");
    }
}
