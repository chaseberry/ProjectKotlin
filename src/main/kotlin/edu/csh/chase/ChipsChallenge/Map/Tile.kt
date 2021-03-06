package edu.csh.chase.ChipsChallenge.Map

import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Map.Tiles.*
import edu.csh.chase.ChipsChallenge.Object.Button
import edu.csh.chase.kjson.Json
import edu.csh.chase.kjson.JsonObject
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
val TOGGLE_WALL_TYPE_ID = 18
val INVISIBLE_WALL_ID = 19
val REVEALABLE_WALL_TYPE_ID = 20
val BLUE_WALL_TYPE_ID = 21
val BLUE_FLOOR_TYPE_ID = 22
val THIEF_TYPE_ID = 23
val RECESSED_WALL_TYPE_ID = 24

fun tileIdToTile(typeId: Int, location: Point, open: Boolean = false): Tile {
    return tileFromId(typeId, location, open = open)
}

fun tileFromId(typeId: Int, location: Point, id: Id = Id(IdType.TILE), open: Boolean = false): Tile {
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
        FORCE_FLOOR_LEFT -> ForceFloor(typeId, location, id)
        FORCE_FLOOR_UP -> ForceFloor(typeId, location, id)
        FORCE_FLOOR_RIGHT -> ForceFloor(typeId, location, id)
        FORCE_FLOOR_DOWN -> ForceFloor(typeId, location, id)
        FORCE_FLOOR_RANDOM -> ForceFloor(typeId, location, id)
        TOGGLE_WALL_TYPE_ID -> ToggleWall(location, id, open)
        INVISIBLE_WALL_ID -> InvisibleWall(location, id)
        REVEALABLE_WALL_TYPE_ID -> RevealableWall(location, id)
        BLUE_WALL_TYPE_ID -> BlueWall(location, id)
        BLUE_FLOOR_TYPE_ID -> BlueFloor(location, id)
        THIEF_TYPE_ID -> Thief(location, id)
        RECESSED_WALL_TYPE_ID -> RecessedWall(location, id)
        else -> Wall(location)
    }
}

abstract class Tile(image: BufferedImage, val tileId: Int, location: Point,
                    val uniqueId: Id) : EngineObjectBase(location, image) {

    fun typeEquals(tile: Tile): Boolean {
        return tile.tileId == tileId
    }

    override fun equals(other: Any?): Boolean {
        if (other != null && other is Tile) {
            return other.tileId == tileId && other.uniqueId == uniqueId
        }
        return false;
    }

    override fun getSaveObject(): JsonObject {
        return Json("typeId" to tileId, "id" to uniqueId.getJson())
    }

    open fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        val obj = engine.objectManager.objects[location] ?: return
        if (obj is Button) {
            obj.trigger(engine)
        }
    }

    open fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
        val obj = engine.objectManager.objects[location] ?: return
        if (obj is Button) {
            obj.offTrigger(engine)
        }
    }
}
