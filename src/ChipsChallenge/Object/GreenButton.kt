package ChipsChallenge.Object

import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.greenButtonImage

/**
 * Created by chase on 3/2/15.
 */
class GreenButton(location: Point) : Button(12, location, greenButtonImage) {
    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.TRIGGER
    }

    override fun onTick(engine: Engine) {
    }

}