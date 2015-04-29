package ChipsChallenge.Map

import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.EngineObjectBase
import ChipsChallenge.Engine.Id
import ChipsChallenge.Engine.Point
import ChipsChallenge.JSON.JSONArray
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.UI.Viewport
import java.awt.image.BufferedImage
import kotlin.properties.Delegates

/**
 * Created by chase on 2/25/15.
 */

fun mapFromIds(mapIds: Array<Array<Int>>, playerStart: Point, chipTotal: Int): Map {
    return Map(Array(mapIds.size(), { x -> Array(mapIds[x].size(), { y -> tileIdToTile(mapIds[x][y], Point(x, y)) }) }), playerStart, chipTotal)
}

fun blankMap(x: Int, y: Int): Map {
    return Map(Array(x) { Array(y) { tileIdToTile(0, Point(x, y)) } }, Point(0, 0), 0)
}

fun mapFromJSON(mapData: JSONObject): Map? {
    try {
        val mapArray = mapData.getJSONArray("map")
        val tileArray = Array(mapArray.length()) { x ->
            Array(mapArray.getJSONArray(x).length()) { y ->
                tileFromJson(
                        mapArray.getJSONArray(x).getJSONObject(y),
                        Point(x, y)
                )
            }
        }

        val defaultLocation = Point(mapData.getJSONArray("playerStartLocation").getInt(0),
                mapData.getJSONArray("playerStartLocation").getInt(1))

        val chipCount = mapData.getInt("chipCount")

        return Map(tileArray, defaultLocation, chipCount)

    } catch(except: Exception) {
        except.printStackTrace()
        return null
    }

}

data class Map internal (val map: Array<Array<Tile>>, var defaultPlayerLocation: Point,
                         var chipTotal: Int) : EngineObjectBase(Point(0, 0)) {

    override fun getSaveObject(): JSONObject {
        val mapArray = JSONArray()
        for (x in map) {
            val mapSection = JSONArray()
            for (tile in x) {
                mapSection.put(tile.getSaveObject())
            }
            mapArray.put(mapSection)
        }
        return JSONObject().put("map", mapArray)
    }

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

    public fun getById(id: Id): Tile? {
        map.forEach { it.forEach { if ( it.uniqueId == id) return it } }
        return null
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

    public fun setTile(location: Point, tile: Tile) {
        if (location.x !in 0..(x - 1 ) || location.y !in 0..(y - 1)) {
            return
        }
        map[location.x][location.y] = tile
    }

    override fun onTick(engine: Engine) {
        for (x in map) {
            for (tile in x) {
                tile.onTick(engine)
            }
        }
    }


}