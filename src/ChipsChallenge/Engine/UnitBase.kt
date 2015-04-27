package ChipsChallenge.Engine

import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.Map.Tile
import ChipsChallenge.Unit.Bug
import ChipsChallenge.Unit.PinkBall
import java.awt.image.BufferedImage
import java.util.HashMap

/**
 * Created by chase on 2/25/15.
 */

fun unitFromId(id: Int, location: Point, direction: Direction): UnitBase? {
    return when (id) {
        0 -> PinkBall(direction, location)
        1 -> Bug(direction, location)
        else -> null
    }
}

fun unitFromJson(obj: JSONObject): UnitBase? {
    try {
        return unitFromId(obj.getInt("id"), pointFromJson(obj.getJSONObject("location"))!!,
                directionFromString(obj.getString("direction")))
    } catch(except: Exception) {
        return null
    }
}

abstract class UnitBase(val typeId: Int, location: Point, val moveSpeed: Int = 5, var currentMove: Int = 5,
                        val uniqueId: Id = Id(IdType.UNIT)) : EngineObjectBase(location) {

    protected val imageSet: HashMap<String, BufferedImage> = HashMap()

    var image: BufferedImage? = null

    abstract fun canMoveToTile(tile: Tile): Boolean

    override fun getSaveObject(): JSONObject {
        val obj = JSONObject()
        obj.put("location", location.saveObject)
        obj.put("id", typeId)
        return obj
    }

    override fun onTick(engine: Engine) {
        if (currentMove > 0) {
            currentMove -= 1
        }
    }

    fun move(direction: Direction, engine: Engine) {
        currentMove = moveSpeed
    }

    open fun moveUp(engine: Engine) {
        if (engine.movement.moveUp(this)) {
            location.y -= 1
            move(Direction.UP, engine)
        }
    }

    open fun moveDown(engine: Engine) {
        if (engine.movement.moveDown(this)) {
            location.y += 1
            move(Direction.DOWN, engine)
        }
    }

    open fun moveLeft(engine: Engine) {
        if (engine.movement.moveLeft(this)) {
            location.x -= 1
            move(Direction.LEFT, engine)
        }
    }

    open fun moveRight(engine: Engine) {
        if (engine.movement.moveRight(this)) {
            location.x += 1
            move(Direction.RIGHT, engine)
        }
    }


}