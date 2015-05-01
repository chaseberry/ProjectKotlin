package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Tiles.IceBase
import ChipsChallenge.Map.Tiles.Wall
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 3/1/15.
 */
class Block(location: Point, uniqueId: Id) : ObjectBase(BLOCK_TYPE_ID, location, blockImage, uniqueId) {

    var currentMove = 5
    val moveSpeed = 5
    var forcedDirection: Direction? = null

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
        if (forcedDirection != null) {
            currentMove--
            if (currentMove == 0) {
                moveOnIce(engine)
                currentMove = moveSpeed

            }
        }
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
                (objectUnder is BearTrap && !(objectUnder as BearTrap).isActive)) {
            return false
        }

        return tile !is Wall
    }

    private fun moveOnIce(engine: Engine) {
        val targetTile = engine.map.getTile(when (forcedDirection) {
            Direction.UP -> location.copy(y = location.y - 1)
            Direction.DOWN -> location.copy(y = location.y + 1)
            Direction.LEFT -> location.copy(x = location.x - 1)
            Direction.RIGHT -> location.copy(x = location.x + 1)
            else -> location
        })
        if (targetTile == null || !canMoveToLocation(engine, targetTile.location)) {
            forcedDirection = flipDirection(forcedDirection as Direction)
            return
        }
        val obj = engine.objectManager.objects.get(targetTile)
        if (obj != null ) {
            return
        }
        when (forcedDirection) {
            Direction.UP -> engine.objectManager.forceMoveUp(this)
            Direction.DOWN -> engine.objectManager.forceMoveDown(this)
            Direction.LEFT -> engine.objectManager.forceMoveLeft(this)
            Direction.RIGHT -> engine.objectManager.forceMoveRight(this)
        }//Forced the location change

        val tile = engine.map.getTile(location)
        if (tile is IceBase) {
            forcedDirection = tile.getNewDirection(forcedDirection as Direction)
        } else {
            forcedDirection = null
        }
    }

}