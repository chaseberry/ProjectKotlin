package ChipsChallenge.Engine

import java.awt.image.BufferedImage
import ChipsChallenge.Map.Point
import ChipsChallenge.Object.*

fun objectFromId(id: Int, location: Point): ObjectBase? {
    return when (id) {
        0 -> Chip(location)
        1 -> Socket(location)
        2 -> BlueKey(location)
        3 -> BlueLock(location)
        4 -> RedKey(location)
        5 -> RedLock(location)
        6 -> YellowLock(location)
        7 -> YellowKey(location)
        8 -> GreenLock(location)
        9 -> GreenKey(location)
        else -> null
    }
}

abstract class ObjectBase(var location: Point, imageSource: String?) : EngineObjectBase {

    public open val image: BufferedImage? = loadImage(imageSource)

    abstract fun interact(engine: Engine, direction: Direction): ObjectResolution

}

public enum class ObjectResolution {
    MOVE
    REMOVE
    NOTHING
}