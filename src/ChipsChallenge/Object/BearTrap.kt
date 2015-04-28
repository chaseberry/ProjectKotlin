package ChipsChallenge.Object

import ChipsChallenge.Engine.*

/**
 * Created by chase on 3/2/15.
 */
class BearTrap(location: Point, var isActive: Boolean = true, uniqueId: Id) : ObjectBase(BEAR_TRAP_TYPE_ID, location, bearTrapImage, uniqueId), Triggerable {

    constructor(location: Point) : this(location, true, Id(IdType.OBJECT)) {
    }

    override fun onTrigger() {
        isActive = false
    }

    override fun offTrigger() {
        isActive = true
    }

    override fun clone(): Triggerable {
        return objectFromId(typeId, location) as Triggerable
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.PASSOVER
    }

    override fun onTick(engine: Engine) {
    }

}