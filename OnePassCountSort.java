/*
https://leetcode.com/problems/sort-colors/description/

Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
 */

public class OnePassCountSort {

    private void swap(int[] nums, int index1, int index2) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }

    // red = 0, white = 1, blue = 2
    // use two pointers, one from each end to pile up the reds and blues on the end
    // as we work from each end, skip indexes of the right color,
    // or move them to the other end if they are the other color,
    // or move them along (swap) if they are the middle color
    public void sortColors(int[] nums) {
        int len = nums.length;
        int indexLeft = -1;   // index of last known red one (0)
        int indexRight = len; // index of last known blue one (2)

        for (int i = 0; i < indexRight; i++) {
            // System.out.println("i = " + i + " and nums[i] = " + nums[i] + "   indexLeft = " + indexLeft + " and indexRight = " + indexRight);
            if (nums[i] == 0) {
                if (i > indexLeft + 1) {
                    swap(nums, indexLeft + 1, i);
                    // System.out.println("swapped for 0 at i = " + i);
                    i--;
                }
                indexLeft++;
            }
            else if (nums[i] == 2) {
                if (i < indexRight - 1) {
                    swap(nums, i, indexRight - 1);
                    // System.out.println("swapped for 2 at i = " + i);
                    i--;
                }
                indexRight--;
            }
        }
    }

    public static void main(String[] args) {
        OnePassCountSort opcs = new OnePassCountSort();

        int[] case1 = { 0, 2, 1 };
        opcs.sortColors(case1);
        System.out.print("[ ");
        for (int i = 0; i < case1.length; i++) {
            System.out.print(case1[i] + " ");
        }
        System.out.println("]");

        int[] case2 = { 1, 0, 1, 1, 0, 1, 1 };
        opcs.sortColors(case2);
        System.out.print("[ ");
        for (int i = 0; i < case2.length; i++) {
            System.out.print(case2[i] + " ");
        }
        System.out.println("]");

        int[] case3 = { 2, 2, 1 };
        opcs.sortColors(case3);
        System.out.print("[ ");
        for (int i = 0; i < case3.length; i++) {
            System.out.print(case3[i] + " ");
        }
        System.out.println("]");
    }
}