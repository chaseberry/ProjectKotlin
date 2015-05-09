package ChipsChallenge.Unit

import ChipsChallenge.Engine.*
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.Gravel
import ChipsChallenge.Map.Tiles.RecessedWall

abstract class DirectionalUnit(typeId: Int, location: Point, var direction: Direction, moveSpeed: Int,
                               uniqueId: Id) : UnitBase(typeId, location, moveSpeed, uniqueId) {

    constructor(typeId: Int, location: Point, direction: Direction, moveSpeed: Int) :
    this(typeId, location, direction, moveSpeed, Id(IdType.UNIT)) {

    }

    override fun onTick(engine: Engine) {
        super.onTick(engine)
        if (currentMove == 0) {
            when (direction) {
                Direction.UP -> {
                    moveUp(engine)
                    return;
                }
                Direction.DOWN -> {
                    moveDown(engine)
                    return
                }
                Direction.LEFT -> {
                    moveLeft(engine)
                    return
                }
                Direction.RIGHT -> {
                    moveRight(engine)
                    return
                }
            }
        }
    }

    abstract fun setImage()

    fun rotateDirection() {
        direction = when (direction) {
            Direction.UP -> Direction.RIGHT
            Direction.RIGHT -> Direction.DOWN
            Direction.DOWN -> Direction.LEFT
            Direction.LEFT -> Direction.UP
        }
        setImage()
    }

    override fun getSaveObject(): JSONObject {
        val saveObj = super.getSaveObject()
        saveObj.put("direction", direction)
        return saveObj
    }


    override fun moveUp(engine: Engine) {
        if (engine.movement.moveUp(this)) {
            move(Direction.UP, engine, location.copy(y = location.y - 1))
        } else {
            changeDirection(engine)
        }
    }

    override fun moveDown(engine: Engine) {
        if (engine.movement.moveDown(this)) {
            move(Direction.DOWN, engine, location.copy(y = location.y + 1))
        } else {
            changeDirection(engine)
        }
    }

    override fun moveLeft(engine: Engine) {
        if (engine.movement.moveLeft(this)) {
            move(Direction.LEFT, engine, location.copy(x = location.x - 1))
        } else {
            changeDirection(engine)
        }
    }

    override fun moveRight(engine: Engine) {
        if (engine.movement.moveRight(this)) {
            move(Direction.RIGHT, engine, location.copy(x = location.x + 1))
        } else {
            changeDirection(engine)
        }
    }

    abstract fun changeDirection(engine: Engine)

    override fun canMoveToTile(tile: Tile, direction: Direction): Boolean {
        return super.canMoveToTile(tile, direction) && tile !is Gravel && tile !is RecessedWall
    }

    fun getTileRightOfCurrent(map: ChipsChallenge.Map.Map): Tile? {
        return when (direction) {
            Direction.UP -> map.getRight(location)
            Direction.LEFT -> map.getUp(location)
            Direction.DOWN -> map.getLeft(location)
            Direction.RIGHT -> map.getDown(location)
        }
    }

    fun getTileLeftOfCurrent(map: ChipsChallenge.Map.Map): Tile? {
        return when (direction) {
            Direction.UP -> map.getLeft(location)
            Direction.LEFT -> map.getDown(location)
            Direction.DOWN -> map.getRight(location)
            Direction.RIGHT -> map.getUp(location)
        }
    }

    fun getTileAheadOfCurrent(map: ChipsChallenge.Map.Map): Tile ? {
        return when (direction) {
            Direction.UP -> map.getUp(location)
            Direction.LEFT -> map.getLeft(location)
            Direction.DOWN -> map.getDown(location)
            Direction.RIGHT -> map.getRight(location)
        }
    }

    fun getTileBehindCurrent(map: ChipsChallenge.Map.Map): Tile? {
        return when (direction) {
            Direction.UP -> map.getDown(location)
            Direction.LEFT -> map.getRight(location)
            Direction.DOWN -> map.getUp(location)
            Direction.RIGHT -> map.getLeft(location)
        }
    }

    fun getLeftOfCurrent(): Direction {
        return when (direction) {
            Direction.UP -> Direction.LEFT
            Direction.LEFT -> Direction.DOWN
            Direction.DOWN -> Direction.RIGHT
            Direction.RIGHT -> Direction.UP
        }
    }

    fun getRightOfCurrent(): Direction {
        return when (direction) {
            Direction.UP -> Direction.RIGHT
            Direction.LEFT -> Direction.UP
            Direction.DOWN -> Direction.LEFT
            Direction.RIGHT -> Direction.DOWN
        }
    }

    fun getBehindOfCurrent(): Direction {
        return when (direction) {
            Direction.DOWN -> Direction.UP
            Direction.UP -> Direction.DOWN
            Direction.LEFT -> Direction.RIGHT
            Direction.RIGHT -> Direction.LEFT
        }
    }


}

