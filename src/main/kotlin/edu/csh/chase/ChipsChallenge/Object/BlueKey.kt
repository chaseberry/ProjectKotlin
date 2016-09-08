package edu.csh.chase.ChipsChallenge.Object

import ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

/**
 * Created by chase on 2/27/15.
 */
class BlueKey(location: Point, uniqueId: Id) : ObjectBase(BLUE_KEY_TYPE_ID, location, blueKeyImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor !is Player) {
            return ObjectResolution.PASSOVER
        }
        engine.player.inventory.blueKeys++
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {

    }

}