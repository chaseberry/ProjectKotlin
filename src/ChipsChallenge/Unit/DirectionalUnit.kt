package ChipsChallenge.Unit

import ChipsChallenge.Engine.*
import ChipsChallenge.JSON.JSONObject

abstract class DirectionalUnit(typeId: Int, location: Point, var direction: Direction, moveSpeed: Int,
                               uniqueId: Id) : UnitBase(typeId, location, moveSpeed, uniqueId) {

    constructor(typeId: Int, location: Point, direction: Direction, moveSpeed: Int) :
    this(typeId, location, direction, moveSpeed, Id(IdType.UNIT)) {

    }

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

    abstract fun setImage()

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

