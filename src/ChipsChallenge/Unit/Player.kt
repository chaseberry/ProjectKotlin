package ChipsChallenge.Unit

import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.Floor

class Player(x: Int, y: Int) : UnitBase(x, y, "chip-south.gif") {


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
        val tile = engine.map.getUp(x, y)
        if (tile == null || !canMoveToTile(tile)) {
            return
        }
        y -= 1
        move()
    }

    fun moveDown(engine: Engine) {
        val tile = engine.map.getDown(x, y)
        if (tile == null || !canMoveToTile(tile)) {
            return
        }
        y += 1
        move()
    }

    fun moveLeft(engine: Engine) {
        val tile = engine.map.getLeft(x, y)
        if (tile == null || !canMoveToTile(tile)) {
            return
        }
        x -= 1
        move()
    }

    fun moveRight(engine: Engine) {
        val tile = engine.map.getRight(x, y)
        if (tile == null || !canMoveToTile(tile)) {
            return
        }
        x += 1
        move()
    }

    override fun canMoveToTile(tile: Tile): Boolean {
        return tile == Floor()
    }

}