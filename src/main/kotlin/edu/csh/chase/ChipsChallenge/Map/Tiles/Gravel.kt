package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Map.GRAVEL_TYPE_ID
import edu.csh.chase.ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Engine.*

/**
 * Created by chase on 5/4/15.
 */
class Gravel(location: Point, uniqueId: Id) : Tile(gravelImage, GRAVEL_TYPE_ID, location, uniqueId) {

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {

    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
    }

}