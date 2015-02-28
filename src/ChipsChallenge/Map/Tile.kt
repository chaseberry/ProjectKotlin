package ChipsChallenge.Map

import ChipsChallenge.Map.Tiles.*
import ChipsChallenge.Engine.loadImage
import ChipsChallenge.Engine.EngineObjectBase

/**
 * Created by chase on 2/25/15.
 */

public fun tileIdToTile(id: Int): Tile {
    return when (id) {
        0 -> Floor()
        1 -> Wall()
        2 -> Finish()
        3 -> Help()
        else -> Wall()
    }
}

data abstract class Tile(imageSource: String, id: Int) : EngineObjectBase {

    val tileId = id
    val image = loadImage(imageSource)

    override fun equals(other: Any?): Boolean {
        if (other != null && other is Tile) {
            return other.tileId == tileId
        }
        return false;
    }

}
