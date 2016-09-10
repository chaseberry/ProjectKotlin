package edu.csh.chase.ChipsChallenge.Map

import ChipsChallenge.Map.Tiles.Revealable
import ChipsChallenge.Map.Tiles.Teleport
import ChipsChallenge.Map.Tiles.ToggleWall
import edu.csh.chase.ChipsChallenge.Engine.Engine
import edu.csh.chase.ChipsChallenge.Engine.Id
import edu.csh.chase.ChipsChallenge.Engine.Point
import edu.csh.chase.ChipsChallenge.UI.Viewport
import edu.csh.chase.kjson.JsonArray
import java.awt.image.BufferedImage
import java.util.*

fun mapFromIds(mapIds: Array<Array<Int>>, playerStart: Point, chipTotal: Int): Map {
    return Map(Array(mapIds.size, { x -> Array(mapIds[x].size, { y -> tileIdToTile(mapIds[x][y], Point(x, y)) }) }))
}

fun blankMap(x: Int, y: Int): Map {
    return Map(Array(x) { Array(y) { tileIdToTile(0, Point(x, y)) } })
}

fun mapFromJSON(mapData: JsonArray): Map? {
    try {
        val tileArray = Array(mapData.size) { x ->
            Array(mapData.getJsonArray(x).size) { y ->
                tileFromJson(
                        mapData.getJsonArray(x).getJsonObject(y),
                        Point(x, y)
                )
            }
        }

        return Map(tileArray)

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

data class Map(val map: Array<Array<Tile>>) {

    fun getSaveObject(): JsonArray {
        val mapArray = JsonArray()
        for (x in map) {
            val mapSection = JsonArray()
            for (tile in x) {
                mapSection.put(tile.getSaveObject())
            }
            mapArray.put(mapSection)
        }
        return mapArray
    }

    val x: Int by lazy {
        map.size
    }

    val y: Int by lazy {
        map[0].size
    }


    fun getImage(viewport: Viewport): BufferedImage {
        val img = BufferedImage(32 * 9, 32 * 9, BufferedImage.TYPE_INT_ARGB)
        val g = img.graphics
        var xDraw = 0
        for (x in viewport.xStart..viewport.xEnd) {
            var yDraw = 0
            for (y in viewport.yStart..viewport.yEnd) {
                g.drawImage(map[x][y].image, xDraw * 32, yDraw * 32, null)
                yDraw++
            }
            xDraw++
        }
        return img
    }

    fun getEditorImage(viewport: Viewport): BufferedImage {
        val img = BufferedImage(32 * 9, 32 * 9, BufferedImage.TYPE_INT_ARGB)
        val g = img.graphics
        var xDraw = 0
        for (x in viewport.xStart..viewport.xEnd) {
            var yDraw = 0
            for (y in viewport.yStart..viewport.yEnd) {
                if (map[x][y] is Revealable) {
                    g.drawImage((map[x][y] as Revealable).getEditorImage(), xDraw * 32, yDraw * 32, null)
                } else {
                    g.drawImage(map[x][y].image, xDraw * 32, yDraw * 32, null)
                }
                yDraw++
            }
            xDraw++
        }
        return img
    }

    fun getById(id: Id): Tile? {
        map.forEach { it.forEach { if ( it.uniqueId == id) return it } }
        return null
    }

    fun getUp(curLocation: Point): Tile? {
        if (curLocation.y == 0) {
            return null;
        }
        return map[curLocation.x][curLocation.y - 1]
    }

    fun getDown(curLocation: Point): Tile? {
        if (curLocation.y == (y - 1)) {
            return null
        }
        return map[curLocation.x][curLocation.y + 1]
    }

    fun getLeft(curLocation: Point): Tile? {
        if (curLocation.x == 0) {
            return null
        }
        return map[curLocation.x - 1][curLocation.y]
    }

    fun getRight(curLocation: Point): Tile? {
        if (curLocation.x == (x - 1)) {
            return null
        }
        return map[curLocation.x + 1][curLocation.y]
    }

    fun getTile(location: Point): Tile? {
        if (location.x !in 0..(x - 1 ) || location.y !in 0..(y - 1)) {
            return null
        }
        return map[location.x][location.y]
    }

    fun setTile(location: Point, tile: Tile) {
        if (location.x !in 0..(x - 1 ) || location.y !in 0..(y - 1)) {
            return
        }
        map[location.x][location.y] = tile
    }

    fun onTick(engine: Engine) {
        for (x in map) {
            for (tile in x) {
                tile.onTick(engine)
            }
        }
    }

    fun getAllToggleWalls(): ArrayList<ToggleWall> {
        val walls = ArrayList<ToggleWall>()
        map.forEach { it.forEach { if (it is ToggleWall) walls.add(it) } }
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

    fun copy(): Map {
        return Map(Array(x) { newX ->
            Array(y) { newY ->
                val tile = map[newX][newY]
                var open = false
                if (tile is ToggleWall) {
                    open = tile.open
                }
                tileFromId(tile.tileId, Point(newX, newY), open = open)
            }
        })
    }

}