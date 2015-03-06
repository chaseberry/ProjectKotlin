package ChipsChallenge.Unit

import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Direction

/**
 * Created by chase on 3/6/15.
 */
abstract class DirectionalUnit(id: Int, location: Point, var direction: Direction) : UnitBase(id, location) {

    fun rotateDirection() {
        direction = when (direction) {
            Direction.UP -> Direction.RIGHT
            Direction.RIGHT -> Direction.DOWN
            Direction.DOWN -> Direction.LEFT
            Direction.LEFT -> Direction.UP
        }
    }

}

