public class Program implements Runnable {

    private int counter;
    private String msg;
    private static int count;

    public Program(int counter, String msg) {
        this.counter = counter;
        this.msg = msg;
    }

    public static void main(String[] args) {
        checkArgs(args);
        try {
            Thread threadEgg = new Thread(new Program(count, "Egg"));
            Thread threadHen = new Thread(new Program(count, "Hen"));
            threadEgg.start();
            threadHen.start();
            threadEgg.join();
            threadHen.join();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        new Program(count, "Human").run();
    }

    private static void checkArgs(String[] args) {
        String prefix = "--count=";
        if (args.length > 0 && args[0].startsWith(prefix)) {
            try {
                count = Integer.parseInt(args[0].substring(prefix.length()));
            }
            catch (NumberFormatException e) {
                System.err.println("Wrong number format - " + args[0].substring(prefix.length()));
                System.out.println("Usage: java Program --count=NUMBER");
            }
        }
        else {
            System.err.println("Argument error");
            System.out.println("Usage: java Program --count=NUMBER");
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < counter; ++i)
            System.out.println(msg);
    }
}
