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
    fun move(newLocation: Point, direction: Direction, interactor: UnitBase): Boolean {
        if (engine.map.getTile(newLocation) == null || !interactor.canMoveToTile(engine.map.getTile(newLocation) as Tile)) {
            return false
        }

        return engine.objectManager.resolve(newLocation, direction, interactor)
    }

    public fun moveUp(interactor: UnitBase): Boolean {
        return move(Point(engine.player.location.x, engine.player.location.y - 1), Direction.UP, interactor)

    }

    public fun moveDown(interactor: UnitBase): Boolean {
        return move(Point(engine.player.location.x, engine.player.location.y + 1), Direction.DOWN, interactor)

    }

    public fun moveLeft(interactor: UnitBase): Boolean {
        return move(Point(engine.player.location.x - 1, engine.player.location.y), Direction.LEFT, interactor)

    }

    public fun moveRight(interactor: UnitBase): Boolean {
        return move(Point(engine.player.location.x + 1, engine.player.location.y), Direction.RIGHT, interactor)
    }

}