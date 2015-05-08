package ChipsChallenge.Unit

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.Gravel

val FIREBALL_DEFAULT_MOVE_SPEED = DEFAULT_MOVE_SPEED
val FIREBALL_TYPE_ID = 4

class Fireball(location: Point, direction: Direction, moveSpeed: Int,
               uniqueId: Id) : DirectionalUnit(FIREBALL_TYPE_ID, location, direction, moveSpeed, uniqueId) {

    override fun canSurviveInWater(): Boolean {
        return false
    }

    override fun canSurviveInFire(): Boolean {
        return true
    }

    constructor(location: Point, direction: Direction) : this(location, direction,
            FIREBALL_DEFAULT_MOVE_SPEED, Id(IdType.UNIT)) {
    }

    init {
        image = fireballImage
    }

    override fun changeDirection(engine: Engine) {
        var newTile = getTileAheadOfCurrent(engine.map)
        if (newTile == null || !canMoveToTile(newTile!!, direction, engine)) {
            newTile = getTileRightOfCurrent(engine.map)
            if (newTile == null || !canMoveToTile(newTile!!, getRightOfCurrent(), engine)) {
                newTile = getTileLeftOfCurrent(engine.map)
                if (newTile == null || !canMoveToTile(newTile!!, getLeftOfCurrent(), engine)) {
                    newTile = getTileBehindCurrent(engine.map)
                    if (newTile != null && canMoveToTile(newTile!!, getBehindOfCurrent(), engine)) {
                        direction = getBehindOfCurrent()
                    }
                } else {
                    direction = getLeftOfCurrent()
                }
            } else {
                direction = getRightOfCurrent()
            }
        }
    }

    override fun canMoveToTile(tile: Tile, direction: Direction): Boolean {
        return super.canMoveToTile(tile, direction) && tile !is Gravel
    }

    override fun setImage() {
    }
}