package edu.csh.chase.ChipsChallenge.Engine

import edu.csh.chase.ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Map.Tiles.*
import edu.csh.chase.ChipsChallenge.Unit.*
import edu.csh.chase.kjson.Json
import edu.csh.chase.kjson.JsonObject
import java.awt.image.BufferedImage
import java.util.*

val DEFAULT_MOVE_SPEED = 5

fun unitFromId(id: Int, location: Point, direction: Direction = Direction.UP): UnitBase {
    return when (id) {
        PINK_BALL_TYPE_ID -> PinkBall(location, direction)
        BUG_TYPE_ID -> Bug(location, direction)
        TANK_TYPE_ID -> Tank(location, direction)
        GLIDER_TYPE_ID -> Glider(location, direction)
        FIREBALL_TYPE_ID -> Fireball(location, direction)
        WALKER_TYPE_ID -> Walker(location, direction)
        PARAMECIUM_TYPE_ID -> Paramecium(location, direction)
        BLOB_TYPE_ID -> Blob(location)
        else -> throw LevelException("Failed to load unit with {id:$id}")
    }
}

abstract class UnitBase(val typeId: Int, location: Point, val moveSpeed: Int = 5,
                        val uniqueId: Id = Id(IdType.UNIT)) : EngineObjectBase(location, null) {

    var forcedDirection: Direction? = null

    var currentMove: Int = moveSpeed

    protected val imageSet: HashMap<String, BufferedImage> = HashMap()

    open fun canMoveToTile(tile: Tile, direction: Direction): Boolean {
        return tile !is Wall && tile !is InvisibleWall &&
                tile !is RevealableWall && tile !is BlueWall &&
                (if (tile is RecessedWall) tile.open else true) &&
                (if (tile is ToggleWall) tile.open else true)


    }

    fun canMoveToTile(tile: Tile, direction: Direction, engine: Engine): Boolean {
        val obj = engine.objectManager.objects[tile.location]
        if (obj != null) {
            if (obj.interact(engine, direction, this) == ObjectResolution.NOTHING) {
                return false
            }
        }
        return canMoveToTile(tile, direction)
    }

    override fun getSaveObject(): JsonObject {
        return Json(
                "location" to location.saveObject,
                "typeId" to typeId,
                "id" to uniqueId.getJson(),
                "moveSpeed" to moveSpeed
        )
    }

    override fun onTick(engine: Engine) {
        if (currentMove > 0) {
            currentMove -= 1
        }
        if (currentMove == 0 && forcedDirection != null) {
            when (forcedDirection) {
                Direction.UP -> moveUp(engine)
                Direction.DOWN -> moveDown(engine)
                Direction.LEFT -> moveLeft(engine)
                Direction.RIGHT -> moveRight(engine)
            }
        }
    }

    open fun move(direction: Direction, engine: Engine, newLocation: Point) {
        currentMove = moveSpeed
        engine.map.getTile(location)!!.onExit(this, direction, engine)
        location = newLocation
        engine.map.getTile(location)!!.onEnter(this, direction, engine)
    }

    open fun moveUp(engine: Engine) {
        if (engine.movement.moveUp(this)) {
            move(Direction.UP, engine, location.copy(y = location.y - 1))
        }
    }

    open fun moveDown(engine: Engine) {
        if (engine.movement.moveDown(this)) {
            move(Direction.DOWN, engine, location.copy(y = location.y + 1))
        }
    }

    open fun moveLeft(engine: Engine) {
        if (engine.movement.moveLeft(this)) {
            move(Direction.LEFT, engine, location.copy(x = location.x - 1))
        }
    }

    open fun moveRight(engine: Engine) {
        if (engine.movement.moveRight(this)) {
            move(Direction.RIGHT, engine, location.copy(x = location.x + 1))
        }
    }

    abstract fun canSurviveInWater(): Boolean;

    abstract fun canSurviveInFire(): Boolean;

    override fun equals(other: Any?): Boolean {
        return other is UnitBase && (other.typeId == typeId)
    }
}