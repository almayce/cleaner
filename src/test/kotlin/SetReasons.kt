import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By
import org.openqa.selenium.Keys

class SetReasons {

    companion object {
        val rejectionreason = Selenide.`$`(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Rejection reason'])[1]/following::div[3]"))
        val rejectionReason = Selenide.`$`(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Rejection Reason'])[1]/following::div[3]"))
        val rejectReason = Selenide.`$`(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Reject Reason'])[1]/following::div[3]"))
        val closeReason = Selenide.`$`(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Close Reason'])[1]/following::div[3]"))
    }

    init {
        if (rejectionReason.has(visible)) select(rejectionReason)
        if (rejectionreason.has(visible)) select(rejectionreason)
        if (rejectReason.has(visible)) select(rejectReason)
        if (closeReason.has(visible)) select(closeReason)
    }

    private fun select(dropdown: SelenideElement) {
        dropdown.sendKeys(Keys.ARROW_DOWN)
        dropdown.sendKeys(Keys.ARROW_DOWN)
        dropdown.sendKeys(Keys.ENTER)
        Selenide.sleep(1000)
    }
}