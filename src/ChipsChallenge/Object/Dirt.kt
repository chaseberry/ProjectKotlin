package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.dirtImage

/**
 * Created by chase on 2/28/15.
 */
class Dirt(location: Point) : ObjectBase(10, location, dirtImage) {
    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {
    }

}