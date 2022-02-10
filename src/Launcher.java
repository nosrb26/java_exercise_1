public class Launcher {
    public static int Fibo(int n) {
        if (n <= 1) return n;
        else return Fibo(n-1) + Fibo(n-2);
    }

    public static int max1(java.util.ArrayList<Integer> number){
        int i_max = 0;
        int max = number.get(0);
        for (int i = 1; i < number.size(); i++){
            if (number.get(i) > max){
                max = number.get(i);
                i_max = i;
            }
        }
        return i_max;
    }

    public static int max2(java.util.ArrayList<Integer> number, int i_max1){
        int i_max = 0;
        if (i_max1 == 0)
            i_max = 1;
        int max = number.get(i_max);
        for (int i = i_max + 1; i < number.size(); i++){
            if (i == i_max1)
                continue;
            if (number.get(i) > max){
                max = number.get(i);
                i_max = i;
            }
        }
        return i_max;
    }

    public static int max3(java.util.ArrayList<Integer> number, int i_max1, int i_max2){
        int i_max = 0;
        while (i_max == i_max1 || i_max == i_max2)
            i_max++;
        int max = number.get(i_max);
        for (int i = i_max + 1; i < number.size(); i++){
            if (i == i_max1 || i == i_max2)
                continue;
            if (number.get(i) > max){
                max = number.get(i);
                i_max = i;
            }
        }
        return i_max;
    }

    public static String[] frequence(String file){

        file = file.replaceAll("[^a-zA-Z]", " ");

        // All uppercase to lowercase
        file = file.toLowerCase();

        // Get all the words
        String[] base = file.split(" ");

        // Delete blank words
        java.util.ArrayList<String> no_empty = new java.util.ArrayList<String>();
        for (String word : base){
            if (!word.isBlank()){
                no_empty.add(word);
            }
        }

        java.util.Collections.sort(no_empty);

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

        java.util.ArrayList<Integer> nbow = new java.util.ArrayList<Integer>();
        for (String word : single){
            int cpt = 0;
            for (String subword : no_empty) {
                if (word.equals(subword)) {
                    cpt++;
                }
                else if (cpt > 0)
                    break;
            }
            nbow.add(cpt);
        }

        if (single.size() <= 3){
            String array[] = new String[single.size()];
            for (int i = 0; i < single.size(); i++)
                array[i] = single.get(i);
            return array;
        }

        int i1 = max1(nbow);
        int i2 = max2(nbow, i1);
        int i3 = max3(nbow, i1, i2);

        String[] array = {single.get(i1), single.get(i2), single.get(i3)};
        return array;
    }

    public static void main(String[] args) {
        System.out.println("Welcome");
        java.util.Scanner sc = new java.util.Scanner(System.in);

        String str = sc.nextLine();
        while (!str.equals("quit")) {
            if (!str.equals("fibo") && !str.equals("freq")) {
                System.out.println("Unknown Command");
                str = sc.nextLine();
            }
            else if (str.equals("fibo")){
                System.out.println("Veuillez entrer un nombre n :");
                int fiboo = sc.nextInt();
                sc.nextLine();
                System.out.println(Fibo(fiboo));
                str = sc.nextLine();
            }
            else if (str.equals("freq")){
                System.out.println("Veuillez entrer le chemin du fichier :");
                String path_str = sc.nextLine();
                java.nio.file.Path path = java.nio.file.Paths.get(path_str);
                try {
                    String file = java.nio.file.Files.readString(path);
                    String[] most_freq = frequence(file);
                    if (most_freq.length > 0)
                    {
                        System.out.print(most_freq[0]);
                        for (int i = 1; i < most_freq.length; i++)
                            System.out.print(" " + most_freq[i]);
                        System.out.println();
                    }
                }
                catch (java.io.IOException e){
                    System.out.println("Unreadable file: " + e);
                }

                str = sc.nextLine();
            }
        }


    }
}
