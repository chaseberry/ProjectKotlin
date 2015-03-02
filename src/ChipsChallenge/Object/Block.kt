package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.blockImage
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Unit.Player
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.*

/**
 * Created by chase on 3/1/15.
 */
class Block(location: Point) : ObjectBase(11, location, blockImage) {

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor !is Player) {
            return ObjectResolution.NOTHING
        }

        val targetTile = when (direction) {
            Direction.UP -> engine.map.getUp(location)
            Direction.DOWN -> engine.map.getDown(location)
            Direction.LEFT -> engine.map.getLeft(location)
            Direction.RIGHT -> engine.map.getRight(location)
        }
        if (targetTile == null) {
            return ObjectResolution.NOTHING
        }
        if (canMoveToTile(targetTile)) {
            return ObjectResolution.MOVE
        }

        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {
    }

    fun canMoveToTile(tile: Tile): Boolean {
        return (tile is Floor || tile is Water)
    }

}