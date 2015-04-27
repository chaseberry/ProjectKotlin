package ChipsChallenge.Unit

import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.Point
import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.pinkBallImage
import ChipsChallenge.Map.Tiles.Floor

/**
 * Created by chase on 3/5/15.
 */
class PinkBall(direction: Direction, location: Point) : DirectionalUnit(0, location, direction) {

    init {
        image = pinkBallImage
    }

    override fun canMoveToTile(tile: Tile): Boolean {
        return tile is Floor
    }

    override fun changeDirection() {
        direction = when (direction) {
            Direction.DOWN -> Direction.UP
            Direction.UP -> Direction.DOWN
            Direction.LEFT -> Direction.RIGHT
            Direction.RIGHT -> Direction.LEFT
        }
    }

}