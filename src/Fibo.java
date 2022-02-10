public class Fibo implements Command{

    public String name() {
        return "fibo";
    }

    int fibo(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        else
            return (fibo(n - 1) + fibo((n - 2)));
    }

    public boolean run(String entry) {
        java.util.Scanner scan = new java.util.Scanner(System.in);

        System.out.println("Veuillez entrer un nombre :");
        int number = scan.nextInt();
        scan.nextLine();
        int result = fibo(number);
        System.out.println("Fibo " + number + " = " + result);
        return false;
    }
}