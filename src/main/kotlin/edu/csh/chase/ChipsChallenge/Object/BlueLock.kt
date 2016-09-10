package edu.csh.chase.ChipsChallenge.Object

import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

/**
 * Created by chase on 2/27/15.
 */
class BlueLock(location: Point, uniqueId: Id) : ObjectBase(BLUE_LOCK_TYPE_ID, location, blueKLockImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player && engine.player.inventory.blueKeys > 0) {
            engine.player.inventory.blueKeys--
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {

    }

}