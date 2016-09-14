package edu.csh.chase.ChipsChallenge.Map.Tiles

import edu.csh.chase.ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Map.WALL_TYPE_ID
import edu.csh.chase.ChipsChallenge.Engine.*

class Wall(location: Point, uniqueId: Id) : Tile(wallImage, WALL_TYPE_ID, location, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
    }

}