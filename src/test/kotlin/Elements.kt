import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By

object Elements {

    val submitted = Selenide.`$`(Selectors.byText("The task has already been submitted."))
    val nullOrInvalid = Selenide.`$`(Selectors.byText("The task status null is invalid."))
    val tasks = `$`(By.xpath("//a[@href='/suite/tempo/tasks/']"))
    val sortOrder = Selenide.`$`(By.xpath("//img[@alt='Sort Order']"))
    private val starElement = ".//img[@alt='Unstarred']"
    fun taskLink(index: Int) = Selenide.`$`(By.xpath("($starElement)[$index]/preceding::a[2]"))
}