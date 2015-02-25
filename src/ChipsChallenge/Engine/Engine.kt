package ChipsChallenge.Engine

import ChipsChallenge.UI.Frame
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import ChipsChallenge.Map.mapFromIds
import java.util.Timer

/**
 * Created by chase on 2/25/15.
 */

fun loadImage(imageSource: String?, parent: Any): BufferedImage? {
    if (imageSource == null) {
        return null
    }
    return ImageIO.read((parent as java.lang.Object).getClass().getResource("../../Images/$imageSource"));
}

class Engine {

    val gameTime: Long = 20//==50 FPS

    val frame = Frame(this)

    val map = mapFromIds(Array(4, { x -> Array(4, { y -> if (x == 0 || x == 3 || y == 0 || y == 3) 1 else 0 }) }))
    //Current test map is just a test

    val keyBindings = KeyBindings()

    val gameTimer = Timer()

    public fun start() {
        frame.setVisible(true)
        frame.image = map.image
        /*gameTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                map.onTick()
                frame.image = map.image
            }
        }, gameTime, gameTime)*/
    }


    public fun keyPressed(code: Int) {
        keyBindings.keyPressed(code)
    }

    public fun keyReleased(code: Int) {
        keyBindings.keyReleased(code)
    }

}