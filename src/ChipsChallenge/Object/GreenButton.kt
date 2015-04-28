package ChipsChallenge.Object

import ChipsChallenge.Engine.*

/**
 * Created by chase on 3/2/15.
 */
class GreenButton(location: Point, uniqueId: Id) : Button(GREEN_BUTTON_TYPE_ID, location, greenButtonImage, null, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.TRIGGER
    }

    override fun onTick(engine: Engine) {
    }

}