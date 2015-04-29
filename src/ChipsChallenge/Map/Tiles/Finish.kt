package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.FINISH_TYPE_ID
import ChipsChallenge.Map.Tile

class Finish(location: Point, uniqueId: Id) : Tile(finishImage, FINISH_TYPE_ID, location, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
    }

}