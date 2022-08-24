import java.awt.*
import java.awt.image.BufferedImage
import javax.swing.*

class BarnsleyFern(val dim: Int) : JPanel() {

    private val img: BufferedImage

    init {
        preferredSize = Dimension(dim, dim)
        background = Color.DARK_GRAY
        img = BufferedImage(dim, dim, BufferedImage.TYPE_INT_ARGB)
        createFern(dim, dim)
    }

    private fun createFern(w: Int, h: Int) {
        var x = 0.0
        var y = 0.0
        var c = 0xFF00760c.toInt()
        for (i in 0 until 100_000) {
            var tmpx: Double
            var tmpy: Double
            val r = Math.random()
            when {
                r <= 0.02 -> { // ствол
                    tmpx = 0.0 // смещение линии ствола
                    tmpy = 0.16 * y // смещение по у
                    c *= 4
                }
                r <= 0.86 -> {
                    tmpx = 0.85 * x + 0.04 * y // сила наклона вправо
                    tmpy = -0.04 * x + 0.86 * y + 1.6 // высота
                    c /= 2
                }
                r <= 0.93 -> {
                    tmpx = 0.2 * x - 0.26 * y // 26->16 поворот
                    tmpy = 0.23 * x + 0.22 * y + 1.6
                }
                else -> {
                    tmpx = -0.15 * x + 0.28 * y
                    tmpy = 0.26 * x + 0.24 * y + 0.44
                }
            }
            x = tmpx
            y = tmpy
            img.setRGB(Math.round(w / 2.0 + x * w / 11.0).toInt(),
                    Math.round(h - y * h / 11.0).toInt(), c)
            c = 0xFF00760c.toInt()
        }
    }

    override fun paintComponent(graphics: Graphics) {
        super.paintComponent(graphics)
        val g = graphics as Graphics2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g.drawImage(img, 0, 0, null)
    }
}

fun main(args: Array<String>) {
    SwingUtilities.invokeLater {
        val f = JFrame()
        f.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        f.title = "Barnsley Fern"
        f.setResizable(false)
        f.add(BarnsleyFern(640), BorderLayout.CENTER)
        f.pack()
        f.setLocationRelativeTo(null)
        f.setVisible(true)
    }
}
