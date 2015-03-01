package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.socketImage

/**
 * Created by chase on 2/27/15.
 */
class Socket(location: Point) : ObjectBase(1, location, socketImage) {
    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        if (engine.player.inventory.chipsCollected >= engine.map.chipTotal) {
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING

    }

    override fun onTick(engine: Engine) {
    }

}