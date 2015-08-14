package ChipsChallenge.Object

import ChipsChallenge.Engine.*

class BrownButton(location: Point, uniqueId: Id, target: Id?) :
        Button(BROWN_BUTTON_TYPE_ID, location, brownButtonImage, target, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT), null) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.PASSOVER
    }

    override fun onTick(engine: Engine) {
    }

    override fun getInspectionData(): Inspection {
        return Inspection("Finish", "The game ends here.")
    }

}