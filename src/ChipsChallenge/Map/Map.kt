package ChipsChallenge.Map

import ChipsChallenge.Engine.ObjectBase
import java.awt.image.BufferedImage
import ChipsChallenge.Engine.Engine
import kotlin.properties.Delegates

/**
 * Created by chase on 2/25/15.
 */

fun mapFromIds(mapIds: Array<Array<Int>>): Map {
    return Map(Array(mapIds.size(), { x -> Array(mapIds[x].size(), { y -> tileIdToTile(mapIds[x][y]) }) }))
}

data class Map internal (val map: Array<Array<Tile>>) : ObjectBase(null) {

    public val x: Int by Delegates.lazy {
        map.size()
    }
    
    public val y: Int by Delegates.lazy {
        map[0].size()
    }

    public override val image: BufferedImage
        get() {
            val img = BufferedImage(32 * x, 32 * y, BufferedImage.TYPE_INT_ARGB)
            val g = img.getGraphics()
            for ( x in 0..(map.size() - 1)) {
                for (y in 0..(map[x].size() - 1)) {
                    g.drawImage(map[x][y].image, x * 32, y * 32, null)
                }
            }
            return img
        }

    public fun getUp(curX: Int, curY: Int): Tile? {
        if (curY == 0) {
            return null;
        }
        return map[curX][curY - 1]
    }

    public fun getDown(curX: Int, curY: Int): Tile? {
        if (curY == (y - 1)) {
            return null
        }
        return map[curX][curY + 1]
    }

    public fun getLeft(curX: Int, curY: Int): Tile? {
        if (curX == 0) {
            return null
        }
        return map[curX - 1][curY]
    }

    public fun getRight(curX: Int, curY: Int): Tile? {
        if (curX == (x - 1)) {
            return null
        }
        return map[curX + 1][curY]
    }

    override fun onTick(engine: Engine) {
        for (x in map) {
            for (tile in x) {
                tile.onTick(engine)
            }
        }
    }


}