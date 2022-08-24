import AppianWorking.waitWorking
import Cleaner.Companion.currentButton
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.sleep
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

object Actions {

    fun scrollIntoView(element: SelenideElement) {
        if (element.has(exist))
            element.scrollIntoView("{behavior: \"smooth\", block: \"center\", inline: \"center\"}")
    }

    fun button(text: String): Boolean {
        val target = Selenide.`$`(By.xpath("//button[contains(text(), '$text')]"))
        val targetUppercase = Selenide.`$`(By.xpath("//button[contains(text(), '${text.toUpperCase()}')]"))
        sleep(5000)
        CC.shift()
        sleep(5000)
        when {
            target.has(visible) -> {
                currentButton = target
                return currentButton.has(not(disabled))
            }
            targetUppercase.has(visible) -> {
                currentButton = targetUppercase
                return currentButton.has(not(disabled))
            }
        }
        return false
    }

    fun button(element: SelenideElement) = element.has(visible)

    fun click(text: String) {
        sleep(5000)
        CC.shift()
        sleep(5000)
        val element = Selenide.`$`(By.xpath("(.//*[normalize-space(text())='$text'])[last()]"))
        if (element.has(exist)) {
            scrollIntoView(element)
            element.click()
            CC.shift()
        }
        waitWorking()
    }

    fun click() {
        sleep(5000)
        CC.shift()
        sleep(5000)
        if (currentButton.has(exist)) {
            scrollIntoView(currentButton)
            if (currentButton.has(visible)) currentButton.click()
            CC.shift()
        }
        waitWorking()
    }

    fun click(element: SelenideElement) {
        sleep(5000)
        CC.shift()
        sleep(5000)
        if (element.has(exist)) {
            scrollIntoView(element)
            element.click()
            CC.shift()
        }
        waitWorking()
    }
}