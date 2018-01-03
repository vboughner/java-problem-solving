

/*
https://leetcode.com/problems/longest-palindromic-substring/description/

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.

Example:

Input: "cbbd"

Output: "bb"

 */
public class LongestPalindromicSubstring {

    // brute force algorithm O(n^2):
    // - loop through the characters one at a time from the beginning,
    //   to see if that character is the beginning of a palindrome,
    //   check it by loop in from the other end to check for a palidrome
    // - if found one, and it is the largest one yet, store the length and index of the palindrome
    // - once we get close enough to the end that no longer palindrome is possible, we can break out of loop
    public String longestPalindrome(String s) {
        int origLength = s.length();
        int paliLocation = 0;
        int paliLength = 0;

        // System.out.println("starting search on string '" + s + "'");

        for (int i = 0; i < origLength - paliLength; i++) {
            int rightIndex = -1;
            // System.out.println("  starting search at index " + i);
            for (int j = s.length() - 1; j > i; j--) {
                if (rightIndex == -1) {
                    // search for the initial letter for palindrome until we can get started on a potential one
                    if (s.charAt(i) == s.charAt(j)) {
                        // System.out.println("  potential pali from right at index " + j);
                        rightIndex = j;
                    }
                }
                else {
                    // we're in a potential palindrome, make sure it's still valid
                    if (s.charAt(i + (rightIndex - j)) != s.charAt(j)) {
                        // when it stops begin valid, we might be able to start over again
                        // System.out.println("  potential pali stopped being okay at index " + j);
                        j = rightIndex;
                        rightIndex = -1;
                    }
                }
            }

            if (rightIndex != -1) {
                if (rightIndex - i + 1 > paliLength) {
                    paliLocation = i;
                    paliLength = rightIndex - i + 1;
                    // System.out.println("  new pali found at " + i + " of length " + paliLength + " and is " + s.substring(paliLocation, paliLocation + paliLength));
                }
            }
            else if (paliLength < 1) {
                // you always get at least a one letter palidrome
                paliLocation = i;
                paliLength = 1;
            }
        }

        return (paliLength > 0 ? s.substring(paliLocation, paliLocation + paliLength) : "");
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        String case1 = "babad";
        String case2 = "cbbd";
        String case3 = "";
        String case4 = "a";
        String case5 = "ff";
        String case6 = "fafghjfafj";
        String case7 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        System.out.println("result for '" + case1 + "' is '" + lps.longestPalindrome(case1) + "' and should be 'bab' or 'aba'");
        System.out.println("result for '" + case2 + "' is '" + lps.longestPalindrome(case2) + "' and should be 'bb'");
        System.out.println("result for '" + case3 + "' is '" + lps.longestPalindrome(case3) + "' and should be ''");
        System.out.println("result for '" + case4 + "' is '" + lps.longestPalindrome(case4) + "' and should be 'a'");
        System.out.println("result for '" + case5 + "' is '" + lps.longestPalindrome(case5) + "' and should be 'ff'");
        System.out.println("result for '" + case6 + "' is '" + lps.longestPalindrome(case6) + "' and should be 'jfafj'");
        System.out.println("result for '" + case7 + "' is");
        System.out.println("           '" + lps.longestPalindrome(case7) + "' and");
        System.out.println("should be  '" + case7 + "'");
    }
}