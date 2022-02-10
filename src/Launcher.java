public class Launcher {
    public static void main(String[] args) {

        System.out.println("Welcome");
        java.util.Scanner scan = new java.util.Scanner(System.in);

        java.util.List<Command> cmd = new java.util.ArrayList<>();
        cmd.add(new Quit());
        cmd.add(new Fibo());
        cmd.add(new Freq());
        cmd.add(new Predict());

        boolean refact = true;
        while (refact){
            String entry = scan.nextLine();

            boolean boool = false;
            for (Command command : cmd){
                if (command.name().equals(entry)) {
                    boool = true;
                    if (command.run(entry)) {
                        refact = false;
                    }
                    break;
                }
            }
            if (!boool) {
                System.out.println("Unknown command");
            }
        }


    }
}
