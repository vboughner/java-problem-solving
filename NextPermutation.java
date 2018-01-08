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