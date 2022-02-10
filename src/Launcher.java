public class Launcher {
    public static int Fibo(int n) {
        if (n <= 1) return n;
        else return Fibo(n-1) + Fibo(n-2);
    }

    public static int first(java.util.ArrayList<Integer> number){
        int res = 0;
        int max = number.get(0);
        for (int i = 1; i < number.size(); i++){
            if (number.get(i) > max){
                max = number.get(i);
                res = i;
            }
        }
        return res;
    }

    public static int second(java.util.ArrayList<Integer> number, int first){
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

    public static int third(java.util.ArrayList<Integer> number, int first, int second){
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

    public static String[] freq(String file){

        file = file.replaceAll("[^a-zA-Z]", " ");

        file = file.toLowerCase();

        String[] base_word = file.split(" ");

        java.util.ArrayList<String> kl = new java.util.ArrayList<String>();
        for (String word : base_word){
            if (!word.isBlank()){
                kl.add(word);
            }
        }

        java.util.Collections.sort(kl);

        java.util.ArrayList<String> single = new java.util.ArrayList<String>();
        for (String word : kl){
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

        java.util.ArrayList<Integer> maxarray = new java.util.ArrayList<Integer>();
        for (String word : single){
            int cpt = 0;
            for (String subword : kl) {
                if (word.equals(subword)) {
                    cpt++;
                }
                else if (cpt > 0)
                    break;
            }
            maxarray.add(cpt);
        }

        if (single.size() <= 3){
            String array[] = new String[single.size()];
            for (int i = 0; i < single.size(); i++)
                array[i] = single.get(i);
            return array;
        }

        int i1 = first(maxarray);
        int i2 = second(maxarray, i1);
        int i3 = third(maxarray, i1, i2);

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
                java.nio.file.Path paths = java.nio.file.Paths.get(path_str);
                try {
                    String path = java.nio.file.Files.readString(paths);
                    String[] res = freq(path);
                    if (res.length > 0)
                    {
                        System.out.print(res[0]);
                        for (int i = 1; i < res.length; i++)
                            System.out.print(" " + res[i]);
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
