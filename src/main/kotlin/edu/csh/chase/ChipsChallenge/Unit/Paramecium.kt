package ChipsChallenge.Unit

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Engine.DEFAULT_MOVE_SPEED
import edu.csh.chase.ChipsChallenge.Engine.Direction
import edu.csh.chase.ChipsChallenge.Engine.Engine
import edu.csh.chase.ChipsChallenge.Engine.Point

val PARAMECIUM_DEFAULT_MOVE_SPEED = DEFAULT_MOVE_SPEED
val PARAMECIUM_TYPE_ID = 6

class Paramecium(location: Point, direction: Direction,
                 uniqueId: Id) : DirectionalUnit(PARAMECIUM_TYPE_ID, location, direction, PARAMECIUM_DEFAULT_MOVE_SPEED, uniqueId) {

    override fun canSurviveInWater(): Boolean {
        return false
    }

    override fun canSurviveInFire(): Boolean {
        return false
    }

    constructor(location: Point, direction: Direction) : this(location, direction, Id(IdType.UNIT)) {
    }

    init {
        setImage()
    }

    override fun onTick(engine: Engine) {
        if (currentMove == 1) {
            var newTile = getTileRightOfCurrent(engine.map)
            if (newTile != null && canMoveToTile(newTile, getRightOfCurrent(), engine)) {
                direction = getRightOfCurrent()
            } else {
                newTile = getTileAheadOfCurrent(engine.map)
                if (newTile == null || !canMoveToTile(newTile, direction, engine)) {
                    newTile = getTileLeftOfCurrent(engine.map)
                    if (newTile != null && canMoveToTile(newTile, getLeftOfCurrent(), engine)) {
                        direction = getLeftOfCurrent()
                    } else {
                        newTile = getTileBehindCurrent(engine.map)
                        if (newTile != null && canMoveToTile(newTile, getBehindOfCurrent(), engine)) {
                            direction = getBehindOfCurrent()
                        }
                    }
                }
            }
            setImage()
        }
        super.onTick(engine)
    }

    override fun changeDirection(engine: Engine) {
    }

    override fun canMoveToTile(tile: Tile, direction: Direction): Boolean {
        return super.canMoveToTile(tile, direction)
    }

    override fun setImage() {
        image = when (direction) {
            Direction.UP -> parameciumUpImage
            Direction.LEFT -> parameciumLeftImage
            Direction.RIGHT -> parameciumLeftImage
            Direction.DOWN -> parameciumUpImage
        }
    }

}