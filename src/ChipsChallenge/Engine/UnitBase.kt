package ChipsChallenge.Engine

import java.awt.image.BufferedImage
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Point
import java.util.HashMap
import ChipsChallenge.Unit.PinkBall

/**
 * Created by chase on 2/25/15.
 */

fun unitFromId(id: Int, location: Point, direction: Direction): UnitBase? {
    when (id) {
        0 -> PinkBall(direction, location)
    }
    return null
}

abstract class UnitBase(val id: Int, var location: Point) : EngineObjectBase {

    protected val imageSet: HashMap<String, BufferedImage> = HashMap()

    var image: BufferedImage? = null

    abstract fun canMoveToTile(tile: Tile): Boolean

}