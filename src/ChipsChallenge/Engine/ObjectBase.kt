package ChipsChallenge.Engine

import java.awt.image.BufferedImage

/**
 * Created by chase on 2/25/15.
 */
abstract class ObjectBase(imageSource: String?) : EngineObjectBase {

    public open val image: BufferedImage? = loadImage(imageSource, this)

}