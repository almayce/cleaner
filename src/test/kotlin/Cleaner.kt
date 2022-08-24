import Actions.button
import Actions.click
import com.codeborne.selenide.*
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.*
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.openqa.selenium.By

class Cleaner {

    @Before
    fun beforeClean() {
        open("")
        SignIn()
    }

    @Test
    fun clean() {
        with(Elements) {
            while (tasks.text().contains("(")) {
                click(tasks)
                click(sortOrder)
                click("Oldest")
                click(taskLink(1))
                CC.shift()
                while (taskLink(1).has(not(visible))) {
                    if (submitted.has(visible)) click(tasks)
                    when {
                        button("Yes") -> click()
                        button("Cancel") -> click()
                        button("Close") -> {
                            SetReasons()
                            click("Close")
                            click("Yes")
                        }
                        button("Accept") -> click()
                        button("Return To Operation Maker") -> {
                            SetReasons()
                            click()
                        }
                        button("Return to Branch Maker") -> {
                            SetReasons()
                            click()
                        }
                        button("Skip") -> click()
                        button("Go Back") -> click()
                        button("Reject") -> {
                            SetReasons()
                            print("TEST")
                            click("Reject")
                            click("Yes")
                        }
                        button("Approve") -> click()
//                        button("Reject") -> {
//                            click("Reject")
//                            sleep(500L)
//                            if (button("Reject")) click("Reject")
//                        }
                        button("Reject Container") -> {
                            SetReasons()
                            click("Reject Container")
                            click("Yes")
                        }
                        button("Cancel All Lines") -> {
                            click("Cancel All Lines")
                            click("Yes")
                        }
                        button("Submit") -> click()
                        button("Complete") -> click()
                        button("Done") -> click()
                        button("Confirm") -> click()
                        !tasks.text().contains("(") -> return
                    }
                }
            }
        }
    }

    @Test
    fun cleanRecords() {
        while (true) {
            `$`(Selectors.byText("Records")).shouldBe(visible).click()
            `$`(Selectors.byText("Funds Transfers")).shouldBe(visible).click()
            `$`(Selectors.byText("Search")).shouldBe(visible)
            `$`(By.xpath("//input")).value = "Pending AML Review"
            click("Search")
            click("Transfer Id")
            `$`(By.xpath("//tbody/tr[11]/td/div/p/a")).click()
            val cancel = `$`(Selectors.byText("Cancel")).shouldBe(visible)
            cancel.click()
            cancel.click()
            `$`(Selectors.byText("Canceled")).shouldBe(visible)
        }
    }

    companion object {
        lateinit var currentButton: SelenideElement

        private const val timeout = 120000L
        private const val baseUrl = "https://bpm-qa-01.baexperiment.com"
        private const val browser = "chrome"

        @BeforeClass
        @JvmStatic
        fun setup() {
            Configuration.driverManagerEnabled = true
            Configuration.headless = false
            Configuration.startMaximized = true
            Configuration.holdBrowserOpen = false
            Configuration.savePageSource = false
            Configuration.screenshots = false
            Configuration.timeout = timeout
            Configuration.baseUrl = baseUrl
            Configuration.browser = browser
        }

        @AfterClass
        @JvmStatic
        fun teardown() {
            close()
        }
    }
}