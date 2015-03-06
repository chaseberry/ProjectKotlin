package ChipsChallenge.Unit

import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.Point
import ChipsChallenge.Engine.Direction
import ChipsChallenge.JSON.JSONObject

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

    override fun getSaveObject(): JSONObject {
        val saveObj = super.getSaveObject()
        saveObj.put("direction", direction)
        return saveObj
    }

}

