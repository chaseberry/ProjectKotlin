package edu.csh.chase.ChipsChallenge.Map.Tiles

import edu.csh.chase.ChipsChallenge.Map.FLOOR_TYPE_ID
import edu.csh.chase.ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Engine.*

/**
 * Created by chase on 2/25/15.
 */

class Floor(location: Point, uniqueId: Id) : Tile(floorImage, FLOOR_TYPE_ID, location, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {

    }

}