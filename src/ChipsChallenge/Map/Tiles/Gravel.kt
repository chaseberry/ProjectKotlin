package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.GRAVEL_TYPE_ID
import ChipsChallenge.Map.Tile

/**
 * Created by chase on 5/4/15.
 */
class Gravel(location: Point, uniqueId: Id) : Tile(gravelImage, GRAVEL_TYPE_ID, location, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
    }

}