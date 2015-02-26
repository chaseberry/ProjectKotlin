package ChipsChallenge.Map

import ChipsChallenge.Engine.ObjectBase
import java.awt.image.BufferedImage
import ChipsChallenge.Engine.Engine
import kotlin.properties.Delegates

/**
 * Created by chase on 2/25/15.
 */

fun mapFromIds(mapIds: Array<Array<Int>>, playerStart: Point): Map {
    return Map(Array(mapIds.size(), { x -> Array(mapIds[x].size(), { y -> tileIdToTile(mapIds[x][y]) }) }), playerStart)
}

data class Map internal (val map: Array<Array<Tile>>, val defaultPlayerLocation: Point) : ObjectBase(null) {

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

    public fun getUp(curLocation: Point): Tile? {
        if (curLocation.y == 0) {
            return null;
        }
        return map[curLocation.x][curLocation.y - 1]
    }

    public fun getDown(curLocation: Point): Tile? {
        if (curLocation.y == (y - 1)) {
            return null
        }
        return map[curLocation.x][curLocation.y + 1]
    }

    public fun getLeft(curLocation: Point): Tile? {
        if (curLocation.x == 0) {
            return null
        }
        return map[curLocation.x - 1][curLocation.y]
    }

    public fun getRight(curLocation: Point): Tile? {
        if (curLocation.x == (x - 1)) {
            return null
        }
        return map[curLocation.x + 1][curLocation.y]
    }

    override fun onTick(engine: Engine) {
        for (x in map) {
            for (tile in x) {
                tile.onTick(engine)
            }
        }
    }


}