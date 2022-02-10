public class Freq implements Command {
    public String name(){
        return "freq";
    }

    int first(java.util.ArrayList<Integer> number){
        int res = 0;
        int max = number.get(0);
        for (int i = 1; i < number.size(); i++) {
            if (number.get(i) > max){
                max = number.get(i);
                res = i;
            }
        }
        return res;
    }

    int second(java.util.ArrayList<Integer> number, int first){
        int res = 0;
        if (first == 0)
            res = 1;
        int max = number.get(res);
        for (int i = res + 1; i < number.size(); i++){
            if (i == first)
                continue;
            if (number.get(i) > max){
                max = number.get(i);
                res = i;
            }
        }
        return res;
    }

    int third(java.util.ArrayList<Integer> number, int first, int second){
        int res = 0;
        while (res == first || res == second)
            res++;
        int max = number.get(res);
        for (int i = res + 1; i < number.size(); i++){
            if (i == first || i == second)
                continue;
            if (number.get(i) > max){
                max = number.get(i);
                res = i;
            }
        }
        return res;
    }

    String[] freq(String file){

        file = file.replaceAll("[^a-zA-Z]", " ");


        file = file.toLowerCase();


        String[] kl = file.split(" +");


        java.util.ArrayList<String> uniqword = new java.util.ArrayList<String>();
        for (String word : kl)
            uniqword.add(word);

        java.util.Collections.sort(uniqword);
        java.util.ArrayList<String> single = new java.util.ArrayList<String>();
        for (String word : uniqword){
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

        java.util.ArrayList<Integer> res = new java.util.ArrayList<Integer>();
        for (String word : single){
            int cpt = 0;
            for (String subword : uniqword) {
                if (word.equals(subword)) {
                    cpt++;
                }
                else if (cpt > 0)
                    break;
            }
            res.add(cpt);
        }

        if (single.size() <= 3){
            String array[] = new String[single.size()];
            for (int i = 0; i < single.size(); i++) {
                array[i] = single.get(i);
            }
            return array;
        }

        int max1 = first(res);
        int max2 = second(res, max1);
        int max3 = third(res, max1, max2);

        String[] array = {single.get(max1), single.get(max2), single.get(max3)};
        return array;
    }

    public boolean run(String entry) {
        java.util.Scanner scan = new java.util.Scanner(System.in);

        System.out.println("Veuillez entrer le chemin du fichier :");
        String path_str = scan.nextLine();

        java.nio.file.Path path = java.nio.file.Paths.get(path_str);

        try {
            String file = java.nio.file.Files.readString(path);
            String[] res = freq(file);
            if (res.length > 0)
            {
                System.out.print(res[0]);
                for (int i = 1; i < res.length; i++) {
                    System.out.print(" " + res[i]);
                }
                System.out.println();
            }
        }
        catch (java.io.IOException e){
            System.out.println("Unreadable file: " + e);
        }

        return false;
    }

}