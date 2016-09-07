package edu.csh.chase.ChipsChallenge.Unit

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.Fire
import ChipsChallenge.Unit.DirectionalUnit
import edu.csh.chase.ChipsChallenge.Engine.DEFAULT_MOVE_SPEED
import edu.csh.chase.ChipsChallenge.Engine.Direction
import edu.csh.chase.ChipsChallenge.Engine.Engine
import edu.csh.chase.ChipsChallenge.Engine.Point
import java.util.ArrayList

val WALKER_DEFAULT_MOVE_SPEED = DEFAULT_MOVE_SPEED
val WALKER_TYPE_ID = 5

class Walker(location: Point, direction: Direction,
             uniqueId: Id) : DirectionalUnit(WALKER_TYPE_ID, location, direction, WALKER_DEFAULT_MOVE_SPEED, uniqueId) {

    override fun canSurviveInWater(): Boolean {
        return false
    }

    override fun canSurviveInFire(): Boolean {
        return true
    }

    constructor(location: Point, direction: Direction) : this(location, direction, Id(IdType.UNIT)) {
    }

    init {
        setImage()
    }

    override fun changeDirection(engine: Engine) {
        val list = getLegalDirections(engine)
        if (list.size == 0) {
            return
        }
        direction = list[(Math.random() * list.size).toInt()]
        setImage()
    }

    override fun setImage() {
        image = when (direction) {
            Direction.UP -> walkerUpImage
            Direction.LEFT -> walkerLeftImage
            Direction.RIGHT -> walkerLeftImage
            Direction.DOWN -> walkerUpImage
        }
    }

    fun getLegalDirections(engine: Engine): ArrayList<Direction> {
        val left = getTileLeftOfCurrent(engine.map)
        val right = getTileRightOfCurrent(engine.map)
        val behind = getTileBehindCurrent(engine.map)
        val list = ArrayList<Direction>()
        if (left != null && canMoveToTile(left, getLeftOfCurrent(), engine)) {
            list.add(getLeftOfCurrent())
        }
        if (right != null && canMoveToTile(right, getRightOfCurrent(), engine)) {
            list.add(getRightOfCurrent())
        }
        if (behind != null && canMoveToTile(behind, getBehindOfCurrent(), engine)) {
            list.add(getBehindOfCurrent())
        }
        return list
    }

    override fun canMoveToTile(tile: Tile, direction: Direction): Boolean {
        return super.canMoveToTile(tile, direction) && tile !is Fire
    }

}