package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.RECESSED_WALL_TYPE_ID
import ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player
import java.awt.image.BufferedImage

class RecessedWall(location: Point, uniqueId: Id) : Tile(recessedWallImage, RECESSED_WALL_TYPE_ID, location, uniqueId) {

    var open = true

    override var image: BufferedImage? = null
        get():BufferedImage? {
            return if (open) {
                recessedWallImage
            } else {
                wallImage
            }
        }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {

    }

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        if (interactor is Player) {
            open = false
        }
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
    }

}