package ChipsChallenge.Unit

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Map
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.Fire
import ChipsChallenge.Map.Tiles.Gravel

val GLIDER_DEFAULT_MOVE_SPEED = DEFAULT_MOVE_SPEED
val GLIDER_TYPE_ID = 3

class Glider(location: Point, direction: Direction, moveSpeed: Int,
             uniqueId: Id) : DirectionalUnit(GLIDER_TYPE_ID, location, direction, moveSpeed, uniqueId) {

    override fun canSurviveInWater(): Boolean {
        return true
    }

    override fun canSurviveInFire(): Boolean {
        return false
    }

    constructor(location: Point, direction: Direction) : this(location, direction,
            BUG_DEFAULT_MOVE_SPEED, Id(IdType.UNIT)) {
    }

    init {
        image = gliderUpImage
    }

    override fun changeDirection() {
    }

    override fun canMoveToTile(tile: Tile, direction: Direction): Boolean {
        return super.canMoveToTile(tile, direction) && tile !is Gravel
    }

    override fun onTick(engine: Engine) {
        //Calculate the new direction
        if (currentMove == 1) {
            var newTile = getTileAheadOfCurrent(engine.map)
            if (newTile == null || !canMoveToTile(newTile!!, direction)) {
                newTile = getTileLeftOfCurrent(engine.map)
                if (newTile == null || !canMoveToTile(newTile!!, getLeftOfCurrent())) {
                    newTile = getTileRightOfCurrent(engine.map)
                    if (newTile == null || !canMoveToTile(newTile!!, getRightOfCurrent())) {
                        newTile = getTileBehindCurrent(engine.map)
                        if (newTile != null && canMoveToTile(newTile!!, getBehindOfCurrent())) {
                            direction = getBehindOfCurrent()
                        }
                    } else {
                        direction = getRightOfCurrent()
                    }
                } else {
                    direction = getLeftOfCurrent()
                }
            }
            setImage()
        }
        super.onTick(engine)
    }

    fun getTileRightOfCurrent(map: Map): Tile? {
        return when (direction) {
            Direction.UP -> map.getRight(location)
            Direction.LEFT -> map.getUp(location)
            Direction.DOWN -> map.getLeft(location)
            Direction.RIGHT -> map.getDown(location)
        }
    }

    fun getTileLeftOfCurrent(map: Map): Tile? {
        return when (direction) {
            Direction.UP -> map.getLeft(location)
            Direction.LEFT -> map.getDown(location)
            Direction.DOWN -> map.getRight(location)
            Direction.RIGHT -> map.getUp(location)
        }
    }

    fun getTileAheadOfCurrent(map: Map): Tile ? {
        return when (direction) {
            Direction.UP -> map.getUp(location)
            Direction.LEFT -> map.getLeft(location)
            Direction.DOWN -> map.getDown(location)
            Direction.RIGHT -> map.getRight(location)
        }
    }

    fun getTileBehindCurrent(map: Map): Tile? {
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

    override fun setImage() {
        image = when (direction) {
            Direction.UP -> gliderUpImage
            Direction.LEFT -> gliderLeftImage
            Direction.RIGHT -> gliderRightImage
            Direction.DOWN -> gliderDownImage
        }
    }
}