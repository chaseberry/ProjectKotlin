package ChipsChallenge.Engine

import java.awt.image.BufferedImage
import ChipsChallenge.Map.Point
import ChipsChallenge.Object.*
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.JSON.JSONArray

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

fun objectFromJson(obj: JSONObject): ObjectBase? {
    val locArray = obj.getJSONArray("location")
    return objectFromId(obj.getInt("id"), Point(locArray.getInt(0), locArray.getInt(1)))
}

data abstract class ObjectBase(val id: Int, var location: Point, val image: BufferedImage) : EngineObjectBase {

    val saveObject: JSONObject
        get() {
            return JSONObject()
                    .put("id", id)
                    .put("location", JSONArray().put(location.x).put(location.y))
        }

    abstract fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution

}

public enum class ObjectResolution {
    MOVE
    REMOVE
    NOTHING
    PASSOVER
}