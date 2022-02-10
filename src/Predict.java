import java.util.ArrayList;

public class Predict implements Command{

    public String name(){
        return "predict";
    }

    int max(int[] count) {
        if (count.length > 0) {
            int i_max = 0;
            int max = count[0];
            for (int i = 0; i < count.length; i++) {
                if (count[i] > max) {
                    i_max = i;
                    max = count[i];
                }
            }
            return i_max;
        }
        return -1;
    }

    public boolean run(String entry) {
        // Create a new scanner
        java.util.Scanner scan = new java.util.Scanner(System.in);

        // Ask for a path
        System.out.println("Please enter the path to the file:");
        String path_str = scan.nextLine();

        // Get the path
        java.nio.file.Path path = java.nio.file.Paths.get(path_str);

        try {
            // Read the file
            String file = java.nio.file.Files.readString(path);

            // Replace all non alpha by space
            file = file.replaceAll("[^a-zA-Z]", " ");

            // All uppercase to lowercase
            file = file.toLowerCase();

            // Get all the words
            String[] base = file.split(" +");

            // Transform [] into Array
            java.util.ArrayList<String> no_empty = new java.util.ArrayList<String>();
            for (String word : base)
                no_empty.add(word);

            // Sort the list
            java.util.Collections.sort(no_empty);

            // Create a list with all the word but only one time
            java.util.ArrayList<String> single = new java.util.ArrayList<String>();
            for (String word : no_empty){
                boolean in = false;
                for (String subword : single){
                    if (word.equals(subword)){
                        in = true;
                        break;
                    }
                }
                if (!in){
                    single.add(word);
                }
            }

            // get the list of plausible word
            java.util.ArrayList<String> plausible = new ArrayList<>();
            for (String word : single){
                int[] count = new int[single.size()];
                for (int i = 0; i < base.length - 1; i++) {
                    if (word.equals(base[i])) {
                        String next = base[i + 1];
                        for (int j = 0 ; j < single.size(); j++){
                            if (next.equals(single.get(j))) {
                                count[j] += 1;
                                break;
                            }
                        }
                    }
                }
                int index = max(count);
                plausible.add(single.get(index));
            }

            // Ask for a word
            System.out.print("Please enter a word: ");
            String word = scan.nextLine();

            // Predict
            boolean found = false;
            for (String w : single) {
                if (w.equals(word)) {
                    found = true;
                    // Find the index
                    int index = 0;
                    while (!single.get(index).equals(word))
                        index++;
                    // Print the most plausible word
                    System.out.print(plausible.get(index));
                    word = plausible.get(index);
                    for (int i = 0; i < 19; i++) {

                        // Find the index
                        index = 0;
                        while (!single.get(index).equals(word))
                            index++;

                        // Print the most plausible word
                        System.out.print(" " + plausible.get(index));
                        word = plausible.get(index);
                    }
                    break;
                }
            }
            // if the word isn't in the text
            if (!found)
                System.out.println("The world you provided isn't in the text you provided");
        }

        // Catch an error
        catch (java.io.IOException e){
            System.out.println("Unreadable file: " + e);
        }
        return false;
    }
}