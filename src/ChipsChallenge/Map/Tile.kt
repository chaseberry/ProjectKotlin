package ChipsChallenge.Map

import ChipsChallenge.Engine.*
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.Map.Tiles.*
import java.awt.image.BufferedImage

val FLOOR_TYPE_ID = 0
val WALL_TYPE_ID = 1
val FINISH_TYPE_ID = 2
val HELP_TYPE_ID = 3
val WATER_TYPE_ID = 4
val FIRE_TYPE_ID = 5
val ICE_TYPE_ID = 6
val ICE_CORNER_LEFT_UP_ID = 7
val ICE_CORNER_RIGHT_UP_ID = 8
val ICE_CORNER_LEFT_DOWN_ID = 9
val ICE_CORNER_RIGHT_DOWN_ID = 10
val TELEPORT_TYPE_ID = 11
val GRAVEL_TYPE_ID = 12
val FORCE_FLOOR_LEFT = 13
val FORCE_FLOOR_UP = 14
val FORCE_FLOOR_RIGHT = 15
val FORCE_FLOOR_DOWN = 16
val FORCE_FLOOR_RANDOM = 17

public fun tileIdToTile(typeId: Int, location: Point): Tile {
    return when (typeId) {
        FLOOR_TYPE_ID -> Floor(location)
        WALL_TYPE_ID -> Wall(location)
        FINISH_TYPE_ID -> Finish(location)
        HELP_TYPE_ID -> Help(location)
        WATER_TYPE_ID -> Water(location)
        FIRE_TYPE_ID -> Fire(location)
        ICE_TYPE_ID -> Ice(location)
        ICE_CORNER_LEFT_UP_ID -> IceCorner(ICE_CORNER_LEFT_UP_ID, location)
        ICE_CORNER_LEFT_DOWN_ID -> IceCorner(ICE_CORNER_LEFT_DOWN_ID, location)
        ICE_CORNER_RIGHT_UP_ID -> IceCorner(ICE_CORNER_RIGHT_UP_ID, location)
        ICE_CORNER_RIGHT_DOWN_ID -> IceCorner(ICE_CORNER_RIGHT_DOWN_ID, location)
        TELEPORT_TYPE_ID -> Teleport(location)
        GRAVEL_TYPE_ID -> Gravel(location)
        FORCE_FLOOR_LEFT -> ForceFloor(typeId, location)
        FORCE_FLOOR_UP -> ForceFloor(typeId, location)
        FORCE_FLOOR_RIGHT -> ForceFloor(typeId, location)
        FORCE_FLOOR_DOWN -> ForceFloor(typeId, location)
        FORCE_FLOOR_RANDOM -> ForceFloor(typeId, location)
        else -> Wall(location)
    }
}

public fun tileFromJson(obj: JSONObject, location: Point): Tile {

    val typeId = obj.getInt("typeId")
    val id = idFromJson(obj.getJSONObject("id"))

    return when (typeId) {
        FLOOR_TYPE_ID -> Floor(location, id)
        WALL_TYPE_ID -> Wall(location, id)
        FINISH_TYPE_ID -> Finish(location, id)
        HELP_TYPE_ID -> Help(location, id)
        WATER_TYPE_ID -> Water(location, id)
        FIRE_TYPE_ID -> Fire(location, id)
        ICE_TYPE_ID -> Ice(location, id)
        ICE_CORNER_LEFT_UP_ID -> IceCorner(ICE_CORNER_LEFT_UP_ID, location, id)
        ICE_CORNER_LEFT_DOWN_ID -> IceCorner(ICE_CORNER_LEFT_DOWN_ID, location, id)
        ICE_CORNER_RIGHT_UP_ID -> IceCorner(ICE_CORNER_RIGHT_UP_ID, location, id)
        ICE_CORNER_RIGHT_DOWN_ID -> IceCorner(ICE_CORNER_RIGHT_DOWN_ID, location, id)
        TELEPORT_TYPE_ID -> Teleport(location, id)
        GRAVEL_TYPE_ID -> Gravel(location, id)
        else -> Wall(location)
    }
}

data abstract class Tile(val image: BufferedImage, val  tileId: Int, location: Point,
                         val uniqueId: Id) : EngineObjectBase(location) {

    fun typeEquals(tile: Tile): Boolean {
        return tile.tileId == tileId
    }

    override fun equals(other: Any?): Boolean {
        if (other != null && other is Tile) {
            return other.tileId == tileId && other.uniqueId == uniqueId
        }
        return false;
    }

    override fun getSaveObject(): JSONObject {
        return JSONObject().put("typeId", tileId).put("id", uniqueId.getJson())
    }

    abstract fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine);

    abstract fun onExit(interactor: UnitBase, direction: Direction, engine: Engine);
}
