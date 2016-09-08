package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

class FireBoot(location: Point, uniqueId: Id) : ObjectBase(FIRE_BOOT_TYPE_ID, location, fireBootImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player) {
            interactor.inventory.hasFireBoots = true
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.PASSOVER
    }

    override fun onTick(engine: Engine) {
    }

}