import java.util.Map;
import java.util.HashMap;

/*
   https://www.codechef.com/wiki/tutorial-dynamic-programming
   Problem Statement: On a positive integer, you can perform any one of the following 3 steps:
     1.) Subtract 1 from it. ( n = n - 1 )
     2.) If its divisible by 2, divide by 2. ( if n % 2 == 0 , then n = n / 2  )
     3.) If its divisible by 3, divide by 3. ( if n % 3 == 0 , then n = n / 3  )

   Now the question is, given a positive integer n, find the minimum number of steps that takes n to 1

   eg: 1.) For n = 1 , output: 0
       2.) For n = 4 , output: 2  ( 4  /2 = 2  /2 = 1 )
       3.) For n = 7 , output: 3  ( 7  -1 = 6  /3 = 2  /2 = 1 )
 */
public class DynamicProgramming {
    private Map<Integer,Integer> memo = new HashMap<Integer,Integer>();

    public DynamicProgramming() {
        memo.put(1, 0);
    }

    // top-down approach, or "memoization"
    public int numSteps(int n) {
        if (n < 1) {
            System.out.println("Error: numSteps called with n less than 1");
            return 0;
        }

        // is this something we've already solved?
        Integer memoValue = memo.get(n);
        if (memoValue != null) {
            return memoValue;
        }

        // otherwise, use the minimum of the three choices
        int result = 1 + numSteps(n - 1);
        if (n % 2 == 0) {
            result = Math.min(result, 1 + numSteps(n / 2));
        }
        if (n % 3 == 0) {
            result = Math.min(result, 1 + numSteps(n / 3));
        }

        // need to remember this result
        memo.put(n, result);

        return result;
    }

    // bottom-up approach, or "dynamic programming", notice that we don't need recursion (saves stack space)
    public int numStepsDP(int n) {
        int[] dp = new int[n+1];
        dp[1] = 0;  // trivial case

        for (int i = 2; i <= n; i ++) {
            dp[i] = 1 + dp[i - 1];
            if (i % 2 == 0) dp[i] = Math.min(dp[i], 1 + dp[i/2]);
            if (i % 3 == 0) dp[i] = Math.min(dp[i], 1 + dp[i/3]);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();

        // run the two methods side-by-side so we can compare the results (which should be the same)
        for (int i = 1; i < 25; i++) {
            System.out.println(i + ": " + dp.numSteps(i) + ", " + dp.numStepsDP(i));
        }
    }
}