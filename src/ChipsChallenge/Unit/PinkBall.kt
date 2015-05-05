package ChipsChallenge.Unit

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.Gravel

val PINK_BALL_DEFAULT_MOVE_SPEED = DEFAULT_MOVE_SPEED
val PINK_BALL_TYPE_ID = 0

class PinkBall(location: Point, direction: Direction, moveSpeed: Int,
               uniqueId: Id) : DirectionalUnit(PINK_BALL_TYPE_ID, location, direction, moveSpeed, uniqueId) {


    override fun setImage() {
        image = pinkBallImage
    }

    constructor(location: Point, direction: Direction) : this(location, direction,
            PINK_BALL_DEFAULT_MOVE_SPEED, Id(IdType.UNIT)) {
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

    override fun canMoveToTile(tile: Tile, direction: Direction): Boolean {
        return super.canMoveToTile(tile, direction) && tile !is Gravel
    }

    override fun changeDirection() {
        direction = when (direction) {
            Direction.DOWN -> Direction.UP
            Direction.UP -> Direction.DOWN
            Direction.LEFT -> Direction.RIGHT
            Direction.RIGHT -> Direction.LEFT
        }
    }

}