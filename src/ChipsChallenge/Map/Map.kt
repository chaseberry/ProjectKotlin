package ChipsChallenge.Map

import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.EngineObjectBase
import ChipsChallenge.Engine.Id
import ChipsChallenge.Engine.Point
import ChipsChallenge.JSON.JSONArray
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.Map.Tiles.InvisibleWall
import ChipsChallenge.Map.Tiles.RevealableWall
import ChipsChallenge.Map.Tiles.Teleport
import ChipsChallenge.Map.Tiles.ToggleWall
import ChipsChallenge.UI.Viewport
import java.awt.Color
import java.awt.image.BufferedImage
import java.util.ArrayList
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

//**Test
fun ArrayList<ToggleWall>.invoke(tw: ToggleWall) {
    this.add(tw)
}

//**

data class Map internal (val map: Array<Array<Tile>>, var defaultPlayerLocation: Point,
                         var chipTotal: Int) : EngineObjectBase(Point(0, 0), null) {

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

    public fun getEditorImage(viewport: Viewport): BufferedImage {
        val img = BufferedImage(32 * 9, 32 * 9, BufferedImage.TYPE_INT_ARGB)
        val g = img.getGraphics()
        var xDraw = 0
        for ( x in viewport.xStart..viewport.xEnd) {
            var yDraw = 0
            for (y in viewport.yStart..viewport.yEnd) {
                g.drawImage(map[x][y].image, xDraw * 32, yDraw * 32, null)
                if (map[x][y] is InvisibleWall) {
                    g.setColor(Color.BLACK)
                    g.drawString("X", xDraw * 32 + 16, yDraw * 32 + 16)
                }
                if (map[x][y] is RevealableWall) {
                    g.setColor(Color.BLACK)
                    g.drawString("R", xDraw * 32 + 16, yDraw * 32 + 16)
                }
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

    fun getAllToggleWalls(): ArrayList<ToggleWall> {
        val walls = ArrayList<ToggleWall>()
        map.forEach { it.forEach { if (it is ToggleWall) walls(it) } }
        return walls
    }

    fun findNextTeleport(entrance: Teleport): Teleport? {
        //start at teleports point, go from location.x->0 then --y until 0,0
        //go from map.x -1 , map.y -1 until back to entrance.location
        for (z in entrance.location.x downTo 0) {
            if (map[z][entrance.location.y] == entrance) {
                continue
            }
            if (map[z][entrance.location.y] is Teleport) {
                return map[z][entrance.location.y] as Teleport
            }
        }
        for (v in (entrance.location.y - 1) downTo 0) {
            for (z in (x - 1) downTo 0) {
                if (map[z][v] == entrance) {
                    continue
                }
                if (map[z][v] is Teleport) {
                    return map[z][v] as Teleport
                }
            }
        }
        for (v in (y - 1) downTo entrance.location.y) {
            for (z in (x - 1) downTo entrance.location.x) {
                if (map[z][v] == entrance) {
                    continue
                }
                if (map[z][v] is Teleport) {
                    return map[z][v] as Teleport
                }
            }
        }
        return null
    }

}