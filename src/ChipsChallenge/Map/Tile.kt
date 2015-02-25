package ChipsChallenge.Map

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Tiles.*

/**
 * Created by chase on 2/25/15.
 */

public fun tileIdToTile(id: Int): Tile {
    return when (id) {
        0 -> Floor()
        1 -> Wall()
        else -> Floor()
    }
}

data abstract class Tile(imageSource: String, id: Int) : ObjectBase(imageSource) {

    val tileId = id

    override fun equals(other: Any?): Boolean {
        if (other != null && other is Tile) {
            return other.tileId == tileId
        }
        return false;
    }

}
