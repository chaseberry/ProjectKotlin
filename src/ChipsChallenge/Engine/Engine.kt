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

    val frame = Frame()

    val map = mapFromIds(Array(4, { Array(4, { 0 }) }))

    public fun start() {
        frame.setVisible(true)
        frame.image = map.image
    }


}