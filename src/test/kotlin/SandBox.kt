import com.codeborne.selenide.Selenide.sleep
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

object SandBox {

//    @JvmStatic
//    fun main(args: Array<String>) {
//        while (true){
//            sleep(1600)
//              CC.shift()
//        }
//    }

    //    @JvmStatic
//    fun main(args: Array<String>) {
//        for (i in 0 until 100) {
//            println(UUID.randomUUID().toString())
//        }
//    }
//

    private const val minSleepMillis = 2_000L
    private const val maxSleepMillis = 4_000L
    private const val twoMinutesMillis = 200_000L

    private var changeLimit = 100_000L
    private var sleepMillis = 2_000L
    private var timeStampMillis = System.currentTimeMillis()

    private fun calculateSleepMillis(timeMillis: Long): Long {
        return if (System.currentTimeMillis() - timeMillis > twoMinutesMillis) {
            timeStampMillis = System.currentTimeMillis()
            Random.randomLong(minSleepMillis, maxSleepMillis)
        } else sleepMillis
    }

    fun refreshLimit() {
        changeLimit = 100_000L
    }

    @JvmStatic
    fun main(args: Array<String>) {
        while (true) {
            changeLimit -= sleepMillis
            if (changeLimit < 0) {
                CC.change()
                refreshLimit()
            }
            sleep(sleepMillis)
            CC.shift()
            println(sleepMillis)
            sleepMillis = calculateSleepMillis(timeStampMillis)
        }
    }
}