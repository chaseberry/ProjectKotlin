package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.chipImage

/**
 * Created by chase on 2/27/15.
 */
class Chip(location: Point) : ObjectBase(0, location, chipImage) {

    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        engine.player.inventory.chipsCollected++
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {
    }

}