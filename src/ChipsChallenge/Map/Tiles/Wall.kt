package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.WALL_TYPE_ID

/**
 * Created by chase on 2/25/15.
 */
class Wall(location: Point, uniqueId: Id) : Tile(wallImage, WALL_TYPE_ID, location, uniqueId) {

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {

    }

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
    }
}