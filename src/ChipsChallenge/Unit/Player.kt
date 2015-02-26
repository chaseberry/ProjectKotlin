package ChipsChallenge.Unit

import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.Floor
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.loadImage

class Player(location: Point) : UnitBase(location) {

    {
        imageSet.put("up", loadImage("chip-north.gif"))
        imageSet.put("down", loadImage("chip-south.gif"))
        imageSet.put("left", loadImage("chip-west.gif"))
        imageSet.put("right", loadImage("chip-east.gif"))
        image = imageSet.get("down")
    }

    val playerMoveSpeed = 5
    var currentMove = 0

    override fun onTick(engine: Engine) {
        if (currentMove > 0) {
            currentMove -= 1
        }
        if (currentMove == 0) {
            when (true) {
                engine.keyBindings.up -> {
                    moveUp(engine)
                    return;
                }
                engine.keyBindings.down -> {
                    moveDown(engine)
                    return
                }
                engine.keyBindings.left -> {
                    moveLeft(engine)
                    return
                }
                engine.keyBindings.right -> {
                    moveRight(engine)
                    return
                }
            }
        }
    }

    fun move() {
        currentMove = playerMoveSpeed
    }

    fun moveUp(engine: Engine) {
        image = imageSet.get("up")
        val tile = engine.map.getUp(location)
        if (tile == null || !canMoveToTile(tile)) {
            return
        }
        location.y -= 1
        move()
    }

    fun moveDown(engine: Engine) {
        image = imageSet.get("down")
        val tile = engine.map.getDown(location)
        if (tile == null || !canMoveToTile(tile)) {
            return
        }
        location.y += 1
        move()
    }

    fun moveLeft(engine: Engine) {
        image = imageSet.get("left")
        val tile = engine.map.getLeft(location)
        if (tile == null || !canMoveToTile(tile)) {
            return
        }
        location.x -= 1
        move()
    }

    fun moveRight(engine: Engine) {
        val tile = engine.map.getRight(location)
        image = imageSet.get("right")
        if (tile == null || !canMoveToTile(tile)) {
            return
        }
        location.x += 1
        move()
    }

    override fun canMoveToTile(tile: Tile): Boolean {
        return tile == Floor()
    }

}