public class Quit implements Command {
    public String name(){
        return "quit";
    }

    public boolean run(String entry) {
        return true;
    }
}
