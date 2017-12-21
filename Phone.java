import java.util.List;
import java.util.ArrayList;

public class Phone {

    static private String[] NCHARS = {
	"0",
	"1",
	"abc",
	"def",
	"ghi",
	"jkl",
	"mno",
	"qprs",
	"tuv",
	"wxyz"
    };
    
    Phone() {
    }

    // creating all possible combinations of telephone names:
    // brute force was would be time of O(c^n), is there a faster way?
    // yes, treat this like a depth-first search (because of lower memory requirements), and
    // follow every possible path, all the way to the bottom and create each string in turn
    // all the way through (not stopping to store intermediate strings), this is a traversal
    // of the entire tree, and we only add the results of a node to the list when we 
    // get to a a bottom leaf of the tree

    // TODO: figure out a way to express this iteratively


    public List<String> letterCombinations(String digits) {
	List<String> retval = new ArrayList<String>();
	int len = digits.length();
	if (len > 0) {
	    char firstChar = digits.charAt(0);
	    int firstNum = firstChar - '0';
	    String pickChars = NCHARS[firstNum];
	    if (len == 1) {
		for (int i = 0; i < pickChars.length(); i++) {
		    retval.add(pickChars.substring(i, i+1));
		}
	    }
	    else {
		List<String> combos = letterCombinations(digits.substring(1));
		for (String s : combos) {
		    for (int i = 0; i < pickChars.length(); i++) {
			retval.add(pickChars.substring(i, i+1) + s);
		    }
		}
		
	    }
	}
	return retval;
    }

    public static void main(String[] args) {
	Phone p = new Phone();
	System.out.println(p.letterCombinations(""));
	System.out.println(p.letterCombinations("2"));
	System.out.println(p.letterCombinations("23"));
	System.out.println(p.letterCombinations("234"));
    }
}
