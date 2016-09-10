package edu.csh.chase.ChipsChallenge.Object

import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

class YellowKey(location: Point, uniqueId: Id) : ObjectBase(YELLOW_KEY_TYPE_ID, location, yellowKeyImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor !is Player) {
            return ObjectResolution.PASSOVER
        }
        engine.player.inventory.yellowKeys++
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {
    }

}