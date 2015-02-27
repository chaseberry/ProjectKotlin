package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution

/**
 * Created by chase on 2/27/15.
 */
class GreenKey(location: Point) : ObjectBase(location, "green_key.gif") {

    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        engine.player.inventory.hasGreenKey = true
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {

    }

}