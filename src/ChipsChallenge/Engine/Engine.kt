package ChipsChallenge.Engine

import ChipsChallenge.UI.Frame
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import ChipsChallenge.Map.mapFromIds

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

    val frame = Frame(this)

    val map = mapFromIds(Array(4, { x -> Array(4, { y -> if (x == 0 || x == 3 || y == 0 || y == 3) 1 else 0 }) }))
    //Current test map is just a test

    public fun start() {
        frame.setVisible(true)
        frame.image = map.image
    }


    public fun keyPressed(code: Int) {

    }

    public fun keyReleased(code: Int) {

    }

}