package ChipsChallenge.Object

import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.greenLockImage

/**
 * Created by chase on 2/27/15.
 */
class GreenLock(location: Point) : ObjectBase(9, location, greenLockImage) {

    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        if (engine.player.inventory.hasGreenKey) {
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {

    }

}