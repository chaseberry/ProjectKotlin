package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Tiles.Floor
import ChipsChallenge.Map.Tiles.Water
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 3/1/15.
 */
class Block(location: Point, uniqueId: Id) : ObjectBase(BLOCK_TYPE_ID, location, blockImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    var objectUnder: ObjectBase? = null

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

    fun cover(obj: ObjectBase, engine: Engine?): Boolean {
        if (objectUnder != null) {
            return false
        }
        objectUnder = obj
        objectUnder!!.location = location.copy()
        if (engine != null && obj is Button) {
            obj.trigger(engine)
        }
        return true
    }

    fun unCover(engine: Engine): ObjectBase? {
        val objUnder = objectUnder
        objectUnder = null
        if (objUnder != null && objUnder is Button) {
            objUnder.offTrigger(engine)
        }
        return objUnder
    }

    fun canMoveToLocation(engine: Engine, location: Point): Boolean {
        val tile = engine.map.getTile(location)
        val objectInSpace = engine.objectManager.objects.get(location)
        if (objectInSpace != null && objectInSpace !is Button && objectInSpace !is BearTrap ||
                (objectUnder is BearTrap && (objectUnder as BearTrap).isActive)) {
            return false
        }

        return (tile is Floor || tile is Water)
    }

}