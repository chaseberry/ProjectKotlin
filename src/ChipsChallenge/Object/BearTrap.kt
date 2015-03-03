package ChipsChallenge.Object

import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.bearTrapImage
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.Triggerable

/**
 * Created by chase on 3/2/15.
 */
class BearTrap(location: Point, var isActive: Boolean = true) : ObjectBase(14, location, bearTrapImage), Triggerable {

    override fun onTrigger() {
        isActive = false
    }

    override fun offTrigger() {
        isActive = true
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.PASSOVER
    }

    override fun onTick(engine: Engine) {
    }

}