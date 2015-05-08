package ChipsChallenge.Unit

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.Gravel

val GLIDER_DEFAULT_MOVE_SPEED = DEFAULT_MOVE_SPEED
val GLIDER_TYPE_ID = 3

class Glider(location: Point, direction: Direction, moveSpeed: Int,
             uniqueId: Id) : DirectionalUnit(GLIDER_TYPE_ID, location, direction, moveSpeed, uniqueId) {

    override fun canSurviveInWater(): Boolean {
        return true
    }

    override fun canSurviveInFire(): Boolean {
        return false
    }

    constructor(location: Point, direction: Direction) : this(location, direction,
            GLIDER_DEFAULT_MOVE_SPEED, Id(IdType.UNIT)) {
    }

    init {
        image = gliderUpImage
    }

    override fun changeDirection(engine: Engine) {
        var newTile = getTileAheadOfCurrent(engine.map)
        if (newTile == null || !canMoveToTile(newTile!!, direction, engine)) {
            newTile = getTileLeftOfCurrent(engine.map)
            if (newTile == null || !canMoveToTile(newTile!!, getLeftOfCurrent(), engine)) {
                newTile = getTileRightOfCurrent(engine.map)
                if (newTile == null || !canMoveToTile(newTile!!, getRightOfCurrent(), engine)) {
                    newTile = getTileBehindCurrent(engine.map)
                    if (newTile != null && canMoveToTile(newTile!!, getBehindOfCurrent(), engine)) {
                        direction = getBehindOfCurrent()
                    }
                } else {
                    direction = getRightOfCurrent()
                }
            } else {
                direction = getLeftOfCurrent()
            }
        }
        setImage()
    }

    override fun canMoveToTile(tile: Tile, direction: Direction): Boolean {
        return super.canMoveToTile(tile, direction) && tile !is Gravel
    }

    override fun onTick(engine: Engine) {
        //Calculate the new direction
        if (currentMove == 1) {

        }
        super.onTick(engine)
    }

    override fun setImage() {
        image = when (direction) {
            Direction.UP -> gliderUpImage
            Direction.LEFT -> gliderLeftImage
            Direction.RIGHT -> gliderRightImage
            Direction.DOWN -> gliderDownImage
        }
    }
}