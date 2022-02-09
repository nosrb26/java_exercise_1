import java.util.Scanner;
import java.util.HashMap;


public class Launcher {
    public static int Fibo(int n) {
        if (n <= 1) return n;
        else return Fibo(n-1) + Fibo(n-2);
    }

    public static void Freq(String str) {
        java.nio.file.Path path = java.nio.file.Paths.get(str);
        String contenu = "";
        try {
            contenu = java.nio.file.Files.readString(path);

        }
        catch (Exception e) {
            System.out.println("Unreadable file: " + e);
        }

        HashMap < String, Integer > record = new HashMap < String, Integer > ();

        contenu = contenu.replaceAll("[^a-zA-Z ]", " ");
        String[] word = contenu.split(" ");

        String result = "";
        int max = 0;
        int count = 0;
        int thirda = 0;
        String maxx = "";
        String third = "";

        for (int i = 0; i < word.length; ++i)
        {
            if (record.containsKey(word[i]))
            {
                record.put(word[i], record.get(word[i]) + 1);
            }
            else
            {
                record.put(word[i], 1);
            }
            if (record.get(word[i]) > max)
            {
                max = record.get(word[i]);
            }
        }

        for (int i = 0; i < word.length; ++i)
        {
            if (record.get(word[i]) == max)
            {
                maxx = word[i];
            }
            else if (record.get(word[i]) < max)
            {
                if ((count) < record.get(word[i]))
                {
                    result = word[i];
                    count = record.get(word[i]);
                }
            }
        }

        for (int i = 0; i < word.length; ++i)
        {
            if (record.get(word[i]) < count)
            {
                if (thirda < record.get(word[i]))
                {
                    third = word[i];
                    thirda = record.get(word[i]);
                }
            }
        }

        if (count == 0)
        {
            return;
        }
        else
        {
            result += " " + maxx;
            third += " " + result;
            System.out.println(third);
        }

    }

    public static void main(String[] args) {
        System.out.println("Welcome");
        java.util.Scanner var = new java.util.Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        do {
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
                Freq(path);
                str = sc.nextLine();
            }
        }
        while (!str.equals("quit"));
    }
}
