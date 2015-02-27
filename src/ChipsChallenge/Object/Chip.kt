package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution

/**
 * Created by chase on 2/27/15.
 */
class Chip(location: Point) : ObjectBase(location, "chip.gif") {

    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        engine.player.inventory.chipsCollected++
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {
    }

}