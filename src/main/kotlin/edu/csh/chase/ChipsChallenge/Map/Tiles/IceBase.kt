package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player
import java.awt.image.BufferedImage

/**
 * Created by chase on 4/30/15.
 */
abstract class IceBase (image: BufferedImage, typeId: Int, location: Point,
                        uniqueId: Id) : Tile(image, typeId, location, uniqueId) {

    abstract fun getNewDirection(direction: Direction): Direction

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        if (interactor is Player && ( (this is ForceFloor) && interactor.inventory.hasSuctionBoots
                || ((this is Ice || this is IceCorner) && interactor.inventory.hasIceSkates))) {
            return
        }
        interactor.forcedDirection = getNewDirection(direction)
    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
        interactor.forcedDirection = null
    }


}