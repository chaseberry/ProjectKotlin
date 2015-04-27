package ChipsChallenge.Unit

import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.Point
import ChipsChallenge.Engine.Direction
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.Engine.Engine

/**
 * Created by chase on 3/6/15.
 */
abstract class DirectionalUnit(id: Int, location: Point, var direction: Direction) : UnitBase(id, location) {

    override fun onTick(engine: Engine) {
        super.onTick(engine)
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

    fun rotateDirection() {
        direction = when (direction) {
            Direction.UP -> Direction.RIGHT
            Direction.RIGHT -> Direction.DOWN
            Direction.DOWN -> Direction.LEFT
            Direction.LEFT -> Direction.UP
        }
    }

    override fun getSaveObject(): JSONObject {
        val saveObj = super.getSaveObject()
        saveObj.put("direction", direction)
        return saveObj
    }


    override fun moveUp(engine: Engine) {
        if (engine.movement.moveUp(this)) {
            location.y -= 1
            move(Direction.UP, engine)
        } else {
            changeDirection()
        }
    }

    override fun moveDown(engine: Engine) {
        if (engine.movement.moveDown(this)) {
            location.y += 1
            move(Direction.DOWN, engine)
        } else {
            changeDirection()
        }
    }

    override fun moveLeft(engine: Engine) {
        if (engine.movement.moveLeft(this)) {
            location.x -= 1
            move(Direction.LEFT, engine)
        } else {
            changeDirection()
        }
    }

    override fun moveRight(engine: Engine) {
        if (engine.movement.moveRight(this)) {
            location.x += 1
            move(Direction.RIGHT, engine)
        } else {
            changeDirection()
        }
    }

    abstract fun changeDirection()

}

