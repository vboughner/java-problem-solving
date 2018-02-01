import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/*
https://leetcode.com/problems/group-anagrams/description/

Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note: All inputs will be in lower-case.

Notes:
- assume there is no punctuation or upper-case letters, all inputs are between 'a' and 'z'
- some letters could be used more than once

*/
class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer,List<String>> anagramList = new HashMap<Integer,List<String>>();
        List<List<String>> result = new ArrayList<List<String>>();
        int len = strs.length;
        for (int i = 0; i < len; i++) {
            int hash = 0;
            int clen = strs[i].length();
            for (int c = 0; c < clen; c++) {
                int bitnum = strs[i].charAt(c) - 'a';
                hash |= 1 << bitnum;
            }

            List<String> list = anagramList.get(hash);
            if (list == null) {
                list = new ArrayList<String>();
                anagramList.put(hash, list);
                result.add(list);
            }
            list.add(strs[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();
        String[] case1 = { "eat", "tea", "tan", "ate", "nat", "bat" };
        String[] case2 = { "lee", "eel", "el", "elle" };

        System.out.println("solution1: " + ga.groupAnagrams(case1));
        System.out.println("solution1: " + ga.groupAnagrams(case2));
    }
}