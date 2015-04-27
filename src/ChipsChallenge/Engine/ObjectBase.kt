package ChipsChallenge.Engine

import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.Object.*
import java.awt.image.BufferedImage

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
        12 -> GreenButton(location)
        13 -> BrownButton(location)
        14 -> BearTrap(location)
        15 -> FireBoot(location)
        16 -> Flipper(location)
        17 -> IceSkate(location)
        18 -> SuctionBoot(location)
        else -> null
    }
}

fun objectFromJSON(obj: JSONObject): ObjectBase? {
    try {
        return objectFromId(obj.getInt("id"), pointFromJson(obj.getJSONObject("location"))!!)
    } catch(except: Exception) {

    }
    return null
}

data abstract class ObjectBase(val typeId: Int, location: Point, val image: BufferedImage,
                               val uniqueId: Id = Id(IdType.OBJECT)) : EngineObjectBase(location) {

    abstract fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution

    override fun getSaveObject(): JSONObject {
        val obj = JSONObject()
        obj.put("id", typeId)
        obj.put("location", location.saveObject)
        return obj
    }

}

public enum class ObjectResolution {
    MOVE
    REMOVE
    NOTHING
    PASSOVER
    TRIGGER
}