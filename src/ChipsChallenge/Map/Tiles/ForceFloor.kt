package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.*
import java.awt.image.BufferedImage

fun forceFloorImage(typeId: Int): BufferedImage {
    return when (typeId) {
        FORCE_FLOOR_LEFT -> forceFloorWest
        FORCE_FLOOR_UP -> forceFloorNorth
        FORCE_FLOOR_RIGHT -> forceFloorEast
        FORCE_FLOOR_DOWN -> forceFloorSouth
        else -> forceFloorRandom
    }
}

fun getNextForceFloor(typeId: Int): Int {
    return when (typeId) {
        FORCE_FLOOR_LEFT -> FORCE_FLOOR_UP
        FORCE_FLOOR_UP -> FORCE_FLOOR_RIGHT
        FORCE_FLOOR_RIGHT -> FORCE_FLOOR_DOWN
        FORCE_FLOOR_DOWN -> FORCE_FLOOR_RANDOM
        else -> FORCE_FLOOR_LEFT
    }
}

public class ForceFloor(typeId: Int, location: Point, uniqueId: Id) : IceBase(forceFloorImage(typeId), typeId, location, uniqueId) {

    constructor(typeId: Int, location: Point) : this(typeId, location, Id(IdType.TILE)) {
    }

    override fun getNewDirection(direction: Direction): Direction {
        return when (tileId) {
            FORCE_FLOOR_LEFT -> Direction.LEFT
            FORCE_FLOOR_UP -> Direction.UP
            FORCE_FLOOR_RIGHT -> Direction.RIGHT
            FORCE_FLOOR_DOWN -> Direction.DOWN
            FORCE_FLOOR_RANDOM -> getRandomDirection()
            else -> direction
        }
    }

    fun getRandomDirection(): Direction {
        return when ((Math.random() * 4).toInt()) {
            0 -> Direction.UP
            1 -> Direction.DOWN
            2 -> Direction.LEFT
            else -> Direction.RIGHT
        }
    }

    fun getAllowedOverrideDirections(): Array<Direction> {
        return when (tileId) {
            FORCE_FLOOR_LEFT -> array(Direction.DOWN, Direction.UP)
            FORCE_FLOOR_UP -> array(Direction.LEFT, Direction.RIGHT)
            FORCE_FLOOR_RIGHT -> array(Direction.DOWN, Direction.UP)
            FORCE_FLOOR_DOWN -> array(Direction.LEFT, Direction.RIGHT)
            else -> array()
        }
    }

    override fun onTick(engine: Engine) {
    }

}