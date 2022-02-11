import java.util.*;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome");
        Scanner sc = new Scanner(System.in);
        List<Command> command = new ArrayList<Command>();
        command.add(new Fibo());
        command.add(new Freq());
        command.add(new Quit());
        command.add(new Predict());

        Boolean boole1 = false;
        Boolean boole2 = true;
        while (boole2)
        {
            System.out.println("Veuillez entrer une commande :");
            boole1 = false;
            String str = sc.nextLine();
            for (Command com: command ){
                if (str.equals(com.name())){
                    boole2 = com.run(sc);
                    boole1 = true;
                }
            }
            if (!boole1) {
                System.out.println("Unknown command");
            }
        }


    }
}
