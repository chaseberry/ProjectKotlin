package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.TELEPORT_TYPE_ID
import ChipsChallenge.Map.Tile

/**
 * Created by chase on 5/3/15.
 */
class Teleport(location: Point, uniqueId: Id) : Tile(teleportImage, TELEPORT_TYPE_ID, location, uniqueId) {

    var arriving = false

    //TODO implement teleporting or objects(Blocks)
    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
        if (arriving && engine.player.location != location && !engine.unitManager.isUnitOnPoint(location)) {
            arriving = false
        }
    }

}