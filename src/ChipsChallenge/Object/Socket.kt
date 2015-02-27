package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution

/**
 * Created by chase on 2/27/15.
 */
class Socket(location: Point) : ObjectBase(location, "socket.gif") {
    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        if (engine.player.inventory.chipsCollected >= engine.map.chipTotal) {
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING

    }

    override fun onTick(engine: Engine) {
    }

}