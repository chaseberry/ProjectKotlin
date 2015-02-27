package ChipsChallenge.Engine

import java.awt.image.BufferedImage
import ChipsChallenge.Map.Point

/**
 * Created by chase on 2/25/15.
 */
abstract class ObjectBase(var location: Point, imageSource: String?) : EngineObjectBase {

    public open val image: BufferedImage? = loadImage(imageSource)

    abstract fun interact(engine: Engine, direction: Direction): ObjectResolution

}

public enum class ObjectResolution {
    MOVE
    REMOVE
    NOTHING
}