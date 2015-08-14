package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.WATER_TYPE_ID
import ChipsChallenge.Unit.Player

class Water(location: Point, uniqueId: Id) : Tile(waterImage, WATER_TYPE_ID, location, uniqueId) {

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        if (!interactor.canSurviveInWater()) {
            if (interactor is Player) {
                engine.lose()
            } else {
                engine.unitManager.kill(interactor)
            }
        }
    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {

    }

    override fun getInspectionData(): Inspection {
        return Inspection("Finish", "The game ends here.")
    }

}