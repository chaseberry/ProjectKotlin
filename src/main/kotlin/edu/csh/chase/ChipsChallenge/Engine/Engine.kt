package edu.csh.chase.ChipsChallenge.Engine

import edu.csh.chase.ChipsChallenge.Map.Tiles.Floor
import edu.csh.chase.ChipsChallenge.Map.Tiles.Teleport
import edu.csh.chase.ChipsChallenge.Map.Tiles.Water
import edu.csh.chase.ChipsChallenge.Object.Button
import edu.csh.chase.ChipsChallenge.Object.Dirt
import edu.csh.chase.ChipsChallenge.Map.Map
import edu.csh.chase.ChipsChallenge.Object.Block
import edu.csh.chase.ChipsChallenge.UI.Frame
import edu.csh.chase.ChipsChallenge.UI.getViewport
import edu.csh.chase.ChipsChallenge.Unit.Player
import java.awt.event.KeyEvent
import java.awt.image.BufferedImage
import java.util.*
import kotlin.properties.Delegates

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    fun flip(): Direction {
        return when (this) {
            Direction.UP -> Direction.DOWN
            Direction.DOWN -> Direction.UP
            Direction.LEFT -> Direction.RIGHT
            Direction.RIGHT -> Direction.LEFT
        }
    }

    fun rotateRight(): Direction {
        return when (this) {
            Direction.UP -> Direction.RIGHT
            Direction.RIGHT -> Direction.DOWN
            Direction.DOWN -> Direction.LEFT
            Direction.LEFT -> Direction.UP
        }
    }
}

class Engine(val level: Level) {

    var result: EngineResult by Delegates.vetoable(EngineResult.InProgress) { desc, old, new ->
        old == EngineResult.InProgress
    }

    val map: Map//shortcut to level.map

    val gameTime: Long = 30

    val frame = Frame(this)

    val objectManager = ObjectManager(this)

    val gameTimer = Timer()

    //val player = Player(level.playerStart, KeyBindings())

    val players = listOf(Player(level.playerStart, KeyBindings()), Player(level.playerStart, KeyBindings(
            keyCodeDown = KeyEvent.VK_S,
            keyCodeLeft = KeyEvent.VK_A,
            keyCodeRight = KeyEvent.VK_D,
            keyCodeUp = KeyEvent.VK_W
    )))

    //Done so this can be passed around in the onTick method
    val engine: Engine
        get() {
            return this
        }

    val movement = Movement(this)

    val unitManager = UnitManager(this);

    init {
        map = level.map
        for (obj in level.objects) {
            objectManager.objects.put(obj.location, obj)
        }

        unitManager.addAll(level.units)

    }

    fun start() {
        frame.isVisible = true
        frame.image = buildFrameImage()

        gameTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                unitManager.forEach { it.onTick(engine) }
                map.onTick(engine)
                players.forEach { it.onTick(engine) }

                objectManager.onTick(engine)
                objectManager.applyChanges()

                checkCollisions()

                //
                objectManager.applyChanges()
                //

                frame.image = buildFrameImage()
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
        players.forEach {
            if (unitManager.isUnitOnPoint(it.location)) {
                lose(it)
            }
        }

        objectManager.objects.forEach {
            val location = it.key
            val obj = it.value
            if (obj is Block && engine.map.getTile(location) is Water) {
                objectManager.objects.put(location, Dirt(location, obj.uniqueId))
                val waterTile = engine.map.getTile(location)!!
                engine.map.setTile(location, Floor(location, waterTile.uniqueId))
            }
        }
    }

    fun lose(player: Player) {
        player.dead = true
        result = EngineResult.Defeat
        gameOver()
    }

    fun win() {
        result = EngineResult.Victory
        gameOver()
    }

    fun gameOver() {
        gameTimer.cancel()
    }

    fun aborted() {
        result = EngineResult.Aborted
        gameOver()
    }

    fun buildFrameImage(): BufferedImage {
        val image = BufferedImage(9 * 32, 9 * 32, BufferedImage.TYPE_INT_ARGB)
        val viewport = getViewport(players[0].location, map)
        val mapImage = map.getImage(viewport)
        val g = image.graphics

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
        players.forEach { player ->
            val playerX = if (player.location.x <= 4 ) player.location.x else
                if (player.location.x in (map.x - 4)..(map.x)) 9 - (map.x - player.location.x) else 4
            val playerY = if (player.location.y <= 4 ) player.location.y else
                if (player.location.y in (map.y - 4)..(map.y)) 9 - (map.y - player.location.y) else 4
            g.drawImage(player.image, playerX * 32, playerY * 32, null)
        }

        return image
    }

    fun keyPressed(code: Int) {
        players.forEach {
            it.keyBindings.keyPressed(code)
        }
    }

    fun keyReleased(code: Int) {
        players.forEach {
            it.keyBindings.keyReleased(code)
        }
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