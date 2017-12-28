import java.lang.StringBuilder;

public class Shuffler {
    public static String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
    public static String VOWELS = "aeiou";

    /*
     * Returns a new string that is a combination of the current string and 'count'
     * characters from the source string (using any character in the source string
     * no more than one time).
     */
    public static String shuffleIntoString(String current, String source, int count) {
        if (current == null || source == null || count < 0 || count > source.length()) {
            System.out.println("Error in parameters to shuffleIntoString");
            return null;
        }

        StringBuilder retval = new StringBuilder(current);        // build up by inserting at random locations
        StringBuilder depletedSource = new StringBuilder(source); // remove characters as they are used

        for (int i = 0; i < count; i++) {
            int pick = (int) (Math.random() * depletedSource.length());
            int whereToInsert = (int) (Math.random() * retval.length());
            retval = retval.insert(whereToInsert, depletedSource.charAt(pick));
            depletedSource.deleteCharAt(pick);
        }

        return retval.toString();
    }

    public static void main(String[] args) {
        Shuffler shuf = new Shuffler();

        for (int i = 0; i < 10; i++) {
            String result = shuf.shuffleIntoString("", shuf.CONSONANTS, 5);
            result = shuf.shuffleIntoString(result, shuf.VOWELS, 5);
            System.out.println(result);
        }
    }
}
