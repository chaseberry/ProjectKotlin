package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.ICE_TYPE_ID
import edu.csh.chase.ChipsChallenge.Engine.Direction
import edu.csh.chase.ChipsChallenge.Engine.Engine
import edu.csh.chase.ChipsChallenge.Engine.Point

/**
 * Created by chase on 3/8/15.
 */
open class Ice(location: Point, uniqueId: Id) : IceBase(iceImage, ICE_TYPE_ID, location, uniqueId) {


    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun getNewDirection(direction: Direction): Direction {
        return direction
    }

    override fun onTick(engine: Engine) {

    }

}