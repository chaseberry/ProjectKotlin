package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Engine.Direction
import edu.csh.chase.ChipsChallenge.Engine.Engine
import edu.csh.chase.ChipsChallenge.Engine.Point
import edu.csh.chase.ChipsChallenge.Engine.UnitBase

class BrownButton(location: Point, uniqueId: Id, target: Id?) :
        Button(BROWN_BUTTON_TYPE_ID, location, brownButtonImage, target, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT), null) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.PASSOVER
    }

    override fun onTick(engine: Engine) {
    }

}