package edu.csh.chase.ChipsChallenge.Unit

import edu.csh.chase.ChipsChallenge.Engine.*

val FIREBALL_DEFAULT_MOVE_SPEED = DEFAULT_MOVE_SPEED
val FIREBALL_TYPE_ID = 4

class Fireball(location: Point, direction: Direction,
               uniqueId: Id) : DirectionalUnit(FIREBALL_TYPE_ID, location, direction, FIREBALL_DEFAULT_MOVE_SPEED, uniqueId) {

    override fun canSurviveInWater(): Boolean {
        return false
    }

    override fun canSurviveInFire(): Boolean {
        return true
    }

    constructor(location: Point, direction: Direction) : this(location, direction, Id(IdType.UNIT)) {
    }

    init {
        image = fireballImage
    }

    override fun changeDirection(engine: Engine) {
        var newTile = getTileAheadOfCurrent(engine.map)
        if (newTile == null || !canMoveToTile(newTile, direction, engine)) {
            newTile = getTileRightOfCurrent(engine.map)
            if (newTile == null || !canMoveToTile(newTile, getRightOfCurrent(), engine)) {
                newTile = getTileLeftOfCurrent(engine.map)
                if (newTile == null || !canMoveToTile(newTile, getLeftOfCurrent(), engine)) {
                    newTile = getTileBehindCurrent(engine.map)
                    if (newTile != null && canMoveToTile(newTile, getBehindOfCurrent(), engine)) {
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

    override fun setImage() {
    }

}