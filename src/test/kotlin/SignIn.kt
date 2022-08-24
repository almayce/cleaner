import com.codeborne.selenide.Selenide
import org.openqa.selenium.By

class SignIn {

    companion object {
        private const val login = "alex.kolchanov"
//        private const val login = "test-checker"
//        private const val login = "test-teller"
//        private const val login = "test-operation-local"
        private const val pass = "1234"

        val loginInput = Selenide.`$`(By.xpath("//*[@id=\"un\"]"))
        val passInput = Selenide.`$`(By.xpath("//*[@id=\"pw\"]"))
        val signIn = Selenide.`$`(By.xpath("//input[@type='submit']"))
    }

    init {
        loginInput.value = login
        passInput.value = pass
        signIn.click()
    }
}