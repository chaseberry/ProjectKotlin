package ChipsChallenge.Map.Tiles;

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.ICE_CORNER_LEFT_DOWN_ID
import ChipsChallenge.Map.ICE_CORNER_LEFT_UP_ID
import ChipsChallenge.Map.ICE_CORNER_RIGHT_DOWN_ID
import ChipsChallenge.Map.ICE_CORNER_RIGHT_UP_ID
import java.awt.image.BufferedImage

fun image(typeId: Int): BufferedImage {
    return when (typeId) {
        ICE_CORNER_LEFT_DOWN_ID -> iceLeftDownImage
        ICE_CORNER_RIGHT_DOWN_ID -> iceRightDownImage
        ICE_CORNER_LEFT_UP_ID -> iceLeftUpImage
        ICE_CORNER_RIGHT_UP_ID -> iceRightUpImage
        else -> iceImage
    }
}

public class IceCorner(typeId: Int, location: Point, uniqueId: Id) : IceBase(image(typeId), typeId, location, uniqueId) {

    constructor(typeId: Int, location: Point) : this(typeId, location, Id(IdType.TILE)) {
    }

    override fun getNewDirection(direction: Direction): Direction {
        return when (tileId) {
        //note - the ID tells what the OPENINGS are
            ICE_CORNER_LEFT_DOWN_ID -> when (direction) {
                Direction.RIGHT -> Direction.DOWN
                Direction.UP -> Direction.LEFT
                Direction.LEFT -> Direction.RIGHT
                Direction.DOWN -> Direction.UP
            }
            ICE_CORNER_RIGHT_DOWN_ID -> when (direction) {
                Direction.LEFT -> Direction.DOWN
                Direction.UP -> Direction.RIGHT
                Direction.RIGHT -> Direction.LEFT
                Direction.DOWN -> Direction.UP
            }
            ICE_CORNER_LEFT_UP_ID -> when (direction) {
                Direction.DOWN -> Direction.LEFT
                Direction.RIGHT -> Direction.UP
                Direction.LEFT -> Direction.RIGHT
                Direction.UP -> Direction.DOWN
            }
            ICE_CORNER_RIGHT_UP_ID -> when (direction) {
                Direction.LEFT -> Direction.UP
                Direction.DOWN -> Direction.RIGHT
                Direction.UP -> Direction.DOWN
                Direction.RIGHT -> Direction.LEFT
            }
            else -> direction
        }
    }

    override fun onTick(engine: Engine) {
    }
}
