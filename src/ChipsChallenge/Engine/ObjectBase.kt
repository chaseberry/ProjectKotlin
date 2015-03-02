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
        6 -> YellowKey(location)
        7 -> YellowLock(location)
        8 -> GreenKey(location)
        9 -> GreenLock(location)
        10 -> Dirt(location)
        11 -> Block(location)
        else -> null
    }
}

data abstract class ObjectBase(val id: Int, var location: Point, val image: BufferedImage) : EngineObjectBase {

    abstract fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution

}

public enum class ObjectResolution {
    MOVE
    REMOVE
    NOTHING
    PASSOVER
}