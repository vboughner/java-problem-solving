import java.lang.StringBuilder;

/*

https://leetcode.com/problems/next-permutation/description/

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

Notes:
  - each instance of number may only be used once
  - multiple instances of a number are equivalent
  - lexicographically lowest numbers have lowest numbers on the right
  - highest arrangements have the highest numbers on the left
  - requirement to re-arrange in-place is a clue it can be done with extra data structures
  - this is essentially like incrementing a counter (with limits on how you can do it, because you can't reuse something)
  - if what increment results in the same arrangement (because of duplicate digits), increment again
  - if incrementing isn't possible, roll the entire sequence and make it the lowest possible number arrangement
  - the lowest number arrangement is the reverse of the highest number arrangement

  full count:  1,2,3 -> 1,3,2 -> 2,1,3 -> 2,3,1 -> 3,1,2 -> 3,2,1
               (swaps 3 and 2)
                        (swaps 2 and 3)
                        (swaps 2 and 1)
                                 (swaps 3 and 1)
                                          (swaps 3 and 2)
                                          (swaps 1 and 2)
                                                   (swaps 2 and 1)

  - all steps appear to be swapping two digists that are next to each other
  - but sometimes you aren't starting with the lowest pair
  - will need to think about this some more...

  - I got a hint about the algorithm from the discussions (but didn't look at the code),
    and can now outline how I'm going to solve this...

Algorithm:
  - starting from right to left, look for the first number that is not ascending,
    like the 3 in: 5,3,4,2,1
  - if there is no such number, then we're at the highest permutation, such as 5,4,3,2,1,
    and we need to simply reverse the entire order (in-place)
  - if there was a number (like 3 above), then we need to increment the permutation by one, thusly...
  - starting from right to left, look for the first number that is bigger than that 3
    (in 5,3,4,2,1 it would be the 4)
  - swap those two numbers (making the minimal upward adjustment at the 3's position)
    (that would make the number 5,4,3,2,1)
  - reverse all the numbers to the right of 3 (putting it as low as it can be)
    (that would make the number 5,4,1,2,3)
  - return the result
*/
public class NextPermutation {

    private static String arrayToString(int[] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i] + " ");
        }
        sb.append("]");
        return sb.toString();
    }

    // reverse all elements between and including the startIndex and endIndex
    private void reversePartOfArray(int[] nums, int startIndex, int endIndex) {
        int len = endIndex - startIndex + 1;
        // System.out.println("swap subset length is " + len);
        if (len > 1) {
            for (int i = 0; i < len / 2; i++) {
                int tmp = nums[startIndex + i];
                nums[startIndex + i] = nums[endIndex - i];
                nums[endIndex - i] = tmp;
            }
        }
    }

    public void nextPermutation(int[] nums) {
        // look for first number that is not ascending
        int len = nums.length;
        int currentColumn = len - 1;
        for (int i = len - 2; i >= 0; i--) {
            // System.out.println("i=" + i + ", nums[i]=" + nums[i] + ", nums[currentColumn]=" + nums[currentColumn]);
            if (nums[i] < nums[i + 1]) {
                currentColumn = i;
                // System.out.println("current column is " + i);
                break;
            }
        }
        if (currentColumn == len - 1) {
            // there was no such number, reverse everything and return
            // System.out.println("no number not ascending, reversing everything");
            reversePartOfArray(nums, 0, len - 1);
            return;
        }
        else {
            // System.out.println("First number not ascending is in column " + currentColumn);
        }

        // for the number, from the right, that is bigger than it, and we can swap with that one
        for (int i = len - 1; i > currentColumn; i--) {
            if (nums[i] > nums[currentColumn]) {
                // we found it!  make the swap
                // System.out.println("we found it, make the swap at column " + i);
                int tmp = nums[currentColumn];
                nums[currentColumn] = nums[i];
                nums[i] = tmp;

                // and reverse everything after the original column
                reversePartOfArray(nums, currentColumn + 1, len - 1);
                return;
            }
        }
        System.out.println("Error: we should not reach this point");
    }

    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();

        int[] input1 =    { 1, 2, 3 };
        int[] solution1 = { 1, 3, 2 };

        int[] input2 =    { 3, 2, 1 };
        int[] solution2 = { 1, 2, 3 };

        int[] input3 =    { 1, 1, 5 };
        int[] solution3 = { 1, 5, 1 };

        int[] input4 =    { 2, 3, 1 };
        int[] solution4 = { 3, 1, 2 };

        int[] input5 =    { 5, 1, 1 };
        int[] solution5 = { 1, 1, 5 };

        System.out.print(arrayToString(input1) + " -> ");
        np.nextPermutation(input1);
        System.out.println(arrayToString(input1) + " (should be " + arrayToString(solution1) + ")");

        System.out.print(arrayToString(input2) + " -> ");
        np.nextPermutation(input2);
        System.out.println(arrayToString(input2) + " (should be " + arrayToString(solution2) + ")");

        System.out.print(arrayToString(input3) + " -> ");
        np.nextPermutation(input3);
        System.out.println(arrayToString(input3) + " (should be " + arrayToString(solution3) + ")");

        System.out.print(arrayToString(input4) + " -> ");
        np.nextPermutation(input4);
        System.out.println(arrayToString(input4) + " (should be " + arrayToString(solution4) + ")");

        System.out.print(arrayToString(input5) + " -> ");
        np.nextPermutation(input5);
        System.out.println(arrayToString(input5) + " (should be " + arrayToString(solution5) + ")");
    }
}