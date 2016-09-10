package edu.csh.chase.ChipsChallenge.Object

import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

/**
 * Created by chase on 2/28/15.
 */
class Dirt(location: Point, uniqueId: Id) : ObjectBase(DIRT_TYPE_ID, location, dirtImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player) {
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {
    }

}