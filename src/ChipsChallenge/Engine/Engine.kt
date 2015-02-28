package ChipsChallenge.Engine

import ChipsChallenge.UI.Frame
import java.awt.image.BufferedImage
import ChipsChallenge.Map.mapFromIds
import java.util.Timer
import ChipsChallenge.Unit.Player
import java.util.TimerTask
import ChipsChallenge.Map.Point
import ChipsChallenge.UI.getViewport
import ChipsChallenge.Map.Tiles.Water
import ChipsChallenge.Map.Tiles.Finish

/**
 * Created by chase on 2/25/15.
 */


public enum class Direction {
    UP; DOWN; LEFT; RIGHT
}

class Engine {

    val gameTime: Long = 30//

    val frame = Frame(this)

    val map = mapFromIds(Array(20, { x -> Array(20, { y -> if (x == 0 || x == 19 || y == 0 || y == 19) 1 else 0 }) }),
            Point(1, 1))
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
                checkCollisions()
            }
        }, gameTime, gameTime)
    }

    fun checkCollisions() {
        val tile = map.getTile(player.location)
        when (tile) {
            Water() -> if (!player.inventory.hasFlippers) lose()
            Finish() -> win()
        }
    }

    fun lose() {
        gameOver()
    }

    fun win() {

    }

    fun gameOver() {
        gameTimer.cancel()
    }

    fun buildFrameImage(): BufferedImage {
        val image = BufferedImage(9 * 32, 9 * 32, BufferedImage.TYPE_INT_ARGB)
        val viewport = getViewport(player.location, map)
        val mapImage = map.getImage(viewport)
        val g = image.getGraphics()

        //Draw the map
        g.drawImage(mapImage, 0, 0, null)

        //Draw objects
        val objs = objectManager.objectsInViewport(viewport)
        for (obj in objs) {
            g.drawImage(obj.image, (obj.location.x - viewport.xStart) * 32, (obj.location.y - viewport.yStart) * 32, null)
        }

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