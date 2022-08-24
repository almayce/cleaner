import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class ClickClack {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            r.keyPress(KeyEvent.VK_SHIFT);
            System.out.println("Click");
            r.keyRelease(KeyEvent.VK_SHIFT);
            System.out.println("Clack");
            TimeUnit.MILLISECONDS.sleep(2000);
        }
    }

    private static Robot r;
    static {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}


