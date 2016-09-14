package edu.csh.chase.ChipsChallenge.Map.Tiles

import edu.csh.chase.ChipsChallenge.Map.Tiles.ForceFloor
import edu.csh.chase.ChipsChallenge.Map.Tiles.Ice
import edu.csh.chase.ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player
import java.awt.image.BufferedImage

abstract class IceBase (image: BufferedImage, typeId: Int, location: Point,
                        uniqueId: Id) : Tile(image, typeId, location, uniqueId) {

    abstract fun getNewDirection(direction: Direction): Direction

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        super.onEnter(interactor, direction, engine)
        if (interactor is Player && ( (this is ForceFloor) && interactor.inventory.hasSuctionBoots
                || ((this is Ice || this is IceCorner) && interactor.inventory.hasIceSkates))) {
            return
        }
        interactor.forcedDirection = getNewDirection(direction)
    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
        super.onExit(interactor, direction, engine)
        interactor.forcedDirection = null
    }


}