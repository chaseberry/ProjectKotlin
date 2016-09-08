package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Engine.*

class GreenButton(location: Point, uniqueId: Id) : Button(GREEN_BUTTON_TYPE_ID, location, greenButtonImage, null, uniqueId) {

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
            val tiles = engine.map.getAllToggleWalls()
            tiles.forEach { it.onTrigger() }
        }
    }

    override fun offTrigger(engine: Engine) {
        triggered = false
    }

}