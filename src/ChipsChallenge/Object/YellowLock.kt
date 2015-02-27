package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution

/**
 * Created by chase on 2/27/15.
 */
class YellowLock(location: Point) : ObjectBase(location, "yellow_lock.gif") {

    override fun onTick(engine: Engine) {

    }

    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        if (engine.player.inventory.yellowKeys > 0) {
            engine.player.inventory.yellowKeys--
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

}