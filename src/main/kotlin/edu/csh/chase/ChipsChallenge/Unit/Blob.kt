package edu.csh.chase.ChipsChallenge.Unit

import edu.csh.chase.ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Map.Tiles.Gravel
import edu.csh.chase.ChipsChallenge.Map.Tiles.RecessedWall
import edu.csh.chase.ChipsChallenge.Engine.*

val BLOB_TYPE_ID = 7
val BLOB_DEFAULT_MOVE_SPEED = DEFAULT_MOVE_SPEED * 2

class Blob(location: Point, uniqueId: Id) : UnitBase(BLOB_TYPE_ID, location, BLOB_DEFAULT_MOVE_SPEED, uniqueId) {

    init {
        image = blobImage
    }

    constructor(location: Point) : this(location, Id(IdType.UNIT)) {
    }

    override fun onTick(engine: Engine) {
        super.onTick(engine)
        if (currentMove == 0) {
            when ((Math.random() * 4).toInt()) {
                0 -> {
                    moveUp(engine)
                    return;
                }
                1 -> {
                    moveDown(engine)
                    return
                }
                2 -> {
                    moveLeft(engine)
                    return
                }
                3 -> {
                    moveRight(engine)
                    return
                }
            }
        }
    }

    override fun canSurviveInWater(): Boolean {
        return false
    }

    override fun canSurviveInFire(): Boolean {
        return false
    }

    override fun canMoveToTile(tile: Tile, direction: Direction): Boolean {
        return super.canMoveToTile(tile, direction) && tile !is Gravel && tile !is RecessedWall
    }

}