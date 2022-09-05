import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import printer.Printer;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        Printer printerWithPrefixErr = context.getBean("printerWithPrefix", Printer.class);
        Printer printerWithDateTimeStd = context.getBean("printerWithDateTime", Printer.class);

        printerWithPrefixErr.print ("Hello!") ;
        printerWithDateTimeStd.print("world!");
    }
}
