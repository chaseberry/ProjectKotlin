package edu.csh.chase.ChipsChallenge.Map.Tiles

import edu.csh.chase.ChipsChallenge.Map.ICE_TYPE_ID
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Map.Tiles.IceBase

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