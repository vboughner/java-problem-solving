import java.util.List;
import java.util.ArrayList;


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

    public boolean wordBreak(String s, List<String> wordDict) {
        int firstPartLen = 1;
        while (firstPartLen < s.length()) {
            String firstPart = s.substring(0, firstPartLen);
            System.out.print("first part is '" + firstPart + "' and... ");
            if (wordDict.contains(firstPart)) {
                String secondPart = s.substring(firstPartLen);
                System.out.println("second part is '" + secondPart + "'");
                if (wordBreak(secondPart, wordDict)) {
                    System.out.println(" returning true");
                    return true;
                }
            }
            System.out.println(" keep looking");
            firstPartLen++;
        }
        return wordDict.contains(s);
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