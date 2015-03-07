package ChipsChallenge.Object

import ChipsChallenge.Engine.Point
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Unit.Player
import ChipsChallenge.Engine.flippersImage

/**
 * Created by chase on 3/7/15.
 */
class Flipper(location: Point) : ObjectBase(16, location, flippersImage) {
    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player) {
            interactor.inventory.hasFlippers = true
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.PASSOVER
    }

    override fun onTick(engine: Engine) {
        throw UnsupportedOperationException()
    }

}