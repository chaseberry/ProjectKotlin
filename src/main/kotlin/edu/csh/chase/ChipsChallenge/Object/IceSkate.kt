package edu.csh.chase.ChipsChallenge.Object

import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

class IceSkate(location: Point, uniqueId: Id) : ObjectBase(ICE_SKATE_TYPE_ID, location, iceSkatesImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player) {
            interactor.inventory.hasIceSkates = true
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.PASSOVER
    }

    override fun onTick(engine: Engine) {
    }

}