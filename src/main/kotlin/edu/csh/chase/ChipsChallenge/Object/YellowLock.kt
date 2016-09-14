package edu.csh.chase.ChipsChallenge.Object

import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

class YellowLock(location: Point, uniqueId: Id) : ObjectBase(YELLOW_LOCK_TYPE_ID, location, yellowLockImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun onTick(engine: Engine) {

    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player && interactor.inventory.yellowKeys > 0) {
            interactor.inventory.yellowKeys--
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

}