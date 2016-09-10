package edu.csh.chase.ChipsChallenge.Object

import edu.csh.chase.ChipsChallenge.Engine.*

class Bomb(location: Point, uniqueId: Id) : ObjectBase(BOMB_TYPE_ID, location, bombImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.KILL
    }

    override fun onTick(engine: Engine) {

    }

}