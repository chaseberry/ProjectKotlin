package ChipsChallenge.Map

import java.awt.image.BufferedImage
import ChipsChallenge.Engine.Engine
import kotlin.properties.Delegates
import ChipsChallenge.Engine.EngineObjectBase
import ChipsChallenge.UI.Viewport

/**
 * Created by chase on 2/25/15.
 */

fun mapFromIds(mapIds: Array<Array<Int>>, playerStart: Point, engine: Engine): Map {
    return Map(Array(mapIds.size(), { x -> Array(mapIds[x].size(), { y -> tileIdToTile(mapIds[x][y]) }) }), playerStart, engine, 0)
}

data class Map internal (val map: Array<Array<Tile>>, val defaultPlayerLocation: Point, val engine: Engine,
                         val chipTotal: Int) : EngineObjectBase {

    val x: Int by Delegates.lazy {
        map.size()
    }

    val y: Int by Delegates.lazy {
        map[0].size()
    }

    public fun getImage(viewport: Viewport): BufferedImage {
        val img = BufferedImage(32 * 9, 32 * 9, BufferedImage.TYPE_INT_ARGB)
        val g = img.getGraphics()
        var xDraw = 0
        for ( x in viewport.xStart..viewport.xEnd) {
            var yDraw = 0
            for (y in viewport.yStart..viewport.yEnd) {
                g.drawImage(map[x][y].image, xDraw * 32, yDraw * 32, null)
                yDraw++
            }
            xDraw++
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

    public fun getTile(location: Point): Tile? {
        if (location.x !in 0..(x - 1 ) || location.y !in 0..(y - 1)) {
            return null
        }
        return map[location.x][location.y]
    }

    override fun onTick(engine: Engine) {
        for (x in map) {
            for (tile in x) {
                tile.onTick(engine)
            }
        }
    }


}