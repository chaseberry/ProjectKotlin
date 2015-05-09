package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.THIEF_TYPE_ID
import ChipsChallenge.Map.Tile
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 2/25/15.
 */

class Thief(location: Point, uniqueId: Id) : Tile(thiefImage, THIEF_TYPE_ID, location, uniqueId) {

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        if (interactor is Player) {
            interactor.inventory.removeBoots()
        }
    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {

    }

}