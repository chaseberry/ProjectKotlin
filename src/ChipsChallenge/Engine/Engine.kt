package ChipsChallenge.Engine

import ChipsChallenge.UI.Frame
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import ChipsChallenge.Map.mapFromIds
import java.util.Timer
import ChipsChallenge.Unit.Player
import kotlin.properties.Delegates
import java.net.URL
import java.util.TimerTask

/**
 * Created by chase on 2/25/15.
 */
val fileUrl: String by Delegates.lazy {
    var fileName = ""
    for (filePart in (BaseObject() as java.lang.Object).getClass().getResource("").toString().split("/")) {
        if (filePart == "ChipsChallenge") {
            fileName += "$filePart/Images/"
            break;
        }
        fileName += "$filePart/"
    }
    fileName
}

fun loadImage(imageSource: String?): BufferedImage? {
    if (imageSource == null) {
        return null
    }
    return ImageIO.read(URL("$fileUrl$imageSource"));
}

class Engine {

    val gameTime: Long = 20//==50 FPS

    val frame = Frame(this)

    val map = mapFromIds(Array(9, { x -> Array(9, { y -> if (x == 0 || x == 8 || y == 0 || y == 8) 1 else 0 }) }))
    //Current test map is just a test

    val keyBindings = KeyBindings()

    val gameTimer = Timer()

    val player = Player(1, 1)

    val engine = this

    public fun start() {
        frame.setVisible(true)
        frame.image = buildFrameImage()
        gameTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                map.onTick(engine)
                player.onTick(engine)
                frame.image = buildFrameImage()
            }
        }, gameTime, gameTime)
    }

    fun buildFrameImage(): BufferedImage {
        val image = BufferedImage(9 * 32, 9 * 32, BufferedImage.TYPE_INT_ARGB)
        val mapImage = map.image
        val g = image.getGraphics()
        val x = if (player.x < 5) 5 else (if (player.x > (map.x - 4)) (map.x - 4) else player.x)
        val y = if (player.y < 5) 5 else (if (player.y > (map.y - 4)) (map.y - 4) else player.y)
        g.drawImage(mapImage, (x - 5) * 32, (y - 5) * 32, null)
        g.drawImage(player.image, (player.x + x - 5) * 32, (player.y + y - 5) * 32, null)
        return image
    }

    public fun keyPressed(code: Int) {
        keyBindings.keyPressed(code)
    }

    public fun keyReleased(code: Int) {
        keyBindings.keyReleased(code)
    }

}