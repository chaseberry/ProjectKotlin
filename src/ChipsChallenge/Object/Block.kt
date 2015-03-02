package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.blockImage
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Unit.Player
import ChipsChallenge.Map.Tiles.*

/**
 * Created by chase on 3/1/15.
 */
class Block(location: Point) : ObjectBase(11, location, blockImage) {

    val blockedIds = (0..11)

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor !is Player) {
            return ObjectResolution.NOTHING
        }

        val targetLocation = when (direction) {
            Direction.UP -> location.copy(y = location.y - 1)
            Direction.DOWN -> location.copy(y = location.y + 1)
            Direction.LEFT -> location.copy(x = location.x - 1)
            Direction.RIGHT -> location.copy(x = location.x + 1)
        }
        //Blocks can't be on most Objects
        if (canMoveToLocation(engine, targetLocation)) {
            return ObjectResolution.MOVE
        }

        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {
    }

    fun canMoveToLocation(engine: Engine, location: Point): Boolean {
        val tile = engine.map.getTile(location)
        val objList = engine.objectManager.objects.get(location)
        if (objList != null) {
            for (objectInSpace in objList) {
                if (objectInSpace.id in blockedIds) {
                    return false
                }
            }
        }
        return (tile is Floor || tile is Water)
    }

}