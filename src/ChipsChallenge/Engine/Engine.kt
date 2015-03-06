package ChipsChallenge.Engine

import ChipsChallenge.UI.Frame
import java.awt.image.BufferedImage
import java.util.Timer
import ChipsChallenge.Unit.Player
import java.util.TimerTask
import ChipsChallenge.UI.getViewport
import ChipsChallenge.Map.Tiles.Water
import ChipsChallenge.Map.Tiles.Finish
import ChipsChallenge.Map.Map
import java.util.ArrayList
import java.io.File
import java.io.FileReader
import java.io.BufferedReader
import ChipsChallenge.Map.mapFromFile
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.Map.Point
import java.net.URI

public enum class Direction {
    UP; DOWN; LEFT; RIGHT
}

fun engineFromFile(file: File): Engine? {
    try {
        val reader = BufferedReader(FileReader(file))
        val fileContents = reader.readLine()
        reader.close()
        val levelData = JSONObject(fileContents)
        val map = mapFromFile(levelData)
        val objs = levelData.getJSONArray("objects")
        val objects = ArrayList<ObjectBase>(objs.length())
        for (z in 0..objs.length() - 1) {
            val obj = objs.getJSONObject(z)
            objects.add(objectFromId(obj.getInt("id"), Point(obj.getJSONArray("location").getInt(0), obj.getJSONArray("location").getInt(1))))
        }
        if (map == null) {
            return null
        }
        return Engine(map, objects, ArrayList<UnitBase>())
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

    val movement = Movement(this);

    val unitManager = UnitManager(this);

    {
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
                println("Ticked")
                unitManager.onTick(engine)
                frame.image = buildFrameImage()
                checkCollisions()
            }
        }, gameTime, gameTime)
    }

    fun checkCollisions() {
        val tile = map.getTile(player.location)
        when (tile) {
            Water() -> if (!player.inventory.hasFlippers) lose()
            Finish() -> win()
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

}