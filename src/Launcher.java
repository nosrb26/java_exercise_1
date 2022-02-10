public class Launcher {
    public static int Fibo(int n) {
        if (n <= 1) return n;
        else return Fibo(n-1) + Fibo(n-2);
    }

    public static int firsto(java.util.ArrayList<Integer> number){
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

    public static int secondo(java.util.ArrayList<Integer> number, int num){
        int res = 0;
        if (num == 0) {
            res = 1;
        }
        int max = number.get(res);
        for (int i = res + 1; i < number.size(); i++){
            if (i == num)
                continue;
            if (number.get(i) > max){
                max = number.get(i);
                res = i;
            }
        }
        return res;
    }

    public static int thirdo(java.util.ArrayList<Integer> number, int num1, int num2){
        int res = 0;
        while (res == num1 || res == num2) {
            res++;
        }
        int max = number.get(res);
        for (int i = res + 1; i < number.size(); i++){
            if (i == num1 || i == num2) {
                continue;
            }
            if (number.get(i) > max){
                max = number.get(i);
                res = i;
            }
        }
        return res;
    }


    public static String[] Freq(String str) {
        java.nio.file.Path path = java.nio.file.Paths.get(str);
        String contenu = "";
        try {
            contenu = java.nio.file.Files.readString(path);
        }
        catch (Exception e) {
            System.out.println("Unreadable file: " + e);
        }

        contenu = contenu.replaceAll("[^a-zA-Z]", " ");
        contenu = contenu.toLowerCase();
        String[] words = contenu.split(" ");

        java.util.ArrayList<String> kl = new java.util.ArrayList<String>();


        for (int i = 0; i < words.length; ++i)
        {
            if (!words[i].isBlank())
            {
                kl.add(words[i]);
            }
        }

        java.util.Collections.sort(kl);

        java.util.ArrayList<String> strtemp = new java.util.ArrayList<String>();
        for (String word : kl){
            boolean boool = false;
            for (String singleword : strtemp){
                if (word.equals(singleword)){
                    boool = true;
                    break;
                }
            }
            if (!boool){
                strtemp.add(word);
            }
        }

        java.util.ArrayList<Integer> res = new java.util.ArrayList<Integer>();

        for (String word : strtemp){
            int cpt = 0;
            for (String subword : kl) {
                if (word.equals(subword)) {
                    cpt++;
                }
                else if (cpt > 0)
                    break;
            }
            res.add(cpt);
        }

        if (strtemp.size() <= 3){
            String result[] = new String[strtemp.size()];
            for (int i = 0; i < strtemp.size(); i++) {
                result[i] = strtemp.get(i);
            }
            return result;
        }

        int first = firsto(res);
        int second = secondo(res, first);
        int third = thirdo(res, first, second);

        String[] array = {strtemp.get(first), strtemp.get(second), strtemp.get(third)};
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
                System.out.println("Veuillez indiquer le chemin du fichier :");
                String path = sc.nextLine();
                sc.nextLine();
                String[] res = Freq(path);
                if (res.length > 0)
                {
                    System.out.print(res[0]);
                    for (int i = 1; i < res.length; i++) {
                        System.out.print(" " + res[i]);
                    }
                    System.out.println();
                }

                str = sc.nextLine();
            }
        }


    }
}
