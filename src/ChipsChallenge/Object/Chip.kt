package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import ChipsChallenge.Unit.Player

class Chip(location: Point, uniqueId: Id) : ObjectBase(CHIP_TYPE_ID, location, chipImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
        
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor is Player) {
            engine.player.inventory.chipsCollected++
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {
    }

    override fun getInspectionData(): Inspection {
        return Inspection("Finish", "The game ends here.")
    }

}