package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.yellowLockImage
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 2/27/15.
 */
class YellowLock(location: Point) : ObjectBase(7, location, yellowLockImage) {

    override fun onTick(engine: Engine) {

    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player && engine.player.inventory.yellowKeys > 0) {
            engine.player.inventory.yellowKeys--
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

}