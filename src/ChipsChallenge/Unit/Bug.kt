package ChipsChallenge.Unit

import ChipsChallenge.Engine.Point
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Map.Tiles.Floor
import ChipsChallenge.Map.Tiles.Water

/**
 * Created by chase on 3/6/15.
 */
class Bug(location: Point, direction: Direction) : DirectionalUnit(1, location, direction) {

    override fun changeDirection() {
        
    }

    override fun canMoveToTile(tile: Tile): Boolean {
        return tile is Floor || tile is Water
    }

    override fun onTick(engine: Engine) {
        throw UnsupportedOperationException()
    }

    fun getLeftOfCurrent(): Direction {
        return when (direction) {
            Direction.UP -> Direction.LEFT
            Direction.LEFT -> Direction.DOWN
            Direction.DOWN -> Direction.RIGHT
            Direction.RIGHT -> Direction.UP
        }
    }

}