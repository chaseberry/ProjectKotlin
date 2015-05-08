package ChipsChallenge.Unit

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.Fire
import ChipsChallenge.Map.Tiles.Gravel

val BUG_DEFAULT_MOVE_SPEED = DEFAULT_MOVE_SPEED
val BUG_TYPE_ID = 1

class Bug(location: Point, direction: Direction, moveSpeed: Int,
          uniqueId: Id) : DirectionalUnit(BUG_TYPE_ID, location, direction, moveSpeed, uniqueId) {

    override fun canSurviveInWater(): Boolean {
        return false
    }

    override fun canSurviveInFire(): Boolean {
        return true
    }

    constructor(location: Point, direction: Direction) : this(location, direction,
            BUG_DEFAULT_MOVE_SPEED, Id(IdType.UNIT)) {
    }

    init {
        setImage()
    }

    override fun onTick(engine: Engine) {
        if (currentMove == 1) {
            var newTile = getTileLeftOfCurrent(engine.map)
            if (newTile != null && canMoveToTile(newTile!!, getLeftOfCurrent(), engine)) {
                direction = getLeftOfCurrent()
            } else {
                newTile = getTileAheadOfCurrent(engine.map)
                if (newTile == null || !canMoveToTile(newTile!!, direction, engine)) {
                    newTile = getTileRightOfCurrent(engine.map)
                    if (newTile != null && canMoveToTile(newTile!!, getRightOfCurrent(), engine)) {
                        direction = getRightOfCurrent()
                    } else {
                        newTile = getTileBehindCurrent(engine.map)
                        if (newTile != null && canMoveToTile(newTile!!, getBehindOfCurrent(), engine)) {
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
        return super.canMoveToTile(tile, direction) && tile !is Fire && tile !is Gravel
    }

    override fun setImage() {
        image = when (direction) {
            Direction.UP -> bugUpImage
            Direction.LEFT -> bugLeftImage
            Direction.RIGHT -> bugRightImage
            Direction.DOWN -> bugDownImage
        }
    }

}