import java.util.List;
import java.util.ArrayList;

/*
  https://leetcode.com/problems/generate-parentheses/description/

  Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

  For example, given n = 3, a solution set is:

  [
    "((()))",
    "(()())",
    "(())()",
    "()(())",
    "()()()"
  ]

  Initial thoughts of my own:
  - we're trying to figure out all the possible combinations.  This could be written recursively.
  - for n=1, there's only 1 possibilty: ()
  - for n=2, there's two copies of what n=1 is one, plus a new combination for n=2:  ()() and (())
  - for n=3, there are these combo types 1-2, 2-1, and 3: ()()(), ()(()), (())(), ()()(), ((())) (remove duplicates)
  - for n=4, there are these combo types 1-3, 3-1, 2-2, and 4 (remove duplicates)
  - we can probably improve performance by saving results as they are built in some lists.
  - loop through from 0 to n/2, and concatenate together the strings from all the sub-cominbations
  - add a combination that possible only with the new total n, where they are all nested
  - sort and remove duplicates

  I knew this was not going to turn out well (too verbose, not performant, there had to be a better way).
  I looked at the solutions, because I was stumped on how to approach this without any further hints.

  Thank you goes to this solution:
  https://discuss.leetcode.com/topic/36057/easy-java-solution

  My observations are below (which I made about this problem after reading the solution, franky):
  - there is always n left parens and n right parens in every possible solution
  - a left paren can be added any time there is any left parens left to be used
  - a right paren can only be added when there have been enough left parens to support it
  - this can be drawn as a binary tree of depth n*2, where some paths are illegal
  - that means it could be built recursively, if you follow some rules
  - you can have to build out every possibility

  Future approach: formulated some rules about how to build such strings and
  that can help me come to a recursive solution to build them up. The solution looks a lot like
  building out a binary true.

 */
public class ParenBuilding {

    private void helper(List<String> results, String current, int leftCount, int rightCount) {
        if (rightCount == 0) {
            // once the string is built, add it to the standing results list
            results.add(current);
        }
        if (leftCount > 0) {
            // you can always try adding a left paren when there are some left
            helper(results, current + "(", leftCount - 1, rightCount);
        }
        if (rightCount > leftCount) {
            // add a right paren if they outnumber the remaining left parens
            helper(results, current + ")", leftCount, rightCount -1);
        }
    }


    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<String>();
        // there are always n left parens and n right parens
        helper(results, "", n, n);
        return results;
    }

/*
 * This is as far as I got on my original ideas before I gave up on that line of thought:

    public List<String> oldGenerateParenthesis(int n) {
        if (n < 1) {
            System.out.println("Error: cannot generate parens when n is less than 1");
            return null;
        }

        List<List<String>> storage = new ArrayList<List<String>>(n);

        List<String> retval = new ArrayList<String>();
        retval.add("()");
        storage.add(retval);

        for (int i = 2; i <= n; i++) {
            System.out.println("building n=" + i);
            for (int j = 1; j <= i/2; j++) {
                System.out.println("  combo " + j + " and " + (i - j));


                if (j != (i - j)) {
                    System.out.println("  combo " + (i - j) + " and " + j);
                }
            }
            System.out.println("  combo " + i);
            storage.add(retval);
        }

        return retval;
    }

*/

    public static void main(String[] args) {
        ParenBuilding pb = new ParenBuilding();
        System.out.println(pb.generateParenthesis(1));
        System.out.println(pb.generateParenthesis(2));
        System.out.println(pb.generateParenthesis(3));
        System.out.println(pb.generateParenthesis(4));
    }
}
