package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.FIRE_TYPE_ID
import ChipsChallenge.Map.Tile

/**
 * Created by chase on 3/6/15.
 */
class Fire(location: Point, uniqueId: Id) : Tile(fireImage, FIRE_TYPE_ID, location, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
    }

}