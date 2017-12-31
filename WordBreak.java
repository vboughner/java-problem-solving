import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/*
  https://leetcode.com/problems/word-break/description/
  Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
  determine if s can be segmented into a space-separated sequence of one or more dictionary words.
  You may assume the dictionary does not contain duplicate words.

  For example, given
  s = "leetcode",
  dict = ["leet", "code"].

  Return true because "leetcode" can be segmented as "leet code".


 */
public class WordBreak {

    private boolean wordBreakHelper(String s, Set<String> wordSet) {
        int len = s.length();
        boolean[] firstPartWorks = new boolean[len + 1];
        firstPartWorks[0] = true;
        for (int i = 1; i < len + 1; i++) {
            for (int j = 0; j < i; j++) {
                // if we can string together two words, we know that firstPart of greater length works
                if (firstPartWorks[j] && wordSet.contains(s.substring(j, i))) {
                    firstPartWorks[i] = true;   // cache it
                    break;
                }
            }
        }
        return firstPartWorks[len];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<String>();
        for (String word : wordDict) {
            wordSet.add(word);
        }

        return wordBreakHelper(s, wordSet);
    }

    public static void main(String args[]) {
        WordBreak wb = new WordBreak();

        String s = "leetcode";
        List<String> dict = new ArrayList<String>();
        dict.add("leet");
        dict.add("code");
        dict.add("a");
        dict.add("aa");
        dict.add("aaa");
        dict.add("aaaa");
        dict.add("aaaaa");
        dict.add("aaaaaa");
        dict.add("aaaaaaa");

        System.out.println("leetcode: " + wb.wordBreak(s, dict));
        System.out.println("leetcode2: " + wb.wordBreak("leetcode2", dict));
        System.out.println("lots of a: " + wb.wordBreak("aaaaaaaaaaaaaaaaaaa", dict));
    }
}