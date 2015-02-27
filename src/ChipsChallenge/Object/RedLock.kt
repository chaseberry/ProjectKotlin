package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution

/**
 * Created by chase on 2/26/15.
 */
class RedLock(location: Point, imageSource: String?) : ObjectBase(location, imageSource) {

    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        if (engine.player.inventory.redKeys > 0) {
            engine.player.inventory.redKeys--
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {

    }

}