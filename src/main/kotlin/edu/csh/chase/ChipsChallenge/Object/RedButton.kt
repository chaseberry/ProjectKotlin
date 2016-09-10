package edu.csh.chase.ChipsChallenge.Object

import edu.csh.chase.ChipsChallenge.Object.Button
import edu.csh.chase.ChipsChallenge.Engine.*


class RedButton(location: Point, uniqueId: Id, target: Id?) :
        Button(RED_BUTTON_TYPE_ID, location, redButtonImage, target, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT), null) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.PASSOVER
    }

    override fun onTick(engine: Engine) {
    }

    override fun trigger(engine: Engine) {
        if (target == null) {
            return
        }
        if (!triggered) {
            triggered = true
            val cloner = engine.getEngineObjectBase(target!!)
            if (cloner == null || cloner !is Cloner) {
                return
            }
            cloner.clone(engine)
        }
    }

    override fun offTrigger(engine: Engine) {
        triggered = false
    }

}