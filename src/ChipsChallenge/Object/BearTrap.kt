package ChipsChallenge.Object

import ChipsChallenge.Engine.*

class BearTrap(location: Point, var isActive: Boolean = false, uniqueId: Id) : ObjectBase(BEAR_TRAP_TYPE_ID, location, bearTrapImage, uniqueId), Triggerable {

    constructor(location: Point) : this(location, true, Id(IdType.OBJECT)) {
    }

    override fun onTrigger() {
        isActive = true
    }

    override fun offTrigger() {
        isActive = false
    }

    override fun canInteractorMove(engine: Engine, interactor: UnitBase): Boolean {
        return isActive // if the trap is active nothing can move
    }

    override fun clone(): Triggerable {
        return objectFromTypeId(typeId, location) as Triggerable
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.PASSOVER
    }

    override fun onTick(engine: Engine) {
    }

    override fun getInspectionData(): Inspection {
        return Inspection("Finish", "The game ends here.")
    }

}