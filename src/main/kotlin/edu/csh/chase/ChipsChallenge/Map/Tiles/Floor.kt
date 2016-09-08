package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.FLOOR_TYPE_ID
import ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Engine.*

/**
 * Created by chase on 2/25/15.
 */

class Floor(location: Point, uniqueId: Id) : Tile(floorImage, FLOOR_TYPE_ID, location, uniqueId) {

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {

    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {

    }

}