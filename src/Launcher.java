import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome");
        java.util.Scanner var = new java.util.Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (!str.equals("quit")) {
            System.out.println("Unknown Command");
        }
    }
}
