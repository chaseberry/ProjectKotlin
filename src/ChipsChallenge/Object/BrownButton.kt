package ChipsChallenge.Object

import ChipsChallenge.Engine.*

/**
 * Created by chase on 3/2/15.
 */
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