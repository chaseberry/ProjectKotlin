package ChipsChallenge.Unit

import ChipsChallenge.Engine.*

/**
 * Created by chase on 5/6/15.
 */
val TANK_DEFAULT_MOVE_SPEED = DEFAULT_MOVE_SPEED
val TANK_TYPE_ID = 2

public enum class TANKSTATUS {
    STOP
    TURN
    MOVE
}

class Tank(location: Point, direction: Direction, moveSpeed: Int,
           uniqueId: Id) : DirectionalUnit(TANK_TYPE_ID, location, direction, moveSpeed, uniqueId) {

    constructor(location: Point, direction: Direction) : this(location, direction,
            TANK_DEFAULT_MOVE_SPEED, Id(IdType.UNIT)) {
    }


    init {
        setImage()
    }

    var currentStatus = TANKSTATUS.STOP

    fun startMove() {
        currentStatus = TANKSTATUS.TURN
    }

    override fun setImage() {
        image = when (direction) {
            Direction.UP -> tankUpImage
            Direction.LEFT -> tankLeftImage
            Direction.DOWN -> tankDownImage
            Direction.RIGHT -> tankRightImage
        }
    }

    override fun onTick(engine: Engine) {
        if (currentStatus == TANKSTATUS.TURN && currentMove == 1) {
            changeDirection()
            setImage()
            currentMove = moveSpeed
            currentStatus = TANKSTATUS.MOVE
            return
        }
        super.onTick(engine)

        if (currentMove == 0) {
            currentMove = moveSpeed
        }
    }

    override fun changeDirection() {
        if (currentStatus != TANKSTATUS.TURN) {
            currentStatus = TANKSTATUS.STOP
        }
        direction = when (direction) {
            Direction.UP -> Direction.DOWN
            Direction.LEFT -> Direction.RIGHT
            Direction.DOWN -> Direction.UP
            Direction.RIGHT -> Direction.LEFT
        }
    }

    override fun moveUp(engine: Engine) {
        if (engine.movement.moveUp(this) && currentStatus == TANKSTATUS.MOVE) {
            move(Direction.UP, engine, location.copy(y = location.y - 1))
        } else {
            currentStatus = TANKSTATUS.STOP
        }
    }

    override fun moveDown(engine: Engine) {
        if (engine.movement.moveDown(this) && currentStatus == TANKSTATUS.MOVE) {
            move(Direction.DOWN, engine, location.copy(y = location.y + 1))
        } else {
            currentStatus = TANKSTATUS.STOP
        }
    }

    override fun moveLeft(engine: Engine) {
        if (engine.movement.moveLeft(this) && currentStatus == TANKSTATUS.MOVE) {
            move(Direction.LEFT, engine, location.copy(x = location.x - 1))
        } else {
            currentStatus = TANKSTATUS.STOP
        }
    }

    override fun moveRight(engine: Engine) {
        if (engine.movement.moveRight(this) && currentStatus == TANKSTATUS.MOVE) {
            move(Direction.RIGHT, engine, location.copy(x = location.x + 1))
        } else {
            currentStatus = TANKSTATUS.STOP
        }
    }

    override fun canSurviveInWater(): Boolean {
        return false
    }

    override fun canSurviveInFire(): Boolean {
        return false
    }

}