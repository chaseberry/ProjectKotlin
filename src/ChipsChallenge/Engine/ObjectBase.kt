package ChipsChallenge.Engine

import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.Object.*
import java.awt.image.BufferedImage

val CHIP_TYPE_ID = 0
val SOCKET_TYPE_ID = 1
val BLUE_KEY_TYPE_ID = 2
val BLUE_LOCK_TYPE_ID = 3
val RED_KEY_TYPE_ID = 4
val RED_LOCK_TYPE_ID = 5
val YELLOW_KEY_TYPE_ID = 6
val YELLOW_LOCK_TYPE_ID = 7
val GREEN_KEY_TYPE_ID = 8
val GREEN_LOCK_TYPE_ID = 9
val DIRT_TYPE_ID = 10
val BLOCK_TYPE_ID = 11
val GREEN_BUTTON_TYPE_ID = 12
val BROWN_BUTTON_TYPE_ID = 13
val BEAR_TRAP_TYPE_ID = 14
val FIRE_BOOT_TYPE_ID = 15
val FLIPPER_TYPE_ID = 16
val ICE_SKATE_TYPE_ID = 17
val SUCTION_BOOT_TYPE_ID = 18

fun objectFromId(typeId: Int, location: Point): ObjectBase? {
    return when (typeId) {
        CHIP_TYPE_ID -> Chip(location)
        SOCKET_TYPE_ID -> Socket(location)
        BLUE_KEY_TYPE_ID -> BlueKey(location)
        BLUE_LOCK_TYPE_ID -> BlueLock(location)
        RED_KEY_TYPE_ID -> RedKey(location)
        RED_LOCK_TYPE_ID -> RedLock(location)
        YELLOW_KEY_TYPE_ID -> YellowKey(location)
        YELLOW_LOCK_TYPE_ID -> YellowLock(location)
        GREEN_KEY_TYPE_ID -> GreenKey(location)
        GREEN_LOCK_TYPE_ID -> GreenLock(location)
        DIRT_TYPE_ID -> Dirt(location)
        BLOCK_TYPE_ID -> Block(location)
        GREEN_BUTTON_TYPE_ID -> GreenButton(location)
        BROWN_BUTTON_TYPE_ID -> BrownButton(location)
        BEAR_TRAP_TYPE_ID -> BearTrap(location)
        FIRE_BOOT_TYPE_ID -> FireBoot(location)
        FLIPPER_TYPE_ID -> Flipper(location)
        ICE_SKATE_TYPE_ID -> IceSkate(location)
        SUCTION_BOOT_TYPE_ID -> SuctionBoot(location)
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
                               val uniqueId: Id) : EngineObjectBase(location) {

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