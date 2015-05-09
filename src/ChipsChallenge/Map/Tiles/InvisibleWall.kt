package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.INVISIBLE_WALL_ID
import ChipsChallenge.Map.Tile


class InvisibleWall(location: Point, uniqueId: Id) : Tile(floorImage, INVISIBLE_WALL_ID, location, uniqueId) {

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {

    }

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
    }
}