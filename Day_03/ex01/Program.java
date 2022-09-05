public class Program {

    private static int count;

    public static void main(String[] args) {
        checkArgs(args);
        try {
            Print print = new Print();
            threadEgg threadEgg = new threadEgg(print, count);
            threadHen threadHen = new threadHen(print, count);
            threadEgg.start();
            threadHen.start();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    static class threadEgg extends Thread {

        Print print;
        int count;

        public threadEgg(Print print, int count) {
            this.print = print;
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < count; ++i)
                print.Egg();
        }
    }

    static class threadHen extends Thread {

        Print print;
        int count;

        public threadHen(Print print, int count) {
            this.print = print;
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < count; ++i)
                print.Hen();
        }
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
}
