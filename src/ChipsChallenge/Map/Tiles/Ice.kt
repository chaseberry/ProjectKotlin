package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.ICE_TYPE_ID

/**
 * Created by chase on 3/8/15.
 */
open class Ice(location: Point, uniqueId: Id) : IceBase(iceImage, ICE_TYPE_ID, location, uniqueId) {


    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    override fun getNewDirection(direction: Direction): Direction {
        return direction
    }

    override fun onTick(engine: Engine) {

    }

}