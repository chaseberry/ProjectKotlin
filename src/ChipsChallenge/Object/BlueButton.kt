package ChipsChallenge.Object

import ChipsChallenge.Engine.*


class BlueButton(location: Point, uniqueId: Id) : Button(BLUE_BUTTON_TYPE_ID, location, blueButtonImage, null, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.PASSOVER
    }

    override fun onTick(engine: Engine) {
    }

    override fun trigger(engine: Engine) {
        if (!triggered) {
            triggered = true
            engine.unitManager.getAllTanks().forEach { it.startMove() }
        }
    }

    override fun offTrigger(engine: Engine) {
        triggered = false
    }

    override fun getInspectionData(): Inspection {
        return Inspection("Finish", "The game ends here.")
    }

}