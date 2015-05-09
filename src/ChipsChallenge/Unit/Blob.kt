package ChipsChallenge.Unit

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.Gravel
import ChipsChallenge.Map.Tiles.RecessedWall

val BLOB_TYPE_ID = 7
val BLOB_DEFAULT_MOVE_SPEED = DEFAULT_MOVE_SPEED * 2

class Blob(location: Point, moveSpeed: Int,
           uniqueId: Id) : UnitBase(BLOB_TYPE_ID, location, moveSpeed, uniqueId) {

    init {
        image = blobImage
    }

    constructor(location: Point) : this(location,
            BLOB_DEFAULT_MOVE_SPEED, Id(IdType.UNIT)) {
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