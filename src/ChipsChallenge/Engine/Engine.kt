package ChipsChallenge.Engine

import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.Map.Map
import ChipsChallenge.Map.Tiles.Floor
import ChipsChallenge.Map.Tiles.Teleport
import ChipsChallenge.Map.Tiles.Water
import ChipsChallenge.Map.mapFromJSON
import ChipsChallenge.Object.Block
import ChipsChallenge.Object.Button
import ChipsChallenge.Object.Dirt
import ChipsChallenge.UI.Frame
import ChipsChallenge.UI.getViewport
import ChipsChallenge.Unit.Player
import java.awt.image.BufferedImage
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.net.URI
import java.util.ArrayList
import java.util.Timer
import java.util.TimerTask

public enum class Direction {
    UP
    DOWN
    LEFT
    RIGHT
}

//
//TODO implemenet levels 1-9 http://strategywiki.org/wiki/Chip%27s_Challenge/Levels_1-20#Level_1:_LESSON_1
//

fun directionFromString(str: String?): Direction {
    return when (str) {
        "UP" -> Direction.UP
        "DOWN" -> Direction.DOWN
        "LEFT" -> Direction.LEFT
        "RIGHT" -> Direction.RIGHT
        else -> Direction.UP
    }
}

fun flipDirection(direction: Direction): Direction {
    return when (direction) {
        Direction.UP -> Direction.DOWN
        Direction.DOWN -> Direction.UP
        Direction.LEFT -> Direction.RIGHT
        Direction.RIGHT -> Direction.LEFT
    }
}

fun engineFromFile(file: File): Engine? {
    try {
        val reader = BufferedReader(FileReader(file))
        val fileContents = reader.readLine()
        reader.close()
        val levelData = JSONObject(fileContents)
        val map = mapFromJSON(levelData)
        val objs = levelData.getJSONArray("objects")
        val objects = ArrayList<ObjectBase>(objs.length())
        for (z in 0..objs.length() - 1) {
            //TODO null check
            objects.add(objectFromJSON(objs.getJSONObject(z)))
        }
        val uns = levelData.getJSONArray("units")
        val units = ArrayList<UnitBase>(uns.length())
        for ( z in 0..uns.length() - 1) {
            //TODO null check
            units.add(unitFromJson(uns.getJSONObject(z)))
        }
        if (map == null) {
            return null
        }
        return Engine(map, objects, units)
    } catch(e: Exception) {
        e.printStackTrace()
    }

    return null
}

fun loadLevel(name: String): File {
    return File(URI(fileUrl + "Levels/${name}.ccl"))
}

class Engine(val map: Map, objects: ArrayList<ObjectBase>, units: ArrayList<UnitBase>) {

    val gameTime: Long = 30

    val frame = Frame(this)

    //Current test map is just a test

    val objectManager = ObjectManager(this)

    val keyBindings = KeyBindings()

    val gameTimer = Timer()

    val player = Player(map.defaultPlayerLocation.copy())

    //Done so this can be passed around in the onTick method
    val engine = this

    val movement = Movement(this)

    val unitManager = UnitManager(this);

    init {
        for (obj in objects) {
            objectManager.objects.put(obj.location, obj)
        }

        unitManager.addAll(units)

    }

    public fun start() {
        frame.setVisible(true)
        frame.image = buildFrameImage()

        gameTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                map.onTick(engine)
                player.onTick(engine)
                unitManager.onTick(engine)
                objectManager.onTick(engine)
                frame.image = buildFrameImage()
                checkCollisions()
            }
        }, gameTime, gameTime)
    }

    fun getEngineObjectBase(id: Id): EngineObjectBase? {
        return when (id.type) {
            IdType.UNIT -> unitManager.getById(id)
            IdType.OBJECT -> objectManager.getById(id)
            IdType.TILE -> map.getById(id)
        }
    }

    fun checkCollisions() {
        if (unitManager.isUnitOnPoint(player.location)) {
            lose()
            return
        }

        objectManager.objects.forEach {
            val location = it.getKey()
            val obj = it.getValue()
            if (obj is Block && engine.map.getTile(location) is Water) {
                objectManager.objects.put(location, Dirt(location, obj.uniqueId))
                val waterTile = engine.map.getTile(location)!!
                engine.map.setTile(location, Floor(location, waterTile.uniqueId))
            }
            if (location == player.location && obj is Block) {
                lose()
                return
            }
            if (it.getValue() is Button) {
                if (player.location == it.getKey() || unitManager.isUnitOnPoint(it.getKey())) {
                    (it.getValue() as Button).trigger(this)
                } else {
                    (it.getValue() as Button).offTrigger(this)
                }
            }

        }
    }

    fun lose() {
        gameOver()
    }

    fun win() {
        gameOver()
    }

    fun gameOver() {
        gameTimer.cancel()
    }

    fun buildFrameImage(): BufferedImage {
        val image = BufferedImage(9 * 32, 9 * 32, BufferedImage.TYPE_INT_ARGB)
        val viewport = getViewport(player.location, map)
        val mapImage = map.getImage(viewport)
        val g = image.getGraphics()

        //Draw the map
        g.drawImage(mapImage, 0, 0, null)

        //Draw objects
        val objs = objectManager.objectsInViewport(viewport)
        for (obj in objs) {
            g.drawImage(obj.image, (obj.location.x - viewport.xStart) * 32, (obj.location.y - viewport.yStart) * 32, null)
        }

        for (unit in unitManager.unitsInViewPort(viewport)) {
            g.drawImage(unit.image, (unit.location.x - viewport.xStart) * 32, (unit.location.y - viewport.yStart) * 32, null)
        }

        //Draw the player
        val playerX = if (player.location.x <= 4 ) player.location.x else
            if (player.location.x in (map.x - 4)..(map.x)) 9 - (map.x - player.location.x) else 4
        val playerY = if (player.location.y <= 4 ) player.location.y else
            if (player.location.y in (map.y - 4)..(map.y)) 9 - (map.y - player.location.y) else 4
        g.drawImage(player.image, playerX * 32, playerY * 32, null)
        return image
    }

    public fun keyPressed(code: Int) {
        keyBindings.keyPressed(code)
    }

    public fun keyReleased(code: Int) {
        keyBindings.keyReleased(code)
    }

    fun teleport(interactor: UnitBase, direction: Direction, teleStart: Teleport) {
        val newTeleport = map.findNextTeleport(teleStart)
        if (newTeleport != null && !teleStart.arriving) {
            interactor.location = newTeleport.location.copy()
            teleStart.onExit(interactor, direction, this)
            newTeleport.onTeleportEnter(interactor, direction, this)
        }
    }

}