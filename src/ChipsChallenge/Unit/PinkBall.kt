package ChipsChallenge.Unit

import ChipsChallenge.Engine.*

val PINK_BALL_DEFAULT_MOVE_SPEED = DEFAULT_MOVE_SPEED
val PINK_BALL_TYPE_ID = 0

class PinkBall(location: Point, direction: Direction,
               uniqueId: Id) : DirectionalUnit(PINK_BALL_TYPE_ID, location, direction, PINK_BALL_DEFAULT_MOVE_SPEED, uniqueId) {


    override fun setImage() {
        image = pinkBallImage
    }

    constructor(location: Point, direction: Direction) : this(location, direction, Id(IdType.UNIT)) {
    }

    init {
        image = pinkBallImage
    }

    override fun canSurviveInWater(): Boolean {
        return false
    }

    override fun canSurviveInFire(): Boolean {
        return false
    }

    override fun changeDirection(engine: Engine) {
        direction = when (direction) {
            Direction.DOWN -> Direction.UP
            Direction.UP -> Direction.DOWN
            Direction.LEFT -> Direction.RIGHT
            Direction.RIGHT -> Direction.LEFT
        }
    }

}