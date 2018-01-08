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

    public void nextPermutation(int[] nums) {
        // TODO: implement
    }

    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();

        int[] input1 =    { 1, 2, 3 };
        int[] solution1 = { 1, 3, 2 };

        int[] input2 =    { 3, 2, 1 };
        int[] solution2 = { 1, 2, 3 };

        int[] input3 =    { 1, 1, 5 };
        int[] solution3 = { 1, 5, 1 };

        System.out.print(arrayToString(input1) + " -> ");
        np.nextPermutation(input1);
        System.out.println(arrayToString(input1) + " (should be " + arrayToString(solution1) + ")");

        System.out.print(arrayToString(input2) + " -> ");
        np.nextPermutation(input2);
        System.out.println(arrayToString(input2) + " (should be " + arrayToString(solution2) + ")");

        System.out.print(arrayToString(input3) + " -> ");
        np.nextPermutation(input3);
        System.out.println(arrayToString(input3) + " (should be " + arrayToString(solution3) + ")");
    }
}