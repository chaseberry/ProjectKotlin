package ChipsChallenge.Object

import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution

/**
 * Created by chase on 2/27/15.
 */
class BlueLock(location: Point) : ObjectBase(location, "blue_lock.gif") {

    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        if (engine.player.inventory.blueKeys > 0) {
            engine.player.inventory.blueKeys--
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {

    }

}