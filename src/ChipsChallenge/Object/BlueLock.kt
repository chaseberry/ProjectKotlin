package ChipsChallenge.Object

import ChipsChallenge.Engine.Point
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.blueKLockImage
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 2/27/15.
 */
class BlueLock(location: Point) : ObjectBase(3, location, blueKLockImage) {

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player && engine.player.inventory.blueKeys > 0) {
            engine.player.inventory.blueKeys--
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {

    }

}