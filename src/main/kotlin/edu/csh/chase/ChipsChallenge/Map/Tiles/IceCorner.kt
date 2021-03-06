package edu.csh.chase.ChipsChallenge.Map.Tiles

import edu.csh.chase.ChipsChallenge.Map.Tiles.IceBase
import edu.csh.chase.ChipsChallenge.Map.ICE_CORNER_LEFT_DOWN_ID
import edu.csh.chase.ChipsChallenge.Map.ICE_CORNER_LEFT_UP_ID
import edu.csh.chase.ChipsChallenge.Map.ICE_CORNER_RIGHT_DOWN_ID
import edu.csh.chase.ChipsChallenge.Map.ICE_CORNER_RIGHT_UP_ID
import edu.csh.chase.ChipsChallenge.Engine.*
import java.awt.image.BufferedImage

fun iceCornerImage(typeId: Int): BufferedImage {
    return when (typeId) {
        ICE_CORNER_LEFT_DOWN_ID -> iceLeftDownImage
        ICE_CORNER_RIGHT_DOWN_ID -> iceRightDownImage
        ICE_CORNER_LEFT_UP_ID -> iceLeftUpImage
        ICE_CORNER_RIGHT_UP_ID -> iceRightUpImage
        else -> iceImage
    }
}

fun getNextIceCorner(typeId: Int): Int {
    return when (typeId) {
        ICE_CORNER_LEFT_DOWN_ID -> ICE_CORNER_RIGHT_DOWN_ID
        ICE_CORNER_RIGHT_DOWN_ID -> ICE_CORNER_LEFT_UP_ID
        ICE_CORNER_LEFT_UP_ID -> ICE_CORNER_RIGHT_UP_ID
        else -> ICE_CORNER_LEFT_DOWN_ID
    }
}

class IceCorner(typeId: Int, location: Point, uniqueId: Id) : IceBase(iceCornerImage(typeId), typeId, location, uniqueId) {

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
