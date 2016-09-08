package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

class RedLock(location: Point, uniqueId: Id) : ObjectBase(RED_LOCK_TYPE_ID, location, redLockImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player && engine.player.inventory.redKeys > 0) {
            engine.player.inventory.redKeys--
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {

    }

}