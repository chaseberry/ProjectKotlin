package ChipsChallenge.Map

import ChipsChallenge.Engine.ObjectBase
import java.awt.image.BufferedImage

/**
 * Created by chase on 2/25/15.
 */

fun mapFromIds(mapIds: Array<Array<Int>>): Map {
    return Map(Array(mapIds.size(), { x -> Array(mapIds[x].size(), { y -> tileIdToTile(mapIds[x][y]) }) }))
}

data class Map internal (val map: Array<Array<Tile>>) : ObjectBase(null) {

    public override val image: BufferedImage
        get() {
            val img = BufferedImage(32 * 9, 32 * 9, BufferedImage.TYPE_INT_ARGB)
            val g = img.getGraphics()
            for ( x in 0..(map.size() - 1)) {
                for (y in 0..(map[x].size() - 1)) {
                    g.drawImage(map[x][y].image, x * 32, y * 32, null)
                }
            }
            return img
        }

    override fun onTick() {
        for (x in map) {
            for (tile in x) {
                tile.onTick()
            }
        }
    }


}