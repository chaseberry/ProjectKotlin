package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.HELP_TYPE_ID
import ChipsChallenge.Map.Tile

/**
 * Created by chase on 2/28/15.
 */
class Help(location: Point, uniqueId: Id) : Tile(helpImage, HELP_TYPE_ID, location, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
    }

}