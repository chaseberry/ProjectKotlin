package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 3/7/15.
 */
class SuctionBoot(location: Point, uniqueId: Id) : ObjectBase(SUCTION_BOOT_TYPE_ID, location, suctionBootImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player) {
            interactor.inventory.hasSuctionBoots = true
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.PASSOVER
    }

    override fun onTick(engine: Engine) {
    }

}