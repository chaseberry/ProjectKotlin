package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Map.FINISH_TYPE_ID
import edu.csh.chase.ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

class Finish(location: Point, uniqueId: Id) : Tile(finishImage, FINISH_TYPE_ID, location, uniqueId) {

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        if (interactor is Player) {
            engine.win()
        }
    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
    }

}