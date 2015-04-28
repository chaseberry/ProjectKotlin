package ChipsChallenge.Engine

import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.Map.Tile
import ChipsChallenge.Unit.BUG_TYPE_ID
import ChipsChallenge.Unit.Bug
import ChipsChallenge.Unit.PINK_BALL_TYPE_ID
import ChipsChallenge.Unit.PinkBall
import java.awt.image.BufferedImage
import java.util.HashMap

/**
 * Created by chase on 2/25/15.
 */

val DEFAULT_MOVE_SPEED = 5

fun unitFromId(id: Int, location: Point, direction: Direction = Direction.UP): UnitBase? {
    return when (id) {
        0 -> PinkBall(location, direction)
        1 -> Bug(location, direction)
        else -> null
    }
}

fun unitFromJson(obj: JSONObject): UnitBase? {
    val typeId = obj.getInt("typeId")
    val location = pointFromJson(obj.getJSONObject("location"))
    val direction = directionFromString(obj.getString("direction"))//Might not always be here, specifically for teeth/blob
    val uniqueId = idFromJson(obj.getJSONObject("id"))
    val moveSpeed = obj.getInt("moveSpeed")
    return when (typeId) {
        PINK_BALL_TYPE_ID -> PinkBall(location, direction, moveSpeed, uniqueId)
        BUG_TYPE_ID -> Bug(location, direction, moveSpeed, uniqueId)
        else -> null
    }
}

abstract class UnitBase(val typeId: Int, location: Point, val moveSpeed: Int = 5,
                        val uniqueId: Id = Id(IdType.UNIT)) : EngineObjectBase(location) {

    var currentMove: Int = moveSpeed

    protected val imageSet: HashMap<String, BufferedImage> = HashMap()

    var image: BufferedImage? = null

    abstract fun canMoveToTile(tile: Tile): Boolean

    override fun getSaveObject(): JSONObject {
        val obj = JSONObject()
        obj.put("location", location.saveObject)
        obj.put("typeId", typeId)
        obj.put("id", uniqueId.getJson())
        obj.put("moveSpeed", moveSpeed)
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