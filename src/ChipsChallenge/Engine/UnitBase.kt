package ChipsChallenge.Engine

import java.awt.image.BufferedImage
import ChipsChallenge.Map.Tile
import java.util.HashMap
import ChipsChallenge.Unit.PinkBall
import ChipsChallenge.JSON.JSONObject

/**
 * Created by chase on 2/25/15.
 */

fun unitFromId(id: Int, location: Point, direction: Direction): UnitBase? {
    return when (id) {
        0 -> PinkBall(direction, location)
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

abstract class UnitBase(val id: Int, var location: Point, val moveSpeed: Int = 5, var currentMove: Int = 5) : EngineObjectBase {

    protected val imageSet: HashMap<String, BufferedImage> = HashMap()

    var image: BufferedImage? = null

    abstract fun canMoveToTile(tile: Tile): Boolean

    override fun getSaveObject(): JSONObject {
        val obj = JSONObject()
        obj.put("location", location.saveObject)
        obj.put("id", id)
        return obj
    }

    override fun onTick(engine: Engine) {
        if (currentMove > 0) {
            currentMove -= 1
        }

    }

    fun move() {
        currentMove = moveSpeed
    }

    open fun moveUp(engine: Engine) {
        image = imageSet.get("up")
        if (engine.movement.moveUp(this)) {
            location.y -= 1
            move()
        }
    }

    open fun moveDown(engine: Engine) {
        image = imageSet.get("down")
        if (engine.movement.moveDown(this)) {
            location.y += 1
            move()
        }
    }

    open fun moveLeft(engine: Engine) {
        image = imageSet.get("left")
        if (engine.movement.moveLeft(this)) {
            location.x -= 1
            move()
        }
    }

    open fun moveRight(engine: Engine) {
        image = imageSet.get("right")
        if (engine.movement.moveRight(this)) {
            location.x += 1
            move()
        }
    }


}