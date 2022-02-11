import java.util.Scanner;

public class Fibo implements Command{

    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public boolean run(Scanner console) {
        System.out.println("Veuillez entrer un nombre :");
        int nb = console.nextInt();
        int res = fibo(nb);
        System.out.println(res);
        String str = console.nextLine();
        return true;
    }

    public static int fibo(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        else
            return (fibo(n - 1) + fibo((n - 2)));
    }


}