import java.util.Scanner;

public class Launcher {
    public static int Fibo(int n) {
        if (n <= 1) return n;
        else return Fibo(n-1) + Fibo(n-2);
    }
    public static void main(String[] args) {
        System.out.println("Welcome");
        java.util.Scanner var = new java.util.Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        do {
            if (!str.equals("fibo")) {
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
        }
        while (!str.equals("quit"));
    }
}
