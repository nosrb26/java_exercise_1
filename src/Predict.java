import java.util.ArrayList;

public class Predict implements Command{

    public String name(){
        return "predict";
    }

    int maxindex(int[] count) {
        if (count.length > 0) {
            int res = 0;
            int max = count[0];
            for (int i = 0; i < count.length; i++) {
                if (count[i] > max) {
                    res = i;
                    max = count[i];
                }
            }
            return res;
        }
        return -1;
    }

    public boolean run(String entry) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        System.out.println("Veuillez entrer le chemin du fichier :");
        String path_str = scan.nextLine();
        java.nio.file.Path path = java.nio.file.Paths.get(path_str);

        try {
            String file = java.nio.file.Files.readString(path);
            file = file.replaceAll("[^a-zA-Z]", " ");
            file = file.toLowerCase();
            String[] base = file.split(" +");

            java.util.ArrayList<String> woblk = new java.util.ArrayList<String>();
            for (String word : base) {
                woblk.add(word);
            }
            java.util.ArrayList<String> uniqword = new java.util.ArrayList<String>();
            for (String word : woblk){
                boolean in = false;
                for (String subword : uniqword){
                    if (word.equals(subword)){
                        in = true;
                        break;
                    }
                }
                if (!in){
                    uniqword.add(word);
                }
            }

            java.util.ArrayList<String> getable = new ArrayList<>();
            for (String word : uniqword){
                int[] count = new int[uniqword.size()];
                for (int i = 0; i < base.length - 1; i++) {
                    if (word.equals(base[i])) {
                        String next = base[i + 1];
                        for (int j = 0 ; j < uniqword.size(); j++){
                            if (next.equals(uniqword.get(j))) {
                                count[j] += 1;
                                break;
                            }
                        }
                    }
                }
                int index = maxindex(count);
                getable.add(uniqword.get(index));
            }

            System.out.print("Veuillez entrer un mot :");
            String str = scan.nextLine();
            str = str.toLowerCase();

            boolean found = false;
            for (String w : uniqword) {
                if (w.equals(str)) {
                    found = true;
                    int index = 0;
                    while (!uniqword.get(index).equals(str)) {
                        index++;
                    }

                    System.out.print(getable.get(index));
                    str = getable.get(index);
                    for (int i = 0; i < 19; i++) {
                        index = 0;
                        while (!uniqword.get(index).equals(str)) {
                            index++;
                        }
                        System.out.print(" " + getable.get(index));
                        str = getable.get(index);
                    }
                    System.out.println();
                    break;
                }
            }

            if (!found) {
                System.out.println("Le mot indiqué n'est pas dans le texte donné");
            }
        }

        catch (java.io.IOException e){
            System.out.println("Unreadable file: " + e);
        }
        return false;
    }
}