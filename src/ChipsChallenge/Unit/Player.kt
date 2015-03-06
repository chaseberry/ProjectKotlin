package ChipsChallenge.Unit

import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.loadImage
import ChipsChallenge.Map.Tiles.Wall

class Player(location: Point) : UnitBase(-1, location) {

    {
        imageSet.put("up", loadImage("chip-north.gif"))
        imageSet.put("down", loadImage("chip-south.gif"))
        imageSet.put("left", loadImage("chip-west.gif"))
        imageSet.put("right", loadImage("chip-east.gif"))
        image = imageSet.get("down")
    }

    val inventory = PlayerInventory()

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
        if (engine.movement.moveUp(this)) {
            location.y -= 1
            move()
        }
    }

    fun moveDown(engine: Engine) {
        image = imageSet.get("down")
        if (engine.movement.moveDown(this)) {
            location.y += 1
            move()
        }
    }

    fun moveLeft(engine: Engine) {
        image = imageSet.get("left")
        if (engine.movement.moveLeft(this)) {
            location.x -= 1
            move()
        }
    }

    fun moveRight(engine: Engine) {
        image = imageSet.get("right")
        if (engine.movement.moveRight(this)) {
            location.x += 1
            move()
        }
    }

    override fun canMoveToTile(tile: Tile): Boolean {
        return tile != Wall()
    }

}