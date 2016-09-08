package edu.csh.chase.ChipsChallenge.Engine

import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.ForceFloor
import ChipsChallenge.Map.Tiles.Revealable
import edu.csh.chase.ChipsChallenge.Unit.DirectionalUnit
import edu.csh.chase.ChipsChallenge.Engine.*

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
            return moveForce(newLocation, direction, interactor, targetTile)
        }

        if (targetTile is Revealable) {
            targetTile.reveal()
        }

        if (targetTile == null || !interactor.canMoveToTile(targetTile, direction)) {
            return false
        }
        val obj = engine.objectManager.objects[interactor.location]
        if (obj != null && !obj.canInteractorMove(engine, interactor)) {
            return false
        }
        return engine.objectManager.resolve(newLocation, direction, interactor)


    }

    fun moveForce(newLocation: Point, direction: Direction, interactor: UnitBase, targetTile: Tile?): Boolean {

        if (targetTile is Revealable) {
            targetTile.reveal()
        }

        //Can they move to the next tile? (IE is it a wall)
        if (targetTile == null || !interactor.canMoveToTile(targetTile, direction)) {
            if (engine.map.getTile(interactor.location)!! is ForceFloor) {
                interactor.move(direction, engine, interactor.location)
                return false
            }
            interactor.forcedDirection = flipDirection(interactor.forcedDirection as Direction)
            when (interactor.forcedDirection) {
                Direction.UP -> interactor.moveUp(engine)
                Direction.DOWN -> interactor.moveDown(engine)
                Direction.LEFT -> interactor.moveLeft(engine)
                Direction.RIGHT -> interactor.moveRight(engine)
            }
            //newLocation here is wrong
            return false
        }
        //Is user on some object that they cannot move off of
        val obj = engine.objectManager.objects[interactor.location]
        if (obj != null && !obj.canInteractorMove(engine, interactor)) {
            return false
        }

        //Is there an object in the next location? Is it resolved?
        if (!engine.objectManager.resolve(newLocation, direction, interactor)) {
            if (engine.map.getTile(interactor.location)!! is ForceFloor) {
                interactor.move(direction, engine, interactor.location)
                return false
            }
            interactor.forcedDirection = flipDirection(interactor.forcedDirection as Direction)
            when (interactor.forcedDirection) {
                Direction.UP -> interactor.moveUp(engine)
                Direction.DOWN -> interactor.moveDown(engine)
                Direction.LEFT -> interactor.moveLeft(engine)
                Direction.RIGHT -> interactor.moveRight(engine)
            }
            return false
        }

        //Updated direction of a directional unit
        if (interactor is DirectionalUnit) {
            interactor.direction = interactor.forcedDirection as Direction
            interactor.setImage()
        }
        return true
    }

    fun moveUp(interactor: UnitBase): Boolean {
        return move(Point(interactor.location.x, interactor.location.y - 1), Direction.UP, interactor)

    }

    fun moveDown(interactor: UnitBase): Boolean {
        return move(Point(interactor.location.x, interactor.location.y + 1), Direction.DOWN, interactor)

    }

    fun moveLeft(interactor: UnitBase): Boolean {
        return move(Point(interactor.location.x - 1, interactor.location.y), Direction.LEFT, interactor)

    }

    fun moveRight(interactor: UnitBase): Boolean {
        return move(Point(interactor.location.x + 1, interactor.location.y), Direction.RIGHT, interactor)
    }

}