package ChipsChallenge.Engine

import java.awt.image.BufferedImage

/**
 * Created by chase on 2/25/15.
 */
abstract class ObjectBase(imageSource: String) {

    public open val image: BufferedImage = loadImage(imageSource, this)

    public abstract fun onTick()


}