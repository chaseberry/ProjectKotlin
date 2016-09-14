package edu.csh.chase.ChipsChallenge.Object

import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

class GreenKey(location: Point, uniqueId: Id) : ObjectBase(GREEN_KEY_TYPE_ID, location, greenKeyImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor !is Player) {
            return ObjectResolution.PASSOVER
        }
        interactor.inventory.hasGreenKey = true
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {

    }

}