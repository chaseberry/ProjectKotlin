package ChipsChallenge.Engine

import ChipsChallenge.Engine.Point
import ChipsChallenge.Map.Tile
import ChipsChallenge.Object.BearTrap
import ChipsChallenge.Object.Button

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
        val obj = engine.objectManager.objects.get(interactor.location)
        if (obj != null && obj is BearTrap && obj.isActive) {
            return false
        }
        val canMove = engine.objectManager.resolve(newLocation, direction, interactor)
        if (canMove && engine.objectManager.objects.get(interactor.location) is Button) {
            (engine.objectManager.objects.get(interactor.location) as Button).offTrigger()
        }

        return canMove
    }

    public fun moveUp(interactor: UnitBase): Boolean {
        return move(Point(interactor.location.x, interactor.location.y - 1), Direction.UP, interactor)

    }

    public fun moveDown(interactor: UnitBase): Boolean {
        return move(Point(interactor.location.x, interactor.location.y + 1), Direction.DOWN, interactor)

    }

    public fun moveLeft(interactor: UnitBase): Boolean {
        return move(Point(interactor.location.x - 1, interactor.location.y), Direction.LEFT, interactor)

    }

    public fun moveRight(interactor: UnitBase): Boolean {
        return move(Point(interactor.location.x + 1, interactor.location.y), Direction.RIGHT, interactor)
    }

}