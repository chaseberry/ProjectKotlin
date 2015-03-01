package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.dirtImage
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 2/28/15.
 */
class Dirt(location: Point) : ObjectBase(10, location, dirtImage) {
    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player) {
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {
    }

}