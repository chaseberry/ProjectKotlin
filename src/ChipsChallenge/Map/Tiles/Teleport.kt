package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.TELEPORT_TYPE_ID
import ChipsChallenge.Map.Tile

/**
 * Created by chase on 5/3/15.
 */
class Teleport(location: Point, uniqueId: Id) : Tile(teleportImage, TELEPORT_TYPE_ID, location, uniqueId) {

    var arriving = false

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
        if (engine.player.location != location) {
            arriving = false
        }
    }

}