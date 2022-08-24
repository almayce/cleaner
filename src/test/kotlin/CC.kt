
import Random.randomLong
import com.codeborne.selenide.Selenide.sleep
import java.awt.Robot
import java.awt.event.KeyEvent

object CC {

    val r = Robot()

    fun enter() {
        r.keyPress(KeyEvent.VK_ENTER)
        r.keyRelease(KeyEvent.VK_ENTER)
    }

    fun shift() {
        r.keyPress(KeyEvent.VK_SHIFT)
        r.keyRelease(KeyEvent.VK_SHIFT)
    }

    fun ctrl() {
        r.keyPress(KeyEvent.VK_CONTROL)
        r.keyRelease(KeyEvent.VK_CONTROL)
    }

    fun change() {
        r.keyPress(KeyEvent.VK_ALT)
        val count = randomLong(1, 9)
        for (o in count downTo 0) {
            sleep(100)
            r.keyPress(KeyEvent.VK_TAB)
            r.keyRelease(KeyEvent.VK_TAB)
        }
        r.keyRelease(KeyEvent.VK_ALT)
    }
}