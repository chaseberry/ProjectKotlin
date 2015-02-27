package ChipsChallenge.Engine

import ChipsChallenge.Map.Point
import ChipsChallenge.Map.Tile

/**
 * Created by chase on 2/26/15.
 */
class Movement(val engine: Engine) {

    /**
     * Attempts to move to newLocation
     *
     */
    fun move(newLocation: Point, direction: Direction): Boolean {
        if (engine.map.getTile(newLocation) == null || !engine.player.canMoveToTile(engine.map.getTile(newLocation) as Tile)) {
            return false
        }
        
        return engine.objectManager.resolve(newLocation, direction)
    }

    public fun moveUp(): Boolean {
        return move(Point(engine.player.location.x, engine.player.location.y - 1), Direction.UP)

    }

    public fun moveDown(): Boolean {
        return move(Point(engine.player.location.x, engine.player.location.y + 1), Direction.DOWN)

    }

    public fun moveLeft(): Boolean {
        return move(Point(engine.player.location.x - 1, engine.player.location.y), Direction.LEFT)

    }

    public fun moveRight(): Boolean {
        return move(Point(engine.player.location.x + 1, engine.player.location.y), Direction.RIGHT)
    }

}