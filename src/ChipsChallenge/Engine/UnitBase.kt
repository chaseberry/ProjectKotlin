package ChipsChallenge.Engine

import java.awt.image.BufferedImage
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Point
import java.util.HashMap

/**
 * Created by chase on 2/25/15.
 */

abstract class UnitBase(var location: Point) : EngineObjectBase {

    protected val imageSet: HashMap<String, BufferedImage> = HashMap()

    var image: BufferedImage? = null

    abstract fun canMoveToTile(tile: Tile): Boolean

}