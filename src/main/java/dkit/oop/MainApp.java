package dkit.oop;

/*  SpellChecker     Nov 2023
 *
 * Demonstrates : HashSet and Set - (fast access and no duplicates)
 *
 * This MainApp reads a text file containing all the words from the textbook "Alice in Wonderland"
 * into one HashSet, and reads a list of dictionary of words into aa second HashSet.
 * It then checks to see if the words from the textbook document
 * exist in the dictionary.  If a word is not in the dictionary, it is printed.
 *
 * We use a HashSet data structure to store the dictionary words, and the document words.
 * A Set does not allow duplicates.  If we attempt to add a duplicate word to Set,
 * that duplicate word is simply ignored.  This is useful as it filters out any repeated
 * words from the textbook. (There is no point in checking two words if they are the same)
 *
 * Specifically, we use a HashSet - which is a Set that uses a Hash function for
 * storing and retrieving elements. This is the best choice for speed (performance).
 */

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;


public class MainApp {

    public static void main(String[] args) throws FileNotFoundException {
        MainApp app = new MainApp();
        app.start();
    }

    public void start() throws FileNotFoundException {

        // Load Set of dictionary words from the dictionary file
        Set<String> dictionaryWords = loadSetFromFile("dictionary.txt");  // the dictionary of words

        // Load words from the story book into a Set.
        // A Set is used here so that we only load one of each word from the book,
        // and thus avoid looking up the same word twice in the dictionary.
        // Remember that a Set does not accept duplicate values, so attempting
        // to add a word that is already there has no effect.

        Set<String> documentWords = loadSetFromFile("alice_in_wonderland.txt");  // document words file

        // Print all words that are in the document but not the dictionary
        System.out.println("Spell Checking of Alice in Wonderland text, using a dictionary\n)");

        System.out.println("Words from the Book that are NOT in the dictionary:");
        for (String word : documentWords) {
            if (!dictionaryWords.contains(word)) {   // check if dictionary contains the current word
                System.out.print(word + ", ");
            }
        }
        System.out.println("\nProgram finished.");

        // the .contains() method above will be very efficient/fast in looking up
        // a key word from the dictionary because it is a HashSet and therefore
        // uses a Hash Table approach. O(1).
    }

    /**
     * Reads all words from a text file and returns them in Set of lowercase words.
     *
     * @param filename the name of the set file
     * @return a Set containing all words from the file in lowercase. Here, a
     * word is a sequence of upper- and lower-case letters.
     */
    public Set<String> loadSetFromFile(String filename)
            throws FileNotFoundException {

        Set<String> words = new HashSet<>();  // HashSet for fastest access

        Scanner in = new Scanner(new File(filename));

        // Use any character other than a-z or A-Z as a delimiter.
        // The "^" indicates 'NOT', so match characters other than characters a-z or A-Z
        in.useDelimiter("[^a-zA-Z]+");     // a RegularExpression (RegEx)

        while (in.hasNext()) {
            words.add(in.next().toLowerCase());
        }
        return words;
    }

    // HashSet requires that hashCode() and equals() methods are implemented
    // for the type of object stored.  Above, words are stored as String types
    // so hashCode() and equals() methods must be implemented in the String
    // class - which they are!
    // If we use our own class (e.g. Student) as the Key type for HashSet, then
    // that class MUST implement the hashCode() and equals() methods appropriately.
    // Therefore, we must write those two methods.
    // (otherwise the HashSet won't operate correctly).
}
