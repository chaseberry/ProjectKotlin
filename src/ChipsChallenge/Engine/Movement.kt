package ChipsChallenge.Engine

import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.IceBase
import ChipsChallenge.Unit.DirectionalUnit

/**
 * Created by chase on 2/26/15.
 */
class Movement(val engine: Engine) {

    /**
     * Attempts to move to newLocation
     *
     */
    fun move(newLocation: Point, direction: Direction, interactor: UnitBase): Boolean {
        val targetTile = engine.map.getTile(newLocation)
        if (interactor.forcedDirection != null) {
            return moveIce(newLocation, direction, interactor, targetTile)
        }
        if (targetTile == null || !interactor.canMoveToTile(targetTile)) {
            return false
        }
        val obj = engine.objectManager.objects.get(interactor.location)
        if (obj != null && !obj.canInteractorMove(engine, interactor)) {
            return false
        }
        return engine.objectManager.resolve(newLocation, direction, interactor)
    }

    fun moveIce(newLocation: Point, direction: Direction, interactor: UnitBase, targetTile: Tile?): Boolean {
        if (targetTile == null || !interactor.canMoveToTile(targetTile)) {
            interactor.forcedDirection = flipDirection(interactor.forcedDirection as Direction)
            return false
        }
        val obj = engine.objectManager.objects.get(interactor.location)
        if (obj != null && !obj.canInteractorMove(engine, interactor)) {
            return false
        }

        if (obj != null && !engine.objectManager.resolve(newLocation, direction, interactor)) {
            interactor.forcedDirection = flipDirection(interactor.forcedDirection as Direction)
            return false
        }

        if (interactor is DirectionalUnit) {
            interactor.direction = interactor.forcedDirection as Direction
        }

        if (targetTile !is IceBase) {
            interactor.forcedDirection = null
        }
        return true
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