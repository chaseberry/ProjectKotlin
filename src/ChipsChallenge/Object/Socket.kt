package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.socketImage
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 2/27/15.
 */
class Socket(location: Point) : ObjectBase(1, location, socketImage) {
    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player && engine.player.inventory.chipsCollected >= engine.map.chipTotal) {
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING

    }

    override fun onTick(engine: Engine) {
    }

}