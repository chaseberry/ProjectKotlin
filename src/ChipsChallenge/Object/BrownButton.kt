package ChipsChallenge.Object

import ChipsChallenge.Engine.Point
import ChipsChallenge.Engine.brownButtonImage
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.ObjectResolution

/**
 * Created by chase on 3/2/15.
 */
class BrownButton(location: Point) : Button(13, location, brownButtonImage, null) {

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.TRIGGER
    }

    override fun onTick(engine: Engine) {
    }

}