package ChipsChallenge.Map

import ChipsChallenge.Map.Tiles.*
import ChipsChallenge.Engine.EngineObjectBase
import java.awt.image.BufferedImage
import ChipsChallenge.JSON.JSONObject

/**
 * Created by chase on 2/25/15.
 */

public fun tileIdToTile(id: Int): Tile {
    return when (id) {
        0 -> Floor()
        1 -> Wall()
        2 -> Finish()
        3 -> Help()
        4 -> Water()
        else -> Wall()
    }
}

data abstract class Tile(val image: BufferedImage, val  tileId: Int) : EngineObjectBase {

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
