package ChipsChallenge.Map

import ChipsChallenge.Engine.ObjectBase
import java.awt.image.BufferedImage

/**
 * Created by chase on 2/25/15.
 */

fun map(x: Int, y: Int, mapIds: Array<Array<Int>>): Map {
    return Map(Array(x, { Array(y, { tileIdToTile(it) }) }))
}

data class Map internal (val map: Array<Array<Tile>>) : ObjectBase("") {

    public override val image: BufferedImage
        get() {
            val img = BufferedImage(32 * 9, 32 * 9, BufferedImage.TYPE_INT_ARGB)
            val g = img.getGraphics()
            //TODO finish
            return img
        }

    override fun onTick() {

    }


}