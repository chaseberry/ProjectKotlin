package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Point
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.redLockImage
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 2/26/15.
 */
class RedLock(location: Point) : ObjectBase(5, location, redLockImage) {

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player && engine.player.inventory.redKeys > 0) {
            engine.player.inventory.redKeys--
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {

    }

}