import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/decode-ways/description/

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26

Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 */

public class DecodeWays {
    private int numDecodingsHelper(Map<String,Integer> numWays, String s, int index) {
        if (s.length() - index <= 1) {
            return 1;
        }

        Integer ways = numWays.get(s.substring(index));
        if (ways != null) {
            return ways;
        }

        int retval;
        int pairValue = Integer.valueOf(s.substring(index, index + 2));
        if (pairValue > 26) {
            retval = numDecodingsHelper(numWays, s, index + 1);
        }
        else {
            retval = numDecodingsHelper(numWays, s, index + 1) + numDecodingsHelper(numWays, s, index + 2);
        }

        numWays.put(s.substring(index), retval);
        return retval;
    }

    public int numDecodings(String s) {
        Map<String,Integer> numWays = new HashMap<String,Integer>();

        // NOTE: need error checking for digits
        if (s == null || s.length() == 0) {
            return 0;
        }

        return numDecodingsHelper(numWays, s, 0);
    }

    static public void main(String[] args) {
        DecodeWays dw = new DecodeWays();
        String case0 = "";
        String case1 = "1";
        String case2 = "3";
        String case3 = "12";
        String case4 = "1221";
        String case5 = "1261";
        String case6 = "1271";

        System.out.println("'" + case0 + "' has " + dw.numDecodings(case0) + " decodings");
        System.out.println("'" + case1 + "' has " + dw.numDecodings(case1) + " decoding");
        System.out.println("'" + case2 + "' has " + dw.numDecodings(case2) + " decoding");
        System.out.println("'" + case3 + "' has " + dw.numDecodings(case3) + " decodings");
        System.out.println("'" + case4 + "' has " + dw.numDecodings(case4) + " decodings");
        System.out.println("'" + case5 + "' has " + dw.numDecodings(case5) + " decodings");
        System.out.println("'" + case6 + "' has " + dw.numDecodings(case6) + " decodings");
    }
}