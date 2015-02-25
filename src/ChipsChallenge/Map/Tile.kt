package ChipsChallenge.Map

import ChipsChallenge.Engine.ObjectBase

/**
 * Created by chase on 2/25/15.
 */

abstract class Tile(imageSource: String, id: Int) : ObjectBase(imageSource) {

    val tileId = id

    override fun equals(other: Any?): Boolean {
        if (other != null && other is Tile) {
            return other.tileId == tileId
        }
        return false;
    }

}
