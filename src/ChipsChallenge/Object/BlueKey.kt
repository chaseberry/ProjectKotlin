package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.blueKeyImage
import ChipsChallenge.Engine.UnitBase

/**
 * Created by chase on 2/27/15.
 */
class BlueKey(location: Point) : ObjectBase(2, location, blueKeyImage) {

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        engine.player.inventory.blueKeys++
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {

    }

}