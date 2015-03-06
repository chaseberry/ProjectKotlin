package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.greenKeyImage
import ChipsChallenge.Engine.UnitBase

/**
 * Created by chase on 2/27/15.
 */
class GreenKey(location: Point) : ObjectBase(8, location, greenKeyImage) {

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        engine.player.inventory.hasGreenKey = true
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {

    }

}