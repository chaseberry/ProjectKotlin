package ChipsChallenge.Map

import ChipsChallenge.Engine.EngineObjectBase
import ChipsChallenge.Engine.Id
import ChipsChallenge.Engine.IdType
import ChipsChallenge.Engine.Point
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.Map.Tiles.*
import java.awt.image.BufferedImage

/**
 * Created by chase on 2/25/15.
 */

public fun tileIdToTile(id: Int, location: Point): Tile {
    return when (id) {
        0 -> Floor(location)
        1 -> Wall(location)
        2 -> Finish(location)
        3 -> Help(location)
        4 -> Water(location)
        5 -> Fire(location)
        6 -> Ice(location)
        else -> Wall(location)
    }
}

data abstract class Tile(val image: BufferedImage, val  tileId: Int, location: Point,
                         val uniqueId: Id = Id(IdType.TILE)) : EngineObjectBase(location) {

    override fun equals(other: Any?): Boolean {
        if (other != null && other is Tile) {
            return other.tileId == tileId
        }
        return false;
    }

    override fun getSaveObject(): JSONObject {
        return JSONObject().put("id", tileId)
    }

}
