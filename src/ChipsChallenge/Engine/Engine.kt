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
import ChipsChallenge.Map.Point
import ChipsChallenge.UI.getViewport

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

public enum class Direction {
    UP; DOWN; LEFT; RIGHT
}

fun loadImage(imageSource: String?): BufferedImage? {
    if (imageSource == null) {
        return null
    }
    return ImageIO.read(URL("$fileUrl$imageSource"));
}

class Engine {

    val gameTime: Long = 30//

    val frame = Frame(this)

    val map = mapFromIds(Array(20, { x -> Array(20, { y -> if (x % 2 == 0 && 0 == y % 2 ) 1 else 0 }) }),
            Point(1, 1), this)
    //Current test map is just a test

    val keyBindings = KeyBindings()

    val gameTimer = Timer()

    val player = Player(map.defaultPlayerLocation)

    //Done so this can be passed around in the onTick method
    val engine = this

    val movement = Movement(this)

    val objectManager = ObjectManager(this)

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
        val viewport = getViewport(player.location, map)
        val mapImage = map.getImage(viewport)
        val g = image.getGraphics()

        //Draw the map
        g.drawImage(mapImage, 0, 0, null)

        //Draw objects

        //Draw the player
        val playerX = if (player.location.x <= 4 ) player.location.x else
            if (player.location.x in (map.x - 4)..(map.x)) 9 - (map.x - player.location.x) else 4
        val playerY = if (player.location.y <= 4 ) player.location.y else
            if (player.location.y in (map.y - 4)..(map.y)) 9 - (map.y - player.location.y) else 4
        g.drawImage(player.image, playerX * 32, playerY * 32, null)
        return image
    }

    public fun keyPressed(code: Int) {
        keyBindings.keyPressed(code)
    }

    public fun keyReleased(code: Int) {
        keyBindings.keyReleased(code)
    }

}