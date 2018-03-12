import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/*
    Problem:
        given a list of sysnoym pair words, like:
          ["apple", "fruit"]
          ["banana", "fruit"]
          ["likes", "enjoys"]
        return true if two phrases are really the same phrase
        because of synonym matches, like:
          "Steve likes fruit" and
          "Steve enjoys apple"

    Algorithm:
        // add all the synonyms to the map so they get ids
            // check to see if either word is in the map already,
            // if so, use that id for both words
            // if not, create a new id for both words
            // place both words in the map

        // go through query a and b and create a list of ids for the words
            // check to see if the word is in the map already,
            // if so, use the id,
            // if not, create a new unique id, and place in the map

        // compare the two lists, return the result
 */
public class QueryWords {
    private Map<String,Integer> synomap = new HashMap<String,Integer>();
    private int idCounter = 1;

    // TODO: implement and use an initialize function for better startup
    private void initializeMap(List<String[]> synonyms) {
    }

    private List<Integer> createIdList(String query) {
        List<Integer> result = new ArrayList<Integer>();

        // separate into words, loops through the words
        String[] arr = query.split(" ");
        for (String s : arr) {
            // look up word in the map, use the id if there is one, or create an id
            Integer id = synomap.get(s);
            if (id == null) {
                id = new Integer(idCounter++);
                synomap.put(s, id);
            }

            // add id to the result
            result.add(id);
        }

        return result;
    }

    public boolean areQuerySame(List<String[]> synonyms, String queryA, String queryB) {

        // add all synonyms
        if (synonyms != null) {
            for (String[] synopair : synonyms) {
                Integer id = synomap.get(synopair[0]);
                if (id == null) {
                    id = synomap.get(synopair[1]);
                }
                if (id == null) {
                    id = new Integer(idCounter++);
                }
                // choose one id to use, let's say the first one

                // replace all the entries with the second id to use the first id instead
                synomap.put(synopair[0], id);
                synomap.put(synopair[1], id);

            }
        }

        // create query id lists for a and b
        List<Integer> idsForQueryA = createIdList(queryA);
        List<Integer> idsForQueryB = createIdList(queryB);

        // compare
        if (idsForQueryA.size() == idsForQueryB.size()) {
            for (int i = 0; i < idsForQueryA.size(); i++) {
                if (!idsForQueryA.get(i).equals(idsForQueryB.get(i))) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }


    public void goThruQueries(List<String[]> synonyms, List<String[]> queryPairs) {
        boolean firstTime = true;
        for (String[] pair : queryPairs) {
            boolean same = areQuerySame(firstTime ? synonyms : null, pair[0], pair[1]);
            firstTime = false;
            System.out.println(same);
        }
    }

    public static void main(String[] args) {
        QueryWords qw = new QueryWords();
        List<String[]> synonyms = new ArrayList<>();
        synonyms.add(new String[] { "likes", "enjoys" });
        synonyms.add(new String[] { "apple", "fruit" });
        synonyms.add(new String[] { "fruit", "banana" });

        for (String[] s : synonyms) {
            for (int i = 0; i < s.length; i++) {
                System.out.print(s[i] + " ");
            }
            System.out.println();
        }

        List<String[]> queryPairs = new ArrayList<String[]>();
        queryPairs.add(new String[] { "Steve likes banana", "Steve enjoys fruit" });
        queryPairs.add(new String[] { "Steve likes banana", "Steve enjoys apple" });
        queryPairs.add(new String[] { "Steve likes banana", "Steve enjoys cars" });
        qw.goThruQueries(synonyms, queryPairs);

        synonyms.add(new String[] { "cars", "busses" });
        synonyms.add(new String[] { "cars", "fruit" });
    }
}
