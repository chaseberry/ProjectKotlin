package ChipsChallenge.Unit

import ChipsChallenge.Engine.Point
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Map.Tiles.Floor
import ChipsChallenge.Map.Tiles.Water
import ChipsChallenge.Map.Map

/**
 * Created by chase on 3/6/15.
 */
class Bug(direction: Direction, location: Point) : DirectionalUnit(1, location, direction) {

    override fun changeDirection() {
        direction = getLeftOfCurrent()
    }

    override fun canMoveToTile(tile: Tile): Boolean {
        return tile is Floor || tile is Water
    }

    override fun onTick(engine: Engine) {
        var newTile = getTileLeftOfCurrent(engine.map)
        if (newTile != null && canMoveToTile(newTile!!)) {
            direction = getLeftOfCurrent()
        } else {
            newTile = getTileAheadOfCurrent(engine.map)
            if (newTile == null || !canMoveToTile(newTile!!)) {
                newTile = getTileRightOfCurrent(engine.map)
                if (newTile != null && canMoveToTile(newTile!!)) {
                    direction = getRightOfCurrent()
                } else {
                    newTile = getTileBehindCurrent(engine.map)
                    if (newTile != null && canMoveToTile(newTile!!)) {
                        direction = getBehindOfCurrent()
                    }
                }
            }
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
            Direction.DOWN -> map.getRight(location)
            Direction.RIGHT -> map.getDown(location)
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


}