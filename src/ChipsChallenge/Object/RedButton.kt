package ChipsChallenge.Object

import ChipsChallenge.Engine.*


class RedButton(location: Point, uniqueId: Id) : Button(RED_BUTTON_TYPE_ID, location, redButtonImage, null, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.PASSOVER
    }

    override fun onTick(engine: Engine) {
    }

    override fun trigger(engine: Engine) {
        if (!triggered) {
            triggered = true
            engine.unitManager.getAllTanks().forEach { it.startMove() }
        }
    }

    override fun offTrigger(engine: Engine) {
        triggered = false
    }

}