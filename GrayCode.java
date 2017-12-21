import java.util.List;
import java.util.ArrayList;

/*
 * GrayCode
 */
public class GrayCode {
    public List<Integer> grayCode(int n) {
	// create the list by taking the list for the previous value of n and reversing it with a new high order bit set
	List<Integer> retlist = new ArrayList<Integer>();
	retlist.add(0);
	for (int i = 0; i < n; i++) {
	    int size = retlist.size();
	    for (int k = size - 1; k >= 0; k--) {
		retlist.add(retlist.get(k) | 1 << i);
	    }
	}

	return retlist;
    }

    public static void main(String[] args) {
	GrayCode g = new GrayCode();
	System.out.println(g.grayCode(0));
	System.out.println(g.grayCode(1));
	System.out.println(g.grayCode(2));
	System.out.println(g.grayCode(3));
    }
}