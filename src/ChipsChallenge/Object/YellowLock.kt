package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 2/27/15.
 */
class YellowLock(location: Point, uniqueId: Id) : ObjectBase(YELLOW_LOCK_TYPE_ID, location, yellowLockImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun onTick(engine: Engine) {

    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player && engine.player.inventory.yellowKeys > 0) {
            engine.player.inventory.yellowKeys--
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

}