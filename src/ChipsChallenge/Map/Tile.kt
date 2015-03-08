package ChipsChallenge.Map

import ChipsChallenge.Map.Tiles.*
import ChipsChallenge.Engine.EngineObjectBase
import java.awt.image.BufferedImage
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.Engine.Point

/**
 * Created by chase on 2/25/15.
 */

public fun tileIdToTile(id: Int, location:Point): Tile {
    return when (id) {
        0 -> Floor(location)
        1 -> Wall(location)
        2 -> Finish(location)
        3 -> Help(location)
        4 -> Water(location)
        5 -> Fire(location)
        else -> Wall(location)
    }
}

data abstract class Tile(val image: BufferedImage, val  tileId: Int, location:Point) : EngineObjectBase(location) {

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
