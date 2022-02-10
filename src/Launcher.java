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

        java.util.ArrayList<String> kl = new java.util.ArrayList<String>();


        for (int i = 0; i < word.length; ++i)
        {
            if (!word[i].isBlank())
            {
                kl.add(word[i]);
            }
        }

        String result = "";
        int max = 0;
        int count = 0;
        int thirda = 0;
        String maxx = "";
        String third = "";

        for (int i = 0; i < kl.size(); ++i)
        {
            if (record.containsKey(kl.get(i)))
            {
                record.put(kl.get(i), record.get(kl.get(i)) + 1);
            }
            else
            {
                record.put(kl.get(i), 1);
            }
            if (record.get(kl.get(i)) > max)
            {
                max = record.get(kl.get(i));
            }
        }

        for (int i = 0; i < kl.size(); ++i)
        {
            if (record.get(kl.get(i)) == max)
            {
                maxx = kl.get(i);
            }
            else if (record.get(kl.get(i)) < max)
            {
                if ((count) < record.get(kl.get(i)))
                {
                    result = kl.get(i);
                    count = record.get(kl.get(i));
                }
            }
        }

        for (int i = 0; i < kl.size(); ++i)
        {
            if (record.get(kl.get(i)) < count)
            {
                if (thirda < record.get(kl.get(i)))
                {
                    third = kl.get(i);
                    thirda = record.get(kl.get(i));
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
                Freq(path);
                str = sc.nextLine();
            }
        }


    }
}
