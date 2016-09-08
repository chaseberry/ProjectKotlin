package edu.csh.chase.ChipsChallenge.Unit

import ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.DirectionalUnit

val GLIDER_DEFAULT_MOVE_SPEED = DEFAULT_MOVE_SPEED
val GLIDER_TYPE_ID = 3

class Glider(location: Point, direction: Direction,
             uniqueId: Id) : DirectionalUnit(GLIDER_TYPE_ID, location, direction, GLIDER_DEFAULT_MOVE_SPEED, uniqueId) {

    override fun canSurviveInWater(): Boolean {
        return true
    }

    override fun canSurviveInFire(): Boolean {
        return false
    }

    constructor(location: Point, direction: Direction) : this(location, direction, Id(IdType.UNIT)) {
    }

    init {
        setImage()
    }

    override fun changeDirection(engine: Engine) {
        var newTile = getTileAheadOfCurrent(engine.map)
        if (newTile == null || !canMoveToTile(newTile, direction, engine)) {
            newTile = getTileLeftOfCurrent(engine.map)
            if (newTile == null || !canMoveToTile(newTile, getLeftOfCurrent(), engine)) {
                newTile = getTileRightOfCurrent(engine.map)
                if (newTile == null || !canMoveToTile(newTile, getRightOfCurrent(), engine)) {
                    newTile = getTileBehindCurrent(engine.map)
                    if (newTile != null && canMoveToTile(newTile, getBehindOfCurrent(), engine)) {
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

    override fun setImage() {
        image = when (direction) {
            Direction.UP -> gliderUpImage
            Direction.LEFT -> gliderLeftImage
            Direction.RIGHT -> gliderRightImage
            Direction.DOWN -> gliderDownImage
        }
    }

}