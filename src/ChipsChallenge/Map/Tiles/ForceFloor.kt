package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.FORCE_FLOOR_DOWN
import ChipsChallenge.Map.FORCE_FLOOR_LEFT
import ChipsChallenge.Map.FORCE_FLOOR_RIGHT
import ChipsChallenge.Map.FORCE_FLOOR_UP
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

public class ForceFloor(typeId: Int, location: Point, uniqueId: Id) : IceBase(forceFloorImage(typeId), typeId, location, uniqueId) {

    constructor(typeId: Int, location: Point) : this(typeId, location, Id(IdType.TILE)) {
    }

    override fun getNewDirection(direction: Direction): Direction {
        return when (tileId) {
            FORCE_FLOOR_LEFT -> Direction.LEFT
            FORCE_FLOOR_UP -> Direction.UP
            FORCE_FLOOR_RIGHT -> Direction.RIGHT
            FORCE_FLOOR_DOWN -> Direction.DOWN
            else -> direction
        }
    }

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        interactor.forcedDirection = getNewDirection(direction)
    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
        interactor.forcedDirection = null
    }

    override fun onTick(engine: Engine) {
    }

}