package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.Id
import ChipsChallenge.Engine.Point
import ChipsChallenge.Map.Tile
import java.awt.image.BufferedImage

/**
 * Created by chase on 4/30/15.
 */
abstract class IceBase (image: BufferedImage, typeId: Int, location: Point,
                        uniqueId: Id) : Tile(image, typeId, location, uniqueId) {

    abstract fun getNewDirection(direction: Direction): Direction

}