package ChipsChallenge.Unit

import ChipsChallenge.Engine.Direction
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.pinkBallImage
import ChipsChallenge.Map.Tiles.Floor

/**
 * Created by chase on 3/5/15.
 */
class PinkBall(var direction: Direction, location: Point) : UnitBase(1, location) {

    {
        image = pinkBallImage
    }

    val moveSpeed = 5
    var currentMove = 5

    override fun canMoveToTile(tile: Tile): Boolean {
        val canMove = tile is Floor
        if (!canMove) {
            swapDirection()
        }
        return canMove
    }

    override fun onTick(engine: Engine) {
        if (currentMove > 0) {
            currentMove -= 1
        }
        if (currentMove == 0) {
            when (direction) {
                Direction.UP -> {
                    moveUp(engine)
                    return;
                }
                Direction.DOWN -> {
                    moveDown(engine)
                    return
                }
                Direction.LEFT -> {
                    moveLeft(engine)
                    return
                }
                Direction.RIGHT -> {
                    moveRight(engine)
                    return
                }
            }
        }
    }


    fun move() {
        currentMove = moveSpeed
    }

    fun moveUp(engine: Engine) {
        if (engine.movement.moveUp(this)) {
            location.y -= 1
            move()
        }
    }

    fun moveDown(engine: Engine) {
        if (engine.movement.moveDown(this)) {
            location.y += 1
            move()
        }
    }

    fun moveLeft(engine: Engine) {
        if (engine.movement.moveLeft(this)) {
            location.x -= 1
            move()
        }
    }

    fun moveRight(engine: Engine) {
        if (engine.movement.moveRight(this)) {
            location.x += 1
            move()
        }
    }

    fun swapDirection() {
        direction = when (direction) {
            Direction.DOWN -> Direction.UP
            Direction.UP -> Direction.DOWN
            Direction.LEFT -> Direction.RIGHT
            Direction.RIGHT -> Direction.LEFT
        }
    }

}