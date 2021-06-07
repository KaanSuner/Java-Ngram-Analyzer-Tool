
import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Kaan
 * @version 1.8.0_111
 */
class NgramExtractor {

    /**
     * keeps the first strings reading file coming from user.
     */
    ArrayList<String> words = new ArrayList<>();
    /**
     * keeps the tokenized strings from "words" list.
     */
    ArrayList<String> ngramwords = new ArrayList<>();
    /**
     * keeps the K=token,V=count values on hashmap.
     */
    HashMap<String, Integer> ngramfeatures = new HashMap<>();
    /**
     * number of total tokens.
     */
    double totaltoken;
    String space = " ";
    /**
     * single token
     */
    int token;

    public static void main(String[] args) throws IOException {
        NgramExtractor object = new NgramExtractor();
        object.readFile(args[0]);
        int tokensize = Integer.parseInt(args[2]);
        if (tokensize == 0) {
            System.out.println("You did not enter a valid value.");
        } else {
            object.tokenizer(tokensize);
            object.counter(object.ngramwords, args[1]);
        }
    }

    /**
     * Reading file function. It reads the text file,converts it to string list.
     *
     * @param input Input file name received from the user.
     */
    void readFile(String input) {
        try {

            File file = new File(input);
            Scanner s = new Scanner(file);

            while (s.hasNext()) {
                String next = s.next();
                String[] distinct = next.replaceAll("[^a-zA-Z ]", "")
                        .toLowerCase().split(" ");
                String word = distinct[0];
                words.add(word);
            }

            s.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * It converts the string list to token list with the token size received
     * from the user.
     *
     * @param n ngram size received from the user.
     */
    void tokenizer(int n) {
        token = n;
        if (token > words.size()) {
            System.out.println("You did not enter a valid value");
        } else {
            for (int i = 0, j = 0; i < words.size() - (token - 1)
                    && j < words.size() - (token - 1); i++, j++) {
                ngramwords.add(token(i, j));
            }
            totaltoken = ngramwords.size();
        }
    }

    /**
     * function for every single token. Returns the token itself.
     *
     * @param a index of first element of current token.
     * @param b b+(token-1) is index of last element of current token.
     * @return token.
     */
    String token(int a, int b) {
        if (a == (b + (token - 1))) {
            return words.get(a);
        }
        return words.get(a) + space.toString() + token(a + 1, b);
    }

    /**
     * It checks,sorts and prints how many times the token is repeated in the
     * file.
     *
     * @param list token list.
     * @param args output file name received from user.
     * @throws IOException
     */
    void counter(ArrayList<String> list, String args) throws IOException {

        FileWriter output = new FileWriter(args);
        PrintWriter printOut = new PrintWriter(output);

        printOut.println("Total number of Tokens: " + Math.round(totaltoken));
        printOut.println();
        printOut.println("ngram,count,frequency");

        for (String i : list) {
            Integer j = ngramfeatures.get(i);
            ngramfeatures.put(i, (j == null) ? 1 : j + 1);
        }

        Map<String, Integer> hashmap = sortDescendingly(ngramfeatures);

        // displaying the occurrence of elements in the arraylist 
        for (Map.Entry<String, Integer> sortedngramfeatures : hashmap.entrySet()) {
            printOut.println(sortedngramfeatures.getKey()
                    + "," + sortedngramfeatures.getValue()
                    + "," + (sortedngramfeatures.getValue() / totaltoken) * 100);
        }
        printOut.flush();
        printOut.close();
    }

    /**
     * Sorts the tokens in descending order according to their frequency values
     * using hashmap.
     *
     * @param HM hashmap to be sorted
     * @return Final hashmap
     */
    HashMap<String, Integer> sortDescendingly(HashMap<String, Integer> HM) {

        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer>> list
                = new LinkedList<Map.Entry<String, Integer>>(HM.entrySet());

        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> HM2 : list) {
            temp.put(HM2.getKey(), HM2.getValue());
        }
        return temp;
    }

}
