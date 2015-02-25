package ChipsChallenge.Engine

import ChipsChallenge.UI.Frame
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

/**
 * Created by chase on 2/25/15.
 */


fun loadImage(imageSource: String, parent: Any): BufferedImage {
    return ImageIO.read((parent as java.lang.Object).getClass().getResource("../Images/$imageSource"));
}

class Engine {

    val frame = Frame()

    public fun start() {
        frame.setVisible(true)
    }


}