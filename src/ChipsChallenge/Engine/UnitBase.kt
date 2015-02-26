package ChipsChallenge.Engine

import java.awt.image.BufferedImage

/**
 * Created by chase on 2/25/15.
 */
abstract class UnitBase(var x: Int, var y: Int, imageName: String) : EngineObjectBase {

    val image: BufferedImage? = loadImage(imageName, this)

}