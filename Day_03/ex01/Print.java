public class Print {

    public static boolean bool = false;

    public synchronized void Egg() {
        while (!bool) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bool = false;
        System.out.println("Egg");
        notify();
    }

    public synchronized void Hen() {
        while (bool) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bool = true;
        System.out.println("Hen");
        notify();
    }
}