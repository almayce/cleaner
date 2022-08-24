import com.codeborne.selenide.Selenide
import org.openqa.selenium.By

object AppianWorking {

    private val working get() = Selenide.`$`(By.className("appian-indicator-message"))

    fun waitWorking() {

        var trackIt = false

        for (o in 5 downTo 0) {
            Selenide.sleep(50L)
            if (working.isDisplayed()) {
                trackIt = true
                break
            }
        }

        if (trackIt) {
            while (working.isDisplayed())
                Selenide.sleep(200L)
        }
    }
}

interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

class Derived(b: Base) : Base by b

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    Derived(b).print() // prints 10
}