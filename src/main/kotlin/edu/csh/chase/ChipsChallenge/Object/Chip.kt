package edu.csh.chase.ChipsChallenge.Object

import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

class Chip(location: Point, uniqueId: Id) : ObjectBase(CHIP_TYPE_ID, location, chipImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
        
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player) {
            interactor.inventory.chipsCollected++
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {
    }

}