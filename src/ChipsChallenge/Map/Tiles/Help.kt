package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.HELP_TYPE_ID
import ChipsChallenge.Map.Tile
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 2/28/15.
 */
class Help(location: Point, uniqueId: Id) : Tile(helpImage, HELP_TYPE_ID, location, uniqueId) {
    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        if (interactor is Player) {
            //Display hint
        }
    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
    }

}