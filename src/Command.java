public interface Command {
    String name();
    boolean run(String entry);
}
